package com.atguigu.eduservice.client;

import org.springframework.stereotype.Component;

@Component
public class OrdersFeignClient implements OrdersClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
