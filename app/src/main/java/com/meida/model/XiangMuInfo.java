package com.meida.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/13 0013
 * 公司  郑州软盟
 */

public class XiangMuInfo implements Serializable {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"id":32,"uid":12,"merchant_id":1008,"parent_merchant_id":10008,"transaction_number":"2018090351524857","pay_type":2,"brand_name":"立冰","property_name":"商场","product_type_name":"立冰","address_name":"刘强","address_tel":"13780001234","province_id":440000,"city_id":440300,"area_id":440303,"area_merger_name":" 广东省,深圳市,罗湖区","address":"万达广场","area":15000,"title":"室内天花机项目","content":"新建万达广场部分空调的安装","create_time":"2018-09-03 14:26:59","update_time":"2018-09-03 14:26:59","status":2,"contract_price":"1000000.00","commission_rate":"3.00","contract_time":"0000-00-00 00:00:00","commission_status":2,"total_times":1,"first_payment":"0.00","first_payment_date":"0000-00-00","nickname":"沧桑影子","user_tel":"13783451041","payment_list":[{"id":11,"project_id":32,"pay_type":2,"amount":"1000000.00","total_amount":"1000000.00","current_times":1,"total_times":1,"status":1,"commission_status":1,"admin_user_login":"","create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","create_user":"","back_date":"2018-09-04","back_time":"0000-00-00 00:00:00"}]}
     */

    private String code;
    private String msg;
    /**
     * id : 32
     * uid : 12
     * merchant_id : 1008
     * parent_merchant_id : 10008
     * transaction_number : 2018090351524857
     * pay_type : 2
     * brand_name : 立冰
     * property_name : 商场
     * product_type_name : 立冰
     * address_name : 刘强
     * address_tel : 13780001234
     * province_id : 440000
     * city_id : 440300
     * area_id : 440303
     * area_merger_name :  广东省,深圳市,罗湖区
     * address : 万达广场
     * area : 15000
     * title : 室内天花机项目
     * content : 新建万达广场部分空调的安装
     * create_time : 2018-09-03 14:26:59
     * update_time : 2018-09-03 14:26:59
     * status : 2
     * contract_price : 1000000.00
     * commission_rate : 3.00
     * contract_time : 0000-00-00 00:00:00
     * commission_status : 2
     * total_times : 1
     * first_payment : 0.00
     * first_payment_date : 0000-00-00
     * nickname : 沧桑影子
     * user_tel : 13783451041
     * payment_list : [{"id":11,"project_id":32,"pay_type":2,"amount":"1000000.00","total_amount":"1000000.00","current_times":1,"total_times":1,"status":1,"commission_status":1,"admin_user_login":"","create_time":"1970-01-01 08:00:00","update_time":"1970-01-01 08:00:00","create_user":"","back_date":"2018-09-04","back_time":"0000-00-00 00:00:00"}]
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

    public static class DataBean implements Serializable {
        private String id;
        private String uid;
        private String merchant_id;
        private String parent_merchant_id;
        private String transaction_number;
        private String pay_type;
        private String brand_name;
        private String property_name;
        private String product_type_name;
        private String address_name;
        private String address_tel;
        private String province_id;
        private String city_id;
        private String area_id;
        private String area_merger_name;
        private String address;
        private String area;
        private String title;
        private String content;
        private String create_time;
        private String update_time;
        private String status;
        private String contract_price;
        private String commission_rate;
        private String contract_time;
        private String commission_status;
        private String total_times;
        private String first_payment;
        private String first_payment_date;
        private String nickname;
        private String user_tel;
        /**
         * id : 11
         * project_id : 32
         * pay_type : 2
         * amount : 1000000.00
         * total_amount : 1000000.00
         * current_times : 1
         * total_times : 1
         * status : 1
         * commission_status : 1
         * admin_user_login :
         * create_time : 1970-01-01 08:00:00
         * update_time : 1970-01-01 08:00:00
         * create_user :
         * back_date : 2018-09-04
         * back_time : 0000-00-00 00:00:00
         */

        private List<PaymentListBean> payment_list;

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

        public String getParent_merchant_id() {
            return parent_merchant_id;
        }

        public void setParent_merchant_id(String parent_merchant_id) {
            this.parent_merchant_id = parent_merchant_id;
        }

        public String getTransaction_number() {
            return transaction_number;
        }

        public void setTransaction_number(String transaction_number) {
            this.transaction_number = transaction_number;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getProperty_name() {
            return property_name;
        }

        public void setProperty_name(String property_name) {
            this.property_name = property_name;
        }

        public String getProduct_type_name() {
            return product_type_name;
        }

        public void setProduct_type_name(String product_type_name) {
            this.product_type_name = product_type_name;
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

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContract_price() {
            return contract_price;
        }

        public void setContract_price(String contract_price) {
            this.contract_price = contract_price;
        }

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
        }

        public String getContract_time() {
            return contract_time;
        }

        public void setContract_time(String contract_time) {
            this.contract_time = contract_time;
        }

        public String getCommission_status() {
            return commission_status;
        }

        public void setCommission_status(String commission_status) {
            this.commission_status = commission_status;
        }

        public String getTotal_times() {
            return total_times;
        }

        public void setTotal_times(String total_times) {
            this.total_times = total_times;
        }

        public String getFirst_payment() {
            return first_payment;
        }

        public void setFirst_payment(String first_payment) {
            this.first_payment = first_payment;
        }

        public String getFirst_payment_date() {
            return first_payment_date;
        }

        public void setFirst_payment_date(String first_payment_date) {
            this.first_payment_date = first_payment_date;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public List<PaymentListBean> getPayment_list() {
            return payment_list;
        }

        public void setPayment_list(List<PaymentListBean> payment_list) {
            this.payment_list = payment_list;
        }

        public static class PaymentListBean implements Serializable{
            private String id;
            private String project_id;
            private String pay_type;
            private String amount;
            private String total_amount;
            private String current_times;
            private String total_times;
            private String status;
            private String commission_status;
            private String admin_user_login;
            private String create_time;
            private String update_time;
            private String create_user;
            private String back_date;
            private String back_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProject_id() {
                return project_id;
            }

            public void setProject_id(String project_id) {
                this.project_id = project_id;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getCurrent_times() {
                return current_times;
            }

            public void setCurrent_times(String current_times) {
                this.current_times = current_times;
            }

            public String getTotal_times() {
                return total_times;
            }

            public void setTotal_times(String total_times) {
                this.total_times = total_times;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCommission_status() {
                return commission_status;
            }

            public void setCommission_status(String commission_status) {
                this.commission_status = commission_status;
            }

            public String getAdmin_user_login() {
                return admin_user_login;
            }

            public void setAdmin_user_login(String admin_user_login) {
                this.admin_user_login = admin_user_login;
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

            public String getCreate_user() {
                return create_user;
            }

            public void setCreate_user(String create_user) {
                this.create_user = create_user;
            }

            public String getBack_date() {
                return back_date;
            }

            public void setBack_date(String back_date) {
                this.back_date = back_date;
            }

            public String getBack_time() {
                return back_time;
            }

            public void setBack_time(String back_time) {
                this.back_time = back_time;
            }
        }
    }
}
