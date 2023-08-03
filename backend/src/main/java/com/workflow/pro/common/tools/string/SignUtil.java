package com.workflow.pro.common.tools.string;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;

public class SignUtil {

    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String token="leonjo";
        String[] array=new String[]{token,timestamp,nonce};
        Arrays.sort(array);
        String content=array[0].concat(array[1]).concat(array[2]);
        String ciphertext=null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            ciphertext=byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ciphertext!=null?ciphertext.equals(signature.toUpperCase()):false;
    }
    private static String bytesToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String sign(String url, String nonce, Long timestamp, String ticket) throws Exception {
        String plain = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%d&url=%s", ticket, nonce, timestamp, url);
        System.out.println("plain:" + plain);
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.reset();
            sha1.update(plain.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(sha1.digest());
        } catch (NoSuchAlgorithmException e) {
            // throw new LeysenException("jsapi_ticket计算签名错误");
            throw new Exception("jsapi_ticket计算签名错误");
        }
    }
    public static String byteToStr(byte[] byteArray){
        String strDigest="";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest+=byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    public static String byteToHexStr(byte mByte){
        char[] Digit={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tempArr = new char[2];
        tempArr[0]=Digit[(mByte>>>4)&0X0F];
        tempArr[1]=Digit[mByte&0X0F];
        String s=new String(tempArr);
        return s;
    }
}