package com.taoyb.simon.wechat.controller;

import com.taoyb.simon.common.utils.Config;
import com.taoyb.simon.web.utils.BaseController;
import com.taoyb.simon.wechat.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by taoyb on 2018-03-09.
 * 微信
 */
@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
    @Autowired
    private Config config;
    /**
     * @作者 TYB
     * @日期 2018-03-09 下午 6:20
     * @描述 接入微信
     */
    @RequestMapping(value = "/connectWeixin", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody void connectWeixin() throws IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter out = response.getWriter();
        try {
            if (isGet) {
                String signature = request.getParameter("signature");// 微信加密签名
                String timestamp = request.getParameter("timestamp");// 时间戳
                String nonce = request.getParameter("nonce");// 随机数
                String echostr = request.getParameter("echostr");//随机字符串
                if (SignUtil.checkSignature(config.getWechatToken(), signature, timestamp, nonce)) {
                    logger.info("Connect the weixin server is successful.");
                    response.getWriter().write(echostr);
                } else {
                    logger.error("Failed to verify the signature!");
                }
            }else {
                String respMessage = "异常消息！";

                /*try {
                    respMessage = wechatService.weixinPost(request);
                    out.write(respMessage);
                    LOGGER.info("The request completed successfully");
                    LOGGER.info("to weixin server "+respMessage);
                } catch (Exception e) {
                    LOGGER.error("Failed to convert the message from weixin!");
                }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
