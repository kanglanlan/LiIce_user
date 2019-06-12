package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/1 0001
 * 公司  郑州软盟
 */

public class JiFenList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":1,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":68,"score_type":2,"type":103,"score":"200.00","transaction_number":"2018090110253994","create_time":"2018-09-01 16:30:55","score_type_name":"可兑换","type_name":"兑换商品"}]}
     */

    private String code;
    private String msg;
    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":68,"score_type":2,"type":103,"score":"200.00","transaction_number":"2018090110253994","create_time":"2018-09-01 16:30:55","score_type_name":"可兑换","type_name":"兑换商品"}]
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
        private int last_page;
        /**
         * id : 68
         * score_type : 2
         * type : 103
         * score : 200.00
         * transaction_number : 2018090110253994
         * create_time : 2018-09-01 16:30:55
         * score_type_name : 可兑换
         * type_name : 兑换商品
         */

        private List<DataBsean> data;

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

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBsean> getData() {
            return data;
        }

        public void setData(List<DataBsean> data) {
            this.data = data;
        }

        public static class DataBsean {
            private String id;
            private String score_type;
            private String type;
            private String score;
            private String transaction_number;
            private String create_time;
            private String score_type_name;
            private String type_name;
            private String current_score;

            public String getCurrent_score() {
                return current_score;
            }

            public void setCurrent_score(String current_score) {
                this.current_score = current_score;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getScore_type() {
                return score_type;
            }

            public void setScore_type(String score_type) {
                this.score_type = score_type;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getTransaction_number() {
                return transaction_number;
            }

            public void setTransaction_number(String transaction_number) {
                this.transaction_number = transaction_number;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getScore_type_name() {
                return score_type_name;
            }

            public void setScore_type_name(String score_type_name) {
                this.score_type_name = score_type_name;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }
        }
    }
}
