package luyao.android.context;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ContextService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("context", "getApplication() in Service :" + getApplication().getClass().getName());
        Log.e("context", "getApplicationContext() in Service :" + getApplicationContext().getClass().getName());
        Log.e("context", "getBaseContext() in Service :" + getBaseContext().getClass().getName());
    }
}
