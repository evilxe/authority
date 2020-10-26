package com.fzw.authority.enums.systems;

public enum MenuTypeEnums {

    CATALOGUE("CATALOGUE","目录"),
    MENUS("MENUS","菜单"),
    BUTTON("BUTTON", "按钮"),
    ;

    private String code;

    private String desc;

    MenuTypeEnums(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

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
