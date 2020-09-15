package com.user.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.user.test.dao.UploadDAO;
import com.user.test.dao.impl.UploadDAOImpl;


@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int ramSize = 100*1024;
    private static int maxTotalSize = 10 * 1024 * 1024;
    private static int maxFileSize = 2 * 1024 * 1024;
    private UploadDAO upDAO = new UploadDAOImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getContentType();
		DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
		dfiFactory.setSizeThreshold(ramSize);
		
		ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
		sfu.setFileSizeMax(maxFileSize);
		sfu.setSizeMax(maxTotalSize);
		try {
			Map<String,String>  upload = new HashMap<>();
			List<FileItem> fiList = sfu.parseRequest(new ServletRequestContext(request));
			for(FileItem fi:fiList) {
				if(fi.isFormField()) {
					upload.put(fi.getFieldName(), fi.getString("UTF-8"));
				}else {
					String fileName = fi.getName();
					String extName = fileName.substring(fileName.lastIndexOf("."));
					String path = "C:\\java_study\\upload\\" + System.nanoTime() + extName;
					File f = new File(path);
					fi.write(f);
					upload.put(fi.getFieldName(), fileName);
					upload.put(fi.getFieldName().substring(4), path);
				}
			}
			upDAO.insertUpload(upload);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(contentType);
		//doGet(request, response);
	}

}
