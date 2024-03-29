package xin.zhuyao.wechat_bot_public.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 微信基础响应
 *
 * @author biezhi
 * @date 2018/1/19
 */
@Data
public class BaseResponse {

    @SerializedName("Ret")
    private Integer ret;

    @SerializedName("ErrMsg")
    private String msg;

}
