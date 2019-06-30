package xin.zhuyao.wechat_bot_public;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WechatBotPublicApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatBotPublicApplication.class, args);
    }

}
