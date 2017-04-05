package com.seagen.ecc.common;

/**
 * 执行某个操作返回代码
 * 
 * @author kuangjianbo<kuangjianbo_sea@163.com>
 * @createdate 2013-12-26
 */
public interface Ret {
    /**
     * 表示正确，没有问题
     */
    public final static String OK = "10000";

    /**
     * 输入错误格式不对
     */
    public final static String INPUT = "10001";

    /**
     * 需要（重新）输入验证码
     */
    public final static String CHECKCODE = "10002";

    /**
     * 用户名或者密码错误
     */
    public final static String PASSWD = "10003";

    /**
     * 余额不足
     */
    public final static String NO_MONEY = "10004";

    /**
     * 手机号格式错误
     */
    public final static String TEL_ERROR = "10005";

    /**
     * 手机号重复
     */
    public final static String TEL_REPEAT = "10006";

    /**
     * 短信验证码发送过于频繁
     */
    public final static String SMSCODE_REPEAT = "10007";

    /**
     * 会员账户不存在
     */
    public final static String MEMBER_ACCOUNT_ONT_EXITST = "10008";

    /**
     * 远程通信失败(RemoteLookupFailureException)
     */
    public final static String Remote_Lookup_Failure = "10009";

    /**
     * 会员已注销/禁用
     */
    public final static String Member_Forbidden = "10010";

    /**
     * 数据错误
     */
    public final static String DATA_ERR = "10011";

    /**
     * 请求失败(第三方服务异常)
     */
    public final static String SERVICE_ERR = "10012";

    /**
     * 操作频繁
     */
    public final static String OP_REPEAT = "10013";

    /**
     * 未知错误
     */
    public final static String UNKOWN = "10100";

    /**
     * 添加片区没有快递柜
     */
    public final static String NOCABINTES = "10200";

    /**
     * 数据过期
     */
    public final static String DATA_DATED = "10201";

    /**
     * 会员不存在
     */
    public final static String MEMBER_ID_NOT_EXITST = "10202";

    /**
     * 支付密码不存在
     */
    public final static String MEMBER_PAYPWD_NOT_EXITST = "10203";

    /**
     * 密码错误
     */
    public final static String MEMBER_PWD_ERROR = "10204";

    /**
     * 密码过于简单
     */
    public final static String MEMBER_PWD_SIMPLE = "10205";

    /**
     * 数量限制
     */
    public static final String LIMIT_OF_COUNT = "10206";

    /**
     * 认证失败
     */
    public static final String AUTH_ERR = "10207";

    /**
     * 请求超时
     */
    public static final String REQ_TIME_OUT = "10208";

    /**
     * 任务正在运行,请等待
     */
    public static final String TASK_WAIT = "10209";

    /**
     * 需要微信登录
     */
    public static final String LOGIN_WEIXIN = "10211";
}
