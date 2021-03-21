package org.example.common.account.service;


import org.dromara.hmily.annotation.Hmily;
import org.example.common.account.DTO.AccountDTO;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-21
 */
public interface IAccountService{

    /**
     * 支付人民币
     * @param accountDTO
     * @return
     */
    @Hmily
    boolean PayRMB(AccountDTO accountDTO);

    /**
     * 支付美元
     * @param accountDTO
     * @return
     */
    @Hmily
    boolean PayUSB(AccountDTO accountDTO);

    /**
     * 进账人民币
     * @param accountDTO
     * @return
     */
    @Hmily
    boolean IncomeRMB(AccountDTO accountDTO);

    /**
     * 进账美元
     * @param accountDTO
     * @return
     */
    @Hmily
    boolean IncomeUSB(AccountDTO accountDTO);
}
