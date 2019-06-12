package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/31 0031
 * 公司  郑州软盟
 */

public class BaoBeiList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":7,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":6,"uid":6,"title":"1234567","property_name":"学校","area":123456,"address_name":"123","address_tel":"122344","brand_name":"立冰","create_time":"2018-08-15 19:04:30","status":2,"user_real_name":""}]}
     */

    private String code;
    private String msg;
    /**
     * total : 7
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":6,"uid":6,"title":"1234567","property_name":"学校","area":123456,"address_name":"123","address_tel":"122344","brand_name":"立冰","create_time":"2018-08-15 19:04:30","status":2,"user_real_name":""}]
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
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        /**
         * id : 6
         * uid : 6
         * title : 1234567
         * property_name : 学校
         * area : 123456
         * address_name : 123
         * address_tel : 122344
         * brand_name : 立冰
         * create_time : 2018-08-15 19:04:30
         * status : 2
         * user_real_name :
         */

        private List<DataBeasn> data;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public String getLast_page() {
            return last_page;
        }

        public void setLast_page(String last_page) {
            this.last_page = last_page;
        }

        public List<DataBeasn> getData() {
            return data;
        }

        public void setData(List<DataBeasn> data) {
            this.data = data;
        }

        public static class DataBeasn {
            private String id;
            private String uid;
            private String title;
            private String property_name;
            private String area;
            private String address_name;
            private String address_tel;
            private String brand_name;
            private String create_time;
            private String status;
            private String user_real_name;
            private String transaction_number;
            private String commission_status;

            public String getCommission_status() {
                return commission_status;
            }

            public void setCommission_status(String commission_status) {
                this.commission_status = commission_status;
            }

            public String getTransaction_number() {
                return transaction_number;
            }

            public void setTransaction_number(String transaction_number) {
                this.transaction_number = transaction_number;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getProperty_name() {
                return property_name;
            }

            public void setProperty_name(String property_name) {
                this.property_name = property_name;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
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

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUser_real_name() {
                return user_real_name;
            }

            public void setUser_real_name(String user_real_name) {
                this.user_real_name = user_real_name;
            }
        }
    }
}
