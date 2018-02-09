package com.qzdsoft.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 模块名：通用金额工具类
 * <p>详细描述：包含有千分位添加或去除，数值转中文大写
 * @author DamingChen Oct 13, 2017
 * @see
 * @since 1.0
 */
public class MoneyUtil {
	private static final Pattern AMOUNT_PATTERN = Pattern.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$"); // 不考虑分隔符的正确性
	private static final char[] RMB_NUMS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
	private static final String[] UNITS = { "元", "角", "分", "整" };
	private static final String[] U1 = { "", "拾", "佰", "仟" };
	private static final String[] U2 = { "", "万", "亿" };

	/**
	 * 将金额（整数部分等于或少于12位，小数部分2位）转换为中文大写形式.
	 * 
	 * @param amount
	 *            金额数字
	 * @return 中文大写
	 * @throws IllegalArgumentException
	 */
	public static String convertToChinese(String amount) throws IllegalArgumentException {
		if(StringUtil.isEmpty(amount)){
			return "";
		}
		// 去掉分隔符
		//amount = amount.replace(",", "");
		String money=moneyFormat(removeQuartile(amount));

		// 验证金额正确性
		if (money.equals("0.00")) {
			throw new IllegalArgumentException("金额不能为零.");
		}
		Matcher matcher = AMOUNT_PATTERN.matcher(money);
		if (!matcher.find()) {
			throw new IllegalArgumentException("转换的金额过大，目前只支持千亿位。");
		}

		String integer = matcher.group(1); // 整数部分
		String fraction = matcher.group(2); // 小数部分

		String result = "";
		if (!integer.equals("0")) {
			result += integer2rmb(integer) + UNITS[0]; // 整数部分
		}
		if (fraction.equals("00")) {
			result += UNITS[3]; // 添加[整]
		} else if (fraction.startsWith("0") && integer.equals("0")) {
			result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
		} else {
			result += fraction2rmb(fraction); // 小数部分
		}

		return result;
	}

	// 将金额小数部分转换为中文大写
	private static String fraction2rmb(String fraction) {
		char jiao = fraction.charAt(0); // 角
		char fen = fraction.charAt(1); // 分
		return (RMB_NUMS[jiao - '0'] + (jiao > '0' ? UNITS[1] : ""))
				+ (fen > '0' ? RMB_NUMS[fen - '0'] + UNITS[2] : "");
	}

	// 将金额整数部分转换为中文大写
	private static String integer2rmb(String integer) {
		StringBuilder buffer = new StringBuilder();
		// 从个位数开始转换
		int i, j;
		for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
			char n = integer.charAt(i);
			if (n == '0') {
				// 当n是0且n的右边一位不是0时，插入[零]
				if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
					buffer.append(RMB_NUMS[0]);
				}
				// 插入[万]或者[亿]
				if (j % 4 == 0) {
					if (i > 0 && integer.charAt(i - 1) != '0' || i > 1 && integer.charAt(i - 2) != '0'
							|| i > 2 && integer.charAt(i - 3) != '0') {
						buffer.append(U2[j / 4]);
					}
				}
			} else {
				if (j % 4 == 0) {
					buffer.append(U2[j / 4]); // 插入[万]或者[亿]
				}
				buffer.append(U1[j % 4]); // 插入[拾]、[佰]或[仟]
				buffer.append(RMB_NUMS[n - '0']); // 插入数字
			}
		}
		return buffer.reverse().toString();
	}

	/**
	 * 去除千分位
	 * @param money
	 * @return
	 */
	public static String removeQuartile(String money) {
		if(StringUtil.isEmpty(money)){
			return "0.00";
		}
		try {
			double d1 = new DecimalFormat().parse(money).doubleValue();
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setGroupingUsed(false); // 设置了以后不会有千分位，如果不设置，默认是有的
			return numberFormat.format(d1);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return "";
	}
	/**
	 * 去除千分位
	 * @param money
	 * @return
	 */
	public static Double removeQuartileToDouble(String money) {
		if(StringUtil.isEmpty(money)){
			return 0.00;
		}
		try {
			double d1 = new DecimalFormat().parse(money).doubleValue();
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setGroupingUsed(false); // 设置了以后不会有千分位，如果不设置，默认是有的
			return Double.valueOf(numberFormat.format(d1));
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return 0.00;
	}
	/**
	 * 去除千分位*10000
	 * @param money
	 * @return
	 */
	public static Long toQzdMoney(String money) {
		if(StringUtil.isEmpty(money)){
			return (long)0;
		}
		try {
			double d1 = new DecimalFormat().parse(money).doubleValue();
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setGroupingUsed(false); // 设置了以后不会有千分位，如果不设置，默认是有的
			Double d2=Double.valueOf(numberFormat.format(d1))*10000;
			return d2.longValue();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return (long) 0;
	}
	/**
	 * 去除千分位*10000
	 * @param money
	 * @return
	 */
	public static String toQzdAmount(String money) {
		if(StringUtil.isEmpty(money)){
			return "0.0";
		}
		try {
			double d1 = new DecimalFormat().parse(money).doubleValue();
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setGroupingUsed(false); // 设置了以后不会有千分位，如果不设置，默认是有的
			Double d2=Double.valueOf(numberFormat.format(d1))*10000;
			return d2.longValue()+"";
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return "0.0";
	}
	/**
	 * 去除千分位/10000
	 * @param money
	 * @return
	 */
	public static String toRemoveQzdMoney(String money) {
		if(StringUtil.isEmpty(money)){
			return "";
		}
		try {
			double d1 = new DecimalFormat().parse(money).doubleValue();
			 DecimalFormat decimalFormat = new DecimalFormat("###0.00");//格式化设置  
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setGroupingUsed(false); // 设置了以后不会有千分位，如果不设置，默认是有的
			double d2 =Double.valueOf(numberFormat.format(d1))/10000;
			String num = decimalFormat.format(d2); 
			return num;
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return "0";
	}
	/**
	 * 转换千分位
	 * @param money
	 * @return
	 */
	public static String convertQuartile(String money) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		double number = 0.0;
		try {
			number = Double.parseDouble(money);
		} catch (Exception e) {
			number = 0.0;
		}
		return df.format(number);
	}
	/**
	 * 转换千分位
	 * @param money
	 * @return
	 */
	public static String convertQuartile(String money,int len) {
		DecimalFormat df = null;
		if (len > 0) {
			//方式一  小数位数不足4位者有几位就是几位，多于4位者仅留4位
			//df = new DecimalFormat("#.0000");
			//添加千位分隔符
		    //df = new DecimalFormat("#,###.00");
			//有多少位保费多少位
			/*
			if (money.length() - money.indexOf(".") - 1 == 0) {
				df = new DecimalFormat("###,##0.");
			} else if (money.length() - money.indexOf(".") - 1 == 1) {
				df = new DecimalFormat("###,##0.0");
			} else {
				df = new DecimalFormat("###,##0.00");
			}
			*/
			//最长只支持8会小数
			if(len>8) 
				len=8;
			String str= "0000000000".substring(0,len);
			str="#,###."+str;
			df=new DecimalFormat(str);
		} else {
			df = new DecimalFormat("#,###.00");
		}
		double number = 0.0;
		try {
			number = Double.parseDouble(money);
		} catch (Exception e) {
			number = 0.0;
		}
		return df.format(number);
	}
	/**
	 * 转换千分位
	 * @param money
	 * @return
	 */
	public static String convertQuartile(Double money) {
		String text=money+"";
		return convertQuartile(text,2);
	}
    /** 
     * 对金额的格式调整到分 
     * @param money 
     * @return 
     */  
    public static String moneyFormat(String money){//23->23.00  
        StringBuffer sb=new StringBuffer();  
        if(money==null){  
            return "0.00";  
        }  
        int index=money.indexOf(".");  
        if(index==-1){  
            return money+".00";  
        }else{  
            String s0=money.substring(0,index);//整数部分  
            String s1=money.substring(index+1);//小数部分  
            if(s1.length()==1){//小数点后一位  
                s1=s1+"0";  
            }else if(s1.length()>2){//如果超过3位小数，截取2位就可以了  
                s1=s1.substring(0,2);  
            }  
            sb.append(s0);  
            sb.append(".");  
            sb.append(s1);  
        }  
        return sb.toString();  
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//转换千分位
		System.out.println(toQzdMoney("6.4"));
		
		System.out.println(toRemoveQzdMoney("64,000,000.00"));
		
		System.out.println(toRemoveQzdMoney("20,000,000,000.00"));
//		//去除千分位
		System.out.println(removeQuartile("123,123,133.2853"));
//		//金额只使用两位小数
		System.out.println(convertQuartile(123123133.2863));
		
		
		
//		System.out.println(moneyFormat(convertQuartile("123123133.2863")));
//		// 转换成中文
//		System.out.println(convertToChinese(moneyFormat(convertQuartile(123123133.2863))));
//		System.out.println(convertToChinese(moneyFormat(convertQuartile("123123133.2863"))));

		
		
        System.out.println(convertQuartile(9110000.911911+"",4));
        System.out.println("////////");
	}

}