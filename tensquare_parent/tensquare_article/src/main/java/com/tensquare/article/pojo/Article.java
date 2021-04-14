package com.tensquare.article.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Deng Zhiwen
 * @date 2021/4/5 20:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_article")
public class Article implements Serializable {

    @TableId(type = IdType.INPUT)
    // AUTO(0, "数据库ID自增"), INPUT(1, "用户输入ID"),
    //
    //    以下2种类型、只有当插入对象ID 为空，才自动填充。
    //    ID_WORKER(2, "全局唯一ID"), UUID(3, "全局唯一ID"), NONE(4, "该类型为未设置主键类型"),
    //    ID_WORKER_STR(5, "字符串全局唯一ID");
    private String id;//ID

    private String columnid;    //专栏ID
    private String userid;      //用户ID
    private String title;       //标题
    private String content;     //文章正文
    private String image;       //文章封面
    private Date createtime;    //发表日期
    private Date updatetime;    //修改日期
    private String ispublic;    //是否公开
    private String istop;       //是否置顶
    private Integer visits;     //浏览量
    private Integer thumbup;    //点赞数
    private Integer comment;    //评论数
    private String state;       //审核状态
    private String channelid;   //所属频道
    private String url;         //URL
    private String type;        //类型

}
