package org.example.common.wallet.mapper;

import org.apache.ibatis.annotations.Update;
import org.example.common.wallet.dto.WalletDTO;

/**
 * <p>
 * 用户钱包 Mapper 接口
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
public interface WalletMapper{
    @Update("update wallet set amount_cny = amount_cny - #{amount}" +
            " where userId =#{userId} and amount_cny >= #{amount}")
    int payCNY(WalletDTO walletDTO);


    @Update("update wallet set amount_usb = amount_usb + #{amount}" +
            " where userId =#{userId}")
    int incomeUSB(WalletDTO walletDTO);

    @Update("update wallet set amount_cny = amount_cny + #{amount}" +
            " where userId =#{userId}")
    int inComeCNY(WalletDTO walletDTO);
}
