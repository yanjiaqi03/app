package com.ihealth.aijiakang.utils;

import java.math.BigDecimal;

/**
 * Created by YanJiaqi on 15/12/9
 */
public class BpUtils {
    private static BpUtils mInstance = null;

    public static BpUtils getInstance(){
        if(mInstance == null){
            mInstance = new BpUtils();
        }

        return mInstance;
    }

    /**
     * mmhg 转换 kpa
     * Author YanJiaqi
     * Created at 15/12/9 下午7:21
     */

    public float mmghToKpa(float i) {
        // kpa*7.5=mmhg
        float f = (float) (i / 7.5);
        BigDecimal b = new BigDecimal(f);
        float f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    /**
     * 获取血压等级
     * @param sys 高压
     * @param dia 低压
     * Author YanJiaqi
     * Created at 15/12/9 下午7:24
     */

    public int getBpLevel(int sys,int dia ) {
        int UnitDIA = 1;
        if (dia < 80)
            UnitDIA = 6;
        else if (dia < 85 && dia >= 80)
            UnitDIA = 5;
        else if (dia < 90 && dia >= 85)
            UnitDIA = 4;
        else if (dia < 100 && dia >= 90)
            UnitDIA = 3;
        else if (dia < 110 && dia >= 100)
            UnitDIA = 2;
        else if (dia >= 110)
            UnitDIA = 1;

        int UnitSYS = 1;
        if (sys < 120)
            UnitSYS = 6;
        else if (sys < 130 && sys >= 120)
            UnitSYS = 5;
        else if (sys < 140 && sys >= 130)
            UnitSYS = 4;
        else if (sys < 160 && sys >= 140)
            UnitSYS = 3;
        else if (sys < 180 && sys >= 160)
            UnitSYS = 2;
        else if (sys >= 180)
            UnitSYS = 1;

        int UnitWHO = 1;
        if (UnitDIA < UnitSYS)
            UnitWHO = UnitDIA;
        else
            UnitWHO = UnitSYS;
        return UnitWHO;
    }
}
