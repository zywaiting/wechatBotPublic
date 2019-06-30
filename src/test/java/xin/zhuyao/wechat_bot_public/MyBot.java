package xin.zhuyao.wechat_bot_public;

import lombok.extern.slf4j.Slf4j;
import xin.zhuyao.wechat_bot_public.api.annotation.Bind;
import xin.zhuyao.wechat_bot_public.api.constant.Config;
import xin.zhuyao.wechat_bot_public.api.enums.AccountType;
import xin.zhuyao.wechat_bot_public.api.enums.MsgType;
import xin.zhuyao.wechat_bot_public.api.model.WeChatMessage;
import xin.zhuyao.wechat_bot_public.utils.StringUtils;

/**
 * 我的小机器人
 *
 * @author biezhi
 * @date 2018/1/19
 */
@Slf4j
public class MyBot extends WeChatBot {

    public MyBot(Config config) {
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
//        this.api().sendText(message.getFromUserName(),message.getText());
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

    public static void main(String[] args) {
        new MyBot(Config.me().autoLogin(true).showTerminal(true)).start();
    }

}
