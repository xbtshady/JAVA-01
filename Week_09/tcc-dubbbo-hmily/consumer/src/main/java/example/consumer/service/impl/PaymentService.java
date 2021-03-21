package example.consumer.service.impl;

import example.consumer.service.IPaymentService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.example.common.account.DTO.AccountDTO;
import org.example.common.account.service.IAccountService;
import org.example.common.order.entity.Order;
import org.example.common.order.enums.OrderStatusEnum;
import org.example.common.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService implements IPaymentService {

    private final IAccountService accountService;

    private final IOrderService orderService;

    @Autowired(required = false)
    public PaymentService(IAccountService accountService, IOrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmTransfer", cancelMethod = "cancelTransfer")
    public void makePayment(Order order) {
        //7人民币
        BigDecimal rmb = new BigDecimal(7);
        //1美元
        BigDecimal usb = new BigDecimal(1);

        Integer userId1 = 1;
        Integer userId2 = 2;
        /**
         * 用户a(UserId=1) 用人民币给 用户B(UserId=2) 转账 1美元
         */
        accountService.PayRMB(buildAccountDTO(userId1,rmb));
        /**
         * 用户a(UserId=2) 账户增加 1美元
         */
        accountService.IncomeUSB(buildAccountDTO(userId2,usb));
    }


    public void confirmTransfer(Order order) {
        /**
         * 成功更新订单为成功状态
         */
        order.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
        orderService.update(order);
    }

    public void cancelTransfer(Order order) {
        /**
         * 失败更新订单为失败状态
         */
        order.setStatus(OrderStatusEnum.PAY_FAIL.getCode());
        orderService.update(order);
    }

    private AccountDTO buildAccountDTO(Integer userId, BigDecimal amount) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(amount);
        accountDTO.setUserId(userId);
        return accountDTO;
    }
}
