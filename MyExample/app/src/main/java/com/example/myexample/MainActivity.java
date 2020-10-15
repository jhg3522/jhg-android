package com.example.myexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.Image;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button button1;
    ImageView ivBattery;
    EditText edtBattery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배경색 바꾸기");

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button1= (Button) findViewById(R.id.button1);
        ivBattery = (ImageView) findViewById(R.id.ivBattery);
        edtBattery = (EditText) findViewById(R.id.edtBattery);
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(intent.ACTION_BATTERY_CHANGED)){
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                edtBattery.setText("현재 충전량 : "+ remain + "%\n");

                if(remain>=90)
                    ivBattery.setImageResource(R.drawable.battery_100);
                else if(remain>=70)
                    ivBattery.setImageResource(R.drawable.battery_80);
                else if(remain>=50)
                    ivBattery.setImageResource(R.drawable.battery_60);
                else if(remain>=20)
                    ivBattery.setImageResource(R.drawable.battery_20);
                else
                    ivBattery.setImageResource(R.drawable.battery_0);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,intentFilter);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.subRotate:
                button1.setRotation(45);
                return true;
            case R.id.subSize:
                button1.setScaleX(2);
                return true;
        }
        return false;
    }*/
}
