package com.loyalty.one.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.one.model.dao.PostsDao;
import com.loyalty.one.model.request.PostRequest;
import com.loyalty.one.model.response.PostResponse;
import com.loyalty.one.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private WeatherService weatherService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void persistPost() {

        PostRequest postRequest = new PostRequest();
        postRequest.setCity("Toronto");
        postRequest.setContent("Hello");
        postRequest.setUsername("Suresh");


        PostsDao postsDao = new PostsDao();
        postsDao.setCity("Toronto");
        postsDao.setContent("Hello");
        postsDao.setUsername("Suresh");
        postsDao.setLatitude(78.02f);
        postsDao.setLongitude(-0.13f);
        postsDao.setTemperature(20.5f);
        postsDao.setPostId(1);

        when(postRepository.save(postsDao)).thenReturn(postsDao);
        doNothing().when(weatherService).addWeatherInformation(any(), anyString());

        PostResponse actualProductResponse = postService.addPostData(postRequest);
        assertEquals(postsDao.getCity(), actualProductResponse.getCity());
        assertEquals(postsDao.getContent(), actualProductResponse.getContent());
        assertEquals(postsDao.getUsername(), actualProductResponse.getUsername());
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

        List<PostResponse> actualProductResponseList = postService.getAllPosts();
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
