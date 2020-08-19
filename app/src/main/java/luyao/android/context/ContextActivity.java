package luyao.android.context;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import luyao.android.R;

public class ContextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);

        Log.e("context", "getApplication in Activity: " + getApplication().getClass().getName());
        Log.e("context", "getApplicationContext in Activity: " + getApplicationContext().getClass().getName());
        Log.e("context", "getBaseContext in Activity: " + getBaseContext().getClass().getName());

        startService(new Intent(this,ContextService.class));
    }

    // 使用 Application 创建 Dialog
    public void showDialog(View view){
        Dialog dialog = new Dialog(getApplicationContext());
        dialog.show();
    }
}
