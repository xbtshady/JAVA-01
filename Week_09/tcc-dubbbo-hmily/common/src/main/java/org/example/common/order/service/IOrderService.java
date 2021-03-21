package org.example.common.order.service;

import org.example.common.order.entity.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-19
 */
public interface IOrderService{
    public void test(Order order);

    public void save(Order order);

    void update(Order order);
}
