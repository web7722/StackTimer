package com.lee.stacktimer.data;

public class Time {
	private String taskName;
	private long startTime;
	
	public Time(String taskName){
		this.taskName = taskName;
		startTime = System.currentTimeMillis();
	}
	
	public String getTaskName(){
		return taskName;
	}
	
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	public long getStartTime(){
		return startTime;
	}
	
	public void setStartTime(long startTime){
		this.startTime = startTime;
	}
}
