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
public class JiguangPushSeller {

    private static final Logger log = LoggerFactory.getLogger(JiguangPushSeller.class);
    @Value("${jiguang.push.seller.appKey}")
    private  String appKey  ;
    @Value("${jiguang.push.seller.masterSecret}")
    private  String  masterSecret  ;

    private  JPushClient jPushClient;

    @PostConstruct
    protected  void init(){
        ClientConfig clientConfig = ClientConfig.getInstance();
        this.jPushClient = new JPushClient(this.masterSecret, this.appKey, null, clientConfig);
    }

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
    public   PushResult push(String alias,String alert) throws APIConnectionException,APIRequestException{
        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(alias,alert);
        return jPushClient.sendPush(payload);
    }

    /*public  JiguangPushSeller(String appKey,String masterSecret){
        this.appKey =  appKey;
        this.masterSecret =  masterSecret;
        ClientConfig clientConfig = ClientConfig.getInstance();
        this.jPushClient = new JPushClient(this.masterSecret, this.appKey, null, clientConfig);
    }
    public static void main(String[] args) {
        String alias = "897598862961463296";//声明别名
        String ALERT = "{\"playload\":{\"createTime\":1543401465510,\"machineName\":\"真北路商家酒机\",\"orderId\":\"12434358989080\",\"payment\":100},\"type\":1}";
        System.out.println("对别名" + alias + "的用户推送信息");
       try{
           JiguangPushSeller jiguangPushSeller = new JiguangPushSeller("6db873975a2fafbf26d6e2ee","d8e8498ff6bd4afcecae8ad4");

           PushResult result = jiguangPushSeller.push(alias,ALERT);

           if(result != null && result.isResultOK()){
               System.out.println("针对别名" + alias + "的信息推送成功！");
           }else{
               System.out.println("针对别名" + alias + "的信息推送失败！");
           }
       } catch (APIConnectionException e) {
           System.out.println("Connection error. Should retry later. ");

       } catch (APIRequestException e) {
           System.out.println("Error response from JPush server. Should review and fix it. ");
           System.out.println("HTTP Status: " + e.getStatus());
           System.out.println("Error Code: " + e.getErrorCode());
           System.out.println("Error Message: " + e.getErrorMessage());
           System.out.println("Msg ID: " + e.getMsgId());

       }
    }*/

}
