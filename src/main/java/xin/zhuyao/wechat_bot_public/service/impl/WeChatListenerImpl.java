package xin.zhuyao.wechat_bot_public.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.zhuyao.wechat_bot_public.WeChatClient;
import xin.zhuyao.wechat_bot_public.domain.entity.DonationEntity;
import xin.zhuyao.wechat_bot_public.domain.entity.WechatMessageSendEntity;
import xin.zhuyao.wechat_bot_public.domain.entity.WechatPublicSendEntity;
import xin.zhuyao.wechat_bot_public.entity.contact.WXContact;
import xin.zhuyao.wechat_bot_public.entity.contact.WXGroup;
import xin.zhuyao.wechat_bot_public.entity.message.WXMessage;
import xin.zhuyao.wechat_bot_public.repository.DonationRepository;
import xin.zhuyao.wechat_bot_public.repository.WechatMessageSendRepository;
import xin.zhuyao.wechat_bot_public.repository.WechatPubliceSendRepository;
import xin.zhuyao.wechat_bot_public.utils.GuavaCacheUtils;
import xin.zhuyao.wechat_bot_public.utils.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName WeChatListenerImpl
 * @Description: TODO
 * author zy
 * @date 2019/6/30 8:00
 **/
@Service
@Slf4j
public class WeChatListenerImpl extends WeChatClient.WeChatListener {

    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private WechatMessageSendRepository wechatMessageSendRepository;

    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void onQRCode(@Nonnull WeChatClient client, @Nonnull String qrCode) {
        System.out.println("onQRCode：" + qrCode);
    }

    @Override
    public void onLogin(@Nonnull WeChatClient client) {
        System.out.println(String.format("onLogin：您有%d名好友、活跃微信群%d个", client.userFriends().size(), client.userGroups().size()));
    }

    @Override
    public void onMessage(@Nonnull WeChatClient client, @Nonnull WXMessage message) {
        if (message.getFromGroup() == null) {
            if (message.getFromUser() != null&&("微信支付").equals(message.getFromUser().getName())) {
                if (message.getContent().contains("[CDATA[收款金额")) {
                    int begin = message.getContent().indexOf("[CDATA[收款金额");
                    int end = message.getContent().indexOf("已存入零钱。点击可查看详情]]");
                    String substring = message.getContent().substring(begin, end);
                    int moneyBegin = substring.indexOf("收款金额￥");
                    int moneyEnd = substring.indexOf("<br/>付款方");
                    String money = substring.substring(moneyBegin, moneyEnd).replace("收款金额￥", "");
                    int nameBegin = substring.indexOf("付款方备注");
                    int nameEnd = substring.indexOf("<br/>汇总今日");
                    String name = substring.substring(nameBegin, nameEnd).replace("付款方备注", "");
                    DonationEntity donationEntity = new DonationEntity();
                    donationEntity.setMoney(new Double(money));
                    donationEntity.setName(name);
                    donationRepository.save(donationEntity);
                }
            }
        }
        if (message.getFromGroup() != null) {
            WXGroup fromGroup = message.getFromGroup();
            log.info("接收到群 [{}] 的消息: {}", fromGroup.getName(), message.getContent());
            if (StringUtils.isNotEmpty(fromGroup.getName()) && fromGroup.getName().contains("互")) {
//                Optional<WechatPublicSendEntity> wechatPublicSendEntityOptional = wechatPubliceSendRepository.findByFromUserName(fromGroup.getId());
//                if (!wechatPublicSendEntityOptional.isPresent()) {
//                    WechatPublicSendEntity wechatPublicSendEntity = new WechatPublicSendEntity();
//                    wechatPublicSendEntity.setFromUserName(fromGroup.getId());
//                    wechatPublicSendEntity.setName(fromGroup.getName());
//                    wechatPubliceSendRepository.save(wechatPublicSendEntity);
//                }
                Object object = GuavaCacheUtils.get(fromGroup.getName());
                if (object != ObjectUtils.NULL) {
                    GuavaCacheUtils.put(fromGroup.getName(), (Integer) object + 1);
                } else {
                    GuavaCacheUtils.put(fromGroup.getName(), 1);
                }
                if ((Integer) GuavaCacheUtils.get(fromGroup.getName()) % 20 == 0) {
                    String sendMessage = "有阅、点广告,必回\r\n";
                    List<WechatMessageSendEntity> wechatMessageSendEntityList = wechatMessageSendRepository.findByIsSend(true);
                    if (wechatMessageSendEntityList.size() > 0) {
                        for (WechatMessageSendEntity wechatMessageSendEntity : wechatMessageSendEntityList) {
                            sendMessage = sendMessage + "\r\n\r\n" + wechatMessageSendEntity.getContextUrl();
                        }
                        WXContact contact = client.userContact(fromGroup.getId());
                        if (contact != null) {
                            log.info("111111111111111111111111111111111111111111111111111111111");
                            log.info("success:" + GSON.toJson(client.sendText(contact, sendMessage)));
                        } else {
                            log.info("联系人未找到");
                        }
                    }
                }
                if (message.getContent().contains("加入群聊")) {
                    String sendMessage = "有阅、点广告,必回\r\n";
                    List<WechatMessageSendEntity> wechatMessageSendEntityList = wechatMessageSendRepository.findByIsSend(true);
                    if (wechatMessageSendEntityList.size() > 0) {
                        for (WechatMessageSendEntity wechatMessageSendEntity : wechatMessageSendEntityList) {
                            sendMessage = sendMessage + "\r\n\r\n" + wechatMessageSendEntity.getContextUrl();
                        }
                        WXContact contact = client.userContact(fromGroup.getId());
                        if (contact != null) {
                            log.info("222222222222222222222222222222222222222222222222222222");
                            log.info("success:" + GSON.toJson(client.sendText(contact, sendMessage)));
                        } else {
                            log.info("联系人未找到");
                        }
                    }
                }
            }
        }
    }
//            if (message instanceof WXVerify) {
//                //是好友请求消息，自动同意好友申请
//                client.passVerify((WXVerify) message);
//            } else if (message instanceof WXLocation && message.fromUser != null && !message.fromUser.id.equals(client.userMe().id)) {
//                // 如果对方告诉我他的位置，发送消息的不是自己，则我也告诉他我的位置
//                if (message.fromGroup != null) {
//                    // 群消息
//                    // client.sendLocation(message.fromGroup, "120.14556", "30.23856", "我在这里", "西湖");
//                } else {
//                    // 用户消息
//                    client.sendLocation(message.fromUser, "120.14556", "30.23856", "我在这里", "西湖");
//                }
//            } else if (message instanceof WXText && message.fromUser != null && !message.fromUser.id.equals(client.userMe().id)) {
//                //是文字消息，并且发送消息的人不是自己，发送相同内容的消息
//                if (message.fromGroup != null) {
//                    // 群消息
//                    // client.sendText(message.fromGroup, message.content);
//                } else {
//                    // 用户消息
//                    client.sendText(message.fromUser, message.content);
//                }
//            }
//        }


    @Override
    public void onContact(@Nonnull WeChatClient client, @Nullable WXContact oldContact, @Nullable WXContact newContact) {
        System.out.println(String.format("检测到联系人变更:旧联系人名称：%s:新联系人名称：%s", (oldContact == null ? "null" : oldContact.name), (newContact == null ? "null" : newContact.name)));
    }
}
