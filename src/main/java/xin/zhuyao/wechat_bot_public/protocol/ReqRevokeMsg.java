package xin.zhuyao.wechat_bot_public.protocol;

public class ReqRevokeMsg {
    public xin.zhuyao.wechat_bot_public.protocol.BaseRequest BaseRequest;
    public String ClientMsgId;
    public String SvrMsgId;
    public String ToUserName;

    public ReqRevokeMsg(xin.zhuyao.wechat_bot_public.protocol.BaseRequest baseRequest, String clientMsgId, String serverMsgId, String toUserName) {
        this.BaseRequest = baseRequest;
        this.ClientMsgId = clientMsgId;
        this.SvrMsgId = serverMsgId;
        this.ToUserName = toUserName;
    }
}
