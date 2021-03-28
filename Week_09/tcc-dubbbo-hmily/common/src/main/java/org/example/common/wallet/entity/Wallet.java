package org.example.common.wallet.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户钱包
 * </p>
 *
 * @author xuwang
 * @since 2021-03-28
 */
@Data
public class Wallet implements Serializable {

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 人民币余额
     */
    private Integer amountCny;

    /**
     * 美元余额
     */
    private Integer amountUsb;


}
