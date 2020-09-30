package luyao.android.context;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import luyao.android.R;

public class ContextActivity extends AppCompatActivity {

    private ContextBroadCastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);

        receiver = new ContextBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter("luyao.context");
        registerReceiver(receiver,intentFilter);

        Log.e("context", "getApplication in Activity: " + getApplication().getClass().getName());
        Log.e("context", "getApplicationContext in Activity: " + getApplicationContext().getClass().getName());
        Log.e("context", "getBaseContext in Activity: " + getBaseContext().getClass().getName());

        startService(new Intent(this,ContextService.class));
    }


    public void showDialog(View view){
       sendBroadcast(new Intent("luyao.context"));
    }
}
