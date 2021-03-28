package org.example.common.walletFrozen.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户冻结钱包
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
@Data
@Builder
public class WalletFrozen implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 冻结人民币
     */
    private Integer moneyCny;

    /**
     * 冻结美元
     */
    private Integer moneyUsb;


}
