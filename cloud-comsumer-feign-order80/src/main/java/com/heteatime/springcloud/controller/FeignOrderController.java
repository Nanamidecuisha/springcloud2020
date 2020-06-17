package com.heteatime.springcloud.controller;

import com.heteatime.springcloud.server.IOrderSver;
import com.hteatime.springcloud.entities.CommonResult;
import com.hteatime.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FeignOrderController {
    @Resource
    private IOrderSver orderSver;
    @GetMapping(value = "/comsumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id")Long id){
        return orderSver.get(id);
    }
}
