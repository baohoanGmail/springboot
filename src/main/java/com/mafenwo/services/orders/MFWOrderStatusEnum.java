package com.mafenwo.services.orders;

public enum MFWOrderStatusEnum {
    MFW1000(1000,"booking"),
    MFW1001(1001,"confirmed"),
    MFW1002(1002,"cancelled"),
    MFW1003(1003,"booking fails"),
    MFW1004(1004,"unconfirm"),
    MFW1005(1005,"stock empty"),
    MFW1006(1006,"hotel empty"),
    MFW1007(1007,"order not exists"),
    MFW1008(1008,"cancelling"),
    MFW1009(1009,"cancel fails");

    private final Integer code;
    private final String text;
    
    MFWOrderStatusEnum(Integer code,String text){
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
