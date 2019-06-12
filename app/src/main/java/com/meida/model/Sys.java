package com.meida.model;

/**
 * 作者 亢兰兰
 * 时间 2018/9/4 0004
 * 公司  郑州软盟
 */

public class Sys {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"service_weixin":"Libing-china","service_tel":"4009696236","merchant_stock_amount":"30000","merchant_restock_amount":"10000","merchant_score_block_rate":"10","merchant_score_block_days":"365","withdraw_start_date":"2018-08-08","withdraw_start_days":"45","withdraw_day_num":"5","withdraw_charge_user":"10","withdraw_charge_merchant":"5","withdraw_low":"100","withdraw_high":"50000","user_score_back_times":"8","user_score_back_days":"45","merchant_join_amount":"20000","merchant_restock_rate":"60","withdraw_low_merchant":"1000","withdraw_high_merchant":"50000","start_date":"2018-09-22","end_date":"2018-09-26","start_flag":2}
     */

    private String code;
    private String msg;
    /**
     * service_weixin : Libing-china
     * service_tel : 4009696236
     * merchant_stock_amount : 30000
     * merchant_restock_amount : 10000
     * merchant_score_block_rate : 10
     * merchant_score_block_days : 365
     * withdraw_start_date : 2018-08-08
     * withdraw_start_days : 45
     * withdraw_day_num : 5
     * withdraw_charge_user : 10
     * withdraw_charge_merchant : 5
     * withdraw_low : 100
     * withdraw_high : 50000
     * user_score_back_times : 8
     * user_score_back_days : 45
     * merchant_join_amount : 20000
     * merchant_restock_rate : 60
     * withdraw_low_merchant : 1000
     * withdraw_high_merchant : 50000
     * start_date : 2018-09-22
     * end_date : 2018-09-26
     * start_flag : 2
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
        private String service_weixin;
        private String service_tel;
        private String merchant_stock_amount;
        private String merchant_restock_amount;
        private String merchant_score_block_rate;
        private String merchant_score_block_days;
        private String withdraw_start_date;
        private String withdraw_start_days;
        private String withdraw_day_num;
        private String withdraw_charge_user;
        private String withdraw_charge_merchant;
        private String withdraw_low;
        private String withdraw_high;
        private String user_score_back_times;
        private String user_score_back_days;
        private String merchant_join_amount;
        private String merchant_restock_rate;
        private String withdraw_low_merchant;
        private String withdraw_high_merchant;
        private String start_date;
        private String end_date;
        private String start_flag;
        private String url_user;
        private String url_down_merchant;
        private String is_withdraw_card_check ;
        private String invite_content_user ;
        private String invite_title_user ;

        private String product_block1;
        private String product_block2;
        private String product_block3;
        private String invite_desc_user;

        public String getInvite_desc_user() {
            return invite_desc_user;
        }

        public void setInvite_desc_user(String invite_desc_user) {
            this.invite_desc_user = invite_desc_user;
        }

        public String getProduct_block1() {
            return product_block1;
        }

        public void setProduct_block1(String product_block1) {
            this.product_block1 = product_block1;
        }

        public String getProduct_block2() {
            return product_block2;
        }

        public void setProduct_block2(String product_block2) {
            this.product_block2 = product_block2;
        }

        public String getProduct_block3() {
            return product_block3;
        }

        public void setProduct_block3(String product_block3) {
            this.product_block3 = product_block3;
        }

        public String getInvite_content_user() {
            return invite_content_user;
        }

        public void setInvite_content_user(String invite_content_user) {
            this.invite_content_user = invite_content_user;
        }

        public String getInvite_title_user() {
            return invite_title_user;
        }

        public void setInvite_title_user(String invite_title_user) {
            this.invite_title_user = invite_title_user;
        }

        public String getUrl_down_merchant() {
            return url_down_merchant;
        }

        public void setUrl_down_merchant(String url_down_merchant) {
            this.url_down_merchant = url_down_merchant;
        }

        public String getIs_withdraw_card_check() {
            return is_withdraw_card_check;
        }

        public void setIs_withdraw_card_check(String is_withdraw_card_check) {
            this.is_withdraw_card_check = is_withdraw_card_check;
        }

        public String getUrl_user() {
            return url_user;
        }

        public void setUrl_user(String url_user) {
            this.url_user = url_user;
        }

        public String getService_weixin() {
            return service_weixin;
        }

        public void setService_weixin(String service_weixin) {
            this.service_weixin = service_weixin;
        }

        public String getService_tel() {
            return service_tel;
        }

        public void setService_tel(String service_tel) {
            this.service_tel = service_tel;
        }

        public String getMerchant_stock_amount() {
            return merchant_stock_amount;
        }

        public void setMerchant_stock_amount(String merchant_stock_amount) {
            this.merchant_stock_amount = merchant_stock_amount;
        }

        public String getMerchant_restock_amount() {
            return merchant_restock_amount;
        }

        public void setMerchant_restock_amount(String merchant_restock_amount) {
            this.merchant_restock_amount = merchant_restock_amount;
        }

        public String getMerchant_score_block_rate() {
            return merchant_score_block_rate;
        }

        public void setMerchant_score_block_rate(String merchant_score_block_rate) {
            this.merchant_score_block_rate = merchant_score_block_rate;
        }

        public String getMerchant_score_block_days() {
            return merchant_score_block_days;
        }

        public void setMerchant_score_block_days(String merchant_score_block_days) {
            this.merchant_score_block_days = merchant_score_block_days;
        }

        public String getWithdraw_start_date() {
            return withdraw_start_date;
        }

        public void setWithdraw_start_date(String withdraw_start_date) {
            this.withdraw_start_date = withdraw_start_date;
        }

        public String getWithdraw_start_days() {
            return withdraw_start_days;
        }

        public void setWithdraw_start_days(String withdraw_start_days) {
            this.withdraw_start_days = withdraw_start_days;
        }

        public String getWithdraw_day_num() {
            return withdraw_day_num;
        }

        public void setWithdraw_day_num(String withdraw_day_num) {
            this.withdraw_day_num = withdraw_day_num;
        }

        public String getWithdraw_charge_user() {
            return withdraw_charge_user;
        }

        public void setWithdraw_charge_user(String withdraw_charge_user) {
            this.withdraw_charge_user = withdraw_charge_user;
        }

        public String getWithdraw_charge_merchant() {
            return withdraw_charge_merchant;
        }

        public void setWithdraw_charge_merchant(String withdraw_charge_merchant) {
            this.withdraw_charge_merchant = withdraw_charge_merchant;
        }

        public String getWithdraw_low() {
            return withdraw_low;
        }

        public void setWithdraw_low(String withdraw_low) {
            this.withdraw_low = withdraw_low;
        }

        public String getWithdraw_high() {
            return withdraw_high;
        }

        public void setWithdraw_high(String withdraw_high) {
            this.withdraw_high = withdraw_high;
        }

        public String getUser_score_back_times() {
            return user_score_back_times;
        }

        public void setUser_score_back_times(String user_score_back_times) {
            this.user_score_back_times = user_score_back_times;
        }

        public String getUser_score_back_days() {
            return user_score_back_days;
        }

        public void setUser_score_back_days(String user_score_back_days) {
            this.user_score_back_days = user_score_back_days;
        }

        public String getMerchant_join_amount() {
            return merchant_join_amount;
        }

        public void setMerchant_join_amount(String merchant_join_amount) {
            this.merchant_join_amount = merchant_join_amount;
        }

        public String getMerchant_restock_rate() {
            return merchant_restock_rate;
        }

        public void setMerchant_restock_rate(String merchant_restock_rate) {
            this.merchant_restock_rate = merchant_restock_rate;
        }

        public String getWithdraw_low_merchant() {
            return withdraw_low_merchant;
        }

        public void setWithdraw_low_merchant(String withdraw_low_merchant) {
            this.withdraw_low_merchant = withdraw_low_merchant;
        }

        public String getWithdraw_high_merchant() {
            return withdraw_high_merchant;
        }

        public void setWithdraw_high_merchant(String withdraw_high_merchant) {
            this.withdraw_high_merchant = withdraw_high_merchant;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getStart_flag() {
            return start_flag;
        }

        public void setStart_flag(String start_flag) {
            this.start_flag = start_flag;
        }
    }
}
