package com.simple.dubbo.log.level;

/**
 * DEBUG级别日志接口
 * @author chengzhengzheng
 *
 */
public interface DebugLog {
	/**
	 * @return DEBUG 等级是否开启
	 */
	boolean isDebugEnabled();

	/**
	 * 打印 DEBUG 等级的日志
	 * 
	 * @param t 错误对象
	 */
	void debug(Throwable t);

	/**
	 * 打印 DEBUG 等级的日志
	 * 
	 * @param format 消息模板
	 * @param arguments 参数
	 */
	void debug(String format, Object... arguments);

	/**
	 * 打印 DEBUG 等级的日志
	 * 
	 * @param t 错误对象
	 * @param format 消息模板
	 * @param arguments 参数
	 */
	void debug(Throwable t, String format, Object... arguments);
}
