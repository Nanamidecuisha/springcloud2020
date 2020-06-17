package com.hteatime.springcloud.controller;

import com.hteatime.springcloud.entities.CommonResult;
import com.hteatime.springcloud.entities.Payment;
import com.hteatime.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentServiceImpl;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int i = paymentServiceImpl.create(payment);
        if(i > 0){
            return new CommonResult<>(200,"创建数据成功",null);
        }else {

            return new CommonResult<>(500,"创建数据失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    @RequestMapping(method = RequestMethod.GET)
    public CommonResult<Payment> get(@PathVariable("id")Long id){
       /*
       //测试Feign超时调用
       try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        Payment payment = paymentServiceImpl.getPaymentById(id);
        log.info("查询的结果为 payment:"+payment+"serverPort:"+serverPort);
        if(payment != null){
            return new CommonResult<>(200,"查询数据成功,serverPort"+serverPort,payment);
        }else {
            return new CommonResult<>(500,"查询数据失败",null);
        }
    }

    @GetMapping("/payment/discovery")
    @ResponseBody
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services){
            log.info("*******element:"+element);

        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance serviceInstance : instances){
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());

        }
        return this.discoveryClient;
    }
}
