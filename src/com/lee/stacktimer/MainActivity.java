package com.lee.stacktimer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.lee.stacktimer.constants.AppConstant;

public class MainActivity extends Activity {
	private BroadcastReceiver stackTimeReceiver = new BroadcastReceiver(){
    	@Override
    	public void onReceive(Context context, Intent intent){
    		Log.d(AppConstant.LOG_TAG, "BroadcastReceiver is called.");
    		
    		TextView time = (TextView) findViewById(R.id.time);
    		time.setText("" + intent.getStringExtra("taskTime"));
    		
    		TextView title = (TextView) findViewById(R.id.title);
    		title.setText("" + intent.getStringExtra("taskName"));
    	}
    };
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = new Intent(this, StackTimerService.class);
        startService(intent);
        
        registerReceiver(stackTimeReceiver, new IntentFilter("timeStack"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	registerReceiver(stackTimeReceiver, new IntentFilter("timeStack"));
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
     	unregisterReceiver(stackTimeReceiver);
    }
}
