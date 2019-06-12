package com.meida.model;

import com.google.gson.annotations.SerializedName;

/**
 * 作者 亢兰兰
 * 时间 2018/9/14 0014
 * 公司  郑州软盟
 */

public class PayInfo {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"pay_type":"1","order_id":"201808301054339264510008","total_price":"20000","alipay":"app_id=2018011101781835&method=alipay.trade.app.pay&format=JSON&charset=utf-8&sign_type=RSA2&version=1.0&return_url=http%3A%2F%2Fwww.libing.com%2Fmobile%2Fnotify%2Falipay¬ify_url=http%3A%2F%2Fwww.libing.com%2Fmobile%2Fnotify%2Falipay×tamp=2018-08-30+10%3A54%3A33&sign=DXeYLovRxHo3sYphk1n3I1ddlYzF1nGbvpIH2q2w%2FOWq5TuQBsY5O9SrSVHm1pvCX9sGbFReA%2BG9HMoeL2DVx9v2cbyhSG6aMUMI6ONbVdMeKESnMmTBQLIDWgc6Fh%2Bnj%2FDy2wJuRkOWPokzoywqctlsOevZRgiqGk64Yg6c3GxkkiuDBbM7rMu92OJMWip2m7nxLgh1GOpx6MjuPYpol0HrKGHMYstKwtHn4FJLr89aQzLMDztytiDTTVvuHrfzhB3d9BQxd3d0dOxxtBmdTfaTVfdoIr4paX5d9BHjjASXyeGe7EG9duvzzS0UFsrj9%2B%2BRbsLWn%2BJpFwe2UgLAKg%3D%3D&biz_content=%7B%22out_trade_no%22%3A%22201808301054339264510008%22%2C%22total_amount%22%3A%2220000%22%2C%22subject%22%3A%22%5Cu7acb%5Cu51b0%5Cu8865%5Cu8d34%5Cu5546%5Cu57ce%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D","wechat":{"appid":"wx4e17b99e77e5c518","partnerid":"1497302072","prepayid":"wx30105435601749e37dd652d02004771000","timestamp":"1535597673","noncestr":"h8182BzVTrZWIJll","package":"Sign=WXPay","sign":"E68C7A671E521A92C2C5FEE29F7409C2"},"pay_status":0}
     */

    private String code;
    private String msg;
    /**
     * pay_type : 1
     * order_id : 201808301054339264510008
     * total_price : 20000
     * alipay : app_id=2018011101781835&method=alipay.trade.app.pay&format=JSON&charset=utf-8&sign_type=RSA2&version=1.0&return_url=http%3A%2F%2Fwww.libing.com%2Fmobile%2Fnotify%2Falipay¬ify_url=http%3A%2F%2Fwww.libing.com%2Fmobile%2Fnotify%2Falipay×tamp=2018-08-30+10%3A54%3A33&sign=DXeYLovRxHo3sYphk1n3I1ddlYzF1nGbvpIH2q2w%2FOWq5TuQBsY5O9SrSVHm1pvCX9sGbFReA%2BG9HMoeL2DVx9v2cbyhSG6aMUMI6ONbVdMeKESnMmTBQLIDWgc6Fh%2Bnj%2FDy2wJuRkOWPokzoywqctlsOevZRgiqGk64Yg6c3GxkkiuDBbM7rMu92OJMWip2m7nxLgh1GOpx6MjuPYpol0HrKGHMYstKwtHn4FJLr89aQzLMDztytiDTTVvuHrfzhB3d9BQxd3d0dOxxtBmdTfaTVfdoIr4paX5d9BHjjASXyeGe7EG9duvzzS0UFsrj9%2B%2BRbsLWn%2BJpFwe2UgLAKg%3D%3D&biz_content=%7B%22out_trade_no%22%3A%22201808301054339264510008%22%2C%22total_amount%22%3A%2220000%22%2C%22subject%22%3A%22%5Cu7acb%5Cu51b0%5Cu8865%5Cu8d34%5Cu5546%5Cu57ce%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D
     * wechat : {"appid":"wx4e17b99e77e5c518","partnerid":"1497302072","prepayid":"wx30105435601749e37dd652d02004771000","timestamp":"1535597673","noncestr":"h8182BzVTrZWIJll","package":"Sign=WXPay","sign":"E68C7A671E521A92C2C5FEE29F7409C2"}
     * pay_status : 0
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
        private String pay_type;
        private String order_id;
        private String total_price;
        private String alipay;
        /**
         * appid : wx4e17b99e77e5c518
         * partnerid : 1497302072
         * prepayid : wx30105435601749e37dd652d02004771000
         * timestamp : 1535597673
         * noncestr : h8182BzVTrZWIJll
         * package : Sign=WXPay
         * sign : E68C7A671E521A92C2C5FEE29F7409C2
         */

        private WechatBean wechat;
        private String pay_status;

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public WechatBean getWechat() {
            return wechat;
        }

        public void setWechat(WechatBean wechat) {
            this.wechat = wechat;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public static class WechatBean {
            private String appid;
            private String partnerid;
            private String prepayid;
            private String timestamp;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
