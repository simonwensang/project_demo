package com.project.mc.common.message;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.JSMSClient;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.JSMSConfig;
import cn.jsms.api.common.model.SMSPayload;
import com.project.mc.common.CommonBizException;
import com.project.mc.common.jpush.JiguangPushSeller;
import com.project.mc.result.McResultCode;
import com.project.mc.common.jpush.JiguangPushSeller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sang on 2018/12/19.
 */
@Component
public class JPushMessage {

    private static final Logger logger = LoggerFactory.getLogger(JiguangPushSeller.class);
    @Value("${jiguang.push.seller.appKey}")
    private  String appKey  ;
    @Value("${jiguang.push.seller.masterSecret}")
    private  String  masterSecret  ;

    private JSMSClient jsmsClient;

    @PostConstruct
    private void init(){
        JSMSConfig jsmsConfig = JSMSConfig.getInstance();
        jsmsClient = new JSMSClient(masterSecret,appKey,null,jsmsConfig);
    }

    public void send(String mobile,String code) {
        try{
            SMSPayload.Builder smsPayload = SMSPayload.newBuilder();
            smsPayload.setCode(code);
            smsPayload.setMobileNumber(mobile);
            smsPayload.setTempId(159106);
            Map<String, String> temp_para = new HashMap<String, String>();
            temp_para.put("code",code);
            smsPayload.setTempPara(temp_para);
            SendSMSResult result = jsmsClient.sendTemplateSMS(smsPayload.build());
            if(result != null && result.isResultOK()){
                logger.info("手机号" + mobile + "的信息发送成功！");
            }else{
                logger.info("手机号" + mobile + "的信息发送失败！");
                throw new CommonBizException(McResultCode.SHORT_MESSAGE_ERROR,mobile);
            }
        }catch(  APIConnectionException e){
            logger.info("Connection error. Should retry later. ");
            throw new CommonBizException(McResultCode.SHORT_MESSAGE_ERROR,mobile);
        }catch (APIRequestException e){
            logger.info("Error response from JPush server. Should review and fix it. ");
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            throw new CommonBizException(McResultCode.SHORT_MESSAGE_ERROR,mobile);
        }
    }

   /* public  JPushMessage(String appKey,String masterSecret){
        jsmsClient = new JSMSClient(masterSecret,appKey);
    }

    public static void main(String[] args)  {
        try{
            String mobile = "18616328868";
            String code = "123456";
            JPushMessage jPushMessage = new JPushMessage("6db873975a2fafbf26d6e2ee","d8e8498ff6bd4afcecae8ad4");

            SendSMSResult result = jPushMessage.send(mobile,code);
            if(result != null && result.isResultOK()){
                System.out.println("手机号" + mobile + "的信息发送成功！");
            }else{
                System.out.println("手机号" + mobile + "的信息发送失败！");
            }
        }catch(  APIConnectionException e){
            System.out.println("Connection error. Should retry later. ");
        }catch (APIRequestException e){
            System.out.println("Error response from JPush server. Should review and fix it. ");
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            System.out.println("Msg ID: " + e.getMsgId());
        }


    }*/

}
