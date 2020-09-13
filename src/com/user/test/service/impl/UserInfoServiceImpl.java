package com.user.test.service.impl;

import javax.servlet.http.HttpSession;

import com.user.test.dao.UserInfoDAO;
import com.user.test.dao.impl.UserInfoDAOImpl;
import com.user.test.service.UserInfoService;
import com.user.test.vo.UserInfoVO;

public class UserInfoServiceImpl implements UserInfoService {
	UserInfoDAO userInfoDAO = new UserInfoDAOImpl();
	@Override
	public int insertUserService(UserInfoVO userInfoVO) {
		return userInfoDAO.insertUserInfoDAO(userInfoVO);
	}
	@Override
	public UserInfoVO selectUserLogin(UserInfoVO userInfoVO) {
		userInfoVO = userInfoDAO.selectUserLogin(userInfoVO);
		if(userInfoVO != null) {
			return userInfoVO;
		}
		return null;
	}

}
