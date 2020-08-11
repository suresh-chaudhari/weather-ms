package com.loyalty.one.repository;

import com.loyalty.one.model.dao.PostsDao;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Suresh Chaudhari
 */
public interface PostRepository extends CrudRepository<PostsDao, Integer> {

}
