package com.meida.utils;

import android.content.Context;
import android.text.TextUtils;

import com.meida.model.CityList;
import com.meida.model.Coommt;
import com.meida.model.FenLeiList;
import com.meida.model.Login;
import com.meida.model.Sys;
import com.meida.nohttp.CallServer;
import com.meida.nohttp.CustomHttpListener;
import com.meida.nohttp.CustomHttpListenermoney;
import com.meida.share.Const;
import com.meida.share.HttpIp;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;

import org.json.JSONException;
import org.json.JSONObject;

import static com.meida.share.Params.app_timecha;
import static com.meida.share.Params.app_token;


/**
 * 作者 亢兰兰
 * 时间 2017/11/8 0008
 * 公司  郑州软盟
 */

public class Nonce {
    private static Request<String> mRequest;
    private static String Nonc;
    private static String Timestamp;

    public static String getNonce() {
        int no = (int) ((Math.random() * 9 + 1) * 100000);
        return no + "";
    }

    public interface CommCallback {
        void doWork(Coommt string);
    }

    /**
     * 库存不足时给服务商发推送
     */
    public static void Sendpush(String user_id, final Context baseContext) {
        mRequest = NoHttp.createStringRequest(HttpIp.push, Const.POST);
        mRequest.add("user_id", user_id);
        getRequest(baseContext, new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
            }
        }, false);
    }
    /**
     *设置默认地址
     */
    public static void setmoren(String id, final Context baseContext,final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.set_default, Const.POST);
        mRequest.add("id", id);
        getRequest(baseContext, new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                callback.doWork("");
            }
        }, true);
    }

    /**
     * 库存不足提示
     */
    public static void Updateversion(boolean b, final Context baseContext, final CommCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.version, Const.POST);
        getRequest(baseContext, new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork(data);
                }
            }
        }, b);
    }

    public static String gettimes() {
        String ti = app_timecha
                + System.currentTimeMillis() / 1000 + "";
        return ti;
    }

    public static String getkey() {
//        String ti = System.currentTimeMillis() / 1000 ;
        String ti = "";
        return ti;
    }

    public static String gettime() {
        String ti = System.currentTimeMillis() / 1000 + "";
        return ti;
    }

    public static void getstime(Context baseContext) {
        mRequest = NoHttp.createStringRequest(HttpIp.get_timestamp, Const.POST);
        CallServer.getRequestInstance().add(baseContext, 1,
                mRequest, new CustomHttpListener(baseContext, true, Coommt.class) {
                    @Override
                    public void doWork(Object data, String code) {
                        Coommt times = (Coommt) data;
                        app_timecha = Long.parseLong(times.getData().getTimestamp()) - System.currentTimeMillis() / 1000;
                    }

                }, false, false);
    }

    public static void putlogin(Context baseContext, Login person) {
        PreferencesUtils.putString(baseContext, "uid", person.getData().getId());
        PreferencesUtils.putString(baseContext, "token", person.getData().getToken());

    }

    public static void putinfo(Context baseContext, Login person) {
        PreferencesUtils.putString(baseContext, "uid", person.getData().getId());
        PreferencesUtils.putString(baseContext, "token", person.getData().getToken());
        PreferencesUtils.putString(baseContext, "logo", person.getData().getLogo());
        PreferencesUtils.putString(baseContext, "nickname", person.getData().getNickname());
        PreferencesUtils.putString(baseContext, "invite_merchant_id", person.getData().getInvite_merchant_id());
        PreferencesUtils.putString(baseContext, "score_exchange", person.getData().getScore_exchange());
        PreferencesUtils.putString(baseContext, "score", person.getData().getScore());
        PreferencesUtils.putString(baseContext, "total_score_give", person.getData().getTotal_score_give());
        PreferencesUtils.putString(baseContext, "total_score", person.getData().getTotal_score());
        PreferencesUtils.putString(baseContext, "user_type", person.getData().getUser_type());
        PreferencesUtils.putString(baseContext, "user_tel", person.getData().getUser_tel());
        PreferencesUtils.putString(baseContext, "sex", person.getData().getSex());
        PreferencesUtils.putString(baseContext, "birthday", person.getData().getBirthday());
        PreferencesUtils.putString(baseContext, "user_status", person.getData().getUser_status());
        PreferencesUtils.putString(baseContext, "card_status", person.getData().getCard_status());
        PreferencesUtils.putString(baseContext, "merchant_fee", person.getData().getMerchant_fee());
        PreferencesUtils.putString(baseContext, "pay_pass_status", person.getData().getPay_pass_status());
        PreferencesUtils.putString(baseContext, "total_score_invite", person.getData().getTotal_score_invite());
        PreferencesUtils.putString(baseContext, "total_payment_score", person.getData().getTotal_payment_score());
    }

    public static void dellogin(Context baseContext) {
        PreferencesUtils.putString(baseContext, "uid", "");
        PreferencesUtils.putString(baseContext, "token", "");
        PreferencesUtils.putString(baseContext, "logo", "");
        PreferencesUtils.putString(baseContext, "nickname", "");
        PreferencesUtils.putString(baseContext, "invite_merchant_id", "");
        PreferencesUtils.putString(baseContext, "score_exchange", "");
        PreferencesUtils.putString(baseContext, "score", "");
        PreferencesUtils.putString(baseContext, "user_type", "");
        PreferencesUtils.putString(baseContext, "user_tel", "");
        PreferencesUtils.putString(baseContext, "sex", "");
        PreferencesUtils.putString(baseContext, "birthday", "");
        PreferencesUtils.putString(baseContext, "user_status", "");
        PreferencesUtils.putString(baseContext, "card_status", "");
        PreferencesUtils.putString(baseContext, "merchant_fee", "");
        PreferencesUtils.putString(baseContext, "pay_pass_status", "");
    }


    public static void changecart(String product_id, String product_num, String id,
                                  final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.shopCart, Const.POST);
        mRequest.add("product_id", product_id);
        mRequest.add("product_num", product_num);
        mRequest.add("id", id);
        getRequest(baseContext, new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                try {
                    if (obj != null) {
                        CommonUtil.showToask(baseContext, obj.getString("msg"));
                    }
                    if ("1".equals(obj.getString("code"))) {
                        callback.doWork("");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, true);
    }

    public static void getadd(final Context baseContext, final CityCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.areaAll, Const.POST);
        getRequest(baseContext, new CustomHttpListener<CityList>(baseContext, true, CityList.class) {
            @Override
            public void doWork(CityList data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork(data);
                }
            }
        }, true);
    }

    /**
     * 订单操作
     *
     * @param baseContext
     * @param callback    /	1关闭订单；2删除订单；3催单；4确认订单
     */
    public static void order(String cancel_reason, String order_id, String type, final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.actions, Const.POST);
        mRequest.add("order_id", order_id);
        mRequest.add("type", type);
        if (!TextUtils.isEmpty(cancel_reason)) {
            mRequest.add("cancel_reason", cancel_reason);
        }
        getRequest(baseContext, new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork("");
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                if (obj != null) {
                    try {
                        CommonUtil.showToask(baseContext, obj.getString("msg"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);
    }

    public static void getcartnum(final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.user_cart_num, Const.POST);
        getRequest(baseContext, new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork(data.getData().getCart_num());
                }
            }
        }, false);
    }

    /**
     * 商品评价数量
     * @param baseContext
     * @param callback
     */
    public static void getcommentnum(String product_id,final Context baseContext, final StringtwoCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.comment_count, Const.POST);
        mRequest.add("product_id",product_id);
        getRequest(baseContext, new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork(data.getData().getTotal_num(),data.getData().getSmeta_num());
                }
            }
        }, false);
    }

    public static void task_status(final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.task_status, Const.POST);
        getRequest(baseContext, new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork(data.getData().getLogo_status());
                }
            }
        }, false);
    }

    public static void getordernum(final Context baseContext, final Strings4Callback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.user_order_num, Const.POST);
        getRequest(baseContext, new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork(data.getData().getNum0(), data.getData().getNum1(), data.getData().getNum2()
                            , data.getData().getNum3());
                }
            }
        }, false);
    }

    public static void getpinpai(boolean b, int type, final Context baseContext, final fenleiCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.labellist, Const.POST);
        mRequest.add("type", type);
        getRequest(baseContext, new CustomHttpListener<FenLeiList>(baseContext, true, FenLeiList.class) {
            @Override
            public void doWork(FenLeiList data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork(data);
                }
            }
        }, b);
    }
    /**
     * 根据用户id获取用户信息
     */
    public static void getusername(String id, final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.get_user_name, Const.POST);
        mRequest.add("user_id", id);
        getRequest(baseContext, new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    if (!TextUtils.isEmpty(data.getData().getUser_real_name())) {
                        callback.doWork(data.getData().getUser_real_name());
                    } else {
                        callback.doWork(data.getData().getNickname());
                    }

                }else{
                    callback.doWork("");
                }
            }
        }, true);
    }
    /**
     * 获取实名认证信息
     */
    public static void getcard_info( final Context baseContext, final StringsCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.card_info, Const.POST);
        getRequest(baseContext, new CustomHttpListenermoney<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                        callback.doWork(data.getData().getCard_name(),data.getData().getCard_number(),data.getData().getContent());

                }
            }
        }, true);
    }
    public static void getcode(String phone, int type, final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.sms, Const.POST);
        mRequest.add("user_tel", phone);
        mRequest.add("type", type);//类型：1通用;11用户注册；12用户密码相关；21服务商注册；22服务商密码相关
        getRequest(baseContext, new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork("");
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                if (obj != null) {
                    try {
                        CommonUtil.showToask(baseContext, obj.getString("msg"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);
    }

    public static void checkcode(String phone, String code, int type, final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.check_sms, Const.POST);
        mRequest.add("verify_code", code);
        mRequest.add("user_tel", phone);
        mRequest.add("type", type);//类型：1通用;11用户注册；12用户密码相关；21服务商注册；22服务商密码相关
        getRequest(baseContext, new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
                if ("1".equals(data.getCode())) {
                    callback.doWork("");
                }
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                if (obj != null) {
                    try {
                        CommonUtil.showToask(baseContext, obj.getString("msg"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, true);
    }

    public static void setsc(String source_id, final Context baseContext, final StringCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.collect, Const.POST);
        mRequest.add("source_id", source_id);
        getRequest(baseContext, new CustomHttpListener<Coommt>(baseContext, true, Coommt.class) {
            @Override
            public void doWork(Coommt data, String code) {
            }

            @Override
            public void onFinally(JSONObject obj, String code, boolean isNetSucceed) {
                super.onFinally(obj, code, isNetSucceed);
                try {
                    if (obj != null) {
                        CommonUtil.showToask(baseContext, obj.getString("msg"));
                        if ("1".equals(obj.getString("code"))) {
                            callback.doWork(obj.getJSONObject("data").getString("status"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, true);
    }

    public static void getsys(final Context baseContext) {
        mRequest = NoHttp.createStringRequest(HttpIp.configs, Const.POST);
        getRequest(baseContext, new CustomHttpListenermoney<Sys>(baseContext, true, Sys.class) {
            @Override
            public void doWork(Sys data, String code) {
                PreferencesUtils.putString(baseContext, "service_weixin", data.getData().getService_weixin());
                PreferencesUtils.putString(baseContext, "service_tel", data.getData().getService_tel());
                PreferencesUtils.putString(baseContext, "merchant_stock_amount", data.getData().getMerchant_stock_amount());
                PreferencesUtils.putString(baseContext, "merchant_restock_amount", data.getData().getMerchant_restock_amount());
                PreferencesUtils.putString(baseContext, "merchant_score_block_rate", data.getData().getMerchant_score_block_rate());
                PreferencesUtils.putString(baseContext, "merchant_score_block_days", data.getData().getMerchant_score_block_days());
                PreferencesUtils.putString(baseContext, "withdraw_start_date", data.getData().getWithdraw_start_date());
                PreferencesUtils.putString(baseContext, "withdraw_start_days", data.getData().getWithdraw_start_days());
                PreferencesUtils.putString(baseContext, "withdraw_day_num", data.getData().getWithdraw_day_num());
                PreferencesUtils.putString(baseContext, "withdraw_charge_user", data.getData().getWithdraw_charge_user());
                PreferencesUtils.putString(baseContext, "withdraw_charge_merchant", data.getData().getWithdraw_charge_merchant());
                PreferencesUtils.putString(baseContext, "withdraw_low", data.getData().getWithdraw_low());
                PreferencesUtils.putString(baseContext, "withdraw_high", data.getData().getWithdraw_high());
                PreferencesUtils.putString(baseContext, "user_score_back_times", data.getData().getUser_score_back_times());
                PreferencesUtils.putString(baseContext, "user_score_back_days", data.getData().getUser_score_back_days());
                PreferencesUtils.putString(baseContext, "merchant_join_amount", data.getData().getMerchant_join_amount());
                PreferencesUtils.putString(baseContext, "merchant_restock_rate", data.getData().getMerchant_restock_rate());
                PreferencesUtils.putString(baseContext, "withdraw_low_merchant", data.getData().getWithdraw_low_merchant());
                PreferencesUtils.putString(baseContext, "withdraw_high_merchant", data.getData().getWithdraw_high_merchant());
                PreferencesUtils.putString(baseContext, "start_date", data.getData().getStart_date());
                PreferencesUtils.putString(baseContext, "end_date", data.getData().getEnd_date());
                PreferencesUtils.putString(baseContext, "start_flag", data.getData().getStart_flag());
                PreferencesUtils.putString(baseContext, "url_user", data.getData().getUrl_user());
                PreferencesUtils.putString(baseContext, "is_withdraw_card_check", data.getData().getIs_withdraw_card_check());
                PreferencesUtils.putString(baseContext, "url_down_merchant", data.getData().getUrl_down_merchant());
                PreferencesUtils.putString(baseContext, "invite_content_user", data.getData().getInvite_content_user());
                PreferencesUtils.putString(baseContext, "invite_title_user", data.getData().getInvite_title_user());
                PreferencesUtils.putString(baseContext, "product_block1", data.getData().getProduct_block1());
                PreferencesUtils.putString(baseContext, "product_block2", data.getData().getProduct_block2());
                PreferencesUtils.putString(baseContext, "product_block3", data.getData().getProduct_block3());
                PreferencesUtils.putString(baseContext, "invite_desc_user", data.getData().getInvite_desc_user());
            }
        }, false);
    }
    public static void getinfo(final Context baseContext, final LoginCallback callback) {
        mRequest = NoHttp.createStringRequest(HttpIp.info, Const.POST);
        getRequest(baseContext, new CustomHttpListenermoney<Login>(baseContext, true, Login.class) {
            @Override
            public void doWork(Login data, String code) {
                if ("1".equals(data.getCode())) {
                    Nonce.putinfo(baseContext, data);
                    callback.doWork(data);
                }
            }
        }, false);
    }
    public interface LoginCallback {
        void doWork(Login string);
    }
    public interface fenleiCallback {
        void doWork(FenLeiList string);
    }

    public interface CityCallback {
        void doWork(CityList string);
    }

    public interface StringCallback {
        void doWork(String string);
    }
    public interface StringtwoCallback {
        void doWork(String string1,String string2);
    }

    public interface StringsCallback {
        void doWork(String string1, String string2, String string3);
    }
    public interface Strings4Callback {
        void doWork(String string1, String string2, String string3, String string4);
    }

    //Nohttp请求
    public static <T> void getRequest(Context baseContext, CustomHttpListener<T> customHttpListener, boolean isloading) {
        String Nonc = Nonce.getNonce();
        String Timestamp = Nonce.gettimes();
        mRequest.addHeader("Ha-Timestamp", Timestamp);//XX-Timestamp、XX-Nonce、XX-Token三个参数
        mRequest.addHeader("Ha-Nonce", Nonc);
        String str=mRequest.url();
        mRequest.addHeader("Ha-Signature", MD5Utils.md5Password(str.substring(str.indexOf("api/")+4)+Timestamp + Nonc + app_token));
        mRequest.addHeader("Ha-DeviceType", "1");//设备类型1安卓2苹果
        mRequest.addHeader("Ha-AppType", "1");//客户端类型1用户端2商户端
        mRequest.addHeader("Ha-VersionCode", CommonUtil.getVersion(baseContext));
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "uid"))) {
            mRequest.addHeader("Uid", PreferencesUtils.getString(baseContext, "uid"));
        }
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "token"))) {
            mRequest.addHeader("Token", PreferencesUtils.getString(baseContext, "token"));
        }
        CallServer.getRequestInstance().add(baseContext, mRequest, customHttpListener, isloading);
    }

    //Nohttp请求
    public static <T> void getRequest(Context baseContext, CustomHttpListenermoney<T> customHttpListener, boolean isloading) {
        String Nonc = Nonce.getNonce();
        String Timestamp = Nonce.gettimes();
        mRequest.addHeader("Ha-Timestamp", Timestamp);//XX-Timestamp、XX-Nonce、XX-Token三个参数
        mRequest.addHeader("Ha-Nonce", Nonc);
        String str=mRequest.url();
        mRequest.addHeader("Ha-Signature", MD5Utils.md5Password(str.substring(str.indexOf("api/")+4)+Timestamp + Nonc + app_token));

        mRequest.addHeader("Ha-DeviceType", "1");//设备类型1安卓2苹果
        mRequest.addHeader("Ha-AppType", "1");//客户端类型1用户端2商户端
        mRequest.addHeader("Ha-VersionCode", CommonUtil.getVersion(baseContext));
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "uid"))) {
            mRequest.addHeader("Uid", PreferencesUtils.getString(baseContext, "uid"));
        }
        if (!TextUtils.isEmpty(PreferencesUtils.getString(baseContext, "token"))) {
            mRequest.addHeader("Token", PreferencesUtils.getString(baseContext, "token"));
        }
        CallServer.getRequestInstance().add(baseContext, mRequest, customHttpListener, isloading);
    }

}
