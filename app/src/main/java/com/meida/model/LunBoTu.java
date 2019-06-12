package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/28 0028
 * 公司  郑州软盟
 */

public class LunBoTu {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"list":[{"id":7,"title":"","logo":"http://www.libing.com/upload/manager/20180818/9b1d5a15ca2c4fe6233acea2dec8f1e9.jpg","redirect_type":1,"redirect_value":"","detail":""}]}
     */

    private String code;
    private String msg;
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
        /**
         * id : 7
         * title :
         * logo : http://www.libing.com/upload/manager/20180818/9b1d5a15ca2c4fe6233acea2dec8f1e9.jpg
         * redirect_type : 1
         * redirect_value :
         * detail :
         *

         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String id;
            private String title;
            private String logo;
            private String redirect_type;
            private String redirect_value;
            private String detail;
            private String type;
            private String notice;
            private String uid;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getRedirect_type() {
                return redirect_type;
            }

            public void setRedirect_type(String redirect_type) {
                this.redirect_type = redirect_type;
            }

            public String getRedirect_value() {
                return redirect_value;
            }

            public void setRedirect_value(String redirect_value) {
                this.redirect_value = redirect_value;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }
        }
    }
}
