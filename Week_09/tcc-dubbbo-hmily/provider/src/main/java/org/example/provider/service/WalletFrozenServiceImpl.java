package org.example.provider.service;

import org.example.common.wallet.dto.WalletDTO;
import org.example.common.wallet.mapper.WalletMapper;
import org.example.common.wallet.service.IWalletService;
import org.example.common.walletFrozen.dto.WalletFrozenDTO;
import org.example.common.walletFrozen.mapper.WalletFrozenMapper;
import org.example.common.walletFrozen.service.IWalletFrozenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户冻结钱包 服务实现类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
@Service("walletFrozenService")
public class WalletFrozenServiceImpl implements IWalletFrozenService {
    private final WalletFrozenMapper walletFrozenMapper;

    @Autowired(required = false)
    public WalletFrozenServiceImpl(WalletFrozenMapper walletFrozenMapper) {
        this.walletFrozenMapper = walletFrozenMapper;
    }

    @Override
    public int freezeCNY(WalletFrozenDTO walletFrozenDTO) {
        return walletFrozenMapper.freezeCNY(walletFrozenDTO);
    }

    @Override
    public int unfreezeCNY(WalletFrozenDTO walletFrozenDTO) {
        return walletFrozenMapper.unfreezeCNY(walletFrozenDTO);
    }
}
