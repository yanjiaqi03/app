package iHealth.AiJiaKang.MI;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.ihealth.aijiakang.activity.comm.HostActivity;
import com.ihealth.aijiakang.sharedpreference.PushConfig;
import com.ihealth.aijiakang.sharedpreference.UserConfig;
import com.ihealth.aijiakang.system.AJKLog;
import com.ihealth.aijiakang.system.SystemTool;
import com.ihealth.aijiakang.thirdparty.MiPushTools;
import com.ihealth.aijiakang.utils.VariableUtils;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * Created by YanJiaqi on 15/12/3
 */
public class PushMessageReciever extends PushMessageReceiver {
    private final String TAG = "PushMessageReciever";
    private final String WELCOMEACTIVITY = "com.ihealth.aijiakang.activity.comm.WelcomeActivity";
    private final String HOSTACTIVITY = "com.ihealth.aijiakang.activity.comm.HostActivity";

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        /** 如果不需要出现通知栏去执行动作,使用透传
         *  一般这里只去发送广播通知存在的Activity更新界面,不会发生莫名的界面跳转 **/
        AJKLog.i(TAG, "透传->" + miPushMessage.toString());

    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        AJKLog.i(TAG, "通知栏点击->" + miPushMessage.toString());
//        int state = SystemTool.getInstance().isTopActivity(context);
//        AJKLog.i(TAG, "通知栏点击->state->" + state);
        PushConfig.getInstance().setPushCount(context, PushConfig.PUSH_CONFIG_VALUE_ADD_FAMILY);

        String packageName = SystemTool.getInstance().getPackageName(context);
        ComponentName cn = new ComponentName(packageName, HOSTACTIVITY);
        Intent intent = new Intent();
        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        /** App启动时才有通知栏到达，否则就算重新打开app也不会出现通知栏到达
         *  接收到通知栏到达，通过发送广播去刷新指定界面（指定界面开启情况下，未开启下广播不会收到，等到进入指定界面由其自身更新）
         *  一般这里只去发送广播通知存在的Activity更新界面,不会发生莫名的界面跳转**/

        AJKLog.i(TAG, "通知栏到达->" + miPushMessage.toString());
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        AJKLog.i(TAG, "Push Success -> command -> " + command);
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        if (MiPushClient.COMMAND_SET_ACCOUNT.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                String mAccount = arguments.get(0);
                AJKLog.i(TAG, "Push Success -> " + mAccount);
            }
        } else if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            MiPushTools.getInstance().setCurrentUserPush(context);
        }
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
    }
}
