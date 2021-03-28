package example.consumer.service;

import example.consumer.po.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-19
 */
public interface ITestService {
    Boolean transfer(Order order);
}
