package xin.zhuyao.wechat_bot_public.protocol;

import java.util.ArrayList;

public class RspBatchGetContact {
    public BaseResponse BaseResponse;
    public int Count;
    public ArrayList<RspInit.User> ContactList;
}
