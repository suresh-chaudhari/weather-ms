package com.loyalty.one.model.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reply_post")
@Getter
@Setter
public class ReplyPostDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_post_id")
    private Integer replyPostId;

    @Column(name = "comment")
    @NotNull
    private String comment;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "post_id")
//    @NotNull
    private Integer postId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "post_id")
//    private PostsDao postsDao;

}
