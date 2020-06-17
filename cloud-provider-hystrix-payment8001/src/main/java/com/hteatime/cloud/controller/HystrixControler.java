package com.hteatime.cloud.controller;

import com.hteatime.cloud.server.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixControler {
    @Resource
    private HystrixService hystrixService;

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = hystrixService.paymentInfo_OK(id);
        log.info("*****result:" + result);
        return result;
    }

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties ={
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    } )
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = hystrixService.paymentInfo_TimeOut(id);
        log.info("*****result:" + result);
        return result;

    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "支付异常,请稍后再试^@ _ @^"+Thread.currentThread().getName();
    }
}
