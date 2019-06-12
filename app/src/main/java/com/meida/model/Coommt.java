package com.meida.model;

/**
 * 作者 亢兰兰
 * 时间 2018/8/28 0028
 * 公司  郑州软盟
 */

public class Coommt {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"timestamp":1535074741}
     */

    private String code;
    private String msg;
    /**
     * timestamp : 1535074741
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
        private String timestamp;
        private String cart_count;
        private String logo;
        private String detail;
        private String status;
        private String cart_num;
        private String num0;
        private String num1;
        private String num2;
        private String logo_status;

        private String is_force;
        private String type;
        private String device_type;
        private int version_code;
        private String version_number;
        private String url;
        private String file_size;
        private String content;
        private String create_time;
        private String update_time;
        private String user_real_name;
        private String nickname;
        private String card_name;
        private String card_number;
        private String total_num;
        private String smeta_num;
        private String num3;

        public String getNum3() {
            return num3;
        }

        public void setNum3(String num3) {
            this.num3 = num3;
        }

        public String getTotal_num() {
            return total_num;
        }

        public void setTotal_num(String total_num) {
            this.total_num = total_num;
        }

        public String getSmeta_num() {
            return smeta_num;
        }

        public void setSmeta_num(String smeta_num) {
            this.smeta_num = smeta_num;
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

        public String getUser_real_name() {
            return user_real_name;
        }

        public void setUser_real_name(String user_real_name) {
            this.user_real_name = user_real_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getIs_force() {
            return is_force;
        }

        public void setIs_force(String is_force) {
            this.is_force = is_force;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }

        public String getVersion_number() {
            return version_number;
        }

        public void setVersion_number(String version_number) {
            this.version_number = version_number;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
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

        public String getLogo_status() {
            return logo_status;
        }

        public void setLogo_status(String logo_status) {
            this.logo_status = logo_status;
        }

        public String getNum0() {
            return num0;
        }

        public void setNum0(String num0) {
            this.num0 = num0;
        }

        public String getNum1() {
            return num1;
        }

        public void setNum1(String num1) {
            this.num1 = num1;
        }

        public String getNum2() {
            return num2;
        }

        public void setNum2(String num2) {
            this.num2 = num2;
        }

        public String getCart_num() {
            return cart_num;
        }

        public void setCart_num(String cart_num) {
            this.cart_num = cart_num;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCart_count() {
            return cart_count;
        }

        public void setCart_count(String cart_count) {
            this.cart_count = cart_count;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
