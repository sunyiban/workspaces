package com.oasys.util;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.oasys.model.Users;
import com.oasys.model.VO.NoticeReceiveModel;
import com.oasys.viewModel.ProcessNameModel;
import com.oasys.websocket.WebSocketMessageInboundPool;

/**
 * 
 * @ClassName: SendMessageUtil
 * @Description: TODO 发送任务个数的消息提示工具类
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年12月4日 上午10:21:58
 *
 */
public class SendMessageUtil {

	/** OA代办任务的提示信息类型 */
	public static final String DATATYPE_TASKOA = "taskOA";
	/** OA代办任务的提示信息类型 */
	public static final String DATATYPE_NOTICEOA = "noticeOA";

	/** 文本信息类型。 */
	private static final String MESSAGE_TYPE = "message";

	/** 任务消息增加的类型。 */
	public static final String CALCULATETYPE_ADD = "add";

	/** 任务消息减少的类型。 */
	public static final String CALCULATETYPE_SUBTRACT = "subtract";

	/**
	 * 组织发送菜单气泡的提示消息
	 * 
	 * @param dataType
	 *            数据类型具体订单类型
	 * @param calculateType
	 *            计算类型 add是增加，subtract
	 * @return
	 */
	public static String getMessageData(String dataType,List<ProcessNameModel> processNameList) {
		JSONObject result = new JSONObject();
		result.element("type", MESSAGE_TYPE);
		result.element("dataType", dataType);
//		result.element("calculateType", calculateType);
//		result.element("taskId", taskId);
		result.element("processNameList", processNameList);
		return result.toString();
	}
	/**
	 * 组织发送菜单气泡的提示消息
	 * 
	 * @return
	 */
	public static String getNoticeData(String dataType,List<NoticeReceiveModel> infoList) {
		JSONObject result = new JSONObject();
		result.element("type", MESSAGE_TYPE);
		result.element("dataType", dataType);
		result.element("infoList", infoList);
		return result.toString();
	}

	/**
	 * 发送消息
	 * 
	 * @param userId
	 *            发送用户的ID
	 * @param message
	 *            发送的消息体
	 */
	public static void sendMessageByUserId(String message, String userId) {
		if (StringUtils.isNotBlank(userId)) {
			WebSocketMessageInboundPool.sendMessageToUser(userId, message);
		}
	}

	/**
	 * 向多个用户发送消息
	 * 
	 * @param users
	 *            发送用户的列表
	 * @param message
	 *            发送的消息体
	 */
	public static void sendMessageByUsers(String message, List<Users> users) {
		if (Collections.listIsNotEmpty(users)) {
			for (Users user : users) {
				sendMessageByUserId(message, String.valueOf(user.getUserId()));
			}
		}
	}

	/**
	 * 向多个用户发送消息
	 * 
	 * @param userIds
	 *            发送用户id列表
	 * @param message
	 *            发送的消息体
	 */
	public static void sendMessageByUserIds(String message, List<String> userIds) {
		if (Collections.listIsNotEmpty(userIds)) {
			for (String userId : userIds) {
				sendMessageByUserId(message, userId);
			}
		}
	}

}
