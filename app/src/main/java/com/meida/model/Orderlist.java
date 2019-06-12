package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/1 0001
 * 公司  郑州软盟
 */

public class Orderlist {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":70,"per_page":"10","current_page":1,"last_page":7,"data":[{"id":85,"merchant_id":2,"order_type":1,"order_id":"201808241424197548112","status":2,"order_amount":"11798.00","pay_amount":"11798.00","pay_type":3,"create_time":"2018-08-24 14:24:19","status_name":"待付款","list":[{"product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"comment_status":1}]}]}
     */

    private String code;
    private String msg;
    /**
     * total : 70
     * per_page : 10
     * current_page : 1
     * last_page : 7
     * data : [{"id":85,"merchant_id":2,"order_type":1,"order_id":"201808241424197548112","status":2,"order_amount":"11798.00","pay_amount":"11798.00","pay_type":3,"create_time":"2018-08-24 14:24:19","status_name":"待付款","list":[{"product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"comment_status":1}]}]
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
         * id : 85
         * merchant_id : 2
         * order_type : 1
         * order_id : 201808241424197548112
         * status : 2
         * order_amount : 11798.00
         * pay_amount : 11798.00
         * pay_type : 3
         * create_time : 2018-08-24 14:24:19
         * status_name : 待付款
         * list : [{"product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"comment_status":1}]
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
            private String merchant_id;
            private String order_type;
            private String order_id;
            private String status;
            private String order_amount;
            private String pay_amount;
            private String pay_type;
            private String create_time;
            private String status_name;
            private String total_product_num;
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

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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
        }
    }
}
