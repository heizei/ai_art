package com.ai.art.common.utils.log;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;

/**
 * @author wangBo
 */
public class LogPrintUtil {

    private final static String CO_LINK_TIME_PRINT  = "CO_LINK_TIME_PRINT";

    /**
     * 日志打印前缀
     */
    public static final String  PREFIX              = "call [";

    /**
     * 双中括号
     */
    public static final String  DOUBLE_MID_BRACKETS = "] [";

    /**
     * 单位：毫秒
     */
    public static final String  MILLISECOND         = "ms";

    /**
     * 状态：成功
     */
    public static final String  SUCCESS             = "Success";

    /**
     * 中括号结束]
     */
    public static final String  END_PARENTHESES     = "]";

    /**
     * 中括号开始[
     */
    private static final String START_PARENTHESES   = "[";

    /**
     * 空格字符串
     */
    private static final String BLANK_STR           = " ";

    /**
     * 入参占位符
     */
    private static final String PLACEHOLDER         = ", [{}]";

    /**
     * 请求参数前缀
     */
    private static final String START_PARAMETER     = " PARAMETER:[{}]";

    /**
     * 结果日志打印前缀
     */
    private static final String END_PREFIX          = "RESPONSE:{{}}";

    private static final String REQUEST_STR         = "请求参数:";

    private static final String RESPONSE_STR        = "返回结果:";

    /**
     * 结果日志打印前缀
     */
    private static final String EXCEPTION_PREFIX    = "EXCEPTION:Cause[{}]";

    private LogPrintUtil() {

    }

    /**
     * 1.（开始）打印接口请求的日志信息
     * @param log Log
     * @param parameters 参数
     */
    public static void startLog(Logger log, Object... parameters) {
        // 请求的当前时间
        MDC.put(CO_LINK_TIME_PRINT, ((Long) System.currentTimeMillis()).toString());
        // 打印日志
        doPrintStartLogInfo(log, parameters);
    }

    /**
     * 2.结束）打印接口返回的日志信息
     * @param log Log
     * @param result 接口返回结果
     */
    public static void endLog(Logger log, Object... result) {
        // 获取请求接口时放入的时间
        long startLong = NumberUtils.toLong(MDC.get(CO_LINK_TIME_PRINT),
            System.currentTimeMillis());
        // 1.1 打印返回日志
        doPrintEndLogInfo(log, startLong, result);
        // mdc clear
        MDC.clear();
    }

    /**
     * 3.日常日志打印
     * @param log Log
     * @param e 异常
     */
    public static void printExceptionInfo(Logger log, Exception e) {
        // 日志级别error 3.1 拼接异常信息
        log.error(appendExceptionPrefix(getSimpleClazzName(log.getName())), e);
    }

    private static void doPrintStartLogInfo(Logger log, Object... parameters) {
        // 拼接请求日志前缀字符串  获取类名
        log.info(appendStartPrefix(getSimpleClazzName(log.getName()), parameters), parameters);
    }

    private static String appendStartPrefix(String clazzName, Object... objects) {
        // 根据参数个数拼接前缀
        StringBuilder prefix = appendPrefix(clazzName);
        // action
        prefix.append(BLANK_STR);
        prefix.append(START_PARENTHESES);
        prefix.append(REQUEST_STR);
        prefix.append(END_PARENTHESES);
        // 请求参数
        prefix.append(START_PARAMETER);
        // 去除固定的1个入参，开始循环，不是空就在原有的sb后面拼
        for (int i = 0; i < objects.length - 1; i++) {
            prefix.append(PLACEHOLDER);
        }
        return prefix.toString();
    }

    private static String getSimpleClazzName(String clazzName) {
        return clazzName.substring(clazzName.lastIndexOf('.') + 1);
    }

    private static StringBuilder appendPrefix(String clazzName) {
        StringBuilder prefix = new StringBuilder();
        prefix.append(PREFIX);
        //类名
        prefix.append(clazzName);
        prefix.append(DOUBLE_MID_BRACKETS);
        // 获取接口调用时间
        prefix.append(getTime(Long.parseLong(MDC.get(CO_LINK_TIME_PRINT))));
        prefix.append(MILLISECOND);
        prefix.append(END_PARENTHESES);
        return prefix;
    }

    private static long getTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public static void doPrintEndLogInfo(Logger log, Long startTime, Object... result) {
        // 打印日志   2.2 拼接返回日志前缀字符串
        log.info(appendEndPrefix(getSimpleClazzName(log.getName()), startTime), result);
    }

    private static String appendEndPrefix(String clazzName, Long startTime) {
        StringBuilder prefix = appendPrefix(clazzName);

        prefix.append(BLANK_STR);
        prefix.append(START_PARENTHESES);
        prefix.append(RESPONSE_STR);
        prefix.append(END_PARENTHESES);

        prefix.append(BLANK_STR);
        prefix.append(END_PREFIX);
        return prefix.toString();
    }

    private static String appendExceptionPrefix(String clazzName) {
        StringBuilder prefix = appendPrefix(clazzName);

        prefix.append(BLANK_STR);
        // 打印栈异常信息
        prefix.append(EXCEPTION_PREFIX);
        return prefix.toString();
    }
}