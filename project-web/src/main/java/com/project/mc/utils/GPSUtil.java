package com.project.mc.utils;

import com.alibaba.fastjson.JSON;
import com.project.mc.result.GpsspgResult;
import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static org.apache.poi.util.HexDump.UTF8;
/**
 * Created by sang on 2019/3/22.
 */
@Component
public class GPSUtil {

    private static final Logger logger = LoggerFactory.getLogger(GPSUtil.class);

    public static final String OID= "9919";

    public static final String KEY= "9815x6v2zw361yz1vwu5v37yz32y03y50z396";

    @Cacheable(value = "gpsspg", key = "'gpsspg-bs:'.concat(#bs)", unless = "#gpsspgResult?.count == 0" )
    public  GpsspgResult getGpsspgResult(String bs){
        GpsspgResult gpsspgResult = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://api.gpsspg.com/bs/");
        try {
            List<NameValuePair> formParams = Lists.newArrayList();
            formParams.add(new BasicNameValuePair("oid",OID));
            formParams.add(new BasicNameValuePair("key",KEY));
            formParams.add(new BasicNameValuePair("output","json"));
            formParams.add(new BasicNameValuePair("bs",bs));
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity( formParams, "UTF-8");
            httpPost.setEntity(encodedFormEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            logger.info("GpsspgResult response："+ JSON.toJSONString(httpEntity));
            if(null != httpEntity){
                gpsspgResult =  JSON.parseObject(EntityUtils.toString(httpEntity, UTF8), GpsspgResult.class);
                logger.info("GpsspgResult ："+ JSON.toJSONString(gpsspgResult));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gpsspgResult;
    }

}
