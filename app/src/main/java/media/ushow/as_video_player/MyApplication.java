package media.ushow.as_video_player;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.util.List;

/**
 * Created by alankong on 15/9/2.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static Resources getAppResources() {
        return mContext.getResources();
    }

    public static MyApplication getAppInstance() {
        return mContext;
    }
}
