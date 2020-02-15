package com.example.common;

import java.io.Serializable;


public class ResultBase implements Serializable {

    /**
     * 状态
     */
    private Status status;

    /**
     * 消息
     */
    private String message;


    public void setToFail(){
        this.message = "未知异常";
        this.status = Status.FAIL;
    }

    public void setToFail(String message){
        this.message = message;
        this.status = Status.FAIL;
    }

    public void setToSuccess(){
        this.message = "成功";
        this.status = Status.SUCCESS;
    }

    public void setToSuccess(String message){
        this.message = message;
        this.status = Status.SUCCESS;
    }

}
