package com.loyalty.one.service;

import com.loyalty.one.constants.ErrorResponseCodes;
import com.loyalty.one.exceptions.ServiceException;
import com.loyalty.one.model.dao.PostsDao;
import com.loyalty.one.model.dao.ReplyPostDao;
import com.loyalty.one.model.request.PostRequest;
import com.loyalty.one.model.request.ReplyRequest;
import com.loyalty.one.model.response.PostResponse;
import com.loyalty.one.model.response.ReplyResponse;
import com.loyalty.one.repository.PostRepository;
import com.loyalty.one.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Suresh Chaudhari
 */
@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ReplyRepository replyRepository;

    /**
     * It persist the Post data in database
     *
     * @return
     */
    @Transactional
    public PostResponse addPostData(PostRequest postRequest) {

        PostsDao postsDao = new PostsDao();
        postsDao.setContent(postRequest.getContent());
        postsDao.setCity(postRequest.getCity());
        postsDao.setUsername(postRequest.getUsername());

        //add weather information entered by user for that city
        weatherService.addWeatherInformation(postsDao, postRequest.getCity());

        postRepository.save(postsDao);
        log.info("Post information stored in database.");

        return createPostResponse(postsDao);
    }

    /**
     * It gives the all post data from database
     *
     * @return
     */
    public List<PostResponse> getAllPosts() {
        List<PostsDao> postDaoList = (List<PostsDao>) postRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postDaoList, new TypeToken<List<PostResponse>>() {
        }.getType());
    }


    /**
     * It persist the replied comments data by post Id
     *
     * @return
     */
    public ReplyResponse addPostReplyData(Integer postId, ReplyRequest replyRequest) {

        //check postId exist in database;
        boolean isPost = postRepository.existsById(postId);
        if(!isPost)
            throw new ServiceException(ErrorResponseCodes.UNPROCESSABLE_ENTITY_POST_ID);

        ReplyPostDao replyPostDao = new ReplyPostDao();
        replyPostDao.setComment(replyRequest.getComment());
        replyPostDao.setUsername(replyRequest.getUsername());
        replyPostDao.setPostId(postId);

        replyRepository.save(replyPostDao);
        log.info("Reply post added in database.");

        return createReplyResponse(replyPostDao);
    }

    /**
     * It gives all replied comments by post Id
     *
     * @param postId
     * @return
     */
    public List<ReplyResponse> getAllReplyByPostId(int postId) {
        List<ReplyPostDao> postDaoList =  replyRepository.findByPostId(postId);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postDaoList, new TypeToken<List<ReplyResponse>>() {
        }.getType());
    }

    /**
     * It deletes reply for given post Id and given reply Id
     *
     */
    public void deleteReplyPostById(int postId, int replyId) {
        replyRepository.deleteReplyByPostIdAndReplyId(replyId, postId);
    }


    /**
     * It creates post response object which stored in database.
     *
     * @param postsDao
     * @return
     */
    private PostResponse createPostResponse(PostsDao postsDao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postsDao, PostResponse.class);
    }

    /**
     * It creates reply response object which stored in database.
     *
     * @param replyPostDao
     * @return
     */
    private ReplyResponse createReplyResponse(ReplyPostDao replyPostDao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(replyPostDao, ReplyResponse.class);
    }



}
