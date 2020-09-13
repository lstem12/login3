package com.user.test.dao;

import com.user.test.vo.UserInfoVO;

public interface UserInfoDAO {
	int insertUserInfoDAO(UserInfoVO userInfoVO);
	UserInfoVO selectUserLogin(UserInfoVO userInfoVO);
}
