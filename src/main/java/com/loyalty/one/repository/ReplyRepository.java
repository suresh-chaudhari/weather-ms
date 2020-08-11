package com.loyalty.one.repository;

import com.loyalty.one.model.dao.ReplyPostDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Suresh Chaudhari
 */
public interface ReplyRepository extends CrudRepository<ReplyPostDao, Integer> {

   List<ReplyPostDao> findByPostId(int postId);


}
