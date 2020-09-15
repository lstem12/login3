package com.user.test.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.google.gson.Gson;
import com.user.test.dao.UploadDAO;
import com.user.test.dao.impl.UploadDAOImpl;


@WebServlet("/upload2")
public class FileUploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int ramSize = 100 * 1024;
    private static int maxFileSize = 5 * 1024 * 1024;
    private static int maxTotalSize = 25 * 1024 * 1024;
    private UploadDAO upDAO = new UploadDAOImpl();
    Gson gson = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getContentType();
		DiskFileItemFactory difFactory = new DiskFileItemFactory();
		difFactory.setSizeThreshold(ramSize);
		
		ServletFileUpload sfu = new ServletFileUpload(difFactory);
		sfu.setFileSizeMax(maxFileSize);
		sfu.setSizeMax(maxTotalSize);
		
		try {
			Map<String, String> upload = new HashMap<>();
			List<FileItem> fiList = sfu.parseRequest(new ServletRequestContext(request));
			for(FileItem fi : fiList) {
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
			Map<String,Object> result = new HashMap<>();
			result.put("result", upDAO.insertUpload(upload));
			response.setContentType("application/json;charSet=utf-8");
			PrintWriter pw = response.getWriter();
			String json = gson.toJson(result);
			pw.print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(contentType);

	}

}
