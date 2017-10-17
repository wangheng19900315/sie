package com.sie.util;

import com.sie.util.model.OAuthInfo;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wangheng on 2017/10/16.
 */
public class WeiXinUtil {

    /**
     * 第一步：用户同意授权，获取code(引导关注者打开如下页面：)
     * 获取 code、state
     */
    public static String getStartURLToGetCode()  {

        String takenUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        takenUrl= takenUrl.replace("APPID", "APPID");
        try {
            takenUrl= takenUrl.replace("REDIRECT_URI", URLEncoder.encode("REDIRECT_URI", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //FIXME ： snsapi_userinfo
        takenUrl= takenUrl.replace("SCOPE", "snsapi_userinfo");
        System.out.println(takenUrl);
        return takenUrl;
    }


    /**
     * 获取access_token、openid
     * 第二步：通过code获取access_token
     * @param code url = "https://api.weixin.qq.com/sns/oauth2/access_token
     *   ?appid=APPID
     *   &secret=SECRET
     *   &code=CODE
     *   &grant_type=authorization_code"
     * */
    public static OAuthInfo getAccess_token(String code){
        String authUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code ";
        authUrl= authUrl.replace("APPID", "APPID");
        authUrl = authUrl.replace("SECRET", "SECRET");
        authUrl = authUrl.replace("CODE", code);
        String jsonString = HTTPUtil.sendApachePostRequest(authUrl,null);
        System.out.println("jsonString: " + jsonString);
        OAuthInfo auth = null;
        try {
            auth = (OAuthInfo) JSONObject.toBean(JSONObject.fromObject(jsonString),OAuthInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auth;
    }
}
