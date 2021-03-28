package org.example.common.walletFrozen.service;

import org.apache.ibatis.annotations.Update;
import org.example.common.walletFrozen.dto.WalletFrozenDTO;


/**
 * <p>
 * 用户冻结钱包 服务类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
public interface IWalletFrozenService{

    int freezeCNY(WalletFrozenDTO walletFrozenDTO);

    int unfreezeCNY(WalletFrozenDTO walletFrozenDTO);
}
