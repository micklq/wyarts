package com.arts.org.webcomn.ueditor.upload;

import com.arts.org.webcomn.ueditor.PathFormat;
import com.arts.org.webcomn.ueditor.define.AppInfo;
import com.arts.org.webcomn.ueditor.define.BaseState;
import com.arts.org.webcomn.ueditor.define.FileType;
import com.arts.org.webcomn.ueditor.define.State;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class BinaryUploader {
	
		
	public static final State save(HttpServletRequest request,Map<String, Object> conf) {
			

		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());  			
            

			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			/***********/ 			
			/**自定义上传**/ 
			savePath = PathFormat.parse(savePath, originFileName);			
			String [] savePathBySplit_temp = savePath.split("/");
	            String temp = "";
	            String fileName = savePathBySplit_temp[savePathBySplit_temp.length-1];
	            for(int i = 1;i < savePathBySplit_temp.length-1; i++){
	                if(i!=savePathBySplit_temp.length-2){
	                    temp+=savePathBySplit_temp[i]+"/";
	                }else{
	                    temp+=savePathBySplit_temp[i];
	                }
	            }
	            String pathTemp = request.getSession().getServletContext().getRealPath("/") +  temp;
	            System.out.println(pathTemp+","+fileName);
	            System.out.println(new File(pathTemp).exists());
	            File targetFile = new File(pathTemp);
	            if(!targetFile.exists()){  
	                targetFile.mkdirs();  
	            }
	           System.out.println(new File(pathTemp).exists());	            
			
	        State storageState = StorageManager.saveFileByInputStream(multipartFile.getInputStream(),pathTemp+"/"+fileName, maxSize);
	        /***********/
	        
			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (Exception e) {
			e.printStackTrace();
	        System.out.println(e.getMessage());
		} 
		return new BaseState(false, AppInfo.IO_ERROR);
	}
//  系统集成  文件保存 找不到文件
//	public static final State save(HttpServletRequest request,Map<String, Object> conf) {
//		FileItemStream fileStream = null;
//		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;
//
//		if (!ServletFileUpload.isMultipartContent(request)) {
//			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
//		}
//
//		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
//
//        if ( isAjaxUpload ) {
//            upload.setHeaderEncoding( "UTF-8" );
//        }
//
//		try {
//			FileItemIterator iterator = upload.getItemIterator(request);
//
//			while (iterator.hasNext()) {
//				fileStream = iterator.next();
//
//				if (!fileStream.isFormField())
//					break;
//				fileStream = null;
//			}
//
//			if (fileStream == null) {
//				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
//			}
//
//			String savePath = (String) conf.get("savePath");
//			String originFileName = fileStream.getName();
//			String suffix = FileType.getSuffixByFilename(originFileName);
//
//			originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
//			savePath = savePath + suffix;
//
//			long maxSize = ((Long) conf.get("maxSize")).longValue();
//
//			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
//				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
//			}
//
//			savePath = PathFormat.parse(savePath, originFileName);
//
//			String physicalPath = (String) conf.get("rootPath") + savePath;
//
//			InputStream is = fileStream.openStream();
//			State storageState = StorageManager.saveFileByInputStream(is,physicalPath, maxSize);
//			is.close();
//
//			if (storageState.isSuccess()) {
//				storageState.putInfo("url", PathFormat.format(savePath));
//				storageState.putInfo("type", suffix);
//				storageState.putInfo("original", originFileName + suffix);
//			}
//
//			return storageState;
//		} catch (FileUploadException e) {
//			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
//		} catch (IOException e) {
//		}
//		return new BaseState(false, AppInfo.IO_ERROR);
//	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
