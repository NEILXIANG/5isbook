package com.wisbook.util;

public class Utils {

    /**
     * @param cs 字符串
     * @return 是不是为空字符串
     */
    public static boolean isEmpty(CharSequence cs) {
        return null == cs || cs.length() == 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotDouble(String d) {
        try {
            Double.parseDouble(d);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    
    /**
     * 判断字符编码
     * @param str
     * @return
     */
	public static String getEncoding(String str) {    
        String encode = "GB2312";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s = encode;    
               return s;    
            }    
        } catch (Exception exception) {    
        }    
        encode = "ISO-8859-1";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s1 = encode;    
               return s1;    
            }    
        } catch (Exception exception1) {    
        }    
        encode = "UTF-8";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s2 = encode;    
               return s2;    
            }    
        } catch (Exception exception2) {    
        }    
        encode = "GBK";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s3 = encode;    
               return s3;    
            }    
        } catch (Exception exception3) {    
        }    
       return "";    
    }

    /**
     * 在字符串左侧填充一定数量的特殊字符
     *
     * @param o 可被 toString 的对象
     * @param width 字符数量
     * @param c 字符
     * @return 新字符串
     */
    public static String alignRight(Object o, int width, char c) {
        if (null == o) {
            return null;
        }
        String s = o.toString();
        int len = s.length();
        if (len >= width) {
            return s;
        }
        return new StringBuilder().append(dup(c, width - len)).append(s).toString();
    }

    /**
     * 复制字符串
     *
     * @param cs 字符串
     * @param num 数量
     * @return 新字符串
     */
    public static String dup(CharSequence cs, int num) {
        if (isEmpty(cs) || num <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(cs.length() * num);
        for (int i = 0; i < num; i++) {
            sb.append(cs);
        }
        return sb.toString();
    }

    /**
     * 复制字符
     *
     * @param c 字符
     * @param num 数量
     * @return 新字符串
     */
    public static String dup(char c, int num) {
        if (c == 0 || num < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 在字符串右侧填充一定数量的特殊字符
     *
     * @param o 可被 toString 的对象
     * @param width 字符数量
     * @param c 字符
     * @return 新字符串
     */
    public static String alignLeft(Object o, int width, char c) {
        if (null == o) {
            return null;
        }
        String s = o.toString();
        int length = s.length();
        if (length >= width) {
            return s.toString();
        }
        return new StringBuilder().append(s).append(dup(c, width - length)).toString();
    }


}

