package com.sie.web.scheduled;

import com.sie.framework.dao.OrderDao;
import com.sie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wangheng on 2017/9/4.
 */
@Component
public class OrderScheduled {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    /**
     * 订单过期时间
     */
    @Value("${order.timeout}")
    private String orderTimeout;

    /**
     * 定时任务，更新作业状态；
     */
    @Scheduled(fixedRate = 1000*60)
    public void cancelOrder(){
        orderService.cancelOrder();
    }

}
