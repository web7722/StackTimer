package com.lee.stacktimer.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 시간관리 기능의 스택을 Singletone으로 구현하였다.
 * */
public enum TimeStack {
	INSTANCE;
	
	private List<Time> stack;
	
	TimeStack(){
		stack = new ArrayList<Time>();
	}
	
	public Time pop(){
		Time time = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		return time;
	}
	
	public void put(Time time){
		stack.add(time);
	}
	
	public Time getCurrentTime(){
		return stack.get(stack.size()-1);
	}
}
