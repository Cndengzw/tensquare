package com.tensquare.article.repository;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Deng Zhiwen
 * @date 2021/4/17 22:00
 */

public interface CommentRepository extends MongoRepository<Comment, String> {

    // SpringDataMongoDB, 支持通过查询方法名进行查询定义的方式


    // 根据发布时间排序
    List<Comment> findByPublishdate(Date date);

    // 根据用户ID查询，并且根据发布时间倒序排序
    List<Comment> findByUseridOrderByPublishdateDesc(String userId);

    // 根据文章 ID 查询文章评论
    List<Comment> findByArticleid(String articleId);
}
