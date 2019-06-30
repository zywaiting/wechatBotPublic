package xin.zhuyao.wechat_bot_public.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import xin.zhuyao.wechat_bot_public.domain.entity.WechatMessageSendEntity;

import java.util.List;
@Service
public interface WechatMessageSendRepository extends JpaRepository<WechatMessageSendEntity, Integer>, JpaSpecificationExecutor<WechatMessageSendEntity> {

    /**
     * 根据是否发送查询数据
     *
     * @param isSend
     * @return
     */
    List<WechatMessageSendEntity> findByIsSend(boolean isSend);
}
