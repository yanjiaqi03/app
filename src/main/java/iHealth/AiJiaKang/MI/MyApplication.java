package iHealth.AiJiaKang.MI;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.ihealth.aijiakang.activity.comm.HostActivity;
import com.ihealth.aijiakang.database.DataBaseHelper;
import com.ihealth.aijiakang.imageloader.ImageLoaderTools;
import com.ihealth.aijiakang.thirdparty.MiPushTools;
import com.ihealth.aijiakang.thirdparty.MiStatTools;
import com.ihealth.aijiakang.utils.FileUtils;

import java.util.HashMap;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by YanJiaqi on 15/11/17.
 */
public class MyApplication extends Application{
    private HashMap<String,Typeface> fontsMap = new HashMap<String,Typeface>();
    public static final String DEFAULT_HYQH = "fonts/default_hyqh.ttf";

    private HashMap<String,Activity> activityMap = new HashMap<String,Activity>();

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化sharesdk
        ShareSDK.initSDK(this);
        //初始化字体
        initialFonts();
        //初始化数据库
        initDatabase();
        //初始化imageLoader
        ImageLoaderTools.getInstance().initImageLoader(this);
        //初始化小米统计
        MiStatTools.getInstance().init(this);
        //初始化小米推送
        MiPushTools.getInstance().init(this);
    }


    /**
     * 初始化字体文件
     * Author YanJiaqi
     * Created at 15/11/19 下午2:27
     */

    private void initialFonts(){
        setFont(DEFAULT_HYQH);
    }

    /**
     * 初始化数据库对象
     * @author lanbaoshi
     * created at 15/11/23 下午8:12
     */
    private void initDatabase() {
        DataBaseHelper helper = DataBaseHelper.getInstance(this);
        helper.getWritableDatabase();
    }

    /**
     * 获取字体
     * Author YanJiaqi
     * Created at 15/11/19 下午3:22
     */

    public Typeface getFont(String myFont) {
        Typeface typeFace = fontsMap.get(myFont);
        if(typeFace == null){
            setFont(myFont);
            return fontsMap.get(myFont);
        }else{
            return typeFace;
        }
    }

    /**
     * 设置字体
     * Author YanJiaqi
     * Created at 15/11/19 下午3:22
     */

    public void setFont(String myFont) {
        if(fontsMap.get(myFont) == null){
            fontsMap.put(myFont,FileUtils.getInstance().getFontFromAsset(this,myFont));
        }
    }

    public HashMap<String, Activity> getActivityMap() {
        return activityMap;
    }
}

