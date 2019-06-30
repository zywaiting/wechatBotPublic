package xin.zhuyao.wechat_bot_public.api.request;

import xin.zhuyao.wechat_bot_public.api.response.ApiResponse;

/**
 * @author biezhi
 * @date 2018/1/18
 */
public class StringRequest extends ApiRequest<StringRequest, ApiResponse> {

    public StringRequest(String url) {
        super(url, ApiResponse.class);
    }

}