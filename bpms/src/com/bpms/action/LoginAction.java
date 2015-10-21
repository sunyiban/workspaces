package com.bpms.action;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.service.LoginService;
import com.bpms.shiro.CaptchaUsernamePasswordToken;
import com.bpms.shiro.IncorrectCaptchaException;
import com.bpms.util.Constants;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ActionContext;

@Action(value = "systemAction", results = {
		@Result(name = Constants.LOGIN_SUCCESS_URL, location = "/index.jsp"),
		@Result(name = Constants.LOGIN_URL, location = "/login.jsp"),
		@Result(name = Constants.LOGIN_LOGIN_OUT_URL, type = "redirect", location = "systemAction!loginInit.action") })
public class LoginAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	private static final long serialVersionUID = -6019556530071263499L;
	private String userName;
	private String password;
	private String captcha;
	private String userMacAddr;
	private String userKey;

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserMacAddr() {
		return userMacAddr;
	}

	public void setUserMacAddr(String userMacAddr) {
		this.userMacAddr = userMacAddr;
	}

	private LoginService loginService;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String load() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		token.setUsername(userName);
		token.setPassword(password.toCharArray());
		token.setCaptcha(captcha);
		token.setRememberMe(true);
		DataModel json = new DataModel();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			ActionContext.getContext().getSession().put(Constants.SHIRO_USER, Constants.getCurrendUser());
			json.setStatus(true);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			logger.error(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException e) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		// token.clear();
		return null;
	}

	/**
	 * 函数功能说明 TODO:用户登出 Administrator修改者名字 2013-5-9修改日期 修改内容
	 * 
	 * @Title: logout
	 * @Description:
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String logout() throws Exception {
		SecurityUtils.getSubject().logout();
		DataModel json = new DataModel();
		json.setStatus(true);
		OutputJson(json);
		return null;
	}

	/**
	 * 函数功能说明 TODO:查询用户所有权限菜单 Administrator修改者名字 2013-5-9修改日期 修改内容
	 * 
	 * @Title: findAllFunctionList
	 * @Description:
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String findAllFunctionList() throws Exception {
		OutputJson(loginService.findMenuList());
		return null;
	}
}
