package com.qzdsoft.utils.file;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.qzdsoft.utils.DateUtil;
import com.qzdsoft.vo.LayeditResponse;


public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
		//创建文件夹
		public static String createUserFolder(String uploadPath,String uploadType) {
			String path = "";
//			String uploadFilePath = config.getUploadFilePath();
				path = uploadPath +  File.separator +uploadType
						+ File.separator +DateUtil.formatTime("yyyy", new Date())+File.separator+DateUtil.formatTime("MM", new Date());
				logger.info(path);
//			CommUtil.createFolder(path);
			return path;
		}
		//文件访问的目录
		public static String createUserFolderURL(String uploadFilePath,String uploadType) {
			String path = "";
			path = uploadFilePath + "/"+uploadType+"/"
					+DateUtil.formatTime("yyyy", new Date())+"/" +DateUtil.formatTime("MM", new Date());
			return path;
		}
		
		public static File createFolder(String folderPath,String fileName) {
	        File dest = new File(folderPath +File.separator+fileName);
	        logger.debug("上传路径creater:{}",folderPath +File.separator+fileName);
	        // 检测是否存在目录
	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }
			return dest;
		}
}
