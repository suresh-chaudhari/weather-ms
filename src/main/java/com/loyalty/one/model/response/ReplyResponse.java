package com.loyalty.one.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Suresh Chaudhari
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyResponse implements Serializable {

    private Integer replyPostId;
    private String username;
    private String comment;
    private String createdDate;
    private Integer postId;

}
