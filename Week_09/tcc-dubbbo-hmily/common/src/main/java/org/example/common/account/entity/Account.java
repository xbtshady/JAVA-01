package org.example.common.account.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuwang
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    /**
     * 用户人民币余额
     */
    private BigDecimal rmbBalance;

    /**
     * 冻结金额，扣款暂存余额
     */
    private BigDecimal freezeRmbAmount;

    /**
     * 用户美元余额
     */
    private BigDecimal usbBalance;

    /**
     * 冻结美元金额，扣款暂存余额
     */
    private BigDecimal freezeUsbAmount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
