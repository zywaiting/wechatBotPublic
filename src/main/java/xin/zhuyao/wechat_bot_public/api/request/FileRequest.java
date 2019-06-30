package xin.zhuyao.wechat_bot_public.api.request;

import xin.zhuyao.wechat_bot_public.api.response.FileResponse;

/**
 * 下载文件请求
 *
 * @author biezhi
 * @date 2018/1/18
 */
public class FileRequest extends ApiRequest<FileRequest, FileResponse> {

    public FileRequest(String url) {
        super(url, FileResponse.class);
    }

}
