package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/28 0028
 * 公司  郑州软盟
 */

public class Goods {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":1,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":9,"title":"户外水杯","spec":"水杯","logo":"","content":"","user_price":"0.00","merchant_price":"0.00","user_score":"0.00","user_score_exchange":"0.00","sales_score":"100.00","sales_score_exchange":"200.00","user_sales_num":0,"labels":[]}]}
     */

    private String code;
    private String msg;
    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":9,"title":"户外水杯","spec":"水杯","logo":"","content":"","user_price":"0.00","merchant_price":"0.00","user_score":"0.00","user_score_exchange":"0.00","sales_score":"100.00","sales_score_exchange":"200.00","user_sales_num":0,"labels":[]}]
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
         * id : 9
         * title : 户外水杯
         * spec : 水杯
         * logo :
         * content :
         * user_price : 0.00
         * merchant_price : 0.00
         * user_score : 0.00
         * user_score_exchange : 0.00
         * sales_score : 100.00
         * sales_score_exchange : 200.00
         * user_sales_num : 0
         * labels : []
         */

        private List<DataBeans> data;

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

        public List<DataBeans> getData() {
            return data;
        }

        public void setData(List<DataBeans> data) {
            this.data = data;
        }

        public static class DataBeans {
            private String id;
            private String title;
            private String spec;
            private String logo;
            private String content;
            private String user_price;
            private String merchant_price;
            private String user_score;
            private String user_score_exchange;
            private String sales_score;
            private String sales_score_exchange;
            private String user_sales_num;
            private String create_time;
            private List<String> labels;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUser_price() {
                return user_price;
            }

            public void setUser_price(String user_price) {
                this.user_price = user_price;
            }

            public String getMerchant_price() {
                return merchant_price;
            }

            public void setMerchant_price(String merchant_price) {
                this.merchant_price = merchant_price;
            }

            public String getUser_score() {
                return user_score;
            }

            public void setUser_score(String user_score) {
                this.user_score = user_score;
            }

            public String getUser_score_exchange() {
                return user_score_exchange;
            }

            public void setUser_score_exchange(String user_score_exchange) {
                this.user_score_exchange = user_score_exchange;
            }

            public String getSales_score() {
                return sales_score;
            }

            public void setSales_score(String sales_score) {
                this.sales_score = sales_score;
            }

            public String getSales_score_exchange() {
                return sales_score_exchange;
            }

            public void setSales_score_exchange(String sales_score_exchange) {
                this.sales_score_exchange = sales_score_exchange;
            }

            public String getUser_sales_num() {
                return user_sales_num;
            }

            public void setUser_sales_num(String user_sales_num) {
                this.user_sales_num = user_sales_num;
            }

            public List<String> getLabels() {
                return labels;
            }

            public void setLabels(List<String> labels) {
                this.labels = labels;
            }
        }
    }
}
