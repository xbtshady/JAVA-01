package example.consumer.service.impl;

import example.consumer.po.Order;
import example.consumer.service.ITestService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.example.common.wallet.dto.WalletDTO;
import org.example.common.wallet.service.IWalletService;
import org.example.common.walletFrozen.dto.WalletFrozenDTO;
import org.example.common.walletFrozen.service.IWalletFrozenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);


    private final IWalletService walletService;

    private final IWalletFrozenService walletFrozenService;


    @Autowired(required = false)
    public TestServiceImpl(IWalletService walletService, IWalletFrozenService walletFrozenService) {
        this.walletService = walletService;
        this.walletFrozenService = walletFrozenService;
    }
    @Override
    @HmilyTCC(confirmMethod = "confirmTransfer", cancelMethod = "cancelTransfer")
    public Boolean transfer(Order order) {
        //try阶段 用户1余额减7元 且冻结用户1的人民币7元
        walletFrozenService.freezeCNY(createWalletFrozenDTO(order.getUser_pay(),order.getCount()));
        return walletService.payCNY(createWalletDTO(order.getUser_pay(),order.getCount())) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmTransfer(Order order) {
        logger.info("confirmTransfer run");
        //confirm阶段 解冻用户1的冻结余额,用户2美元余额增加1
        walletFrozenService.unfreezeCNY(createWalletFrozenDTO(order.getUser_pay(),order.getCount()));
        walletService.inComeUsb(createWalletDTO(order.getUser_income(),order.getCount()/7));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean cancelTransfer(Order order) {
        logger.info("cancelTransfer run");
        //confirm阶段 解冻用户1的冻结余额,用户1美元余额增加7
        walletFrozenService.unfreezeCNY(createWalletFrozenDTO(order.getUser_pay(),order.getCount()));
        walletService.inComeCNY(createWalletDTO(order.getUser_pay(),order.getCount()));
        return true;
    }

    private WalletDTO createWalletDTO(int userId,int count){
        return WalletDTO.builder()
                .userId(userId)
                .amount(count)
                .build();
    }

    private WalletFrozenDTO createWalletFrozenDTO(int userId,int count){
        return WalletFrozenDTO.builder()
                .userId(userId)
                .amount(count)
                .build();
    }
}
