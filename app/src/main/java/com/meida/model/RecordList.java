package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/13 0013
 * 公司  郑州软盟
 */

public class RecordList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":6,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":7,"project_id":31,"type":1,"uid":10008,"admin_id":0,"status":5,"status_name":"已签订合同","content":"先电话简单联系一下，了解用户需求","smeta":[{"url":"http://www.libing.com/upload/portal/20180711/b5d184a4de62570085307c9fe6c4937a.png","filename":"360x360.png"}],"create_time":"2018-09-03 17:14:37","update_time":"2018-09-03 17:14:37"}]}
     */

    private String code;
    private String msg;
    /**
     * total : 6
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":7,"project_id":31,"type":1,"uid":10008,"admin_id":0,"status":5,"status_name":"已签订合同","content":"先电话简单联系一下，了解用户需求","smeta":[{"url":"http://www.libing.com/upload/portal/20180711/b5d184a4de62570085307c9fe6c4937a.png","filename":"360x360.png"}],"create_time":"2018-09-03 17:14:37","update_time":"2018-09-03 17:14:37"}]
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
         * id : 7
         * project_id : 31
         * type : 1
         * uid : 10008
         * admin_id : 0
         * status : 5
         * status_name : 已签订合同
         * content : 先电话简单联系一下，了解用户需求
         * smeta : [{"url":"http://www.libing.com/upload/portal/20180711/b5d184a4de62570085307c9fe6c4937a.png","filename":"360x360.png"}]
         * create_time : 2018-09-03 17:14:37
         * update_time : 2018-09-03 17:14:37
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
            private String project_id;
            private String type;
            private String uid;
            private String admin_id;
            private String status;
            private String status_name;
            private String content;
            private String create_time;
            private String update_time;
            /**
             * url : http://www.libing.com/upload/portal/20180711/b5d184a4de62570085307c9fe6c4937a.png
             * filename : 360x360.png
             */

            private List<SmetaBean> smeta;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProject_id() {
                return project_id;
            }

            public void setProject_id(String project_id) {
                this.project_id = project_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(String admin_id) {
                this.admin_id = admin_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
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

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public List<SmetaBean> getSmeta() {
                return smeta;
            }

            public void setSmeta(List<SmetaBean> smeta) {
                this.smeta = smeta;
            }

            public static class SmetaBean {
                private String url;
                private String filename;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getFilename() {
                    return filename;
                }

                public void setFilename(String filename) {
                    this.filename = filename;
                }
            }
        }
    }
}
