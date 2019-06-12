package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/31 0031
 * 公司  郑州软盟
 */

public class ShouCangList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":3,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":1,"record_id":1,"source_id":5,"logo":"http://www.libing.com/upload/manager/20180819/3e5e53d21b588e33d9fdd83cd6f9c927.png","title":"立冰冷暖、圆柱形柜式空调","content":"3匹 三级能效","user_price":"7899.00","labels":["3匹","定频"]}]}
     */

    private String code;
    private String msg;
    /**
     * total : 3
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":1,"record_id":1,"source_id":5,"logo":"http://www.libing.com/upload/manager/20180819/3e5e53d21b588e33d9fdd83cd6f9c927.png","title":"立冰冷暖、圆柱形柜式空调","content":"3匹 三级能效","user_price":"7899.00","labels":["3匹","定频"]}]
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
         * id : 1
         * record_id : 1
         * source_id : 5
         * logo : http://www.libing.com/upload/manager/20180819/3e5e53d21b588e33d9fdd83cd6f9c927.png
         * title : 立冰冷暖、圆柱形柜式空调
         * content : 3匹 三级能效
         * user_price : 7899.00
         * labels : ["3匹","定频"]
         */

        private List<DataBesan> data;

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

        public List<DataBesan> getData() {
            return data;
        }

        public void setData(List<DataBesan> data) {
            this.data = data;
        }

        public static class DataBesan {
            private String id;
            private String record_id;
            private String source_id;
            private String logo;
            private String title;
            private String content;
            private String user_price;
            private List<String> labels;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRecord_id() {
                return record_id;
            }

            public void setRecord_id(String record_id) {
                this.record_id = record_id;
            }

            public String getSource_id() {
                return source_id;
            }

            public void setSource_id(String source_id) {
                this.source_id = source_id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
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

            public String getUser_price() {
                return user_price;
            }

            public void setUser_price(String user_price) {
                this.user_price = user_price;
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
