package com.example.demo_1.constants;

public enum PartnerType {
    BANK(1),
    NBFC(2);

    private int partnerType;

    PartnerType(int partnerType){
        this.partnerType = partnerType;
    }

    public Integer getPartnerType(){
        return partnerType;
    }

    public static PartnerType fromPartnerType(int partnerType){
        switch (partnerType){
            case 1:
                return PartnerType.BANK;
            case 2:
                return PartnerType.NBFC;
            default:
                throw new IllegalArgumentException("partner type [" + partnerType
                        + "] not supported.");
        }
    }
}
