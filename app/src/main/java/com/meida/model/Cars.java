package com.meida.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/29 0029
 * 公司  郑州软盟
 */

public class Cars implements Serializable{


    /**
     * code : 1
     * msg : 操作成功
     * data : {"list":[{"id":19,"uid":12,"product_id":7,"product_num":1,"price":"4899.00","logo":"http://www.libing.com/upload/manager/20180818/7f861a1f787abf320266fcc76a2cfd6c.jpg","title":"立冰变频 冷暖挂机 一级能效空调","content":"纳料活性碳净化：除甲醛、除尼古丁、除饭菜味，为室内空间打造健康空气环境","spec":"1.5匹、变频、一级能效","user_score":"1600.00"}],"count":2}
     */

    private String code;
    private String msg;
    /**
     * list : [{"id":19,"uid":12,"product_id":7,"product_num":1,"price":"4899.00","logo":"http://www.libing.com/upload/manager/20180818/7f861a1f787abf320266fcc76a2cfd6c.jpg","title":"立冰变频 冷暖挂机 一级能效空调","content":"纳料活性碳净化：除甲醛、除尼古丁、除饭菜味，为室内空间打造健康空气环境","spec":"1.5匹、变频、一级能效","user_score":"1600.00"}]
     * count : 2
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

    public static class DataBean implements Serializable{
        private String count;
        /**
         * id : 19
         * uid : 12
         * product_id : 7
         * product_num : 1
         * price : 4899.00
         * logo : http://www.libing.com/upload/manager/20180818/7f861a1f787abf320266fcc76a2cfd6c.jpg
         * title : 立冰变频 冷暖挂机 一级能效空调
         * content : 纳料活性碳净化：除甲醛、除尼古丁、除饭菜味，为室内空间打造健康空气环境
         * spec : 1.5匹、变频、一级能效
         * user_score : 1600.00
         */

        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            private String id;
            private String uid;
            private String product_id;
            private String product_num;
            private String price;
            private String logo;
            private String title;
            private String content;
            private String spec;
            private String user_score;
            private int check;

            public int getCheck() {
                return check;
            }

            public void setCheck(int check) {
                this.check = check;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getProduct_num() {
                return product_num;
            }

            public void setProduct_num(String product_num) {
                this.product_num = product_num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getUser_score() {
                return user_score;
            }

            public void setUser_score(String user_score) {
                this.user_score = user_score;
            }
        }
    }
}
