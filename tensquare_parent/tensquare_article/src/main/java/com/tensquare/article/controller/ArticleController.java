package com.tensquare.article.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Deng Zhiwen
 * @date 2021/4/9 11:44
 */
@Api(tags = {"文章"})
@RestController
@RequestMapping("/article")
@CrossOrigin    // 解决跨域问题
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "测试公共异常")
    @GetMapping("exception")
    public Result exception() {
        int a = 1 / 0;
        return null;
    }

    @ApiOperation(value = "查询所有")
    @GetMapping
    public Result findAll() {
        List<Article> all = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", all);
    }

    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Article article = articleService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", article);
    }

    @ApiOperation(value = "增加")
    @PostMapping("/addOne")
    public Result addArticle(@RequestBody Article article) {
        articleService.addOne(article);
        return new Result(true, StatusCode.OK, "增加成功", article);
    }

    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("{id}")
    public Result deleteArticle(@PathVariable String id) {
        articleService.deleteOne(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @ApiOperation(value = "修改")
    @PutMapping("{id}")
    public Result updateArticle(@PathVariable String id, @RequestBody Article article) {
        articleService.updateOne(id, article);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @ApiOperation("分页条件查询")
    @PostMapping("pageQuery")
    // 之前接收文章使用的是 POJO ，也就是 Article article，遍历 article 所有属性需要使用反射属性，成本高性能低。改用集合方式遍历
    public Result pageQueryArticle(@RequestParam Integer page,
                                   @RequestParam Integer size,
                                   // @RequestBody Article article,
                                   @RequestBody Map<String, Object> map) {
        // 根据条件分页查询
        Page<Article> pageData = articleService.findByPage(page, size, map);

        // 封装分页返回对象
        PageResult<Article> pageResult = new PageResult<>(
                pageData.getTotal(),  // 总条数
                pageData.getRecords()); // 当前页的条数

        // 返回数据
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }
}
