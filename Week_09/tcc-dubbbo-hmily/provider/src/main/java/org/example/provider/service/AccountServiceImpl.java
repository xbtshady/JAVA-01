package org.example.provider.service;


import org.dromara.hmily.annotation.HmilyTCC;
import org.example.common.account.DTO.AccountDTO;
import org.example.common.account.mapper.AccountMapper;
import org.example.common.account.service.IAccountService;
import org.example.common.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-21
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    private final AccountMapper accountMapper;

    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmPayRMB", cancelMethod = "cancelPayRMB")
    public boolean PayRMB(AccountDTO accountDTO) {
        return accountMapper.updateRMBBalance(accountDTO) > 0;
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmPayRMB(AccountDTO accountDTO) {
        accountMapper.confirmRMB(accountDTO);
        return Boolean.TRUE;
    }

    /**
     * Cancel boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelPayRMB(AccountDTO accountDTO) {
        accountMapper.cancelRMB(accountDTO);
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmPayUSB", cancelMethod = "cancelPayUSB")
    public boolean PayUSB(AccountDTO accountDTO) {
        return accountMapper.updateUSBBalance(accountDTO) > 0;
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmPayUSB(AccountDTO accountDTO) {
        accountMapper.confirmUSB(accountDTO);
        return Boolean.TRUE;
    }

    /**
     * Cancel boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelPayUSB(AccountDTO accountDTO) {
        accountMapper.cancelUSB(accountDTO);
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmIncomeRMB", cancelMethod = "cancelIncomeRMB")
    public boolean IncomeRMB(AccountDTO accountDTO) {
        return accountMapper.addRMBBalance(accountDTO) > 0;
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmIncomeRMB(AccountDTO accountDTO) {
        return Boolean.TRUE;
    }

    /**
     * Cancel boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelIncomeRMB(AccountDTO accountDTO) {
        accountMapper.cancelAddRMBBalance(accountDTO);
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmIncomeUSB", cancelMethod = "cancelIncomeUSB")
    public boolean IncomeUSB(AccountDTO accountDTO) {
        return accountMapper.addUSBBalance(accountDTO) > 0;
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmIncomeUSB(AccountDTO accountDTO) {
        return Boolean.TRUE;
    }

    /**
     * Cancel boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelIncomeUSB(AccountDTO accountDTO) {
        accountMapper.cancelAddUSBBalance(accountDTO);
        return Boolean.TRUE;
    }
}
