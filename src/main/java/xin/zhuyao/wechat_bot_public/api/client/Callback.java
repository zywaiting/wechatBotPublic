package xin.zhuyao.wechat_bot_public.api.client;

import xin.zhuyao.wechat_bot_public.api.request.ApiRequest;
import xin.zhuyao.wechat_bot_public.api.response.ApiResponse;

import java.io.IOException;

public interface Callback<T extends ApiRequest, R extends ApiResponse> {

    void onResponse(T request, R response);

    void onFailure(T request, IOException e);

}