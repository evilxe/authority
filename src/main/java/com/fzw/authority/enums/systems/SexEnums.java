package com.fzw.authority.enums.systems;

public enum SexEnums {

    M("M", "男"),
    W("W", "女"),
    ;

    SexEnums(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private  String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
