package xin.zhuyao.wechat_bot_public.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import xin.zhuyao.wechat_bot_public.api.model.Account;
import xin.zhuyao.wechat_bot_public.api.model.SyncKey;

import java.util.List;

/**
 * 微信初始化响应
 *
 * @author biezhi
 * @date 2018/1/19
 */
@Data
public class WebInitResponse {

    @SerializedName("BaseResponse")
    private JsonResponse baseResponse;

    @SerializedName("Count")
    private Integer count;

    @SerializedName("ContactList")
    private List<Account> contactList;

    @SerializedName("SyncKey")
    private SyncKey syncKey;

    @SerializedName("User")
    private Account account;

    @SerializedName("ChatSet")
    private String chatSet;

    @SerializedName("SKey")
    private String sKey;

    @SerializedName("InviteStartCount")
    private Integer inviteStartCount;

    @SerializedName("ClickReportInterval")
    private Integer clickReportInterval;

}
