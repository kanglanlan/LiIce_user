package com.meida.liice;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/25 0025
 * 公司  郑州软盟
 */

public class InCome {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":1,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":10055,"logo":"","nickname":"136****6667","user_tel":"136****6667","create_time":"2018-09-25 15:58:56"}]}
     */

    private String code;
    private String msg;
    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":10055,"logo":"","nickname":"136****6667","user_tel":"136****6667","create_time":"2018-09-25 15:58:56"}]
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
         * id : 10055
         * logo :
         * nickname : 136****6667
         * user_tel : 136****6667
         * create_time : 2018-09-25 15:58:56
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
            private String logo;
            private String nickname;
            private String user_tel;
            private String create_time;
            private String score;

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
