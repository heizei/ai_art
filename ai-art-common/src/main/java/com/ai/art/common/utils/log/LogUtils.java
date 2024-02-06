package com.ai.art.common.utils.log;

import java.util.UUID;

/**
 * 处理并记录日志文件
 */
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

    public static String createTraceLogId() {
        return UUID.randomUUID().toString();
    }
}
