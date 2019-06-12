package com.meida.model;

/**
 * 作者 亢兰兰
 * 时间 2018/9/5 0005
 * 公司  郑州软盟
 */

public class ZhengJiFE {
    private String name;
    private int re;
    private int check;
    private String jifen;

    public ZhengJiFE(String name, int re, int check, String jifen) {
        this.name = name;
        this.re = re;
        this.check = check;
        this.jifen = jifen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRe() {
        return re;
    }

    public void setRe(int re) {
        this.re = re;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }
}
