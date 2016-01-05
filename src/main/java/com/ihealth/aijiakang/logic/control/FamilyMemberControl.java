package com.ihealth.aijiakang.logic.control;
import android.content.Context;
import com.ihealth.aijiakang.database.data.Data_Family_Member;
import com.ihealth.aijiakang.database.table.Data_TB_Family_Member;
import com.ihealth.aijiakang.logic.database.FamilyMemberLogic;
import com.ihealth.aijiakang.logic.LogicCallbackInterface;
import com.ihealth.aijiakang.logic.LogicListener;
import com.ihealth.aijiakang.logic.database.UserTokenLogic;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.ihealthcloudlibrary.cloudInterface.AJKCloudMethod;
import com.ihealth.ihealthcloudlibrary.cloudInterface.CloudCallback;
import com.ihealth.ihealthcloudlibrary.model.input.InputModelMemberUserInfo;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelFamilyMembers;
import com.ihealth.ihealthcloudlibrary.model.output.OutputModelMemberUserInfo;
import java.util.ArrayList;

/**
 * Created by lanbaoshi on 15/12/3.
 */
public class FamilyMemberControl extends BasicControl{

    private final String TAG = "FamilyMemberControl";
    private static FamilyMemberControl mInstance;
    private LogicCallbackInterface mCallback;
    private FamilyMemberControl() {
        super();
    }

    public static FamilyMemberControl getInstance() {
        if(mInstance == null) {
            mInstance = new FamilyMemberControl();
        }
        return mInstance;
    }

    public void downloadFamilyMembersUserInfo(final Context context, String userName, LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        this.mCallback = callback;
        String accessToken = new UserTokenLogic(context).getAccessToken(userName);
        AJKCloudMethod.getInstance().downloadFamilyMembersUserInfo(context, userName, accessToken, new CloudCallback<OutputModelFamilyMembers>() {
            @Override
            public void onSuccess(final ArrayList<OutputModelFamilyMembers> arrayList) {
                if(context == null) {
                    return;
                }
                AJKLog.i(TAG, "downloadFamilyMembers success");
                int familyId = arrayList.get(0).getFamilyId();
                AJKLog.i(TAG, "familyId = " + familyId);
                UserConfig.getInstance().setConfigFamilyId(context, familyId);
                ArrayList<Data_Family_Member> familyList = new ArrayList<Data_Family_Member>();
                int m = arrayList.get(0).getMembers().size();
                if(m > 0) {
                    for(int i=0; i<m; i++) {
                        familyList.add(translateCloudObjectToNative(arrayList.get(0).getMembers().get(i)));
                    }
                    FamilyMemberLogic familyMemberLogic = new FamilyMemberLogic(context);
                    //familyMemberLogic.clearTable();
                    familyMemberLogic.saveFamilyMemberData(familyList, new LogicListener() {
                        @Override
                        public <T> void onSuccess(T model) {

                            if(mCallback != null) {
                                mCallback.onSuccess("");
                            }
                        }
                        @Override
                        public <T> void onFailure(T model) {
                            if(mCallback != null) {
                                mCallback.onFailure("", "");
                            }
                        }
                    }, myHandler);
                }else {
                    AJKLog.i(TAG, "Do not have family members");
                    mCallback.onSuccess("");
                }
            }

            @Override
            public void onFailure(String backNum, String str) {
                AJKLog.i(TAG, "downloadFamilyMembers fail");
                if(mCallback != null) {
                    mCallback.onFailure(backNum, str);
                }
            }
        });
    }

    public void addFamilyMember(final Context context, String name, float weight, long birthday,
                                   int height, String logo, long logoTS, int gender, LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        Data_Family_Member data_family_member = new Data_Family_Member();
        data_family_member.setFamilyUserBirthday(birthday);
        data_family_member.setRemark(name);
        data_family_member.setHeadImg(logo);
        data_family_member.setTS(logoTS);
        data_family_member.setFamilyUserGender(gender);
        data_family_member.setFamilyId(UserConfig.getInstance().getConfigFamilyId(context));
        data_family_member.setFamilyUserHeight(height);
        data_family_member.setFamilyUserWeight(weight);
        data_family_member.setFamilyUserName(name);
        uploadFamilyMember(context, AJKCloudMethod.UPLOADMEMBER_ADD, data_family_member, callback);
    }

    public void updateFamilyMember(final Context context, int familyUserId, final String name, final float weight, final long birthday,
                                   final int height, final String logo, final long logoTS, final int gender, final LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }

        int currentFamilyId = UserConfig.getInstance().getConfigFamilyId(context);
        AJKLog.i(TAG, "familyUserId = " + familyUserId);
        new FamilyMemberLogic(context).searchFamilyMember(currentFamilyId, familyUserId, new LogicListener() {
            @Override
            public <T> void onSuccess(T model) {
                ArrayList<Data_Family_Member> list = (ArrayList<Data_Family_Member>)model;
                if(list != null && list.size() > 0) {
                    Data_Family_Member data_family_member = list.get(0);
                    data_family_member.setRemark(name);
                    data_family_member.setFamilyUserName(name);
                    data_family_member.setFamilyUserWeight(weight);
                    data_family_member.setFamilyUserBirthday(birthday);
                    data_family_member.setFamilyUserGender(gender);
                    data_family_member.setFamilyUserHeight(height);
                    data_family_member.setHeadImg(logo);
                    data_family_member.setTS(logoTS);
                    AJKLog.i(TAG, data_family_member.getRefreshToken());
                    AJKLog.i(TAG, data_family_member.getToken());
                    uploadFamilyMember(context, AJKCloudMethod.UPLOADMEMBER_MODIFY, data_family_member, callback);
                }else {
                    AJKLog.i(TAG, "Can not find the family member");
                }
            }
            @Override
            public <T> void onFailure(T model) {

            }
        }, myHandler);

    }

    public void delFamilyMember(final Context context, int familyUserId, LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        Data_Family_Member data_family_member = new Data_Family_Member();
        data_family_member.setFamilyId(UserConfig.getInstance().getConfigFamilyId(context));
        data_family_member.setUserId(familyUserId);
        uploadFamilyMember(context, AJKCloudMethod.UPLOADMEMBER_DEL, data_family_member, callback);
    }

    public void uploadFamilyMember(final Context context, final String type, final Data_Family_Member data, final LogicCallbackInterface callback) {
        if(context == null) {
            return;
        }
        InputModelMemberUserInfo inputModelMember = new InputModelMemberUserInfo();
        String uN = UserConfig.getInstance().getConfigCurrentUser(context);
        String accessToken = new UserTokenLogic(context).getAccessToken(uN);
        inputModelMember.setUn(uN);
        inputModelMember.setToken(accessToken);
        inputModelMember.setFamilyId(data.getFamilyId());
        inputModelMember.setBirthday(data.getFamilyUserBirthday());
        inputModelMember.setGender(data.getFamilyUserGender());
        inputModelMember.setHeight(data.getFamilyUserHeight());
        inputModelMember.setLogo(data.getHeadImg());
        inputModelMember.setLogoTs(data.getTS());
        inputModelMember.setMemberUserId(data.getUserId());
        inputModelMember.setUserName(data.getFamilyUserName());
        inputModelMember.setWeight(data.getFamilyUserWeight());
        this.mCallback = callback;
        AJKCloudMethod.getInstance().uploadFamilyMember_ReturnUserInfo(context, type, inputModelMember, new CloudCallback<OutputModelFamilyMembers>() {
            @Override
            public void onSuccess(final ArrayList<OutputModelFamilyMembers> arrayList) {
                if(context == null) {
                    return;
                }
                if(type.equals(AJKCloudMethod.UPLOADMEMBER_DEL)) {
                    AJKLog.i(TAG, "Delete family member");
                    String selection = Data_TB_Family_Member.FAMILY_FAMILYID + " = " + data.getFamilyId() + " and "
                            + Data_TB_Family_Member.FAMILY_USERID + " = " + data.getUserId();
                    if(new FamilyMemberLogic(context).deleteItem(selection)) {
                        AJKLog.i(TAG, "Delete family member success");
                        mCallback.onSuccess("");
                    }
                }else {
                    AJKLog.i(TAG, "Upload family member info success");
                    ArrayList<Data_Family_Member> familyList = new ArrayList<Data_Family_Member>();
                    for(int i=0; i<arrayList.size(); i++) {
                        familyList.add(translateCloudObjectToNative(arrayList.get(0).getMembers().get(i)));
                    }
                    new FamilyMemberLogic(context).saveFamilyMemberData(familyList, new LogicListener() {
                        @Override
                        public <T> void onSuccess(T model) {
                            AJKLog.i(TAG, "save family member info success");
                            mCallback.onSuccess(arrayList.get(0).getMembers().get(0).getLogo());
                        }
                        @Override
                        public <T> void onFailure(T model) {
                            AJKLog.i(TAG, "save family member info fail");
                            mCallback.onFailure("", "");
                        }
                    }, myHandler);
                }
            }
            @Override
            public void onFailure(String backNum, String str) {
                AJKLog.i(TAG, "Upload family member info fail");
                callback.onFailure(backNum, str);
            }
        });
    }

    private Data_Family_Member translateCloudObjectToNative(OutputModelMemberUserInfo outputModelMemberUserInfo) {
        Data_Family_Member data_family_member = new Data_Family_Member();
        data_family_member.setFamilyUserGender(outputModelMemberUserInfo.getGender());
        data_family_member.setRemark(outputModelMemberUserInfo.getName());
        data_family_member.setFamilyUserHeight(outputModelMemberUserInfo.getHeight());
        data_family_member.setHeadImg(outputModelMemberUserInfo.getLogo());
        data_family_member.setFamilyUserBirthday(outputModelMemberUserInfo.getBirthDay());
        data_family_member.setUserId(outputModelMemberUserInfo.getMberUserId());
        data_family_member.setFamilyUserName(outputModelMemberUserInfo.getName());
        data_family_member.setFamilyUserWeight(outputModelMemberUserInfo.getWeight());
        data_family_member.setRefreshToken(outputModelMemberUserInfo.getMemberRefreshToken());
        data_family_member.setToken(outputModelMemberUserInfo.getMemberToken());
        data_family_member.setTS(outputModelMemberUserInfo.getLogoTS());
        data_family_member.setUn(outputModelMemberUserInfo.getUn());
        data_family_member.setFamilyId(outputModelMemberUserInfo.getFamilyId());
        return data_family_member;
    }
}
