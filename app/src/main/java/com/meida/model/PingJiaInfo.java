package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/5 0005
 * 公司  郑州软盟
 */

public class PingJiaInfo {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"id":2,"level_manner":4,"level_speed":4,"level_express":4,"content":"效率很高","smeta":[{"url":"http://www.libing.com/upload/a.png"}],"create_time":"2018-08-28 17:13:30"}
     */

    private String code;
    private String msg;
    /**
     * id : 2
     * level_manner : 4
     * level_speed : 4
     * level_express : 4
     * content : 效率很高
     * smeta : [{"url":"http://www.libing.com/upload/a.png"}]
     * create_time : 2018-08-28 17:13:30
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
        private String level_manner;
        private String level_speed;
        private String level_express;
        private String content;
        private String create_time;
        /**
         * url : http://www.libing.com/upload/a.png
         */

        private List<SmetaBean> smeta;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevel_manner() {
            return level_manner;
        }

        public void setLevel_manner(String level_manner) {
            this.level_manner = level_manner;
        }

        public String getLevel_speed() {
            return level_speed;
        }

        public void setLevel_speed(String level_speed) {
            this.level_speed = level_speed;
        }

        public String getLevel_express() {
            return level_express;
        }

        public void setLevel_express(String level_express) {
            this.level_express = level_express;
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

        public List<SmetaBean> getSmeta() {
            return smeta;
        }

        public void setSmeta(List<SmetaBean> smeta) {
            this.smeta = smeta;
        }

        public static class SmetaBean {
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
