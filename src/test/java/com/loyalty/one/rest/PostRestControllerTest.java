package com.loyalty.one.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.one.model.request.PostRequest;
import com.loyalty.one.model.response.PostResponse;
import com.loyalty.one.service.PostService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PostRestController postRestController;

    @Mock
    private PostService postService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(postRestController)
                .build();
    }

    @Test
    public void createPost() throws Exception {
        PostRequest postRequest = new PostRequest();
        postRequest.setCity("Toronto");
        postRequest.setContent("Hello");
        postRequest.setUsername("Suresh");

        PostResponse postResponse = new PostResponse();
        postResponse.setCity("Toronto");
        postResponse.setContent("Hello");
        postResponse.setUsername("Suresh");
        postResponse.setLatitude(78.02f);
        postResponse.setLongitude(-0.13f);
        postResponse.setTemperature(20.5f);
        postResponse.setPostId(1);


        when(postService.addPostData(any())).thenReturn(postResponse);

        MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/post")
                .content(new ObjectMapper().writeValueAsString(postRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        PostResponse actualProductResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), PostResponse.class);
        assertEquals(postResponse.getPostId(), actualProductResponse.getPostId());
        assertEquals(postResponse.getCity(), actualProductResponse.getCity());
        assertEquals(postResponse.getContent(), actualProductResponse.getContent());
        assertEquals(postResponse.getLatitude(), actualProductResponse.getLatitude());
        assertEquals(postResponse.getLongitude(), actualProductResponse.getLongitude());
        assertEquals(postResponse.getTemperature(), actualProductResponse.getTemperature());
        assertEquals(postResponse.getUsername(), actualProductResponse.getUsername());
    }

    @Test
    public void createPostWhenCityIsEmpty() throws Exception {
        expectedException.expect(NestedServletException.class);
        expectedException.expectMessage("Please enter username, content and city.");

        PostRequest postRequest = new PostRequest();
//        postRequest.setCity("Toronto");
        postRequest.setContent("Hello");
        postRequest.setUsername("Suresh");

        PostResponse postResponse = new PostResponse();
        postResponse.setCity("Toronto");
        postResponse.setContent("Hello");
        postResponse.setUsername("Suresh");
        postResponse.setLatitude(78.02f);
        postResponse.setLongitude(-0.13f);
        postResponse.setTemperature(20.5f);
        postResponse.setPostId(1);


        when(postService.addPostData(any())).thenReturn(postResponse);

        this.mockMvc.perform(post("/api/v1/post")
                .content(new ObjectMapper().writeValueAsString(postRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void getAllPosts() throws Exception {

        PostResponse postResponse = new PostResponse();
        postResponse.setCity("Toronto");
        postResponse.setContent("Hello");
        postResponse.setUsername("Suresh");
        postResponse.setLatitude(78.02f);
        postResponse.setLongitude(-0.13f);
        postResponse.setTemperature(20.5f);
        postResponse.setPostId(1);


        when(postService.getAllPosts()).thenReturn(Collections.singletonList(postResponse));

        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/post")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<PostResponse> actualProductResponseList = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<PostResponse>>(){});
        PostResponse actualProductResponse = actualProductResponseList.get(0);
        assertEquals(postResponse.getPostId(), actualProductResponse.getPostId());
        assertEquals(postResponse.getCity(), actualProductResponse.getCity());
        assertEquals(postResponse.getContent(), actualProductResponse.getContent());
        assertEquals(postResponse.getLatitude(), actualProductResponse.getLatitude());
        assertEquals(postResponse.getLongitude(), actualProductResponse.getLongitude());
        assertEquals(postResponse.getTemperature(), actualProductResponse.getTemperature());
        assertEquals(postResponse.getUsername(), actualProductResponse.getUsername());
    }

}
