package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/4 0004
 * 公司  郑州软盟
 */

public class OrderInfo {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"id":85,"uid":12,"merchant_id":2,"order_type":1,"order_id":"201808241424197548112","status":2,"order_amount":"11798.00","pay_amount":"11798.00","user_remark":"欲说还休，却道天凉好个秋。","pay_type":2,"pay_flag":"","pay_time":"0000-00-00 00:00:00","create_time":"2018-08-24 14:24:19","update_time":"2018-08-24 14:24:19","express_time":"0000-00-00 00:00:00","confirm_time":"0000-00-00 00:00:00","express_company":"","express_code":"","express_number":"","address_name":"234","address_tel":"23","province_id":342,"city_id":22234,"area_id":4,"area_merger_name":"","address":"24","user_del":1,"status_name":"待付款","list":[{"product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"comment_status":1}]}
     */

    private String code;
    private String msg;
    /**
     * id : 85
     * uid : 12
     * merchant_id : 2
     * order_type : 1
     * order_id : 201808241424197548112
     * status : 2
     * order_amount : 11798.00
     * pay_amount : 11798.00
     * user_remark : 欲说还休，却道天凉好个秋。
     * pay_type : 2
     * pay_flag :
     * pay_time : 0000-00-00 00:00:00
     * create_time : 2018-08-24 14:24:19
     * update_time : 2018-08-24 14:24:19
     * express_time : 0000-00-00 00:00:00
     * confirm_time : 0000-00-00 00:00:00
     * express_company :
     * express_code :
     * express_number :
     * address_name : 234
     * address_tel : 23
     * province_id : 342
     * city_id : 22234
     * area_id : 4
     * area_merger_name :
     * address : 24
     * user_del : 1
     * status_name : 待付款
     * list : [{"product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"comment_status":1}]
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
        private String uid;
        private String merchant_id;
        private String order_type;
        private String order_id;
        private String status;
        private String order_amount;
        private String pay_amount;
        private String user_remark;
        private String pay_type;
        private String pay_flag;
        private String pay_time;
        private String create_time;
        private String update_time;
        private String express_time;
        private String confirm_time;
        private String express_company;
        private String express_code;
        private String express_number;
        private String address_name;
        private String address_tel;
        private String province_id;
        private String city_id;
        private String area_id;
        private String area_merger_name;
        private String address;
        private String user_del;
        private String status_name;
        private String total_product_num;
        private Merchant_info merchant_info;
        /**
         * product_id : 6
         * product_name : 立冰 2匹 热泵采暖空调
         * product_logo : http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png
         * product_spec : 2匹，一级能效，
         * product_price : 11798.00
         * product_num : 2
         * comment_status : 1
         */

        private List<ListBean> list;

        public Merchant_info getMerchant_info() {
            return merchant_info;
        }

        public void setMerchant_info(Merchant_info merchant_info) {
            this.merchant_info = merchant_info;
        }

        public String getTotal_product_num() {
            return total_product_num;
        }

        public void setTotal_product_num(String total_product_num) {
            this.total_product_num = total_product_num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getUser_remark() {
            return user_remark;
        }

        public void setUser_remark(String user_remark) {
            this.user_remark = user_remark;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(String pay_flag) {
            this.pay_flag = pay_flag;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
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

        public String getExpress_time() {
            return express_time;
        }

        public void setExpress_time(String express_time) {
            this.express_time = express_time;
        }

        public String getConfirm_time() {
            return confirm_time;
        }

        public void setConfirm_time(String confirm_time) {
            this.confirm_time = confirm_time;
        }

        public String getExpress_company() {
            return express_company;
        }

        public void setExpress_company(String express_company) {
            this.express_company = express_company;
        }

        public String getExpress_code() {
            return express_code;
        }

        public void setExpress_code(String express_code) {
            this.express_code = express_code;
        }

        public String getExpress_number() {
            return express_number;
        }

        public void setExpress_number(String express_number) {
            this.express_number = express_number;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public String getAddress_tel() {
            return address_tel;
        }

        public void setAddress_tel(String address_tel) {
            this.address_tel = address_tel;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_merger_name() {
            return area_merger_name;
        }

        public void setArea_merger_name(String area_merger_name) {
            this.area_merger_name = area_merger_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUser_del() {
            return user_del;
        }

        public void setUser_del(String user_del) {
            this.user_del = user_del;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String product_id;
            private String product_name;
            private String product_logo;
            private String product_spec;
            private String product_price;
            private String product_num;
            private String comment_status;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_logo() {
                return product_logo;
            }

            public void setProduct_logo(String product_logo) {
                this.product_logo = product_logo;
            }

            public String getProduct_spec() {
                return product_spec;
            }

            public void setProduct_spec(String product_spec) {
                this.product_spec = product_spec;
            }

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }

            public String getProduct_num() {
                return product_num;
            }

            public void setProduct_num(String product_num) {
                this.product_num = product_num;
            }

            public String getComment_status() {
                return comment_status;
            }

            public void setComment_status(String comment_status) {
                this.comment_status = comment_status;
            }
        }
        public static class Merchant_info {
            private String company_name;

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }
        }
    }
}
