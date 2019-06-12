package com.meida.model;

/**
 * 作者 亢兰兰
 * 时间 2018/8/29 0029
 * 公司  郑州软盟
 */

public class Login {


    /**
     * code : 1
     * msg : 登录成功
     * data : {"id":10006,"user_type":1,"merchant_type":0,"user_tel":"15617425029","nickname":"","user_real_name":"","logo":"","sex":0,"age":0,"birthday":"0000-00-00","token":"BnJvle1ISK23adc5fd8fa7b6fb091352a8e5410e485b863a17e1929","invite_merchant_id":0,"user_status":1,"card_status":1,"merchant_fee":"0.00","merchant_fee_status":1,"score":"0.00","score_exchange":"0.00","total_score":"0.00","total_score_exchange":"0.00","total_score_project":"0.00","total_score_sales":"0.00","total_score_express":"0.00","total_score_invite":"0.00","total_score_tips":"0.00","total_score_give":"0.00","total_score_receive":"0.00","block_score":"0.00","unblock_score":"0.00","unblock_stock_score":"0.00","create_time":"2018-08-29 14:15:51","update_time":"2018-08-29 14:15:51","last_login_time":"2018-08-29 14:15:51","location_province_id":0,"location_city_id":0,"location_area_id":0,"location_area_merger_name":"","user_pass_status":1,"pay_pass_status":2}
     */

    private String code;
    private String msg;
    /**
     * id : 10006
     * user_type : 1
     * merchant_type : 0
     * user_tel : 15617425029
     * nickname :
     * user_real_name :
     * logo :
     * sex : 0
     * age : 0
     * birthday : 0000-00-00
     * token : BnJvle1ISK23adc5fd8fa7b6fb091352a8e5410e485b863a17e1929
     * invite_merchant_id : 0
     * user_status : 1
     * card_status : 1
     * merchant_fee : 0.00
     * merchant_fee_status : 1
     * score : 0.00
     * score_exchange : 0.00
     * total_score : 0.00
     * total_score_exchange : 0.00
     * total_score_project : 0.00
     * total_score_sales : 0.00
     * total_score_express : 0.00
     * total_score_invite : 0.00
     * total_score_tips : 0.00
     * total_score_give : 0.00
     * total_score_receive : 0.00
     * block_score : 0.00
     * unblock_score : 0.00
     * unblock_stock_score : 0.00
     * create_time : 2018-08-29 14:15:51
     * update_time : 2018-08-29 14:15:51
     * last_login_time : 2018-08-29 14:15:51
     * location_province_id : 0
     * location_city_id : 0
     * location_area_id : 0
     * location_area_merger_name :
     * user_pass_status : 1
     * pay_pass_status : 2
     */

    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String user_type;
        private String merchant_type;
        private String user_tel;
        private String nickname;
        private String user_real_name;
        private String logo;
        private String sex;
        private String age;
        private String birthday;
        private String token;
        private String invite_merchant_id;
        private String user_status;
        private String card_status;
        private String merchant_fee;
        private int merchant_fee_status;
        private String score;
        private String score_exchange;
        private String total_score;
        private String total_score_exchange;
        private String total_score_project;
        private String total_score_sales;
        private String total_score_express;
        private String total_score_invite;
        private String total_score_tips;
        private String total_score_give;
        private String total_score_receive;
        private String block_score;
        private String unblock_score;
        private String unblock_stock_score;
        private String create_time;
        private String update_time;
        private String last_login_time;
        private String location_province_id;
        private String location_city_id;
        private String location_area_id;
        private String location_area_merger_name;
        private String user_pass_status;
        private String pay_pass_status;
        private String total_payment_score  ;

        public String getTotal_payment_score() {
            return total_payment_score;
        }

        public void setTotal_payment_score(String total_payment_score) {
            this.total_payment_score = total_payment_score;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getMerchant_type() {
            return merchant_type;
        }

        public void setMerchant_type(String merchant_type) {
            this.merchant_type = merchant_type;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUser_real_name() {
            return user_real_name;
        }

        public void setUser_real_name(String user_real_name) {
            this.user_real_name = user_real_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getInvite_merchant_id() {
            return invite_merchant_id;
        }

        public void setInvite_merchant_id(String invite_merchant_id) {
            this.invite_merchant_id = invite_merchant_id;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getCard_status() {
            return card_status;
        }

        public void setCard_status(String card_status) {
            this.card_status = card_status;
        }

        public String getMerchant_fee() {
            return merchant_fee;
        }

        public void setMerchant_fee(String merchant_fee) {
            this.merchant_fee = merchant_fee;
        }

        public int getMerchant_fee_status() {
            return merchant_fee_status;
        }

        public void setMerchant_fee_status(int merchant_fee_status) {
            this.merchant_fee_status = merchant_fee_status;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getScore_exchange() {
            return score_exchange;
        }

        public void setScore_exchange(String score_exchange) {
            this.score_exchange = score_exchange;
        }

        public String getTotal_score() {
            return total_score;
        }

        public void setTotal_score(String total_score) {
            this.total_score = total_score;
        }

        public String getTotal_score_exchange() {
            return total_score_exchange;
        }

        public void setTotal_score_exchange(String total_score_exchange) {
            this.total_score_exchange = total_score_exchange;
        }

        public String getTotal_score_project() {
            return total_score_project;
        }

        public void setTotal_score_project(String total_score_project) {
            this.total_score_project = total_score_project;
        }

        public String getTotal_score_sales() {
            return total_score_sales;
        }

        public void setTotal_score_sales(String total_score_sales) {
            this.total_score_sales = total_score_sales;
        }

        public String getTotal_score_express() {
            return total_score_express;
        }

        public void setTotal_score_express(String total_score_express) {
            this.total_score_express = total_score_express;
        }

        public String getTotal_score_invite() {
            return total_score_invite;
        }

        public void setTotal_score_invite(String total_score_invite) {
            this.total_score_invite = total_score_invite;
        }

        public String getTotal_score_tips() {
            return total_score_tips;
        }

        public void setTotal_score_tips(String total_score_tips) {
            this.total_score_tips = total_score_tips;
        }

        public String getTotal_score_give() {
            return total_score_give;
        }

        public void setTotal_score_give(String total_score_give) {
            this.total_score_give = total_score_give;
        }

        public String getTotal_score_receive() {
            return total_score_receive;
        }

        public void setTotal_score_receive(String total_score_receive) {
            this.total_score_receive = total_score_receive;
        }

        public String getBlock_score() {
            return block_score;
        }

        public void setBlock_score(String block_score) {
            this.block_score = block_score;
        }

        public String getUnblock_score() {
            return unblock_score;
        }

        public void setUnblock_score(String unblock_score) {
            this.unblock_score = unblock_score;
        }

        public String getUnblock_stock_score() {
            return unblock_stock_score;
        }

        public void setUnblock_stock_score(String unblock_stock_score) {
            this.unblock_stock_score = unblock_stock_score;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLocation_province_id() {
            return location_province_id;
        }

        public void setLocation_province_id(String location_province_id) {
            this.location_province_id = location_province_id;
        }

        public String getLocation_city_id() {
            return location_city_id;
        }

        public void setLocation_city_id(String location_city_id) {
            this.location_city_id = location_city_id;
        }

        public String getLocation_area_id() {
            return location_area_id;
        }

        public void setLocation_area_id(String location_area_id) {
            this.location_area_id = location_area_id;
        }

        public String getLocation_area_merger_name() {
            return location_area_merger_name;
        }

        public void setLocation_area_merger_name(String location_area_merger_name) {
            this.location_area_merger_name = location_area_merger_name;
        }

        public String getUser_pass_status() {
            return user_pass_status;
        }

        public void setUser_pass_status(String user_pass_status) {
            this.user_pass_status = user_pass_status;
        }

        public String getPay_pass_status() {
            return pay_pass_status;
        }

        public void setPay_pass_status(String pay_pass_status) {
            this.pay_pass_status = pay_pass_status;
        }
    }
}
