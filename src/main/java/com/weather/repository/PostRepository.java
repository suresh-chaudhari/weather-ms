package com.weather.repository;

import com.weather.model.dao.PostsDao;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Suresh Chaudhari
 */
public interface PostRepository extends CrudRepository<PostsDao, Integer> {

}
