package xin.zhuyao.wechat_bot_public.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import xin.zhuyao.wechat_bot_public.api.enums.RetCode;

/**
 * 心跳检查返回
 *
 * @author biezhi
 * @date 2018/1/20
 */
@Data
@AllArgsConstructor
public class SyncCheckRet {

    private RetCode retCode;
    private int     selector;

}
