package com.weather.utils;

import com.weather.constants.ErrorResponseCodes;
import com.weather.exceptions.ServiceException;
import com.weather.model.request.PostRequest;
import com.weather.model.request.ReplyRequest;
import org.apache.commons.lang3.StringUtils;

public class RequestValidatorUtil {

    /**
     * It validate the post request data
     *
     * @param postRequest
     */
    public static void validatePostRequest(PostRequest postRequest) {

        if(StringUtils.isBlank(postRequest.getUsername()))
            throw new ServiceException(ErrorResponseCodes.BAD_REQUEST_POST);

        if(StringUtils.isBlank(postRequest.getCity()))
            throw new ServiceException(ErrorResponseCodes.BAD_REQUEST_POST);

        if(StringUtils.isBlank(postRequest.getContent()))
            throw new ServiceException(ErrorResponseCodes.BAD_REQUEST_POST);
    }

    /**
     * It validate the post request data
     *
     * @param replyRequest
     */
    public static void validatePostReplyRequest(ReplyRequest replyRequest) {

        if(StringUtils.isBlank(replyRequest.getUsername()))
            throw new ServiceException(ErrorResponseCodes.BAD_REQUEST_REPLY_POST);

        if(StringUtils.isBlank(replyRequest.getComment()))
            throw new ServiceException(ErrorResponseCodes.BAD_REQUEST_REPLY_POST);

    }
}
