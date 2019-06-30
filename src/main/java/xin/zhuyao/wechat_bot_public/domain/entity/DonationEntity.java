package xin.zhuyao.wechat_bot_public.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName DonationEntity
 * @Description: TODO
 * author zy
 * @date 2019/6/30 14:43
 **/
@Data
@Entity
@NoArgsConstructor
@Table(name = "t_wechat_donation")
public class DonationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //捐赠者
    String name;

    //金额
    Double money;
}
