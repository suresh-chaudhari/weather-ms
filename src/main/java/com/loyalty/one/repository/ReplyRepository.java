package com.loyalty.one.repository;

import com.loyalty.one.model.dao.ReplyPostDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Suresh Chaudhari
 */
public interface ReplyRepository extends CrudRepository<ReplyPostDao, Integer> {

   List<ReplyPostDao> findByPostId(int postId);

   @Transactional
   @Modifying
   @Query("DELETE ReplyPostDao rd WHERE rd.replyPostId= :replyPostId AND rd.postId= :postId")
   void deleteReplyByPostIdAndReplyId(@Param("replyPostId") int replyPostId, @Param("postId") int postId);


}
