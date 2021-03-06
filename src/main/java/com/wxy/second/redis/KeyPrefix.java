package com.wxy.second.redis;

public interface KeyPrefix {
		
	int expireSeconds();
	
	String getPrefix();
	
}
