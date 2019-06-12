package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/29 0029
 * 公司  郑州软盟
 */

public class GoodsInfo {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"id":5,"title":"1.5匹 一级变频","spec":"1.5匹 一级变频","logo":"http://www.libing.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg","smeta":[{"url":"http://www.libing.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg","name":"167.jpg"}],"content":"1.5匹 一级变频","detail":"<!DOCTYPE html><html><head><meta charset=\"utf-8\"><base href=\"http://www.libing.com\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"><style>*{max-width: 100% !important; word-break: break-all; word-wrap: break-word;-webkit-user-select: none;user-select: none;}body{width: 100% !important; margin: 0px !important;  padding: 0px !important; background: #fff;font-family: \"Helvetica Neue\",\"微软雅黑\", Helvetica, Arial, sans-serif;}#half_top{width: 94%; border-bottom: 0.0425rem solid #d8d8d8; margin: 10px auto 0px auto; padding: 10px 0px;}#half_top #half_title{font-size: 1.5em; text-align: left;}#half_subtitle{color: #999; padding-top: 10px; font-size:0.8em;}#half_subtitle .half_sub{padding-right: 10px;}#half_content img{max-width: 100% !important; display: block; height: auto !important;}p{-webkit-margin-before: 0.5em;-webkit-margin-after: 0.5em;}iframe{height: 255px !important;}#half_content{padding: 0 3% 10px 3%;}#half_content{font-size: 0.9em !important; line-height: 1.6em !important; color: #666 !important;}<\/style><\/head><body oncontextmenu=\"return false\" onselectstart=\"return false\" oncopy=\"return false\"><div id=\"half_content\"><p>1.5匹 一级变频<\/p><\/div><\/body><\/html>","user_price":"4699.00","user_score":"1600.00","user_score_exchange":"200.00","sales_score":"0.00","sales_score_exchange":"0.00","user_sales_num":0,"labels":"60,53,","label_title":["3匹","定频"],"status":0}
     */

    private String code;
    private String msg;
    /**
     * id : 5
     * title : 1.5匹 一级变频
     * spec : 1.5匹 一级变频
     * logo : http://www.libing.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg
     * smeta : [{"url":"http://www.libing.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg","name":"167.jpg"}]
     * content : 1.5匹 一级变频
     * detail : <!DOCTYPE html><html><head><meta charset="utf-8"><base href="http://www.libing.com" /><meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"><style>*{max-width: 100% !important; word-break: break-all; word-wrap: break-word;-webkit-user-select: none;user-select: none;}body{width: 100% !important; margin: 0px !important;  padding: 0px !important; background: #fff;font-family: "Helvetica Neue","微软雅黑", Helvetica, Arial, sans-serif;}#half_top{width: 94%; border-bottom: 0.0425rem solid #d8d8d8; margin: 10px auto 0px auto; padding: 10px 0px;}#half_top #half_title{font-size: 1.5em; text-align: left;}#half_subtitle{color: #999; padding-top: 10px; font-size:0.8em;}#half_subtitle .half_sub{padding-right: 10px;}#half_content img{max-width: 100% !important; display: block; height: auto !important;}p{-webkit-margin-before: 0.5em;-webkit-margin-after: 0.5em;}iframe{height: 255px !important;}#half_content{padding: 0 3% 10px 3%;}#half_content{font-size: 0.9em !important; line-height: 1.6em !important; color: #666 !important;}</style></head><body oncontextmenu="return false" onselectstart="return false" oncopy="return false"><div id="half_content"><p>1.5匹 一级变频</p></div></body></html>
     * user_price : 4699.00
     * user_score : 1600.00
     * user_score_exchange : 200.00
     * sales_score : 0.00
     * sales_score_exchange : 0.00
     * user_sales_num : 0
     * labels : 60,53,
     * label_title : ["3匹","定频"]
     * status : 0
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
        private String title;
        private String spec;
        private String logo;
        private String content;
        private String detail;
        private String user_price;
        private String user_score;
        private String user_score_exchange;
        private String sales_score;
        private String sales_score_exchange;
        private String user_sales_num;
        private String labels;
        private String status;
        private String question;
        /**
         * url : http://www.libing.com/upload/manager/20180724/b37c6a042d67b7ed7019d8ea5945c20e.jpg
         * name : 167.jpg
         */

        private List<SmetaBean> smeta;
        private List<String> label_title;




        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
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

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUser_price() {
            return user_price;
        }

        public void setUser_price(String user_price) {
            this.user_price = user_price;
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

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<SmetaBean> getSmeta() {
            return smeta;
        }

        public void setSmeta(List<SmetaBean> smeta) {
            this.smeta = smeta;
        }

        public List<String> getLabel_title() {
            return label_title;
        }

        public void setLabel_title(List<String> label_title) {
            this.label_title = label_title;
        }

        public static class SmetaBean {
            private String url;
            private String name;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
