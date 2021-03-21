package example.consumer.service.impl;

import example.consumer.service.ITestService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.example.common.account.DTO.AccountDTO;
import org.example.common.order.entity.Order;
import org.example.common.order.enums.OrderStatusEnum;
import org.example.common.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-19
 */
@Service("testService")
public class TestServiceImpl implements ITestService {
    private final IOrderService orderService;

    private final PaymentService paymentService;


    @Autowired(required = false)
    public TestServiceImpl(IOrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @Override
    public void test(int a) {
        Order order = new Order();
        order.setAmount(new BigDecimal(10.0));
        order.setStatus(1);
        order.setUserId(a);
        order.setUnit(1);
        orderService.test(order);
    }

    @Override
    public void transfer() {
        /**
         * 创建订单
         */
        Order order = saveNewOrder(1,new BigDecimal(1),OrderStatusEnum.UINT_RMB.getCode());
        paymentService.makePayment(order);
    }


    Order saveNewOrder(Integer userId,BigDecimal amount,Integer unit){
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatusEnum.PAYING.getCode());
        order.setAmount(amount);
        order.setUnit(unit);
        orderService.save(order);
        return order;
    }


}
