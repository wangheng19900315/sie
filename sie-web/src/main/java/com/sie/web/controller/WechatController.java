package com.sie.web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sie.framework.entity.StudentEntity;
import com.sie.framework.entity.UserEntity;
import com.sie.service.StudentService;
import com.sie.util.HTTPUtil;
import com.sie.util.QrGenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api/wechat")
public class WechatController {

    @Autowired
    private StudentService studentService;

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    /**
     * 作业jar
     */
    @Value("${wechat.login.nodify.url}")
    private String nodifyUrl;


    /**
     * 获取uuid及二维码图片地址
     * @param req
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/showQrCode", method = RequestMethod.GET)
    @ResponseBody
    public String showQrCode(String systemType) throws IOException {
        return "https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=wx3ad6a193b5d5b499&redirect_uri=https%3A%2F%2Fwww.sieschool.org%2Fcn%2Findex.html&response_type=code&scope=" +
                "snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect";
    }


    /**
     * 手机端扫描二维码执行的方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/loginByQr", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void loginByQr(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取二维码链接中的uuid
        String uuid = req.getParameter("uuid");
        logger.info("loginByQr uuid="+ uuid);
        // 通过应用获取共享的uuid集合
        Map uuidMap = (Map) req.getSession().getAttribute("UUID_MAP");
        // 如果集合内没有这个uuid，则响应结果
        if (uuidMap == null || !uuidMap.containsKey(uuid)) {
            resp.getOutputStream().write("二维码不存在或已失效！".getBytes());
            return;
        }
        // 根据微信传来的code来获取用户的openID
        String code = req.getParameter("code");
        try {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3ad6a193b5d5b499"
                    + "&secret=f27365e41fcad39e9c5bcb843487f45a"
                    + "&grant_type=authorization_code" + "&code=" + code;
            Gson gson = new Gson();
            Map map = gson.fromJson(HTTPUtil.sendGetHttpRequest(url),
                    new TypeToken<Map>() {}.getType());
            Object openID = map.get("openid");
            logger.info("loginByQr openID="+ openID);
            if (openID != null && !"".equals(openID)) {
                // 通过openID获取user对象
                StudentEntity user = studentService.loginByOpenid(openID.toString());
                if (user != null) {
                    // 如果查询到某个user拥有该openID，则设置到map集合内
                    uuidMap.put(uuid, user);
                    // 并返回手机端扫描结果
                    resp.getOutputStream().write("登陆成功！".getBytes());
                    return;
                }
            }
            // 如果没有openID参数，或查询不到openID对应的user对象，则移除该uuid，并响应结果
            uuidMap.remove(uuid);
            logger.info("loginByQr 你还未绑定，请关注微信号并绑定账号！并使用微信客户端扫描");
            resp.getOutputStream().write("你还未绑定，请关注微信号并绑定账号！并使用微信客户端扫描！".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * PC端不断进行轮询检查的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/checkScan", method = RequestMethod.GET)
    @ResponseBody
    public void checkScan(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取页面的uuid参数
        String uuid = req.getParameter("uuid");
        //通过应用获取共享的uuid集合
        Map map = (Map) req.getSession().getAttribute("UUID_MAP");
        if (map != null) {
            //查询该uuid是否存在，且二维码已经被扫描并匹配到账号
            if(map.containsKey(uuid)){
                UserEntity user = (UserEntity) map.get(uuid);
                if (user != null) {
                    //从集合中移除
                    map.remove(uuid);
                    //设置登录信息
                    req.getSession().setAttribute("USER_IN_SESSION", user);
                    resp.getOutputStream().write("ok".getBytes());
                }else{
                    resp.getOutputStream().write("native".getBytes());
                }
            }
        }
    }





}
