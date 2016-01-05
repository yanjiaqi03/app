package com.ihealth.aijiakang.logic;

import java.util.List;

/**
 * Created by lanbaoshi on 15/11/24.
 */
public interface DatabaseLogicInterface {
    <T> boolean saveItem(T model);
    <T> boolean itemExist(T model);
    <T> boolean updateItem(T model);
    <T> boolean deleteItem(String selection);
    <T> boolean updateItem(String selection, String valueStr);
    boolean clearTable();
}
