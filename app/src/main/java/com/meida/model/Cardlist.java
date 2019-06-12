package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/4 0004
 * 公司  郑州软盟
 */

public class Cardlist {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":2,"per_page":100,"current_page":1,"last_page":1,"data":[{"id":32,"bank_id":66,"bank_name":"中国建设银行","sub_bank_name":"南山支行","card_name":"11112345","card_number":"1234567890123456789","card_logo":"","logo":"http://www.libing.com/upload/manager/20180822/114ae01b9f38cc2001393d44997dc22a.png"}]}
     */

    private String code;
    private String msg;
    /**
     * total : 2
     * per_page : 100
     * current_page : 1
     * last_page : 1
     * data : [{"id":32,"bank_id":66,"bank_name":"中国建设银行","sub_bank_name":"南山支行","card_name":"11112345","card_number":"1234567890123456789","card_logo":"","logo":"http://www.libing.com/upload/manager/20180822/114ae01b9f38cc2001393d44997dc22a.png"}]
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
         * id : 32
         * bank_id : 66
         * bank_name : 中国建设银行
         * sub_bank_name : 南山支行
         * card_name : 11112345
         * card_number : 1234567890123456789
         * card_logo :
         * logo : http://www.libing.com/upload/manager/20180822/114ae01b9f38cc2001393d44997dc22a.png
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
            private String bank_id;
            private String bank_name;
            private String sub_bank_name;
            private String card_name;
            private String card_number;
            private String card_logo;
            private String logo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBank_id() {
                return bank_id;
            }

            public void setBank_id(String bank_id) {
                this.bank_id = bank_id;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getSub_bank_name() {
                return sub_bank_name;
            }

            public void setSub_bank_name(String sub_bank_name) {
                this.sub_bank_name = sub_bank_name;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public String getCard_number() {
                return card_number;
            }

            public void setCard_number(String card_number) {
                this.card_number = card_number;
            }

            public String getCard_logo() {
                return card_logo;
            }

            public void setCard_logo(String card_logo) {
                this.card_logo = card_logo;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }
    }
}
