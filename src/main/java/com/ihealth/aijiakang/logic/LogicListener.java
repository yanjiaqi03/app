package com.ihealth.aijiakang.logic;

/**
 * Created by lanbaoshi on 15/12/3.
 */
public interface LogicListener {
    <T> void onSuccess(T model);
    <T> void onFailure(T model);
}
