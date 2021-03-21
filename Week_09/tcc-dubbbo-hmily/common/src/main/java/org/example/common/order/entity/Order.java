package org.example.common.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuwang
 * @since 2021-03-19
 */
@Data
public class Order implements Serializable {

    private Integer userId;

    /**
     * 0-失败 1-成功 2-转账中 订单状态
     */
    private Integer status;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 1-人民币 2-美元 单位
     */
    private Integer unit;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
