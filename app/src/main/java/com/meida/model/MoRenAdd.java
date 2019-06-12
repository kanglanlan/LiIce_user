package com.meida.model;

/**
 * 作者 亢兰兰
 * 时间 2018/8/30 0030
 * 公司  郑州软盟
 */

public class MoRenAdd {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"id":32,"address_name":"王斐然","address_tel":"13780001234","address":"立冰科技","province_id":440000,"city_id":440300,"area_id":440303,"area_merger_name":" 广东省,深圳市,罗湖区"}
     */

    private String code;
    private String msg;
    /**
     * id : 32
     * address_name : 王斐然
     * address_tel : 13780001234
     * address : 立冰科技
     * province_id : 440000
     * city_id : 440300
     * area_id : 440303
     * area_merger_name :  广东省,深圳市,罗湖区
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
        private String address_name;
        private String address_tel;
        private String address;
        private String province_id;
        private String city_id;
        private String area_id;
        private String area_merger_name;

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
    }
}
