/**
 * 
 */
package com.qzdsoft.eshop.controller.pc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.service.orders.WeixinPayService;
import com.qzdsoft.eshop.service.orders.impl.utils.PayCommonUtil;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.utils.xml.XmlUtil;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author Administrator 2018年2月7日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/wx")
public class WxPayController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger(WxPayController.class);

    @Autowired
    private WeixinPayService weixinPayService;
    
    @RequestMapping("/notify")
    public void callBack() {
        ByteArrayOutputStream outSteam = null;
        InputStream inStream = null;
        String returnXml = "";
        try
        {
            response.setContentType("text/xml");     
            PrintWriter writer = response.getWriter();
            inStream = request.getInputStream();
            outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            
            String requestXml = new String(outSteam.toByteArray(), "utf-8");
            
            logger.debug("微信支付回调xml:{}",requestXml);
            Map<String, String> backInfo = XmlUtil.xml2Map(requestXml);
            SortedMap<String, String> sortedBackInfo = new TreeMap<>(backInfo);
            logger.debug("微信支付回调map:{}",sortedBackInfo);
            if(sortedBackInfo!=null&&sortedBackInfo.size()!=0) {
                
                Response<String> result = weixinPayService.notify(sortedBackInfo);
                logger.debug("微信支付service返回:{}",sortedBackInfo);
                if (ResultEnum.SUCCESS.getCode().equals(result.getCode()))
                {
                    returnXml = PayCommonUtil.setXML("SUCCESS", "OK");
                    writer.write(returnXml);
                    writer.flush();
                }
            }
        }
        catch (Exception ex)
        {
            logger.error("微信通知错误：{}", ExceptionUtil.getStackTrace(ex));
        }finally {
            if(outSteam!=null) {
                try
                {
                    outSteam.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(inStream!=null) {
                try
                {
                    inStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        
    } 

}
