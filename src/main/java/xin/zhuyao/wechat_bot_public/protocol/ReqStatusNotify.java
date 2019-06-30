package xin.zhuyao.wechat_bot_public.protocol;

public class ReqStatusNotify {
    public xin.zhuyao.wechat_bot_public.protocol.BaseRequest BaseRequest;
    public int Code;
    public String FromUserName;
    public String ToUserName;
    public long ClientMsgId;

    public ReqStatusNotify(xin.zhuyao.wechat_bot_public.protocol.BaseRequest baseRequest, int code, String myName) {
        this.BaseRequest = baseRequest;
        this.Code = code;
        this.FromUserName = myName;
        this.ToUserName = myName;
        this.ClientMsgId = System.currentTimeMillis();
    }
}
