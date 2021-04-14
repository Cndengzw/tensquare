package com.tensquare.article.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    public List<Article> findAll() {
        return articleDao.selectList(null);
    }

    public Article findById(String id) {
        return articleDao.selectById(id);
    }

    public void addOne(Article article) {
        String id = idWorker.nextId() + ""; // 将 long 转为 String 的简单方法
        article.setId(id);

        // 文章浏览量等信息初始化（如果不设置会为Null，后面+1的时候会出问题）
        article.setVisits(0);   // 浏览量
        article.setThumbup(0);  // 点赞数
        article.setComment(0);  // 评论数

        articleDao.insert(article);
    }

    public void deleteOne(String id) {
        articleDao.deleteById(id);
    }

    public void updateOne(String id, Article article) {
        article.setId(id);
        // 根据主键修改
        articleDao.updateById(article);

//        // 第二种方法：根据条件修改
//        EntityWrapper<Article> wrapper = new EntityWrapper<>();     // 通过 CTRL+H 查看 wrapper 的实现类
//        // 设置条件
//        wrapper.eq("id", id);   // 哪一列 = 哪个值
//        articleDao.update(article, wrapper);
    }

    public Page<Article> findByPage(Integer page, Integer size, Map<String, Object> map) {
        // 设置查询条件
        EntityWrapper<Article> wrapper = new EntityWrapper<>();
        Set<String> keySet = map.keySet();      // map 中 key 的集合，也就是所有条件的字段集合
        for (String key : keySet) {
//            if (map.get(key) != null) { // 判断一下条件确实非空
//                wrapper.eq(key, map.get(key));  // 设置具体条件
//            }
            // 上面的写法可以用下面代替
            wrapper.eq(map.get(key) != null, key, map.get(key));    // 条件为 true 才设置条件，否则不设置。
        }

        // 设置分页参数
        Page<Article> pageData = new Page<>(page, size);

        // 执行查询，第一个是分页参数，第二个是查询条件
        List<Article> articles = articleDao.selectPage(pageData, wrapper);
        pageData.setRecords(articles);
        return pageData;
    }
}