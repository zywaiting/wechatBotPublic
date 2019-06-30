package xin.zhuyao.wechat_bot_public.service.impl;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xin.zhuyao.wechat_bot_public.WeChatBot;
import xin.zhuyao.wechat_bot_public.api.WeChatApi;
import xin.zhuyao.wechat_bot_public.api.annotation.Bind;
import xin.zhuyao.wechat_bot_public.api.constant.Config;
import xin.zhuyao.wechat_bot_public.api.enums.AccountType;
import xin.zhuyao.wechat_bot_public.api.enums.MsgType;
import xin.zhuyao.wechat_bot_public.api.model.WeChatMessage;
import xin.zhuyao.wechat_bot_public.domain.entity.WechatMessageSendEntity;
import xin.zhuyao.wechat_bot_public.domain.entity.WechatPublicSendEntity;
import xin.zhuyao.wechat_bot_public.repository.WechatMessageSendRepository;
import xin.zhuyao.wechat_bot_public.repository.WechatPubliceSendRepository;
import xin.zhuyao.wechat_bot_public.utils.GuavaCacheUtils;
import xin.zhuyao.wechat_bot_public.utils.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName WeChaetImpl
 * @Description: TODO
 * author zy
 * @date 2019/6/30 4:14
 **/
@Slf4j
public class WeChaetImpl extends WeChatBot {

    private WechatMessageSendRepository wechatMessageSendRepository = SpringServiceImpl.getBean(WechatMessageSendRepository.class);

    private WechatPubliceSendRepository wechatPubliceSendRepository = SpringServiceImpl.getBean(WechatPubliceSendRepository.class);

    public WeChaetImpl(Config config) {
        super(config);
    }

    /**
     * 绑定群聊信息
     *
     * @param message
     */
    @Bind(msgType = MsgType.ALL, accountType = AccountType.TYPE_GROUP)
    public void groupMessage(WeChatMessage message) {
        log.info("接收到群 [{}] 的消息: {}", message.getName(), message.getText());
        if (StringUtils.isNotEmpty(message.getName()) && message.getName().contains("你好")) {
            if (message.getText().contains("@加载")) {
                this.sendMsgByName("朱瑶", message.getText());
            }
            Optional<WechatPublicSendEntity> wechatPublicSendEntityOptional = wechatPubliceSendRepository.findByFromUserName(message.getFromUserName());
            if (!wechatPublicSendEntityOptional.isPresent()) {
                WechatPublicSendEntity wechatPublicSendEntity = new WechatPublicSendEntity();
                wechatPublicSendEntity.setFromUserName(message.getFromUserName());
                wechatPublicSendEntity.setName(message.getName());
                wechatPubliceSendRepository.save(wechatPublicSendEntity);
            }
            Object object = GuavaCacheUtils.get(message.getName());
            if (object != ObjectUtils.NULL) {
                GuavaCacheUtils.put(message.getName(), (Integer) object + 1);
            } else {
                GuavaCacheUtils.put(message.getName(), 1);
            }
            if ((Integer) GuavaCacheUtils.get(message.getName()) % 5 == 0) {
                String sendMessage = "有阅必回\r\n";
                List<WechatMessageSendEntity> wechatMessageSendEntityList = wechatMessageSendRepository.findByIsSend(true);
                if (wechatMessageSendEntityList.size() > 0) {
                    for (WechatMessageSendEntity wechatMessageSendEntity : wechatMessageSendEntityList) {
                        sendMessage = sendMessage + "\r\n\r\n" + wechatMessageSendEntity.getContextUrl();
                    }
                    this.api().sendText(message.getFromUserName(), sendMessage);
                }
            }
            if (message.getText().contains("加入群聊")) {
                String sendMessage = "有阅必回\r\n";
                List<WechatMessageSendEntity> wechatMessageSendEntityList = wechatMessageSendRepository.findByIsSend(true);
                if (wechatMessageSendEntityList.size() > 0) {
                    for (WechatMessageSendEntity wechatMessageSendEntity : wechatMessageSendEntityList) {
                        sendMessage = sendMessage + "\r\n\r\n" + wechatMessageSendEntity.getContextUrl();
                    }
                    this.api().sendText(message.getFromUserName(), sendMessage);
                }
            }
        }

    }

    /**
     * 绑定私聊消息
     *
     * @param message
     */
    @Bind(msgType = {MsgType.TEXT, MsgType.VIDEO, MsgType.IMAGE, MsgType.EMOTICONS}, accountType = AccountType.TYPE_FRIEND)
    public void friendMessage(WeChatMessage message) {
        if (StringUtils.isNotEmpty(message.getName())) {
            log.info("接收到好友 [{}] 的消息: {}", message.getName(), message.getText());
//            this.api().sendText(message.getFromUserName(), "自动回复: " + message.getText());
//            this.api().sendFile("战斗型美少女", "/Users/biezhi/Desktop/Hot_Spots_blade2.0.4_alpha1.html");
        }
    }

    /**
     * 好友验证消息
     *
     * @param message
     */
    @Bind(msgType = MsgType.ADD_FRIEND)
    public void addFriend(WeChatMessage message) {
        log.info("收到好友验证消息: {}", message.getText());
        if (message.getText().contains("java")) {
            this.api().verify(message.getRaw().getRecommend());
        }
    }

}
