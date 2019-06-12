package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/6 0006
 * 公司  郑州软盟
 */

public class ServiceCity {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":5,"per_page":500,"current_page":1,"last_page":1,"data":[{"id":440304,"name":"福田区","short_name":"福田","merger_name":" 广东省,深圳市,福田区","pinyin":"Futian"}]}
     */

    private String code;
    private String msg;
    /**
     * total : 5
     * per_page : 500
     * current_page : 1
     * last_page : 1
     * data : [{"id":440304,"name":"福田区","short_name":"福田","merger_name":" 广东省,深圳市,福田区","pinyin":"Futian"}]
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
         * id : 440304
         * name : 福田区
         * short_name : 福田
         * merger_name :  广东省,深圳市,福田区
         * pinyin : Futian
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
            private String name;
            private String short_name;
            private String merger_name;
            private String pinyin;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public String getMerger_name() {
                return merger_name;
            }

            public void setMerger_name(String merger_name) {
                this.merger_name = merger_name;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }
        }
    }
}
