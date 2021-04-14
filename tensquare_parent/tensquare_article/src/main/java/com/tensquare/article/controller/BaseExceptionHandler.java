package com.tensquare.article.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deng Zhiwen
 * @date 2021/4/14 18:20
 */
@ControllerAdvice   // 对 controller 的增强
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody   // 声明把当前处理结果转成 JSON 数据
    public Result handler(Exception e) {
        if (e instanceof NullPointerException) {    // instanceof 用来测试一个对象是否为一个类的实例
            System.out.println("处理空指针异常逻辑");
        } else if (e instanceof ArrayIndexOutOfBoundsException){
            System.out.println("处理数组越界异常逻辑");
        }
        System.out.println("处理异常");
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }

}
