package com.user.test.service;

import com.user.test.vo.UserInfoVO;

public interface UserInfoService {
	int insertUserService(UserInfoVO userInfoVO);
	UserInfoVO selectUserLogin(UserInfoVO userInfoVO);
}
