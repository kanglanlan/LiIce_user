package com.meida.share;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class HttpIp {

    public static String Ip = "http://s.libingglobal.com/api/";//客户服务器
//    public static String Ip = "http://libing.weiruanmeng.com/api/";//测试服务器
    public static String get_timestamp = Ip + "mobile/classify/get_timestamp";//时间戳
    public static String bannerlist = Ip + "mobile/Index/bannerlist";//轮播图
    public static String navlist = Ip + "mobile/Index/navlist";//首页导航
    public static String noticelist = Ip + "mobile/Index/noticelist";//公告
    public static String goodslist = Ip + "mobile/Index/goodslist";//商品列表
    public static String goodsDetail = Ip + "mobile/Goods/goodsDetail";//商品详情
    public static String goodsComment = Ip + "mobile/Goods/goodsComment";//商品评价
    public static String login = Ip + "mobile/public/login";//登录
    public static String shopCart = Ip + "mobile/Goods/shopCart";//添加和编辑购物车
    public static String getshopCart = Ip + "mobile/Goods/getshopCart";//购物车
    public static String delshopCart = Ip + "mobile/Goods/delshopCart";//购物车删除
    public static String info = Ip + "mobile/user/info";//个人资料
    public static String areaAll = Ip + "mobile/Index/areaAll";//省市区
    public static String address = Ip + "mobile/Address/address";//添加地址
    public static String set_info = Ip + "mobile/user/set_info";//修改资料
    public static String getAddress = Ip + "mobile/address/getAddress";//获取默认地址
    public static String addressList = Ip + "mobile/Address/addressList";//收货地址列表
    public static String facilitatorlist = Ip + "mobile/user/facilitatorlist";//服务商
    public static String labellist = Ip + "mobile/Index/labellist";//选择品牌
    public static String project = Ip + "mobile/user/project";//项目报备
    public static String projectlist = Ip + "mobile/record/projectlist";//项目报备列表
    public static String document = Ip + "mobile/Index/document";//图文
    public static String newlist = Ip + "mobile/Index/newlist";//app操作
    public static String detail = Ip + "mobile/Index/detail";//app操作详情
    public static String collect = Ip + "mobile/user/collect";//收藏和取消收藏
    public static String myCollect = Ip + "mobile/user/myCollect";//我的收藏列表
    public static String delcollect = Ip + "mobile/user/delcollect";//删除收藏
    public static String pay = Ip + "mobile/order/pay";//支付订单
    public static String certification = Ip + "mobile/user/certification";//实名认证
    public static String set_pass = Ip + "mobile/user/set_pass";//设置密码
    public static String feedback = Ip + "mobile/record/feedback";//意见反馈
    public static String index = Ip + "mobile/order/index";//订单列表
    public static String integrateList = Ip + "mobile/user/integrateList";//积分明细
    public static String sms = Ip + "mobile/classify/sms";//获取验证码
    public static String register = Ip + "mobile/public/register";//注册
    public static String check_sms = Ip + "mobile/classify/check_sms";//验证吗效验
    public static String findPassword = Ip + "mobile/public/findPassword";//找回密码
    public static String merchant_list = Ip + "mobile/order/merchant_list";// 根据地址选择服务商
    public static String evaluate_list = Ip + "mobile/record/evaluate_list";// 待评价/已评价/一键报修列表
    public static String apply = Ip + "mobile/order/apply";//报修
    public static String apply_list = Ip + "mobile/order/apply_list";//维修列表
    public static String orderdetail = Ip + "mobile/order/detail";//订单详情
    public static String configs = Ip + "mobile/classify/configs";//系统参数
    public static String transfer = Ip + "mobile/score/transfer";//积分转账
    public static String bankCardList = Ip + "mobile/user/bankCardList";//wode yinhangka
    public static String getBankCard = Ip + "mobile/user/getBankCard";//银行卡列表
    public static String withdrawapply = Ip + "mobile/withdraw/apply";//申请提现
    public static String bankCard = Ip + "mobile/user/bankCard";//添加编辑银行卡
    public static String tips = Ip + "mobile/score/tips";//积分打赏
    public static String evaluate = Ip + "mobile/record/evaluate";//评价
    public static String actions = Ip + "mobile/order/actions";//订单操作
    public static String delAddress = Ip + "mobile/Address/delAddress";//删除地址
    public static String evaluate_detail = Ip + "mobile/record/evaluate_detail";//评价详情
    public static String del_evaluate = Ip + "mobile/record/del_evaluate";//评价详情删除
    public static String user_cart_num = Ip + "mobile/order/user_cart_num";//购物车数量
    public static String user_order_num = Ip + "mobile/order/user_order_num";//订单数量
    public static String area_list = Ip + "mobile/classify/area_list";//.省市区信息
    public static String merchant_register = Ip + "mobile/public/merchant_register";//.服务商加盟
    public static String delBankCard = Ip + "mobile/user/delBankCard";// 删除银行卡
    public static String projectdetail = Ip + "mobile/project/detail";// 项目详情
    public static String pdetail = Ip + "mobile/project/detail";//项目跟进记录
    public static String record = Ip + "mobile/project/record";//项目跟进记录
    public static String get_pay_info = Ip + "mobile/order/get_pay_info";//订单获取支付信息（购买订单）
    public static String task_status = Ip + "mobile/classify/task_status";//用户任务完成状态
    public static String version = Ip + "mobile/classify/version";//版本更新
    public static String push = Ip + "mobile/classify/push";//给商户发推送
    public static String get_user_name = Ip + "mobile/score/get_user_name";//根据用户id获取用户信息
    public static String card_info = Ip + "mobile/user/card_info";//获取实名认证信息
    public static String set_default = Ip + "mobile/address/set_default";//设置默认地址
    public static String transfer_list = Ip + "mobile/score/transfer_list";//转账list
    public static String withdrawindex = Ip + "mobile/withdraw/index";//提现记录
    public static String invite_user_list = Ip + "mobile/score/invite_user_list";// 推荐用户列表
    public static String invite_score_list = Ip + "mobile/score/invite_score_list";// 推荐用户收入列表
    public static String comment_count = Ip + "mobile/Goods/comment_count";// 商品评价数量
    public static String bank_card_last_use = Ip + "mobile/user/bank_card_last_use";// 最新银行卡

}