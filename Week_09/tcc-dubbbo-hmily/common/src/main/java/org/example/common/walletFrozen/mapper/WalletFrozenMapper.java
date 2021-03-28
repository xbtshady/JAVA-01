package org.example.common.walletFrozen.mapper;

import org.apache.ibatis.annotations.Update;
import org.example.common.wallet.dto.WalletDTO;
import org.example.common.walletFrozen.dto.WalletFrozenDTO;

/**
 * <p>
 * 用户冻结钱包 Mapper 接口
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
public interface WalletFrozenMapper{
    @Update("update wallet_frozen set money_cny = money_cny + #{amount}" +
            " where userId =#{userId}")
    int freezeCNY(WalletFrozenDTO walletFrozenDTO);


    @Update("update wallet_frozen set money_cny = money_cny - #{amount}" +
            " where userId =#{userId}")
    int unfreezeCNY(WalletFrozenDTO walletFrozenDTO);
}
