package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.entity.OrderPayEntity;
import com.sie.framework.type.PayType;
import com.sie.framework.type.SystemType;
import com.sie.service.OrderService;
import com.sie.service.StudentService;
import com.sie.service.bean.ResultBean;
import com.sie.util.*;
import com.sie.web.config.SieWechatPay;
import com.sie.web.config.TruWechatPay;
import com.sie.web.config.WechatPay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private static final String SYSTEM_ACCESS_TOKEN = "un23n4no2bu4bs34";


    @ResponseBody
    @RequestMapping("/getWechatCode.json")
    public ResultBean getWechatCode(String params, String accessToken, HttpServletResponse response) throws Exception {
        logger.info("getWechatCode.json params=" + params + " accessToken=" + accessToken);
        ResultBean resultBean = new ResultBean();

        if (StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)) {
            resultBean.setMessage("token 为空，请检查参数");
            return resultBean;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String,String >maps = mapper.readValue(params, Map.class);
        String orderId = maps.get("orderId");
        if(StringUtil.isBlank(orderId)){
            resultBean.setMessage("orderId 为空，请检查参数");
            return resultBean;
        }
        String systemType = maps.get("systemType");
        if(StringUtil.isBlank(systemType)){
            resultBean.setMessage("systemType 为空，请检查参数");
            return resultBean;
        }
        ServletOutputStream out = null;

        try {
            //生成支付信息
            OrderPayEntity payEntity = orderService.updatePaymentInfo(Integer.parseInt(orderId), PayType.WECHAT.value());
            String payName = "购买";
            String appid = "";
            String mch_id = "";
            String key = "";
            String notify_url = "";
            // 账号信息
            if(systemType.equals(SystemType.SIE.value())){
                appid = SieWechatPay.APP_ID;  // appid
                mch_id = SieWechatPay.MCH_ID; // 商业号
                key = SieWechatPay.API_KEY; // key
                payName += "SIE课程";
                notify_url = SieWechatPay.NOTIFY_URL;
            }else{
                appid = TruWechatPay.APP_ID;  // appid
                mch_id = TruWechatPay.MCH_ID; // 商业号
                key = TruWechatPay.API_KEY; // key
                payName += "TRU课程";
                notify_url = TruWechatPay.NOTIFY_URL;
            }

            String currTime = PayCommonUtil.getCurrTime();
            String strTime = currTime.substring(8, currTime.length());
            String strRandom = PayCommonUtil.buildRandom(4) + "";
            String nonce_str = strTime + strRandom;
//价格后面会放开
            String order_price = payEntity.getPayTotal() + ""; // 价格   注意：价格的单位是分
//            String order_price = 1 + ""; // 价格   注意：价格的单位是分
            String body = payName;   // 商品名称
            String out_trade_no =orderId; // 订单号

            // 获取发起电脑 ip
            String spbill_create_ip = WechatPay.CREATE_IP;
            // 回调接口
            String trade_type = "NATIVE";
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", appid);
            packageParams.put("mch_id", mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", out_trade_no);
            packageParams.put("total_fee", order_price);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", notify_url);
            packageParams.put("trade_type", trade_type);
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
            packageParams.put("sign", sign);
            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HTTPUtil.postData(WechatPay.UFDODER_URL, requestXML);


            Map map = JDomXMLUtil.doXMLParse(resXml);
            String urlCode = (String) map.get("code_url");

            if (urlCode != null && urlCode.length() > 0) {
                response.setContentType("multipart/form-data");
                out = response.getOutputStream();
                QRcodeUtil.makeQRcode(urlCode, "jpg", out);
                out.flush();
                resultBean.setSuccess(true);
                resultBean.setMessage("获取成功");
            }

        } catch (RuntimeException e){
            e.printStackTrace();
            resultBean.setMessage(e.getMessage());
         }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
        }

        return resultBean;
    }

    @RequestMapping("/wechat/sie/nodify")
    public void weixinSieCallback(HttpServletRequest request, HttpServletResponse response) throws Exception{
        wechatCallback(request, response, SieWechatPay.API_KEY);
    }


    @RequestMapping("/wechat/tru/nodify")
    public void weixinTruCallback(HttpServletRequest request, HttpServletResponse response) throws Exception{
        wechatCallback(request, response, TruWechatPay.API_KEY);

    }

    public void wechatCallback(HttpServletRequest request, HttpServletResponse response, String key){
        //读取参数
        InputStream inputStream ;
        try {
            StringBuffer sb = new StringBuffer();
            inputStream = request.getInputStream();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
            inputStream.close();

            //解析xml成map
            Map<String, String> m = new HashMap<String, String>();
            m = JDomXMLUtil.doXMLParse(sb.toString());

            //过滤空 设置 TreeMap
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            Iterator it = m.keySet().iterator();
            while (it.hasNext()) {
                String parameter = (String) it.next();
                String parameterValue = m.get(parameter);

                String v = "";
                if (null != parameterValue) {
                    v = parameterValue.trim();
                }
                packageParams.put(parameter, v);
            }


            logger.info(packageParams.toString());
            //判断签名是否正确
            if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
                //------------------------------
                //处理业务开始
                //------------------------------
                String resXml = "";
                if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                    // 这里是支付成功
                    //////////执行自己的业务逻辑////////////////
                    String mch_id = (String) packageParams.get("mch_id");
                    String openid = (String) packageParams.get("openid");
                    String is_subscribe = (String) packageParams.get("is_subscribe");
                    String out_trade_no = (String) packageParams.get("out_trade_no");
                    String total_fee = (String) packageParams.get("total_fee");

                    logger.info("mch_id:" + mch_id);
                    logger.info("openid:" + openid);
                    logger.info("is_subscribe:" + is_subscribe);
                    logger.info("out_trade_no:" + out_trade_no);
                    logger.info("total_fee:" + total_fee);

                    //////////执行自己的业务逻辑////////////////
                    orderService.completePaymentInfo(Integer.parseInt(out_trade_no), Double.parseDouble(total_fee));

                    logger.info("支付成功");
                    //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

                } else {
                    logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }
                //------------------------------
                //处理业务完毕
                //------------------------------
                BufferedOutputStream out = new BufferedOutputStream(
                        response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            } else {
                logger.info("通知签名验证失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
