package com.example.demo_1.constants;

public enum TenureType {

    DAYS(1),
    MONTHS(2),
    YEARS(3);

    private int type;

    TenureType(int type) {
        this.type = type;
    }

    public Integer getTenureType(){
        return type;
    }

    public static TenureType getTenureTypeFromValue(int type){
        switch (type){
            case 1:
                return TenureType.DAYS;
            case 2:
                return TenureType.MONTHS;
            case 3:
                return TenureType.YEARS;
            default:
                throw  new IllegalArgumentException("Tenure Type ["+ type + "] is not supported.");
        }
    }
}
