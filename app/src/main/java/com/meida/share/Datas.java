package com.meida.share;

import java.util.ArrayList;

/**
 * 作者 亢兰兰
 * 时间 2017/10/17 0017
 * 公司  郑州软盟
 */

public class Datas {
    public static ArrayList<String> alldatas = new ArrayList<>();
    public static int ISRE = 0;
    public static int ISBACK = 0;
    public static int LOGIN = 0;
    public static String APPID;
    public static int PAYTYPE;//2下单购物
    public static String MONEY;//下单购物money
    public static String CITY="";
    public static int SYTYPE;//1x项目报备

    public static void setdatass() {
        alldatas.clear();
        alldatas.add("补贴空调");
        alldatas.add("积分商城");
        alldatas.add("空调租赁");
        alldatas.add("项目报备");
        alldatas.add("一键报修");
        alldatas.add("服务商加盟");
        alldatas.add("APP操作");
        alldatas.add("我要推广");
    }

    public static ArrayList<String> minedatas = new ArrayList<>();

    public static void setmindedata() {
        minedatas.clear();
        minedatas.add("申请备货");
        minedatas.add("申请补货");
        minedatas.add("预约项目");
        minedatas.add("客服电话");
        minedatas.add("我的积分");
        minedatas.add("积分转增");
        minedatas.add("积分充值");
        minedatas.add("我要推荐");
        minedatas.add("我的消息");
        minedatas.add("我的订单");
        minedatas.add("实名认证");
        minedatas.add("收货地址");
        minedatas.add("帮助中心");
        minedatas.add("意见反馈");
        minedatas.add("设置");
    }
}
