package org.example.common.wallet.service;


import org.example.common.wallet.dto.WalletDTO;

/**
 * <p>
 * 用户钱包 服务类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
public interface IWalletService{
    //支付人民币
    int payCNY(WalletDTO walletDTO);

    int inComeUsb(WalletDTO walletDTO);

    int inComeCNY(WalletDTO walletDTO);
}
