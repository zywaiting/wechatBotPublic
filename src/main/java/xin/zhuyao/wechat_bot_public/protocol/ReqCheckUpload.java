package xin.zhuyao.wechat_bot_public.protocol;

import me.xuxiaoxiao.xtools.common.XTools;

import java.io.File;

public class ReqCheckUpload {
    public final BaseRequest BaseRequest;
    public final String FileMd5;
    public final String FileName;
    public final long FileSize;
    public final int FileType;
    public String FromUserName;
    public String ToUserName;


    public ReqCheckUpload(BaseRequest baseRequest, File file, String fromUserName, String toUserName) {
        this.BaseRequest = baseRequest;
        this.FileMd5 = XTools.md5(file);
        this.FileName = file.getName();
        this.FileSize = file.length();
        this.FileType = 7;
        this.FromUserName = fromUserName;
        this.ToUserName = toUserName;
    }
}
