package xin.zhuyao.wechat_bot_public.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xin.zhuyao.wechat_bot_public.domain.entity.WechatPublicSendEntity;

import java.util.Optional;

public interface WechatPubliceSendRepository extends JpaRepository<WechatPublicSendEntity, Integer>, JpaSpecificationExecutor<WechatPublicSendEntity> {

    /**
     * 根据fromUserName 查询数据
     *
     * @param fromUserName
     * @return
     */
    Optional<WechatPublicSendEntity> findByFromUserName(String fromUserName);
}
