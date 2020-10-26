package com.fzw.authority.util;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseResult<T> {

	public static Map<Integer, String> rsMap = new HashMap<>();
	static {
		//反射获取状态和message
		rsMap = getMessages(BaseResultStatus.class.getName());
	}
	
	// 状态码：0成功，其他为失败
	public int code;

	// 成功为success，其他为失败原因
	public String message;

	// 数据结果集
	public T data;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public BaseResult() {
		this.code = BaseResultStatus.SUCCESS;
		this.message = rsMap.get(this.code);
	}
	public BaseResult(T data) {
		this.code = BaseResultStatus.SUCCESS;
		this.message = rsMap.get(this.code);
		this.data = data;
	}

	public BaseResult(String message, T data) {
		this.code = BaseResultStatus.SUCCESS;
		this.message = message;
		this.data = data;
	}

	public BaseResult(int code) {
		this.code = code;
		this.message = rsMap.get(this.code);
	}
	public BaseResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public BaseResult(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**静态调用方法 开始**/

	public static BaseResult done() {
		return new BaseResult<>();
	}
	public static <T> BaseResult<T> done(T data) {
		return new BaseResult<T>(data);
	}
	public static <T> BaseResult<T> error(int code) {
		return new BaseResult<>(code);
	}
	public static <T> BaseResult<T> error() {
		return new BaseResult<>(BaseResultStatus.ERROR);
	}
	public static <T> BaseResult<T> error(String message) {
		return new BaseResult<>(BaseResultStatus.ERROR,message);
	}
	/**静态调用方法 结束**/


	/**
	 * 获取所有message
	 * @param className
	 * @return
	 */
	public static Map<Integer, String> getMessages(String className){
		Map<Integer, String> map = new HashMap<>();
		try {
			Class clazz = Class.forName(className);
	        Field[] fields = clazz.getFields();
	        for (Field field : fields) {
	        	String name = field.getName();
	        	Object val = field.get(clazz);
	        	ResultMessage resultMessage = field.getAnnotation(ResultMessage.class);
	        	if(null == val || null == resultMessage){
	        		continue;
	        	}
	        	map.put(Integer.valueOf(val.toString()), resultMessage.value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 把message 赋值给baseResult
	 * @param className
	 */
	public static void setMessages(String className){
		try {
			Map<Integer, String> map = getMessages(className);
			Class clazz = Class.forName(BaseResult.class.getName());
			Field[] fields = clazz.getFields();
			for (Field field : fields) {
				String name = field.getName();
				if("rsMap".equals(name)){
					field.set(clazz, map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
