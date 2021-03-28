package example.consumer.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = -8551347266419380333L;

    //支付用户id
    private Integer user_pay;
    //收入用户id
    private Integer user_income;
    //金额
    private Integer count;
}
