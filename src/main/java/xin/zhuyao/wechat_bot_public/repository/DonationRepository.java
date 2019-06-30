package xin.zhuyao.wechat_bot_public.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xin.zhuyao.wechat_bot_public.domain.entity.DonationEntity;

public interface DonationRepository extends JpaRepository<DonationEntity, Integer>, JpaSpecificationExecutor<DonationEntity> {
}
