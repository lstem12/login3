package com.user.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.user.test.service.UserInfoService;
import com.user.test.service.impl.UserInfoServiceImpl;
import com.user.test.vo.UserInfoVO;


@WebServlet("/ajax/user")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	UserInfoService userInfoService = new UserInfoServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String str;
		StringBuffer sb = new StringBuffer();
		while((str=br.readLine()) != null) {
			sb.append(str);
		}
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO = gson.fromJson(sb.toString(), UserInfoVO.class);
		Map<String, Object> result = new HashMap<>();
		if("join".equals(userInfoVO.getCmd())) {
			result.put("result", userInfoService.insertUserService(userInfoVO));
		}else if("login".equals(userInfoVO.getCmd())) {
			result.put("result", userInfoService.selectUserLogin(userInfoVO));
			if(userInfoVO != null) {
				HttpSession hs = request.getSession();
				hs.setAttribute("user", userInfoVO);
			}
		}
		
		response.setContentType("application/json;charSet=utf-8");
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(result);
		pw.print(json);
	}

}
