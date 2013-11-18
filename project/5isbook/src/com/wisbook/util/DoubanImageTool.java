package com.wisbook.util;
/**
 * 对豆瓣取得的图片进行修改尺寸，豆瓣取得的图片的url:http://img3.douban.com/mpic/s26010480.jpg
 * 其实主要尺寸有3中：spic(小) mpic(中) lpic(大)
 * @author volador
 *
 */
public class DoubanImageTool {
	private static final String SMALL = "spic"; //小尺寸
	private static final String MEDIUM = "mpic"; //中尺寸
	private static final String LARGE = "lpic";  //大尺寸
	
	/**
	 * 将图片改为小尺寸
	 * @param originalStr 图片的源url
	 * @return 修改后的图片url
	 */
	public static String transformToSmallSize(String originalStr){
		return transform(originalStr,SMALL);
	}
	/**
	 * 将图片改为中尺寸
	 * @param originalStr 源url
	 * @return 修改后的url
	 */
	public static String tracsformToMediumSize(String originalStr){
		return transform(originalStr,MEDIUM);
	}
	/**
	 * 将图片改为大尺寸
	 * @param originalStr 源url
	 * @return 修改后的url
	 */
	public static String transformToLargeSize(String originalStr){
		return transform(originalStr,LARGE);
	}
	/**
	 * 修改的算法实现
	 * @param originalStr 源url
 	 * @param size 尺寸
	 * @return 修改后的url
	 */
	private static String transform(String originalStr,String size){
		if(originalStr == null || originalStr.length() <= 0) return "";
		int endIndex = originalStr.lastIndexOf('/');
		int	startIndex = originalStr.lastIndexOf('/') - 4;
		if(endIndex <=0 || startIndex <= 0)	return "["+originalStr+"]不是豆瓣上的图片url格式.";
		StringBuilder sb = new StringBuilder();
		String endStr = originalStr.substring(endIndex);
		String startStr = originalStr.substring(0,startIndex);
		sb.append(startStr);
		sb.append(size);
		sb.append(endStr);
		return sb.toString();
	}
}
