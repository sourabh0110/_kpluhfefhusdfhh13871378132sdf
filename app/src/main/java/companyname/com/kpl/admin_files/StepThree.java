package companyname.com.kpl.admin_files;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import companyname.com.kpl.MainActivity;
import companyname.com.kpl.R;

public class StepThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_three);

        LocalBroadcastManager.getInstance(this).registerReceiver(message,
                new IntentFilter("custom-message"));
    }

    BroadcastReceiver message=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String ItemName = intent.getStringExtra("ref_name");
            Toast.makeText(StepThree.this,ItemName +" ", Toast.LENGTH_SHORT).show();
            Log.e("ASD",""+ItemName);
        }
    };
}
