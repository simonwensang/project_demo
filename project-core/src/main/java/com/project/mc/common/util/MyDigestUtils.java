package com.project.mc.common.util;

/**
 * Created by sang on 2017/12/4.
 */
public class MyDigestUtils {

    public final static String WECHATKEY = "adfsIUOmewrPPPOPO@((*^%))_111HHHH";

   /* public static void main(String[] args) {
        String rsOpenId= "a5a5ceb9-083a-4b2b-aecc-716f5ac5282a" ;
        System.out.print(DigestUtils.md5Hex(rsOpenId + WECHATKEY));
    }
*/

   public static  byte[] arraycopy(byte[] a1, byte[] a2){
       byte[] a3 = new byte[a1.length+a2.length];
       System.arraycopy(a1,0,a3,0,a1.length);
       System.arraycopy(a2,0,a3,a1.length,a2.length);
       return a3;
   }
}
