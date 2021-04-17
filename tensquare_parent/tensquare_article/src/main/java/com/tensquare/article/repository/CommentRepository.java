package com.tensquare.article.repository;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Deng Zhiwen
 * @date 2021/4/17 22:00
 */

public interface CommentRepository extends MongoRepository<Comment, String> {
}
