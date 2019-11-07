package com.alisls.demo.springcloud.provider.user.dto;

public class MyMsg {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MyMsg{" +
                "content='" + content + '\'' +
                '}';
    }
}
