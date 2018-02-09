/**
 * 
 */
package com.qzdsoft.eshop.service.sys.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.mapper.sys.SysUserQueryMapper;
import com.qzdsoft.eshop.mapper.user.UserMapper;
import com.qzdsoft.eshop.service.sys.LoginService;
import com.qzdsoft.eshop.service.sys.SmsService;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.MD5Util;
import com.qzdsoft.utils.WxLoginUtil;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;
import com.qzdsoft.vo.TypeInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
@Service
public class LoginServiceImpl implements LoginService
{

    @Autowired
    private SysUserQueryMapper userQueryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SmsService smsService;
    /**
     * @see com.qzdsoft.eshop.service.sys.LoginService#login(java.lang.String, java.lang.String)
     */
    @Override
    public LoginUserInfo login(String userName, String password)
    {
        return userQueryMapper.queryUser(userName, password);
    }
	@Override
	public List<TypeInfo> selectAuthorizedButton(LoginUserInfo user) {
		Integer roleId = user.getRoleId();
		Integer hasAllAuthorized = userQueryMapper.isHasAllFunction(roleId);
		if(hasAllAuthorized == 1) {
			return userQueryMapper.selectAllButton();
		}else{
			
			return userQueryMapper.selectAuthorizedButton(roleId);
		}
	}
	@Override
	public List<String> selectAuthorizedUrl(LoginUserInfo user) {
		Integer roleId = user.getRoleId();
		Integer hasAllAuthorized = userQueryMapper.isHasAllFunction(roleId);
		if(hasAllAuthorized == 1) {
			return userQueryMapper.selectAllUrl();
		}else{
			
			return userQueryMapper.selectAuthorizedUrl(roleId);
		}
	}
	@Override
	public Response<String> regist(String phone, String pwd, String code, String recommend) {
		User record = new User();
		record.setPhone(phone);
		List<User> list = userMapper.select(record);
		if(list.size()>0) {
			return Response.error("该手机号已注册");
		}
		/*Response<String> sendResult = smsService.validVerificationCode(phone, code);
        if(ResultEnum.ERROR.getCode().equals(sendResult.getCode())) {
            return sendResult;
        }*/
        record.setPassword(MD5Util.MD5Encode(pwd, "UTF-8"));
        if(recommend!=null&&"".equals(recommend)) {
        	record.setRefereeId(recommend);
        }
        record.setRegTime(new Date());
        record.setBalance(new BigDecimal(0));
        record.setFreezeMoney(new BigDecimal(0));
        userMapper.insert(record);
		return Response.success("注册成功");
	}
	@Override
	public User loginPC(String userName, String password) {
		User record = new User();
		record.setPhone(userName);
		record.setPassword(MD5Util.MD5Encode(password, "UTF-8"));
		User user = userMapper.selectOne(record);
		if(user == null) {
			return null;
		}
		return user;
	}
	@Override
	public User weichatlogin(String code, String state) {
		String wxLogin = SysConfigInfo.getValue(SysConfigCode.WXCHAT_LOGIN);
		Map<String,Object> wxMap = JsonUtils.jsonToMap(wxLogin);
		
		String userinfo = WxLoginUtil.getUserInfo(wxMap.get("appid").toString(),wxMap.get("secret").toString(),code);
		Map<String,Object> userInfoMap = JsonUtils.jsonToMap(userinfo);
		String openid = (String)userInfoMap.get("openid");
		String nickname = (String)userInfoMap.get("nickname");
		String sex = (String)userInfoMap.get("sex"); //1为男性，2为女性
//		String province = (String)userInfoMap.get("province");
//		String city = (String)userInfoMap.get("city");
//		String country = (String)userInfoMap.get("country");
		String headimgurl = (String)userInfoMap.get("headimgurl");
		String unionid = (String)userInfoMap.get("unionid"); //用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
		User user = new User();
		user.setWxId(unionid);
		List<User> list = userMapper.select(user);
		user.setUserName(nickname);
		user.setHeadImg(headimgurl);
		if(sex.equals("1")) {
			user.setSex(0);
		}else if(sex.equals("2")) {
			user.setSex(1);
		}else {
			user.setSex(2);//保密
		}
		
		if(list.size()==0) {
			user.setRegTime(new Date());
			user.setBalance(new BigDecimal(0));
			user.setFreezeMoney(new BigDecimal(0));
			userMapper.insert(user);
		}else {
			user.setUserId(list.get(0).getUserId());
			userMapper.updateByPrimaryKey(user);
		}
		
		return user;
	}
    
    

}
