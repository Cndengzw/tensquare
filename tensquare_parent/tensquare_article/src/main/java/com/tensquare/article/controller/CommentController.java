package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Deng Zhiwen
 * @date 2021/4/17 22:04
 */
@Api(tags = {"文章评论"})
@RestController
@RequestMapping("/comment")
@CrossOrigin    // 解决跨域问题
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "查询所有")
    @GetMapping()
    public Result queryComment() {
        List<Comment> results = commentService.queryComment();
        return new Result(true, 200, "查询成功", results);
    }

    @ApiOperation(value = "根据评论ID查询")
    @GetMapping("{commentID}")
    public Result queryComment(@PathVariable String commentID) {
        Comment comment = commentService.queryCommentById(commentID);
        return new Result(true, 200, "查询成功", comment);
    }

    @ApiOperation(value = "新增一条评论")
    @PostMapping("/save")
    public Result saveComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return new Result(true, 200, "评论成功");
    }

    @ApiOperation(value = "修改评论")
    @PutMapping("{commentID}")
    public Result updateComment(@PathVariable String commentID, @RequestBody Comment comment) {
        comment.set_id(commentID);
        commentService.updateComment(comment);
        return new Result(true, 200, "修改成功");
    }

    @ApiOperation(value = "修改评论")
    @DeleteMapping("{commentID}")
    public Result deleteComment(@PathVariable String commentID) {
        commentService.deleteCommentById(commentID);
        return new Result(true, 200, "删除成功");
    }

    @ApiOperation(value = "根据文章ID查询评论")
    @GetMapping("/article/{articleID}")
    public Result queryCommentByArticleId(@PathVariable String articleID) {
        List<Comment> results = commentService.queryCommentByArticleId(articleID);
        return new Result(true, 200, "查询成功", results);
    }

    @ApiOperation(value = "根据评论ID点赞")
    @PutMapping("/thumbup/{commentId}")
    public Result thumbup(@PathVariable String commentId) {
        // 1. 把用户点赞信息保存到 redis 中
        String userId = "123";  //模拟获取到用户 ID

        // 2. 每次点赞前，先查询用户是否点赞
        Object flag = redisTemplate.opsForValue().get("thumbup_" + userId + "_" + commentId);

        // 3. 如果没有点赞信息，用户可以点赞
        if (flag == null) {
            commentService.thumbupComment(commentId);

            // 点赞成功，保存点赞信息
            redisTemplate.opsForValue().set("thumbup_" + userId + "_" + commentId, 1);

            return new Result(true, 200, "点赞成功");
        }

        // 4.如果点赞不能重复点赞（可以根据业务实际选择重复点赞取消点赞）
        return new Result(false, StatusCode.REPERROR, "不能重复点赞");
    }

}
