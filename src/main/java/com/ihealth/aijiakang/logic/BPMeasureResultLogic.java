package com.ihealth.aijiakang.logic;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import com.ihealth.aijiakang.database.data.Data_BPMeasureResult;
import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.database.table.Data_TB_BPMeasureResult;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelBP;
import java.util.ArrayList;

/**
 * Created by lanbaoshi on 15/11/30.
 */
public class BPMeasureResultLogic {
    private Context mContext;
    private Data_TB_BPMeasureResult data_tb_bpMeasureResult;
    private Data_BPMeasureResult data_bpMeasureResult;
    private final String TAG = "UserInfoLogic";

    public BPMeasureResultLogic(Context context) {
        this.mContext = context;
        data_tb_bpMeasureResult = new Data_TB_BPMeasureResult(context);
    }
    public <T> boolean saveItem(T model) {
        if(model instanceof OutputModelBP) {
            OutputModelBP outputModelBP = (OutputModelBP)model;
            data_bpMeasureResult = new Data_BPMeasureResult();
            data_bpMeasureResult.setBpActivity(outputModelBP.getBpActivity());
            data_bpMeasureResult.setBpDataID(outputModelBP.getBpDataID());
            data_bpMeasureResult.setBpLevel(outputModelBP.getBpLevel());
            data_bpMeasureResult.setBpmDeviceID(outputModelBP.getBpmDeviceID());
            data_bpMeasureResult.setBpMeasureDate(outputModelBP.getBpMeasureDate());
            data_bpMeasureResult.setBpMeasureID(outputModelBP.getBpMeasureID());
            data_bpMeasureResult.setBpMood(outputModelBP.getBpMood());
            data_bpMeasureResult.setBpNote(outputModelBP.getBpNote());
            data_bpMeasureResult.setCare_Json(outputModelBP.getCare_Json());
            data_bpMeasureResult.setChangeType(outputModelBP.getChangeType());
            data_bpMeasureResult.setContent_Json(outputModelBP.getContent_Json());
            data_bpMeasureResult.setDataCreatTime(outputModelBP.getDataCreatTime());
            data_bpMeasureResult.setDeviceType(outputModelBP.getDeviceType());
            data_bpMeasureResult.setDia(outputModelBP.getDia());
            data_bpMeasureResult.setHumidity(outputModelBP.getHumidity());
            data_bpMeasureResult.setiHealthCloud(outputModelBP.getiHealthCloud());
            data_bpMeasureResult.setIsDisplay(outputModelBP.getIsDisplay());
            data_bpMeasureResult.setIsIHB(outputModelBP.getIsIHB());
            data_bpMeasureResult.setLastChangeTime(outputModelBP.getLastChangeTime());
            data_bpMeasureResult.setLat(outputModelBP.getLat());
            data_bpMeasureResult.setLon(outputModelBP.getLon());
            data_bpMeasureResult.setMeasureType(outputModelBP.getMeasureType());
            data_bpMeasureResult.setNoteChangeTime(outputModelBP.getNoteChangeTime());
            data_bpMeasureResult.setPersonalized(outputModelBP.getPersonalized());
            data_bpMeasureResult.setPulse(outputModelBP.getPulse());
            data_bpMeasureResult.setSys(outputModelBP.getSys());
            data_bpMeasureResult.setTakePill(outputModelBP.getTakePill());
            data_bpMeasureResult.setTemp(outputModelBP.getTemp());
            data_bpMeasureResult.setTimeZone(outputModelBP.getTimeZone());
            data_bpMeasureResult.setTimezoneTS(outputModelBP.getTimezoneTs());
            data_bpMeasureResult.setUsedUserID(outputModelBP.getUsedUserID());
            data_bpMeasureResult.setVisibility(outputModelBP.getVisibility());
            data_bpMeasureResult.setWavelet(outputModelBP.getWavelet());
            data_bpMeasureResult.setWeather(outputModelBP.getWeather());
            data_bpMeasureResult.setwHO(outputModelBP.getwHO());
            return data_tb_bpMeasureResult.addData(data_bpMeasureResult);
        }else if(model instanceof Data_Family_Member) {
            return data_tb_bpMeasureResult.addData(model);
        }
        return false;
    }

    public <T> boolean updateCloudItem(T model) {
        if(model instanceof OutputModelBP) {
            OutputModelBP outputModelBP = (OutputModelBP)model;
            String selection = Data_TB_BPMeasureResult.BPRESULT_BPDATAID + " = '" + outputModelBP.getBpDataID() + "'";
            String valueStr = Data_TB_BPMeasureResult.BPRESULT_BPACTIVITY + " = " + outputModelBP.getBpActivity() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_BPLEVEL + " = " + outputModelBP.getBpLevel() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_BPMDEVICEID + " = '" + outputModelBP.getBpmDeviceID() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_BPMEASUREDATE + " = " + outputModelBP.getBpMeasureDate() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_BPMEASUREID + " = '" + outputModelBP.getBpMeasureID() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_BPMOOD + " = " + outputModelBP.getBpMood() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_BPNOTE + " = '" + outputModelBP.getBpNote() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_CARE_JSON + " = '" + outputModelBP.getCare_Json() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_CHANGETYPE + " = " + outputModelBP.getChangeType() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_CONTENT_JSON + " = '" + outputModelBP.getContent_Json() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_DATACREATETIME + " = " + outputModelBP.getDataCreatTime() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_DEVICETYPE + " = '" + outputModelBP.getDeviceType() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_DIA + " = " + outputModelBP.getDia() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_HUMIDITY + " = '" + outputModelBP.getHumidity() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_IHEALTHCLOUD + " = '" + outputModelBP.getiHealthCloud() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_ISDISPLAY + " = " + outputModelBP.getIsDisplay() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_ISIHB + " = " + outputModelBP.getIsIHB() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_LASTCHANGETIME + " = " + outputModelBP.getLastChangeTime() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_LAT + " = " + outputModelBP.getLat() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_LON + " = " + outputModelBP.getLon() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_MEASURETYPE + " = " + outputModelBP.getMeasureType() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_NOTECHANGETIME + " = " + outputModelBP.getNoteChangeTime() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_PERSONALIZED + " = '" + outputModelBP.getPersonalized() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_PULSE + " = " + outputModelBP.getPulse() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_SYS + " = " + outputModelBP.getSys() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_TAKEPILL + " = " + outputModelBP.getTakePill() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_TEMP + " = '" + outputModelBP.getTemp() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_TIMEZONE + " = " + outputModelBP.getTimeZone() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_TIMEZONETS + " = " + outputModelBP.getTimezoneTs() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_USEDUSERID + " = " + outputModelBP.getUsedUserID() + ", "
                            + Data_TB_BPMeasureResult.BPRESULT_VISIBILITY + " = '" + outputModelBP.getVisibility() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_WAVELET + " = '" + outputModelBP.getWavelet() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_WEATHER + " = '" + outputModelBP.getWeather() + "', "
                            + Data_TB_BPMeasureResult.BPRESULT_WHO + " = " + outputModelBP.getwHO();
            return data_tb_bpMeasureResult.updateData(selection, valueStr);
        }
        return false;
    }

    public <T> boolean updateNativeItem(String selection, String valueStr) {
        return false;
    }

    public <T> boolean deleteNativeItem(String selection) {
        return false;
    }

    public <T> boolean itemExist(T model) {
        String selection = "";
        if(model instanceof OutputModelBP) {
            selection = Data_TB_BPMeasureResult.BPRESULT_BPDATAID + " = '" + ((OutputModelBP) model).getBpDataID() + "'";
        }else if(model instanceof Data_BPMeasureResult) {
            selection = Data_TB_BPMeasureResult.BPRESULT_BPDATAID + " = '" + ((Data_BPMeasureResult) model).getBpDataID() + "'";
        }
        Cursor cursor = data_tb_bpMeasureResult.search(selection);
        if(cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean clearTable() {
        return false;
    }

    public ArrayList<Data_BPMeasureResult> getBPDataByUserId(int userId, String strValue) {
        ArrayList<Data_BPMeasureResult> list = new ArrayList<Data_BPMeasureResult>();
        String selection =  Data_TB_BPMeasureResult.BPRESULT_USEDUSERID + " = " + userId;
        if(strValue != null) {
            selection += " and " + strValue;
        }
        Cursor cursor = data_tb_bpMeasureResult.search(selection);

        if(cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Data_BPMeasureResult data_bpMeasureResult = new Data_BPMeasureResult();
                data_bpMeasureResult.setiHealthCloud(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_IHEALTHCLOUD)));
                data_bpMeasureResult.setVisibility(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_VISIBILITY)));
                data_bpMeasureResult.setBpActivity(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPACTIVITY)));
                data_bpMeasureResult.setNoteChangeTime(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_NOTECHANGETIME)));
                data_bpMeasureResult.setPulse(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_PULSE)));
                data_bpMeasureResult.setBpNote(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPNOTE)));
                data_bpMeasureResult.setBpmDeviceID(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMDEVICEID)));
                data_bpMeasureResult.setBpDataID(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPDATAID)));
                data_bpMeasureResult.setBpLevel(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPLEVEL)));
                data_bpMeasureResult.setBpMeasureDate(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMEASUREDATE)));
                data_bpMeasureResult.setBpMeasureID(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMEASUREID)));
                data_bpMeasureResult.setBpMood(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMOOD)));
                data_bpMeasureResult.setCare_Json(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_CARE_JSON)));
                data_bpMeasureResult.setChangeType(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_CHANGETYPE)));
                data_bpMeasureResult.setContent_Json(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_CONTENT_JSON)));
                data_bpMeasureResult.setDataCreatTime(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_DATACREATETIME)));
                data_bpMeasureResult.setDeviceType(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_DEVICETYPE)));
                data_bpMeasureResult.setDia(cursor.getFloat(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_DIA)));
                data_bpMeasureResult.setHumidity(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_HUMIDITY)));
                data_bpMeasureResult.setIsDisplay(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_ISDISPLAY)));
                data_bpMeasureResult.setIsIHB(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_ISIHB)));
                data_bpMeasureResult.setLastChangeTime(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_LASTCHANGETIME)));
                data_bpMeasureResult.setLat(cursor.getDouble(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_LAT)));
                data_bpMeasureResult.setLon(cursor.getDouble(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_LON)));
                data_bpMeasureResult.setMeasureType(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_MEASURETYPE)));
                data_bpMeasureResult.setPersonalized(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_PERSONALIZED)));
                data_bpMeasureResult.setSys(cursor.getFloat(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_SYS)));
                data_bpMeasureResult.setTakePill(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TAKEPILL)));
                data_bpMeasureResult.setTemp(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TEMP)));
                data_bpMeasureResult.setTimeZone(cursor.getFloat(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TIMEZONE)));
                data_bpMeasureResult.setTimezoneTS(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TIMEZONETS)));
                data_bpMeasureResult.setUsedUserID(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_USEDUSERID)));
                data_bpMeasureResult.setWavelet(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_WAVELET)));
                data_bpMeasureResult.setWeather(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_WEATHER)));
                data_bpMeasureResult.setwHO(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_WHO)));
                list.add(data_bpMeasureResult);
            }
        }
        return list;
    }

    public Data_BPMeasureResult getBpDataByDataId(int dataId) {
        String selection = Data_TB_BPMeasureResult.BPRESULT_BPDATAID + " = " + dataId;
        Cursor cursor = data_tb_bpMeasureResult.search(selection);
        if(cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Data_BPMeasureResult data_bpMeasureResult = new Data_BPMeasureResult();
                data_bpMeasureResult.setiHealthCloud(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_IHEALTHCLOUD)));
                data_bpMeasureResult.setVisibility(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_VISIBILITY)));
                data_bpMeasureResult.setBpActivity(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPACTIVITY)));
                data_bpMeasureResult.setNoteChangeTime(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_NOTECHANGETIME)));
                data_bpMeasureResult.setPulse(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_PULSE)));
                data_bpMeasureResult.setBpNote(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPNOTE)));
                data_bpMeasureResult.setBpmDeviceID(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMDEVICEID)));
                data_bpMeasureResult.setBpDataID(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPDATAID)));
                data_bpMeasureResult.setBpLevel(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPLEVEL)));
                data_bpMeasureResult.setBpMeasureDate(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMEASUREDATE)));
                data_bpMeasureResult.setBpMeasureID(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMEASUREID)));
                data_bpMeasureResult.setBpMood(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_BPMOOD)));
                data_bpMeasureResult.setCare_Json(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_CARE_JSON)));
                data_bpMeasureResult.setChangeType(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_CHANGETYPE)));
                data_bpMeasureResult.setContent_Json(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_CONTENT_JSON)));
                data_bpMeasureResult.setDataCreatTime(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_DATACREATETIME)));
                data_bpMeasureResult.setDeviceType(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_DEVICETYPE)));
                data_bpMeasureResult.setDia(cursor.getFloat(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_DIA)));
                data_bpMeasureResult.setHumidity(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_HUMIDITY)));
                data_bpMeasureResult.setIsDisplay(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_ISDISPLAY)));
                data_bpMeasureResult.setIsIHB(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_ISIHB)));
                data_bpMeasureResult.setLastChangeTime(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_LASTCHANGETIME)));
                data_bpMeasureResult.setLat(cursor.getDouble(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_LAT)));
                data_bpMeasureResult.setLon(cursor.getDouble(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_LON)));
                data_bpMeasureResult.setMeasureType(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_MEASURETYPE)));
                data_bpMeasureResult.setPersonalized(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_PERSONALIZED)));
                data_bpMeasureResult.setSys(cursor.getFloat(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_SYS)));
                data_bpMeasureResult.setTakePill(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TAKEPILL)));
                data_bpMeasureResult.setTemp(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TEMP)));
                data_bpMeasureResult.setTimeZone(cursor.getFloat(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TIMEZONE)));
                data_bpMeasureResult.setTimezoneTS(cursor.getLong(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_TIMEZONETS)));
                data_bpMeasureResult.setUsedUserID(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_USEDUSERID)));
                data_bpMeasureResult.setWavelet(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_WAVELET)));
                data_bpMeasureResult.setWeather(cursor.getString(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_WEATHER)));
                data_bpMeasureResult.setwHO(cursor.getInt(cursor.getColumnIndex(Data_TB_BPMeasureResult.BPRESULT_WHO)));
                return data_bpMeasureResult;
            }
        }
        return null;
    }

    public void saveBPMeasureResultData(final ArrayList<OutputModelBP> list, final LogicListener listener, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<list.size(); i++) {
                    if(itemExist(list.get(i))) {
                        if(!updateCloudItem(list.get(i))) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFailure("");
                                }
                            });
                        }
                    }else {
                        if(!saveItem(list.get(i))) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFailure("");
                                }
                            });
                        }
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess("");
                    }
                });
            }
        }).start();
    }
}
