package util;

import java.security.MessageDigest;

import org.apache.commons.lang3.ObjectUtils;


public class CommonUtil {
    
    
    public static String encode(String str) throws Exception {
        
        if (ObjectUtils.isEmpty(str)) {
            return null;
        }
        
        StringBuffer sb = new StringBuffer();

        try{
            // メッセージダイジェストのインスタンスを生成
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            byte[] result = md5.digest(str.getBytes());

            // 16進数に変換して桁を整える
            int[] i = new int[result.length];
            for (int j=0; j < result.length; j++){
                i[j] = (int)result[j] & 0xff;
                if (i[j]<=15){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i[j]));
            }
            
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
        
        return sb.toString();
    }

}
