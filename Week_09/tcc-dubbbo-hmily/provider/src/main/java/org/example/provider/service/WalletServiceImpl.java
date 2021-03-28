package org.example.provider.service;

import org.example.common.wallet.dto.WalletDTO;
import org.example.common.wallet.mapper.WalletMapper;
import org.example.common.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户钱包 服务实现类
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
@Service("walletService")
public class WalletServiceImpl implements IWalletService {
    private final WalletMapper walletMapper;

    @Autowired(required = false)
    public WalletServiceImpl(WalletMapper walletMapper) {
        this.walletMapper = walletMapper;
    }

    @Override
    public int payCNY(WalletDTO walletDTO) {
        return walletMapper.payCNY(walletDTO);
    }

    @Override
    public int inComeUsb(WalletDTO walletDTO) {
        return walletMapper.incomeUSB(walletDTO);
    }

    @Override
    public int inComeCNY(WalletDTO walletDTO) {
        return walletMapper.inComeCNY(walletDTO);
    }
}
