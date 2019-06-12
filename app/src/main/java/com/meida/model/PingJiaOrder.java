package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/5 0005
 * 公司  郑州软盟
 */

public class PingJiaOrder {
    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":2,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":178,"uid":10004,"merchant_id":10008,"order_id":"201809031651394982110004","product_id":8,"product_name":"1.5匹挂机  三级能效  定频","product_logo":"http://libing.weiruanmeng.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg","product_spec":"1.5匹、变频、三级能效","product_price":"2899.00","product_num":1,"create_time":"2018-09-03 16:51:39","update_time":"2018-09-03 16:51:39","comment_status":1},{"id":177,"uid":10004,"merchant_id":10008,"order_id":"201809031651394982110004","product_id":7,"product_name":"立冰变频 冷暖挂机 一级能效空调","product_logo":"http://libing.weiruanmeng.com/upload/manager/20180818/7f861a1f787abf320266fcc76a2cfd6c.jpg","product_spec":"1.5匹、变频、一级能效","product_price":"4899.00","product_num":1,"create_time":"2018-09-03 16:51:39","update_time":"2018-09-03 16:51:39","comment_status":1}]}
     */

    private String code;
    private String msg;
    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":178,"uid":10004,"merchant_id":10008,"order_id":"201809031651394982110004","product_id":8,"product_name":"1.5匹挂机  三级能效  定频","product_logo":"http://libing.weiruanmeng.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg","product_spec":"1.5匹、变频、三级能效","product_price":"2899.00","product_num":1,"create_time":"2018-09-03 16:51:39","update_time":"2018-09-03 16:51:39","comment_status":1},{"id":177,"uid":10004,"merchant_id":10008,"order_id":"201809031651394982110004","product_id":7,"product_name":"立冰变频 冷暖挂机 一级能效空调","product_logo":"http://libing.weiruanmeng.com/upload/manager/20180818/7f861a1f787abf320266fcc76a2cfd6c.jpg","product_spec":"1.5匹、变频、一级能效","product_price":"4899.00","product_num":1,"create_time":"2018-09-03 16:51:39","update_time":"2018-09-03 16:51:39","comment_status":1}]
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
         * id : 178
         * uid : 10004
         * merchant_id : 10008
         * order_id : 201809031651394982110004
         * product_id : 8
         * product_name : 1.5匹挂机  三级能效  定频
         * product_logo : http://libing.weiruanmeng.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg
         * product_spec : 1.5匹、变频、三级能效
         * product_price : 2899.00
         * product_num : 1
         * create_time : 2018-09-03 16:51:39
         * update_time : 2018-09-03 16:51:39
         * comment_status : 1
         */

        private List<DatasBean> data;

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

        public List<DatasBean> getData() {
            return data;
        }

        public void setData(List<DatasBean> data) {
            this.data = data;
        }

        public static class DatasBean {
            private String id;
            private String uid;
            private String merchant_id;
            private String order_id;
            private String product_id;
            private String product_name;
            private String product_logo;
            private String product_spec;
            private String product_price;
            private String product_num;
            private String create_time;
            private String update_time;
            private String comment_status;

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

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

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

            public String getComment_status() {
                return comment_status;
            }

            public void setComment_status(String comment_status) {
                this.comment_status = comment_status;
            }
        }
    }

}
