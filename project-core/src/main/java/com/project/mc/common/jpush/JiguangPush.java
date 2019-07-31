package com.project.mc.common.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JiguangPush {

    private static final Logger log = LoggerFactory.getLogger(JiguangPush.class);
    @Value("${jiguang.push.appKey}")
    private  String appKey  ;
    @Value("${jiguang.push.masterSecret}")
    private  String  masterSecret  ;

    private  JPushClient jPushClient;

    @PostConstruct
    protected  void init(){
        ClientConfig clientConfig = ClientConfig.getInstance();
        this.jPushClient = new JPushClient(this.masterSecret, this.appKey, null, clientConfig);
    }

    //private static final String ALERT = "{\"orderId\":\"819456627386888192\",\"type\":\"1\"}";

   /*public static void main(String[] args) {
        String alias = "816206425427292160";//声明别名
        log.info("对别名" + alias + "的用户推送信息");
       try{

           PushResult result = JiguangPush.push(alias,ALERT);

           if(result != null && result.isResultOK()){
               log.info("针对别名" + alias + "的信息推送成功！");
           }else{
               log.info("针对别名" + alias + "的信息推送失败！");
           }
       } catch (APIConnectionException e) {
           log.error("Connection error. Should retry later. ", e);

       } catch (APIRequestException e) {
           log.error("Error response from JPush server. Should review and fix it. ", e);
           log.info("HTTP Status: " + e.getStatus());
           log.info("Error Code: " + e.getErrorCode());
           log.info("Error Message: " + e.getErrorMessage());
           log.info("Msg ID: " + e.getMsgId());

       }
    }*/

    /**
     * 生成极光推送对象PushPayload（采用java SDK）
     * @param alias
     * @param alert
     * @return PushPayload
     */
    public PushPayload buildPushObject_android_ios_alias_alert(String alias, String alert){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtra("type", "infomation")
                                .setAlert(alert)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtra("type", "infomation")
                                .setAlert(alert)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)//true-推送生产环境 false-推送开发环境（测试使用参数）
                        //.setTimeToLive(90)//消息在JPush服务器的失效时间（测试使用参数）
                        .build())
                .build();
    }
    public PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String alias, String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                        .setMsgContent(alert)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    /**
     * 极光推送方法(采用java SDK)
     * @param alias
     * @param alert
     * @return PushResult
     */
    public PushResult push(String alias,String alert) throws APIConnectionException,APIRequestException{
        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(alias,alert);
        return jPushClient.sendPush(payload);
    }


}
