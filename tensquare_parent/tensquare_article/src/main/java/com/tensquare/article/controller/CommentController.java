package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/save")
    public Result saveComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return new Result(true, 200, "评论成功");
    }
}
