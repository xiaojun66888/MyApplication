package com.example.test.myapplication.bean;

/**
 * Created by Administrator on 2017/9/26.
 */

public class MessageEvent {

    public  String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
