package com.sie.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.entity.OrderPayEntity;
import com.sie.framework.type.PayType;
import com.sie.framework.type.SystemType;
import com.sie.service.OrderService;
import com.sie.service.bean.ResultBean;
import com.sie.util.*;
import com.sie.web.config.SieWechatPay;
import com.sie.web.config.TruWechatPay;
import com.sie.web.config.WechatPay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private OrderService orderService;

    @Value("${file.upload.url}")
    private String fileUploadUrl;


    // 支付宝当面付2.0服务
    private static AlipayTradeService tradeService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private static final String SYSTEM_ACCESS_TOKEN = "un23n4no2bu4bs34";


    @RequestMapping(value = "/getWechatCode.json", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean getWechatCode(String params, String accessToken, HttpServletResponse response) throws Exception {
        logger.info("getWechatCode.json params=" + params + " accessToken=" + accessToken);
        ResultBean resultBean = new ResultBean();

        if (StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)) {
            resultBean.setMessage("token 为空，请检查参数");
            return resultBean;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> maps = mapper.readValue(params, Map.class);
        String orderId = maps.get("orderId");
        if (StringUtil.isBlank(orderId)) {
            resultBean.setMessage("orderId 为空，请检查参数");
            return resultBean;
        }
        String systemType = maps.get("systemType");
        if (StringUtil.isBlank(systemType)) {
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
            if (systemType.equals(SystemType.SIE.value() + "")) {
                appid = SieWechatPay.APP_ID;  // appid
                mch_id = SieWechatPay.MCH_ID; // 商业号
                key = SieWechatPay.API_KEY; // key
                payName += "SIE课程";
                notify_url = SieWechatPay.NOTIFY_URL;
            } else {
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
            String order_price = payEntity.getPayTotal().intValue() * 100 + ""; // 价格   注意：价格的单位是分
//            String order_price = 1 + ""; // 价格   注意：价格的单位是分
            String body = payName;   // 商品名称
            String out_trade_no = DateUtil.format(new Date(), "yyyyMMddHHmmss") + orderId; // 订单号


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

        } catch (RuntimeException e) {
            e.printStackTrace();
            resultBean.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

        return null;
    }

    @RequestMapping("/wechat/sie/nodify")
    public void weixinSieCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("sie wechat payment callback");
        wechatCallback(request, response, SieWechatPay.API_KEY);
    }


    @RequestMapping("/wechat/tru/nodify")
    public void weixinTruCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("tru wechat payment callback");
        wechatCallback(request, response, TruWechatPay.API_KEY);

    }

    public void wechatCallback(HttpServletRequest request, HttpServletResponse response, String key) {
        //读取参数
        InputStream inputStream;
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

                    out_trade_no = out_trade_no.substring(12);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 测试当面付2.0生成支付二维码
    @RequestMapping(value = "/getAipayCode.json", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean getAipayCode(String params, String accessToken, HttpServletResponse response) throws Exception {
        logger.info("getAipayCode.json params=" + params + " accessToken=" + accessToken);
        ResultBean resultBean = new ResultBean();

        if (StringUtil.isBlank(accessToken) || !accessToken.equals(SYSTEM_ACCESS_TOKEN)) {
            resultBean.setMessage("token 为空，请检查参数");
            return resultBean;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> maps = mapper.readValue(params, Map.class);
        String orderId = maps.get("orderId");
        if (StringUtil.isBlank(orderId)) {
            resultBean.setMessage("orderId 为空，请检查参数");
            return resultBean;
        }
        String systemType = maps.get("systemType");
        if (StringUtil.isBlank(systemType)) {
            resultBean.setMessage("systemType 为空，请检查参数");
            return resultBean;
        }

        String outTradeNo = orderId + "000";
        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "";
        OutputStream out = null;
        try {

            String nodidfyUrl = "";
            //生成支付信息
            OrderPayEntity payEntity = orderService.updatePaymentInfo(Integer.parseInt(orderId), PayType.AIPAY.value());
            if (systemType.equals(SystemType.SIE.value() + "")) {
                subject = "购买SIE课程";
                Configs.init("zfbinfo_sie.properties");
                nodidfyUrl = "http://120.27.13.112/api/payment/apply/sie/nodify";
                tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
            } else {
                subject = "购买tru课程";
                nodidfyUrl = "http://120.27.13.112/api/payment/apply/tru/nodify";
                Configs.init("zfbinfo_tru.properties");
                tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
            }
            // (必填) 订单总金额，单位为元，不能超过1亿元
            // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
//            String totalAmount = "0.01";
            String totalAmount = payEntity.getPayTotal()+"";

            // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
            // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
            String undiscountableAmount = "0";

            // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
            // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
            String sellerId = "";

            // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
            String body = subject + totalAmount + "元";

            // 商户操作员编号，添加此参数可以为商户操作员做销售统计
            String operatorId = "test_operator_id";

            // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
            String storeId = "test_store_id";

            // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
            ExtendParams extendParams = new ExtendParams();
            extendParams.setSysServiceProviderId("2088100200300400500");
            // 支付超时，定义为120分钟
            String timeoutExpress = "120m";

            // 商品明细列表，需填写购买商品详细信息，
            List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
//            // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
            GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", subject, 1, 1);
//            // 创建好一个商品后添加至商品明细列表
            goodsDetailList.add(goods1);
//
//            // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
//            GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
//            goodsDetailList.add(goods2);

            // 创建扫码支付请求builder，设置请求参数
            AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                    .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                    .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                    .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                    .setTimeoutExpress(timeoutExpress)
                    .setNotifyUrl(nodidfyUrl).setGoodsDetailList(goodsDetailList);//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置

            AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
            switch (result.getTradeStatus()) {
                case SUCCESS:
                    logger.info("支付宝预下单成功: )");

                    AlipayTradePrecreateResponse response1 = result.getResponse();
//                    dumpResponse(response);
//
//                    // 需要修改为运行机器上的路径
                    String filePath = String.format(fileUploadUrl + "/applycode/-%s.png",
                            response1.getOutTradeNo());
                    logger.info("filePath:" + filePath);

                    ZxingUtils.getQRCodeImge(response1.getQrCode(), 256, filePath);
                    File file = new File(filePath);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    response.setContentType("multipart/form-data");
                    out = response.getOutputStream();
                    FileInputStream ips = null;
                    //获取图片存放路径
                    ips = new FileInputStream(new File(filePath));
                    response.setContentType("multipart/form-data");
                    out = response.getOutputStream();
                    //读取文件流
                    int len = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((len = ips.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                    break;

                case FAILED:
                    logger.error("支付宝预下单失败!!!");
                    break;

                case UNKNOWN:
                    logger.error("系统异常，预下单状态未知!!!");
                    break;

                default:
                    logger.error("不支持的交易状态，交易返回异常!!!");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

        return null;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/apply/sie/nodify", method = RequestMethod.POST)
    public void alipaySieNodefiy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Configs.init("zfbinfo_sie.properties");
        alipay_notify(request, response);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/apply/tru/nodify", method = RequestMethod.POST)
    public void alipayTruNodefiy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Configs.init("zfbinfo_tru.properties");
        alipay_notify(request, response);
    }


    public void alipay_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("支付宝付款异步通知！");
        String message = "success";
        Map<String, String> params = new HashMap<String, String>();
        // 取出所有参数是为了验证签名
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }
        //验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            message = "failed";
        }
        if (signVerified) {
            logger.info("验证签名成功！");
            // 若参数中的appid和填入的appid不相同，则为异常通知
            if (!Configs.getAppid().equals(params.get("app_id"))) {
                logger.info("与付款时的appid不同，此为异常通知，应忽略！");
                message = "failed";
            } else {
                String outtradeno = params.get("out_trade_no");
                logger.info(outtradeno + "号订单回调通知。");
                //在数据库中查找订单号对应的订单，并将其金额与数据库中的金额对比，若对不上，也为异常通知
                String status = params.get("trade_status");
                if (status.equals("WAIT_BUYER_PAY")) { // 如果状态是正在等待用户付款
                } else if (status.equals("TRADE_CLOSED")) { // 如果状态是未付款交易超时关闭，或支付完成后全额退款
                } else if (status.equals("TRADE_SUCCESS") || status.equals("TRADE_FINISHED")) { // 如果状态是已经支付成功
                    orderService.completePaymentInfo(Integer.parseInt(outtradeno) / 1000, -1.0);
                }
                logger.info(outtradeno + "订单的状态已经修改为" + status);
            }
        } else { // 如果验证签名没有通过
            message = "failed";
            logger.info("验证签名失败！");
        }
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(message.getBytes());
        out.flush();
        out.close();
    }


    public static void main(String[] args) throws AlipayApiException {
        //实例化客户端
        Configs.init("zfbinfo_tru.properties");
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", Configs.getAppid(), Configs.getPrivateKey(), "json", "UTF-8", Configs.getAlipayPublicKey(), "RSA");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数
//此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        AlipayOpenPublicTemplateMessageIndustryModifyResponse response = alipayClient.execute(request);
//调用成功，则处理业务逻辑
        System.out.println(response.getCode());
        System.out.println(response.getBody());
        if(response.isSuccess()){
            //.....
        }
    }
}
