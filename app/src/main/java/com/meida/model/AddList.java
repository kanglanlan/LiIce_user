package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/30 0030
 * 公司  郑州软盟
 */

public class AddList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":3,"per_page":100,"current_page":1,"last_page":1,"data":[{"id":18,"address_name":"王斐然","address_tel":"13783451041","address":"南山区呀","province_id":440000,"city_id":440300,"area_id":440305,"area_merger_name":"广东省,深圳市,南山区","is_default":2}]}
     */

    private String code;
    private String msg;
    /**
     * total : 3
     * per_page : 100
     * current_page : 1
     * last_page : 1
     * data : [{"id":18,"address_name":"王斐然","address_tel":"13783451041","address":"南山区呀","province_id":440000,"city_id":440300,"area_id":440305,"area_merger_name":"广东省,深圳市,南山区","is_default":2}]
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
         * id : 18
         * address_name : 王斐然
         * address_tel : 13783451041
         * address : 南山区呀
         * province_id : 440000
         * city_id : 440300
         * area_id : 440305
         * area_merger_name : 广东省,深圳市,南山区
         * is_default : 2
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
            private String address_name;
            private String address_tel;
            private String address;
            private String province_id;
            private String city_id;
            private String area_id;
            private String area_merger_name;
            private String is_default;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddress_name() {
                return address_name;
            }

            public void setAddress_name(String address_name) {
                this.address_name = address_name;
            }

            public String getAddress_tel() {
                return address_tel;
            }

            public void setAddress_tel(String address_tel) {
                this.address_tel = address_tel;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getArea_merger_name() {
                return area_merger_name;
            }

            public void setArea_merger_name(String area_merger_name) {
                this.area_merger_name = area_merger_name;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }
        }
    }
}
