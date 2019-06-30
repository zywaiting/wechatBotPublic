package xin.zhuyao.wechat_bot_public.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName WechatPublicSendEntity
 * @Description: TODO
 * author zy
 * @date 2019/6/30 3:59
 **/
@Data
@Entity
@NoArgsConstructor
@Table(name = "t_wechat_public_send")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WechatPublicSendEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //公众号名字
    String Name;

    //公众号来自(用于发信息的id)
    String fromUserName;
}
