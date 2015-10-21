package com.bpms.util;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.bpms.model.Users;
import com.bpms.websocket.WebSocketMessageInboundPool;

/**
 * 发送任务个数的消息提示工具类
 * 
 * @author liuhh
 *
 */
public class SendMessageUtil {

	/** 贷款代办任务的提示信息类型 */
	public static final String DATATYPE_UNCLAIMLOANORDER = "unClaimLoanOrder";

	/** 贷款受理人的提示信息的类型 */
	public static final String DATATYPE_CLAIMLOANORDER = "claimLoanOrder";

	/** 接受财富代办任务的提示信息 */
	public static final String DATATYPE_UNCLAIMINVESTORDER = "unClaimInvestOrder";

	/** 接受财富受理任务的提示信息 */
	public static final String DATATYPE_CLAIMINVESTORDER = "claimInvestOrder";

	/** 接受贷款任务的提示信息--总个数 */
	public static final String DATATYPE_ALL_LOANORDER = "loanOrders";

	/** 接受财富任务的提示信息--总个数 */
	public static final String DATATYPE_ALL_INVESTORDERS = "investOrders";

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
	public static String getMessageData(String dataType, String calculateType,
			String taskId, Long claimCount, Long unClaimCount) {
		JSONObject result = new JSONObject();
		result.element("type", MESSAGE_TYPE);
		result.element("dataType", dataType);
		result.element("calculateType", calculateType);
		result.element("taskId", taskId);
		result.element("claimCount", claimCount);
		result.element("unClaimCount", unClaimCount);
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
