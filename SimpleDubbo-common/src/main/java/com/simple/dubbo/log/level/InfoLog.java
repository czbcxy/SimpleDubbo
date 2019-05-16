package com.simple.dubbo.log.level;

/**
 * INFO级别日志接口
 * @author chengzhengzheng
 *
 */
public interface InfoLog {
	/**
	 * @return INFO 等级是否开启
	 */
	boolean isInfoEnabled();

	/**
	 * 打印 INFO 等级的日志
	 * 
	 * @param t 错误对象
	 */
	void info(Throwable t);

	/**
	 * 打印 INFO 等级的日志
	 * 
	 * @param format 消息模板
	 * @param arguments 参数
	 */
	void info(String format, Object... arguments);

	/**
	 * 打印 INFO 等级的日志
	 * 
	 * @param t 错误对象
	 * @param format 消息模板
	 * @param arguments 参数
	 */
	void info(Throwable t, String format, Object... arguments);
}
