package org.example.common.order.mapper;

import org.apache.ibatis.annotations.Update;
import org.example.common.order.entity.Order;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuwang
 * @since 2021-03-19
 */
public interface OrderMapper{
    @Update("INSERT INTO `order`(`user_id`, `status`, `amount`, `unit`) VALUES (" +
            "#{userId}, #{status}, #{amount},#{unit})")
    int insert(Order order);

    @Update("update `order` set status = #{status}  where id = #{id}")
    int update(Order order);
}
