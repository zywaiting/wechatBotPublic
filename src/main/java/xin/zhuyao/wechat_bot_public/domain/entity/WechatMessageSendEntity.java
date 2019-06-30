package xin.zhuyao.wechat_bot_public.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName WechatMessageSendEntity
 * @Description: TODO
 * author zy
 * @date 2019/6/30 4:05
 **/
@Data
@Entity
@NoArgsConstructor
@Table(name = "t_wechat_message_send")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WechatMessageSendEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //公众号文章路径
    String contextUrl;

    //公众号图片路径
    String imageUrl;

    //是否发送
    Boolean isSend;
}
