package com.hteatime.cloud.controller;

import com.hteatime.cloud.service.HystrixService;
import com.hteatime.springcloud.entities.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixController {

    @Resource
    private HystrixService hystrixService;

    @GetMapping("/comsumer/payment/hystrix/ok/{id}")
    public CommonResult<String> paymentInfo_OK(@PathVariable("id") Integer id){
        CommonResult result = new CommonResult();
        result.setCode(200);
        result.setData(hystrixService.paymentInfo_OK(id));
        return result;
    }
    @GetMapping("/comsumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties ={
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    public CommonResult<String> paymentInfo_TimeOut(@PathVariable("id") Integer id){
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int i = 10 / 0;
        CommonResult result = new CommonResult();
        result.setCode(200);
        result.setData(hystrixService.paymentInfo_TimeOut(id));
        return result;
    }
    public CommonResult<String> timeoutHandler(@PathVariable("id")Integer id){
        CommonResult result = new CommonResult();
        result.setMessage("消费者80调用服务端 接口失败,请 稍后再试 ..");
        return result;
    }
}
