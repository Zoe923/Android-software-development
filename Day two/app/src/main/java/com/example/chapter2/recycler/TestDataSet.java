package com.example.chapter2.recycler;

import com.example.chapter2.R;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData(R.mipmap.iv_avatar,"游戏小助手", "抖出好游戏","刚刚"));
        result.add(new TestData(R.mipmap.iv_avatar2,"抖音小助手", "#收下我的双下巴祝福", "昨天"));
        result.add(new TestData(R.mipmap.iv_avatar3,"系统消息","账号登录提醒", "12:28"));
        result.add(new TestData(R.mipmap.iv_avatar4,"我是豆豆啊哇塞","转发[视频]", "12:21"));
        result.add(new TestData(R.mipmap.iv_avatar5,"陌生人消息" ,"teat1：转发[直播]：七舅脑爷", "12:12"));
        result.add(new TestData(R.mipmap.iv_avatar6,"喂喂卫", "[Hi]", "12:04"));
        result.add(new TestData(R.mipmap.iv_avatar7,"李垭超", "转发[视频]", "11:01"));
        result.add(new TestData(R.mipmap.iv_avatar8,"tesyyu", "我是tesyyu，让我们开始聊天吧", "10:19"));
        result.add(new TestData(R.mipmap.iv_avatar9,"花仙子","这个视频超级有趣，快去看！", "10:00"));
        result.add(new TestData(R.mipmap.iv_avatar10,"小可爱", "明天一起录一个抖音小视频可以吗？", "9:25"));
        result.add(new TestData(R.mipmap.iv_avatar11,"大宝贝", "晚安！", "两天前"));
        return result;
    }

}
