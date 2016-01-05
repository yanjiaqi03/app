package com.ihealth.aijiakang.logic;

/**
 * Created by lanbaoshi on 15/11/24.
 */
public interface LogicCallbackInterface {
    void onSuccess(String result);
    void onFailure(String errorNum, String content);
}
