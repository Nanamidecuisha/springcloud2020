package com.hteatime.springcloud.dao;

import com.hteatime.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment  getPaymentById(@Param("id")Long id);
}
