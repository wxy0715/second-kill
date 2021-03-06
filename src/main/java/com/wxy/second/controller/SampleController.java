package com.wxy.second.controller;

import com.wxy.second.domain.User;
import com.wxy.second.rabbitmq.MQSender;
import com.wxy.second.redis.RedisService;
import com.wxy.second.redis.UserKey;
import com.wxy.second.result.Result;
import com.wxy.second.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {
	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;


	@Autowired
	MQSender sender;

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
    	User user = userService.getById(1);
        return Result.success(user);
    }

/*
	@RequestMapping("/mq")
	@ResponseBody
	public void mq() {
		sender.send("testSend");
	}

	@RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
		sender.sendHeader("hello,imooc");
        return Result.success("Hello，world");
    }
*/
/*
	@RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
		sender.sendFanout("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic() {
		sender.sendTopic("hello,imooc");
        return Result.success("Hello，world");
    }*/

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
    	userService.tx();
        return Result.success(true);
    }
    
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
    	User  user  = redisService.get(UserKey.getById, ""+2, User.class);
        return Result.success(user);
    }
    
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
    	User user  = new User();
    	user.setId(2);
    	user.setName("2222");
    	redisService.set(UserKey.getById, ""+2, user);//UserKey:id1
        return Result.success(true);
    }
}
