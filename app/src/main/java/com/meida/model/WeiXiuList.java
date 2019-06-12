package com.meida.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/9/3 0003
 * 公司  郑州软盟
 */

public class WeiXiuList implements Serializable{


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":5,"per_page":"10","current_page":1,"last_page":1,"data":[{"id":9,"uid":12,"merchant_id":10008,"order_id":"201808241412059187112","product_id":6,"address_name":"刘强","address_tel":"13780001234","province_id":440000,"city_id":440300,"area_id":440309,"area_merger_name":" 广东省,深圳市,光明新区","address":"水库路8号立冰节能科技有限公司","address_service_time":"2018-10-23 15:30","product_info":{"id":95,"uid":12,"merchant_id":2,"order_id":"201808241412059187112","product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"create_time":"2018-08-24 14:12:05","update_time":"2018-08-24 14:12:05","comment_status":1},"user_smeta":[{"url":"http://www.libing.com/upload/a.png"}],"user_content":"不制冷也不制热","status":3,"create_time":"2018-08-27 20:02:20","update_time":"2018-08-31 14:52:03","confirm_time":"2018-08-31 14:52:03","finish_time":"0000-00-00 00:00:00","merchant_content":"已维修完成，恢复正常状态","merchant_smeta":[{"url":"http://www.libing.com/upload/user_upload/20180828/8729f54ec76f3f3bd435b25df626b958.png","filename":"QQ图片20180823142236.png"}],"comment_status":1}]}
     */

    private String code;
    private String msg;
    /**
     * total : 5
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"id":9,"uid":12,"merchant_id":10008,"order_id":"201808241412059187112","product_id":6,"address_name":"刘强","address_tel":"13780001234","province_id":440000,"city_id":440300,"area_id":440309,"area_merger_name":" 广东省,深圳市,光明新区","address":"水库路8号立冰节能科技有限公司","address_service_time":"2018-10-23 15:30","product_info":{"id":95,"uid":12,"merchant_id":2,"order_id":"201808241412059187112","product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"create_time":"2018-08-24 14:12:05","update_time":"2018-08-24 14:12:05","comment_status":1},"user_smeta":[{"url":"http://www.libing.com/upload/a.png"}],"user_content":"不制冷也不制热","status":3,"create_time":"2018-08-27 20:02:20","update_time":"2018-08-31 14:52:03","confirm_time":"2018-08-31 14:52:03","finish_time":"0000-00-00 00:00:00","merchant_content":"已维修完成，恢复正常状态","merchant_smeta":[{"url":"http://www.libing.com/upload/user_upload/20180828/8729f54ec76f3f3bd435b25df626b958.png","filename":"QQ图片20180823142236.png"}],"comment_status":1}]
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
        private String total;
        private String per_page;
        private String current_page;
        private String last_page;
        /**
         * id : 9
         * uid : 12
         * merchant_id : 10008
         * order_id : 201808241412059187112
         * product_id : 6
         * address_name : 刘强
         * address_tel : 13780001234
         * province_id : 440000
         * city_id : 440300
         * area_id : 440309
         * area_merger_name :  广东省,深圳市,光明新区
         * address : 水库路8号立冰节能科技有限公司
         * address_service_time : 2018-10-23 15:30
         * product_info : {"id":95,"uid":12,"merchant_id":2,"order_id":"201808241412059187112","product_id":6,"product_name":"立冰 2匹 热泵采暖空调 ","product_logo":"http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png","product_spec":"2匹，一级能效，","product_price":"11798.00","product_num":2,"create_time":"2018-08-24 14:12:05","update_time":"2018-08-24 14:12:05","comment_status":1}
         * user_smeta : [{"url":"http://www.libing.com/upload/a.png"}]
         * user_content : 不制冷也不制热
         * status : 3
         * create_time : 2018-08-27 20:02:20
         * update_time : 2018-08-31 14:52:03
         * confirm_time : 2018-08-31 14:52:03
         * finish_time : 0000-00-00 00:00:00
         * merchant_content : 已维修完成，恢复正常状态
         * merchant_smeta : [{"url":"http://www.libing.com/upload/user_upload/20180828/8729f54ec76f3f3bd435b25df626b958.png","filename":"QQ图片20180823142236.png"}]
         * comment_status : 1
         */

        private List<DataBsean> data;

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

        public List<DataBsean> getData() {
            return data;
        }

        public void setData(List<DataBsean> data) {
            this.data = data;
        }

        public static class DataBsean implements Serializable {
            private String id;
            private String uid;
            private String merchant_id;
            private String order_id;
            private String product_id;
            private String address_name;
            private String address_tel;
            private String province_id;
            private String city_id;
            private String area_id;
            private String area_merger_name;
            private String address;
            private String address_service_time;
            /**
             * id : 95
             * uid : 12
             * merchant_id : 2
             * order_id : 201808241412059187112
             * product_id : 6
             * product_name : 立冰 2匹 热泵采暖空调
             * product_logo : http://www.libing.com/upload/manager/20180819/737935caf83c0978d04689821e296aa2.png
             * product_spec : 2匹，一级能效，
             * product_price : 11798.00
             * product_num : 2
             * create_time : 2018-08-24 14:12:05
             * update_time : 2018-08-24 14:12:05
             * comment_status : 1
             */

            private ProductInfoBean product_info;
            private String user_content;
            private String status;
            private String create_time;
            private String update_time;
            private String confirm_time;
            private String finish_time;
            private String merchant_content;
            private String comment_status;
            private String transaction_number;
            private Merchant_info merchant_info;
            /**
             * url : http://www.libing.com/upload/a.png
             */

            private List<UserSmetaBean> user_smeta;
            /**
             * url : http://www.libing.com/upload/user_upload/20180828/8729f54ec76f3f3bd435b25df626b958.png
             * filename : QQ图片20180823142236.png
             */

            private List<MerchantSmetaBean> merchant_smeta;

            public String getTransaction_number() {
                return transaction_number;
            }

            public void setTransaction_number(String transaction_number) {
                this.transaction_number = transaction_number;
            }

            public Merchant_info getMerchant_info() {
                return merchant_info;
            }

            public void setMerchant_info(Merchant_info merchant_info) {
                this.merchant_info = merchant_info;
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

            public String getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(String merchant_id) {
                this.merchant_id = merchant_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddress_service_time() {
                return address_service_time;
            }

            public void setAddress_service_time(String address_service_time) {
                this.address_service_time = address_service_time;
            }

            public ProductInfoBean getProduct_info() {
                return product_info;
            }

            public void setProduct_info(ProductInfoBean product_info) {
                this.product_info = product_info;
            }

            public String getUser_content() {
                return user_content;
            }

            public void setUser_content(String user_content) {
                this.user_content = user_content;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getConfirm_time() {
                return confirm_time;
            }

            public void setConfirm_time(String confirm_time) {
                this.confirm_time = confirm_time;
            }

            public String getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(String finish_time) {
                this.finish_time = finish_time;
            }

            public String getMerchant_content() {
                return merchant_content;
            }

            public void setMerchant_content(String merchant_content) {
                this.merchant_content = merchant_content;
            }

            public String getComment_status() {
                return comment_status;
            }

            public void setComment_status(String comment_status) {
                this.comment_status = comment_status;
            }

            public List<UserSmetaBean> getUser_smeta() {
                return user_smeta;
            }

            public void setUser_smeta(List<UserSmetaBean> user_smeta) {
                this.user_smeta = user_smeta;
            }

            public List<MerchantSmetaBean> getMerchant_smeta() {
                return merchant_smeta;
            }

            public void setMerchant_smeta(List<MerchantSmetaBean> merchant_smeta) {
                this.merchant_smeta = merchant_smeta;
            }

            public static class ProductInfoBean implements Serializable{
                private String id;
                private String uid;
                private String merchant_id;
                private String order_id;
                private String product_id;
                private String product_name;
                private String product_logo;
                private String product_spec;
                private String product_price;
                private String product_num;
                private String create_time;
                private String update_time;
                private String comment_status;

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

                public String getMerchant_id() {
                    return merchant_id;
                }

                public void setMerchant_id(String merchant_id) {
                    this.merchant_id = merchant_id;
                }

                public String getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(String order_id) {
                    this.order_id = order_id;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public String getProduct_logo() {
                    return product_logo;
                }

                public void setProduct_logo(String product_logo) {
                    this.product_logo = product_logo;
                }

                public String getProduct_spec() {
                    return product_spec;
                }

                public void setProduct_spec(String product_spec) {
                    this.product_spec = product_spec;
                }

                public String getProduct_price() {
                    return product_price;
                }

                public void setProduct_price(String product_price) {
                    this.product_price = product_price;
                }

                public String getProduct_num() {
                    return product_num;
                }

                public void setProduct_num(String product_num) {
                    this.product_num = product_num;
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

                public String getComment_status() {
                    return comment_status;
                }

                public void setComment_status(String comment_status) {
                    this.comment_status = comment_status;
                }
            }

            public static class UserSmetaBean implements Serializable{
                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class MerchantSmetaBean implements Serializable{
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
            public static class Merchant_info implements Serializable {
                private String id;
                private String merchant_type;
                private String nickname;
                private String user_tel;
                private String company_name;
                private String area_merger_name;
                private String company_address;

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

                public String getUser_tel() {
                    return user_tel;
                }

                public void setUser_tel(String user_tel) {
                    this.user_tel = user_tel;
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
            }
        }
    }
}
