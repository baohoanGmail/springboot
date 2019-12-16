package com.mafenwo.services.orders;

public enum MFWSysStatusEnum {
    MFW0(0,"success"),
    MFW500(500,"system error"),
    MFW700(700,"param invalid"),
    MFW800(800,"sign fail"),
    MFW900(900,"ip not allowed"),
    MFW1000(1000,"unknow error");

    private final Integer code;
    private final String text;
    
    MFWSysStatusEnum(Integer code,String text){
        this.code = code;
        this.text = text;
    }

    public static MFWSysStatusEnum fromCode(Integer code) {
        MFWSysStatusEnum [] values = MFWSysStatusEnum.values();
        for (MFWSysStatusEnum item : values) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return MFW500;
    }
    
    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
