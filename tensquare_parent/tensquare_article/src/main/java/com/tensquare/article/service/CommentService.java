package com.tensquare.article.service;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Deng Zhiwen
 * @date 2021/4/17 22:03
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    public void saveComment(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        comment.setThumbup(0);
        comment.setPublishdate(new Date());
        commentRepository.save(comment);
    }

    public List<Comment> queryComment() {
        return commentRepository.findAll();
    }

    public Comment queryCommentById(String id) {
        Optional<Comment> byId = commentRepository.findById(id);
        return byId.orElse(null);
    }


    public void updateComment(Comment comment) {
        // save 方法，如果主键存在执行修改，不存在执行新增
        commentRepository.save(comment);
    }

    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> queryCommentByArticleId(String articleID) {
        return commentRepository.findByArticleid(articleID);
    }

    public void thumbupComment(String commentId) {

        // 涉及到 i++ 问题，是线程不安全的。可以采用分布式锁来解决，推荐使用 redis/zookeeper
        // 或者 synchronized ，但是 synchronized 只能用在同一个JVM中，就是同一个服务器中，不好。
        Optional<Comment> byId = commentRepository.findById(commentId);
        Comment comment = byId.orElse(null);
        if (comment != null) {
            comment.setThumbup(comment.getThumbup() + 1);
            commentRepository.save(comment);
        }


        /////////////////   第二种方法（优化方案）   ////////////////
        // 利用 mongodb 本身的线程安全性来执行点赞 +1 的操作
//        Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is(commentId));
//
//        // 封装修改的值
//        Update update = new Update();
//        // 使用 inc 列值增长，在原有值基础上进行增加或减少
//        update.inc("thumbup", 1);   // 线程安全的
//
//        // 直接修改数据
//        // 第一个参数是修改条件，第二个是修改数值，第三个是集合名称
//        mongoTemplate.updateFirst(query, update, "comment");
    }
}
