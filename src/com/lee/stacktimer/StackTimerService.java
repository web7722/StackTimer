package com.lee.stacktimer;

import java.util.concurrent.TimeUnit;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.lee.stacktimer.constants.AppConstant;
import com.lee.stacktimer.data.Time;
import com.lee.stacktimer.data.TimeStack;

public class StackTimerService extends IntentService {
	public StackTimerService() {
		super("StackTimerService");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Time defaultTime = new Time("Nothing to do");
		TimeStack.INSTANCE.put(defaultTime);
		
		Log.d(AppConstant.LOG_TAG, "StackTimerService is initialized.");
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	protected void onHandleIntent(Intent service){
		while(true){
			synchronized(this){
				try {
					wait(1000);
					displayTaskTimeOnScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void displayTaskTimeOnScreen(){
		long currentTime = System.currentTimeMillis();
		long elapsed = currentTime - TimeStack.INSTANCE.getCurrentTime().getStartTime();
		
		int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(elapsed);
		int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(elapsed);
		int hours = (int) TimeUnit.MILLISECONDS.toHours(elapsed);
		
		String taskTime = 
				((hours < 10) ? "0" + hours : hours) + ":" +
				((minutes < 10) ? "0" + minutes : minutes) + ":" +
				((seconds < 10) ? "0" + seconds : seconds);
		
		Intent message = new Intent("timeStack");
		message.putExtra("taskName", TimeStack.INSTANCE.getCurrentTime().getTaskName());
		message.putExtra("taskTime", taskTime);
		
		getApplicationContext().sendBroadcast(message);
	}
}
