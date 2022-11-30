package com.wisdge.commons.sms;

import java.util.Map;

public interface ISmsService {
	/**
	 * 发送短信
	 * @param mobiles String[] 接收短信的手机号码
	 * @param paramsMap 短信入参
	 * @param smsType String 短信类型
	 * @return SmsResponse 回执对象
	 */
	SmsResponse send(String[] mobiles, Map<String, Object> paramsMap, String smsType) throws Exception;

	/**
	 * 发送短信
	 * @param mobiles String 接收短信的手机号码
	 * @param paramsMap 短信入参
	 * @param smsType String 短信类型
	 * @return SmsResponse 回执对象
	 * @throws Exception
	 */
	SmsResponse send(String mobiles, Map<String, Object> paramsMap, String smsType) throws Exception;

	boolean hasTemplate(String smsType);
}
