package com.ihealth.aijiakang.utils;

import android.os.Environment;

/**
 * Created by YanJiaqi on 15/11/26
 */
public class VariableUtils {
    //路径
    public final static String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/iHealthAiJiaKang/";

    /** 广播字段 **/

    //我的 更新信息广播
    public final static String HOST_ME_UPDATE_USER = "HOST_ME_UPDATE_USER";
    //家人管理 更新广播
    public final static String RELATIONSHIP_UPDATE_FAMILY = "RELATIONSHIP_UPDATE_FAMILY";

    /** 广播字段 **/

}
