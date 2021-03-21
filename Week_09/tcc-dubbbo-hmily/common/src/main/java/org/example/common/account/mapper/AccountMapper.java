package org.example.common.account.mapper;


import org.apache.ibatis.annotations.Update;
import org.example.common.account.DTO.AccountDTO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuwang
 * @since 2021-03-21
 */
public interface AccountMapper{
    /**
     * 修改用户人民币余额
     * @param accountDTO
     * @return
     */
    @Update("update account set rmb_balance = rmb_balance - #{amount}," +
            " freeze_rmb_amount= freeze_rmb_amount + #{amount}" +
            " where user_id =#{userId}  and  rmb_balance > 0  ")
    int updateRMBBalance(AccountDTO accountDTO);

    /**
     * 修改用户美元余额
     * @param accountDTO
     * @return
     */
    @Update("update account set usb_balance = usb_balance - #{amount}," +
            " freeze_usb__amount= freeze_usb__amount + #{amount}" +
            " where user_id =#{userId}  and  usb_balance > 0  ")
    int updateUSBBalance(AccountDTO accountDTO);

    /**
     * 解冻用户人民币冻结余额(取消)
     * @param accountDTO
     * @return
     */
    @Update("update account set rmb_balance = rmb_balance + #{amount}," +
            " freeze_rmb_amount= freeze_rmb_amount -  #{amount} " +
            " where user_id =#{userId}  and freeze_rmb_amount >0")
    int cancelRMB(AccountDTO accountDTO);

    /**
     * 解冻用户美元冻结余额(取消)
     * @param accountDTO
     * @return
     */
    @Update("update account set usb_balance = usb_balance + #{amount}," +
            " freeze_usb__amount= freeze_usb__amount -  #{amount} " +
            " where user_id =#{userId}  and freeze_usb__amount >0")
    int cancelUSB(AccountDTO accountDTO);

    /**
     * Confirm int.
     *
     * @param accountDTO the account dto
     * @return the int
     */
    @Update("update account set " +
            " freeze_rmb_amount= freeze_rmb_amount - #{amount}" +
            " where user_id =#{userId}  and freeze_rmb_amount >0 ")
    int confirmRMB(AccountDTO accountDTO);

    /**
     * Confirm int.
     *
     * @param accountDTO the account dto
     * @return the int
     */
    @Update("update account set " +
            " freeze_usb__amount= freeze_usb__amount - #{amount}" +
            " where user_id =#{userId}  and freeze_usb__amount >0 ")
    int confirmUSB(AccountDTO accountDTO);

    @Update("update account set rmb_balance = rmb_balance + #{amount}," +
            " where user_id =#{userId}")
    int addRMBBalance(AccountDTO accountDTO);

    @Update("update account set usb_balance = usb_balance + #{amount}," +
            " where user_id =#{userId}")
    int addUSBBalance(AccountDTO accountDTO);

    @Update("update account set rmb_balance = rmb_balance - #{amount}," +
            " where user_id =#{userId}")
    int cancelAddRMBBalance(AccountDTO accountDTO);

    @Update("update account set usb_balance = usb_balance - #{amount}," +
            " where user_id =#{userId}")
    int cancelAddUSBBalance(AccountDTO accountDTO);

}
