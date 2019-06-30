package xin.zhuyao.wechat_bot_public.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import xin.zhuyao.wechat_bot_public.WeChatClient;
import xin.zhuyao.wechat_bot_public.api.constant.Config;

/**
 * @ClassName MyApplicationRunner
 * @Description: TODO
 * author zy
 * @date 2019/6/30 4:24
 **/
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private WeChatListenerImpl weChatListener;
    @Override
    public void run(ApplicationArguments args) {
//        new WeChaetImpl(Config.me().autoLogin(true).showTerminal(true)).start();
        //新建一个模拟微信客户端
        WeChatClient wechatClient = new WeChatClient();
        //为模拟微信客户端设置监听器
        wechatClient.setListener(weChatListener);
        //启动模拟微信客户端
        wechatClient.startup();
    }
}
