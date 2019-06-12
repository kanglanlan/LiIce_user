package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/3 0003
 * 公司  郑州软盟
 */

public class FuWuShangList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":1,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":10008,"merchant_type":1,"nickname":"立冰联合服务","company_name":"小强专业安装有限公司","area_merger_name":" 广东省,深圳市,罗湖区","company_address":"小强办事处","merchant_service_status":1,"stock_status":0}]}
     */

    private String code;
    private String msg;
    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":10008,"merchant_type":1,"nickname":"立冰联合服务","company_name":"小强专业安装有限公司","area_merger_name":" 广东省,深圳市,罗湖区","company_address":"小强办事处","merchant_service_status":1,"stock_status":0}]
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
         * id : 10008
         * merchant_type : 1
         * nickname : 立冰联合服务
         * company_name : 小强专业安装有限公司
         * area_merger_name :  广东省,深圳市,罗湖区
         * company_address : 小强办事处
         * merchant_service_status : 1
         * stock_status : 0
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
            private String merchant_type;
            private String nickname;
            private String company_name;
            private String area_merger_name;
            private String company_address;
            private String merchant_service_status;
            private String stock_status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMerchant_type() {
                return merchant_type;
            }

            public void setMerchant_type(String merchant_type) {
                this.merchant_type = merchant_type;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getArea_merger_name() {
                return area_merger_name;
            }

            public void setArea_merger_name(String area_merger_name) {
                this.area_merger_name = area_merger_name;
            }

            public String getCompany_address() {
                return company_address;
            }

            public void setCompany_address(String company_address) {
                this.company_address = company_address;
            }

            public String getMerchant_service_status() {
                return merchant_service_status;
            }

            public void setMerchant_service_status(String merchant_service_status) {
                this.merchant_service_status = merchant_service_status;
            }

            public String getStock_status() {
                return stock_status;
            }

            public void setStock_status(String stock_status) {
                this.stock_status = stock_status;
            }
        }
    }
}
