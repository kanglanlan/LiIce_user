package com.meida.model;

/**
 * Created by LFC
 * on 2018/8/17.
 */

public class TestDataM {
    private String str_name;
    private boolean isSelect = false;

    public TestDataM(String str_name, boolean isSelect) {
        this.str_name = str_name;
        this.isSelect = isSelect;
    }

    public String getStr_name() {
        return str_name == null ? "" : str_name;
    }

    public void setStr_name(String str_name) {
        this.str_name = str_name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
