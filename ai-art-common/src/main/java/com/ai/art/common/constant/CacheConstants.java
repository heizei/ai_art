package com.ai.art.common.constant;

/**
 * 缓存的key 常量
 */
public class CacheConstants
{
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 登录用户 redis key
     */
    public static final String USER_ID_TOKEN_KEY = "user_id_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录短信验证码 redis key
     */
    public static final String SMS_CODE_KEY = "sms_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * 供需编码 redis key
     */
    public static final String DEM_SUP_MAX_NO_KEY = "dem_sup_max_no";

    /**
     * 供需编码 redis key
     */
    public static final String DEM_SUP_APP_MAX_NO_KEY = "dem_sup_app_max_no";

    /**
     * mackey
     */
    public static final String MACKEY_KEY = "MACKEY_KEY";

    /**
     * transKey
     */
    public static final String TRANS_KEY = "TRANS_KEY";

    public static final String PROVINCE_CITY_KEY = "PROVINCE_CITY_LIST_KEY";
    public static final String PROVINCE_CITY_MAP_KEY = "province_city_map_key:";

    /**
     * 企业信息缓存key
     */
    public static final String ENTERPRISE_BASE_INFO_KEY = "ENTERPRISE_BASE_INFO_KEY";

    /**
     * 自动审核任务key
     */
    public static final String AUTO_CHECK_JOB_COMMON_KEY = "AUTO_CHECK_JOB_COMMON_KEY_CO_LINK_PRO";

}
