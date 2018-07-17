package com.wuyson.voybox.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringReplaceUtils {
	/**
     * 替换一个规则字符串中几个特定的字符串
     * @param tar
     * @param repalce
     * @return
     */
    public static String repalceChar(String tar, String[] repalce) {
        if(tar==null||tar.equals("")) {
            return "";
        }
        String result = "";
        String[] target = tar.split(",");
        List<String> targetArr = Arrays.asList(target);
        List<String> targetArr1 = new ArrayList<String>();
        for(String subTarge:targetArr) {
            boolean exits = false;
            for(String str:repalce) {
                if(subTarge.equals(str)){
                    exits = true;
                }
            }
            if(!exits) {
                targetArr1.add(subTarge);
            }
        }
        for(String str:targetArr1) {
            result+=str+",";
        }
        return result.substring(0, result.length()-1);
    }

    /**加密时(放弃使用)
     * 单个字符替换，按照一定的规则
     */
    public static String replaceRegax(String target){
        String s1 = target.replaceAll("a","b").replaceAll("c","d").replaceAll("j","k");
        return s1;
    }
    /**解密时（放弃使用）
     * 按照原有规则
     */
    public static String restoreReplaceRegax(String target){
        String s2 = target.replaceAll("b","a").replaceAll("d","c").replaceAll("k","j");
        //.replaceAll("b","a").replaceAll("d","c").replaceAll("k","j")
        return s2;
    }

    /**
     * 位异或加密
     */
    public static final int KEY = 7;
    public static String sercert(String str) {
        StringBuffer str2 = new StringBuffer();  //存储加密后的字符串
        //加密过程
        for (int i = 0; i < str.length(); i++) {
            char c = (char) (str.charAt(i) ^ KEY);
            str2.append(c);
        }
        return  str2.toString();
    }

    /**
     * 解密
     */
    public  static String restore(String str){
        StringBuffer str3 = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = (char) (str.charAt(i) ^ KEY);
            str3.append(c);
        }
        return str3.toString();
    }
}
