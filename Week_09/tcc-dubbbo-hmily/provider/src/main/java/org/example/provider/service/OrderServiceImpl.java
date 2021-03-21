package org.example.provider.service;

import org.example.common.order.entity.Order;
import org.example.common.order.mapper.OrderMapper;
import org.example.common.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-19
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    private final OrderMapper orderMapper;

    @Autowired(required = false)
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public void test(Order order){
        orderMapper.insert(order);
    }

    @Override
    public void save(Order order) {
        orderMapper.insert(order);
    }


    public void update(Order order) {
        orderMapper.update(order);
    }
}
