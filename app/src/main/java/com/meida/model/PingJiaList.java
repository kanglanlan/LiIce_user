package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/29 0029
 * 公司  郑州软盟
 */

public class PingJiaList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":2,"per_page":"10","current_page":1,"last_page":1,"data":[{"uid":10036,"content":"太可怜了","smeta":[],"create_time":"2018-09-21 18:11:52","nickname":"137****1041","logo":"http://libing.weiruanmeng.com/upload/user_logo/20180920/bc2692dc319e8fed4a70c3de818b10eb.png"},{"uid":10043,"content":"图听我","smeta":[{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/b4e46a263c68623ac4949ddc72f7a5a2.png","filename":"2ef7752a85892fb2.png"},{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/d997690bfbc57ce25f76c5fab8190434.png","filename":"171db74aca7da975.png"},{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/45e0c1e599fe07045d6b686e1c4d95d3.png","filename":"1e69fb14d75cae66.png"},{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/9a8fb63b75acb2e56e4512148ac7d0a2.png","filename":"e3728f2d873d9709.png"}],"create_time":"2018-09-21 15:38:05","nickname":"美达","logo":"http://libing.weiruanmeng.com/upload/user_logo/20180921/357acc3f44d16346953f2a4d57ff12c6.png"}]}
     */

    private String code;
    private String msg;
    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"uid":10036,"content":"太可怜了","smeta":[],"create_time":"2018-09-21 18:11:52","nickname":"137****1041","logo":"http://libing.weiruanmeng.com/upload/user_logo/20180920/bc2692dc319e8fed4a70c3de818b10eb.png"},{"uid":10043,"content":"图听我","smeta":[{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/b4e46a263c68623ac4949ddc72f7a5a2.png","filename":"2ef7752a85892fb2.png"},{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/d997690bfbc57ce25f76c5fab8190434.png","filename":"171db74aca7da975.png"},{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/45e0c1e599fe07045d6b686e1c4d95d3.png","filename":"1e69fb14d75cae66.png"},{"url":"http://libing.weiruanmeng.com/upload/user_logo/20180921/9a8fb63b75acb2e56e4512148ac7d0a2.png","filename":"e3728f2d873d9709.png"}],"create_time":"2018-09-21 15:38:05","nickname":"美达","logo":"http://libing.weiruanmeng.com/upload/user_logo/20180921/357acc3f44d16346953f2a4d57ff12c6.png"}]
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
         * uid : 10036
         * content : 太可怜了
         * smeta : []
         * create_time : 2018-09-21 18:11:52
         * nickname : 137****1041
         * logo : http://libing.weiruanmeng.com/upload/user_logo/20180920/bc2692dc319e8fed4a70c3de818b10eb.png
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
            private String uid;
            private String content;
            private String create_time;
            private String nickname;
            private String logo;
            private List<SmetaBean> smeta;


            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public List<SmetaBean> getSmeta() {
                return smeta;
            }

            public void setSmeta(List<SmetaBean> smeta) {
                this.smeta = smeta;
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
}
