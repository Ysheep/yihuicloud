package com.qzdsoft.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzdsoft.utils.constant.RedisConstant;
import com.qzdsoft.vo.EnumPo;

public final class SysUtils {



	/**
	 * 转换对象为json字符串
	 *
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		StringWriter sw = new StringWriter();
		try {
			new ObjectMapper().writeValue(sw, object);
		} catch (Exception e) {
		}

		return sw.toString();
	}

	/**
	 * 转换对象为json字符串,并指定date的格式化
	 *
	 * @param object
	 * @param dateFormat
	 * @return
	 */
	public static String toJson(Object object, DateFormat dateFormat){
		ObjectMapper mapper = new ObjectMapper();
		if(null != dateFormat)
			mapper.setDateFormat(dateFormat);
			StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, object);
		} catch (Exception e) {
		}

		return sw.toString();
	}

	/**
	 * 解析jsonString字符串为一个具体的object
	 *
	 * @param <T>
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T parseJsonToObject(String jsonString, Class<T> clazz){
		try {
			return new ObjectMapper().readValue(jsonString, clazz);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 解析jsonString字符串为map
	 *
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map parseJsonToMap(String jsonString) {
		if(null == jsonString){
			return null;
		}

		try {
			return new ObjectMapper().readValue(jsonString, Map.class);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 解析jsonString字符串为list
	 *
	 * @param jsonString
	 * @return
	 */
	public static List<?> parseJsonToList(String jsonString) {
		if(null == jsonString){
			return null;
		}

		try {
			return (List<?>) new ObjectMapper().readValue(jsonString, List.class);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 将json字符串转换成java对象
	 * 
	 * @param json
	 * @param object
	 * @return
	 */
	public static Object parseJSONStringToObject(String json, Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, object.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json字符串转换成List
	 * 
	 * @param json
	 * @param object
	 * @return
	 */
	public static List<?> parseJSONStringToList(String json, Class<?> object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, mapper.getTypeFactory()
					.constructParametricType(List.class, object));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String parseObjectToJSONString(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 截取字符串的前limit个字符
	 *
	 * @param string
	 *            原字符串
	 * @param limit
	 *            截取长度
	 * @return String
	 */
	public static String substring(String string, int limit) {
		StringBuffer returnString = new StringBuffer();
		if (string == null || limit <= 0 || limit > string.length()) {
			return string;
		}
		char[] temp = string.toCharArray();
		for (int i = 0; i < limit; i++) {
			returnString.append(temp[i]);
		}
		return returnString.toString();
	}

	/**
	 * 随机产生N位验证码
	 * 
	 * @return
	 */
	public static char[] getRandNum(int n) {
		char[] rand = new char[n];
		String str = "23456789qwertyuipkjhgfdsacvbnmQWERTYUPLKJHGFDSACVBNM";
		for (int i = 0; i < n; i++) {
			Random rd = new Random();
			int index = rd.nextInt(str.length());

			// 通过下标获取字符
			rand[i] = str.charAt(index);
		}
		return rand;
	}

	/**
	 * 随机产生N位验证码
	 * 
	 * @return 字符串
	 */
	public static String getRandNumberString(int n) {
		char[] rand = new char[n];
		String str = "23456789qwertyuipkjhgfdsacvbnmQWERTYUPLKJHGFDSACVBNM";
		for (int i = 0; i < n; i++) {
			int index = new Random().nextInt(str.length());
			rand[i] = str.charAt(index); // 通过下标获取字符
		}
		return String.valueOf(rand);
	}
	
	/**
	 * 随机产生N位短信验证码
	 * 
	 * @return 字符串
	 */
	public static String getMsgCode(int n) {
		char[] rand = new char[n];
		String str = "123456789";
		for (int i = 0; i < n; i++) {
			int index = new Random().nextInt(str.length());
			rand[i] = str.charAt(index); // 通过下标获取字符
		}
		return String.valueOf(rand);
	}

	/**
	 * 生成图片 - 验证码
	 * 
	 * @param randNum
	 *            验证码
	 * @return
	 */
	public static BufferedImage generateRandImg(char[] randNum) {

		// 在内存中创建图象
		int width = 60, height = 30;

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandColor(200, 250));

		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 160; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String strRandNum = "";
		for (int i = 0; i < 4; i++) {// 取出验证码(4位数字)
			strRandNum = Character.toString(randNum[i]);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(strRandNum, 13 * i + 6, 20);// 将验证码显示到图象中
		}

		// 图象生效
		g.dispose();

		return image;
	}

	/**
	 * 获得随机图片颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 生成订单 author jwb
	 * 
	 * @param type
	 *            1.用户订购 2 用户租赁 3 代理商代购 4 代理商代租赁 5 代理商批购
	 * @return
	 */
	public static String getOrderNum(int type) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		return type + sdf.format(new Date());
	}

	/**
	 * author jwb
	 * 
	 * @param arry
	 * @return
	 */
	public static String Arry2Str(int[] arry) {
		StringBuilder sb = new StringBuilder();
		if (arry != null && arry.length > 0) {
			sb.append("(");
			for (Object object : arry) {
				sb.append(object + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
		}
		return sb.toString();
	}

	public static String Arry2Str(List<Integer> arry) {
		StringBuilder sb = new StringBuilder();
		if (arry != null && arry.size() > 0) {
			sb.append("(");
			for (Object object : arry) {
				sb.append(object + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
		}
		return sb.toString();
	}

	/**
	 * author jwb
	 * 
	 * @param arry
	 * @return
	 */
	public static String Arry2Str(String[] arry) {
		StringBuilder sb = new StringBuilder();
		if (arry != null && arry.length > 0) {
			sb.append("(");
			for (String a : arry) {
				sb.append("'" + a + "',");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
		}
		return sb.toString();
	}

	/**
	 * author jwb
	 * 
	 * @param s
	 * @return
	 */
	public static int Object2int(Object o) {
		try {
			return Integer.valueOf(o.toString().trim());
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 加密
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static String Encryption(String passwors, String path)
			throws Exception {
		String[] arstr = findPasswprdPath(path);
		String ret = null;
		Key keySpec = new SecretKeySpec(arstr[0].getBytes(), "AES"); // 两个参数，第一个为私钥字节数组，
																		// 第二个为加密方式
																		// AES或者DES
		IvParameterSpec ivSpec = new IvParameterSpec(arstr[1].getBytes());
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 实例化加密类，参数为加密方式，要写全
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		byte[] b = cipher.doFinal(passwors.getBytes());// 加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64,
														// HEX,
														// UUE,7bit等等。此处看服务器需要什么编码方式
		ret = Base64.encodeBase64String(b);
		return ret;
	}

	/**
	 * 解密
	 * 
	 * @param password
	 * @return
	 * @throws FileNotFoundException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String Decrypt(String password, String path) throws Exception {
		String[] arstr = findPasswprdPath(path);
		String str = null;
		byte[] passByte = Base64.decodeBase64(password); // 先用Base64解码
		IvParameterSpec ivSpec = new IvParameterSpec(arstr[1].getBytes());
		Key key = new SecretKeySpec(arstr[0].getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);// 与加密时不同MODE:Cipher.DECRYPT_MODE
		byte[] ret = cipher.doFinal(passByte);
		str = new String(ret, "utf-8");
		return str;
	}

	/**
	 * 获得秘钥
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static String[] findPasswprdPath(String path)
			throws FileNotFoundException {
		File file = new File(path);
		Scanner in = null;
		String result = "";
		in = new Scanner(file);
		while (in.hasNextLine()) {
			result += in.nextLine() + ",";
		}
		String[] ar = result.split(",");
		return ar;
	}

	/*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = (md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }
    
    /**
     * 发送验证码  
     * @param str
     * @param phone
     * @return 是否成功
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    @SuppressWarnings("unchecked")
    public static Boolean sendPhoneCode(String content, String phone) throws IOException {
        String smsUrl = "http://mt.10690404.com/send.do?Account=zf&Password=111111&Mobile="+phone+"&Content="+content+"&Exno=0&Fmt=json";
        String resStr = doGetRequest(smsUrl.toString());
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> reslt_map = mapper.readValue(resStr,Map.class);
        for (Map.Entry<String, Object> entry : reslt_map.entrySet()) {
            if(entry.getKey().equals("code")){
                if(entry.getValue().equals("9001")){
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings({ "resource", "rawtypes", "deprecation" })
    public static String doGetRequest(String urlstr) {
        HttpClient client = new DefaultHttpClient();
        client.getParams().setIntParameter("http.socket.timeout", 10000);
        client.getParams().setIntParameter("http.connection.timeout", 5000);
        HttpEntity entity = null;
        String entityContent = null;
        try {
            HttpGet httpGet = new HttpGet(urlstr.toString());
            HttpResponse httpResponse = client.execute(httpGet);
            entityContent = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entity != null) {
                try {
                    ((org.apache.http.HttpEntity) entity).consumeContent();
                } catch (Exception e) {
                }
            }
        }
        return entityContent;
    }

    //获取随机的6位数字验证码
    public static String getCode(){
        char[] randomChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        String mobilecode = stringBuffer.toString();
        return mobilecode;
    }

    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
     boolean flag = false;
     try{
      Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
      Matcher m = p.matcher(mobiles);
      flag = m.matches();
     }catch(Exception e){
      flag = false;
     }
     return flag;
    }

    /**
     * 获取上传文件的名称<br>
     * <该文件已复制到指定目录>
     *
     * @param request
     * @param imgInputName
     * @param uploadFilePath
     * @return
     * @throws IOException
     */
//    public static String getUploadFileName(HttpServletRequest request, String imgInputName, String uploadFilePath) throws IOException {
//        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
//        MultipartFile multiFile = mRequest.getFile(imgInputName);
//        if (null == multiFile || StringUtils.isEmpty(multiFile.getOriginalFilename())) {
//            return null;
//        }
//        String originalFileName = multiFile.getOriginalFilename();// 原文件全名
//        StringBuffer newFileName = new StringBuffer(UUID.randomUUID().toString());// 生成系统唯一文件名
//        int index = originalFileName.lastIndexOf(".");
//        if (index != -1) {
//            newFileName.append(originalFileName.substring(index));// 原文件扩展名
//        }
//        String realPath = request.getServletContext().getRealPath(uploadFilePath);
//        FileUtils.copyInputStreamToFile(multiFile.getInputStream(), new File(realPath, newFileName.toString()));
//        return newFileName.toString();
//    }

    /**
     * 获取上传文件的相对路径+文件名<br>
     * <该文件已复制到指定目录>
     *
     * @param request
     * @param multiFile
     * @param uploadFilePath
     * @return
     * @throws IOException
     */
//    public static String getUploadFileName(HttpServletRequest request, MultipartFile multiFile, String uploadFilePath) throws IOException {
//        if (null == multiFile || StringUtils.isEmpty(multiFile.getOriginalFilename())) {
//            return null;
//        }
//        String originalFileName = multiFile.getOriginalFilename();// 源文件全名
//        StringBuffer newFileName = new StringBuffer(UUID.randomUUID().toString());// 生成系统唯一文件名
//        newFileName.append(originalFileName.substring(originalFileName.lastIndexOf(".")));// 拼接源文件扩展名
//        String realPath = request.getServletContext().getRealPath(uploadFilePath);
//        String fileName = newFileName.toString();
//        FileUtils.copyInputStreamToFile(multiFile.getInputStream(), new File(realPath, fileName));
//        return uploadFilePath + fileName;
//    }

    public static int dayDeduction(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
     }
 
    public static String paddingSpace(String target,int len){
        if(target==null||len==0){
            return "";
        }
        int length = target.length();
        while(length<len){
            target += " ";
            length++;
        }
        return 0+target;
    }

	/**
	 * yongh
	 *
	 * @param id
	 * @return
     */
		public static String redisUserSessionId(Integer id){
				return new StringBuilder().append(RedisConstant.redisUidPrefix).append(id).toString();
		}

	/**
	 * 验证码
	 *
	 * @param sessionId
	 * @return
	 */
	public static String redisKaptcha(String sessionId){
		return new StringBuilder().append(RedisConstant.redisKaptchaPrefix).append(sessionId).toString();
	}

	/**
	 *	enum封装成po对象
	 *
	 * @param l
	 * @param m
	 * @param key
	 * @param val
	 */
	public static void enums(List<EnumPo> l, Map<Object, Object> m, Object key, Object val){
		l.add(new EnumPo(key, val));
		m.put(key, val);
	}

    /**
     * 上传文件
     * @param file
     * @param path  path后有/ 如 "test/a/b/"
     * @return
     */
//    public static String upload(String dirRoot,MultipartFile file,HttpServletRequest request) {
//    	 String upload_path =dirRoot;
//         String name= file.getOriginalFilename();
//         try {
//             String extName = "";
//             if (name.lastIndexOf(".") >= 0) {
//                 extName = name.substring(name.lastIndexOf("."));
//             }
//             name = new Date().getTime() +SysUtils.getRandNum(6).toString()+ extName;
//             File f = new File(upload_path, "ices.apk");
//             FileUtils.copyInputStreamToFile(file.getInputStream(), f);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "-1";
//         }
//             return name;
//     }
}
