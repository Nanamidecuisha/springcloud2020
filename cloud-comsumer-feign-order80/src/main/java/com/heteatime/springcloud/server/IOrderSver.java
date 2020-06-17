package com.heteatime.springcloud.server;

import com.hteatime.springcloud.entities.CommonResult;
import com.hteatime.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface IOrderSver {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id")Long id);
}

