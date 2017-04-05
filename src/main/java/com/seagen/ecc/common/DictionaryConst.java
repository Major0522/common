package com.seagen.ecc.common;

/**
 * 字典常量值
 * 
 * @author kuangjianbo
 */
public interface DictionaryConst {

    /**
     * 支付类型,支付宝/二维码
     */
    public final static int PAY_TYPE_ALIPAY_QRCODE = 1;

    /**
     * 支付类型,微信/二维码
     */
    public final static int PAY_TYPE_WEIXIN_QRCODE = 2;

    /**
     * 支付类型,余额支付
     */
    public final static int PAY_TYPE_BALANCE = 3;

    /**
     * 支付宝网页支付
     */
    public final static int PAY_TYPE_WEBPAGE = 4;

    /**
     * 支付宝移动快捷支付
     */
    public final static int PAY_TYPE_MOBILE = 5;

    /**
     * 微信移动快捷支付 
     */
    public final static int PAY_TYPE_WEIXIN = 6;

    /**
     * 支付状态,未支付
     */
    public final static int PAY_STATE_TOPAY = 1;

    /**
     * 支付状态,已经支付
     */
    public final static int PAY_STATE_PAID = 2;

    // -------------------------告警相关------------------------------
    /**
     * 格口使用状态,未使用
     */
    public final static int CELL_STATE_UNUSE = 1;

    /**
     * 格口使用状态,使用中
     */
    public final static int CELL_STATE_USING = 2;

    /**
     * 格口打开
     */
    public final static int CELL_STATE_OPEN = 0;

    /**
     * 格口恢复
     */
    public final static int CELL_STATE_NORMAL = 1;

    /**
     * 格口禁用
     */
    public final static int CELL_STATE_DISABLED = 4;

    /**
     * 副柜状态：正常
     */
    public static final int SLAVE_STATE_NORMAL = 1;

    /**
     * 副柜状态：损坏
     */
    public static final int SLAVE_STATE_UNUSUAL = 2;

    /**
     * 副柜状态：禁用
     */
    public static final int SLAVE_STATE_DISABLE = 3;

    /**
     * 副柜状态：移除
     */
    public static final int SLAVE_STATE_DELETE = 4;

    /**
     * 参数上架时没有放置货物
     */
    public final static int PARAM_STUFF_NOTHING_ERROR = 100024;

    /**
     * 参数购买下架时没有取走货物
     */
    public final static int PARAM_STUFF_KEEP_ERROR = 100025;

    /**
     * 取件时关闭格口失败、退件时关闭格口失败
     */
    public final static int PARAM_GETBACK_CLOSE_CELL_LOCK_ERROR = 100026;

    /**
     * 柜子离线
     */
    public final static int PARAM_CABINET_OFFLINE = 100027;

    /**
     * 看门狗异常
     */
    public final static int PARAM_WATCHDOG_EXCEPTION = 100030;

    /**
     * 格口异常打开
     */
    public final static int PARAM_CELLOPEN_EXCEPTION = 100031;

    /**
     * 放件时关闭格口失败,上架时关闭格口失败,购买时关闭格口失败,下架时关闭格口失败
     */
    public final static int PARAM_CLOSE_CELL_LOCK_ERROR = 100017;

    /**
     * 告警级别普通
     */
    public final static int ALARM_LEVEL_NORMAL = 1;

    /**
     * 告警级别重要
     */
    public final static int ALARM_LEVEL_IMPORTANT = 2;

    /**
     * 告警级别严重
     */
    public final static int ALARM_LEVEL_SERIOUS = 3;

    /**
     * 告警状态未确认
     */
    public final static int ALARM_STATE_NOTCONFIRM = 1;

    /**
     * 告警状态已确认
     */
    public final static int ALARM_STATE_CONFIRM = 2;

    /**
     * 告警状态已清除
     */
    public final static int ALARM_STATE_CLEAR = 3;

    /**
     * 告警状态已恢复
     */
    public final static int ALARM_STATE_RECOVER = 4;

    /**
     * 告警上报
     */
    public final static int ALARM_REPORT = 1;

    /**
     * 告警恢复
     */
    public final static int ALARM_RECOVER = 0;

    /**
     * 默认柜子短信级别
     */
    public final static int CABINET_SMS_LEVEL = 2;

    /**
     * 柜子在线
     */
    public final static int CABINET_ONLINE = 1;

    /**
     * 柜子离线
     */
    public final static int CABINET_OFFLINE = 2;

    /**
     * 柜子升级成功
     */
    public final static int CABINET_UPGRADE_SUCCESS = 1;

    /**
     * 柜子升级失败
     */
    public final static int CABINET_UPGRADE_FAIL = 2;

    /**
     * 快递柜
     */
    public final static int SLAVETYPE_EXPRESS_CABINET = 1;

    /**
     * 售货柜
     */
    public final static int SLAVETYPE_SALE_CABINET = 2;

    /**
     * 分发柜
     */
    public final static int SLAVETYPE_SAMPLE_CABINET = 4;

    /**
     * window操作系统
     */
    public final static int CABINET_OS_WINDOW = 1;

    /**
     * android操作系统
     */
    public final static int CABINET_OS_ANDROID = 2;

    /**
     * 格口操作，添加格口
     */
    public final static int CELL_OPERATE_ADD = 1;

    /**
     * 格口操作，删除格口
     */
    public final static int CELL_OPERATE_DELETE = 2;

    /**
     * 格口操作，修改格口
     */
    public final static int CELL_OPERATE_EDIT = 3;

    /**
     * 格口操作，启用格口
     */
    public final static int CELL_OPERATE_START = 4;

    /**
     * 格口操作，禁用格口
     */
    public final static int CELL_OPERATE_FORBID = 5;

    /**
     * 终端角色(柜子)
     */
    public final static int CONNECTOR_ROLE_CABINET = 1;

    /**
     * 终端状态(不绑定)
     */
    public final static int CONNECTOR_STATE_NOTBINGDING = 3;

    // -------------------------告警相关------------------------------

    /**
     * 登录方式,帐号密码
     */
    public final static int LOGIN_MODE_ACC_PWD = 1;

    /**
     * 登录方式,手机号密码
     */
    public final static int LOGIN_MODE_TEL_PWD = 2;

    /**
     * 登录方式,默认值(帐号密码+手机号密码)
     */
    public final static int LOGIN_MODE_DEFAULT = 3;

    /**
     * 充值组件/来源,后台
     */
    public final static int RECHARGE_COMPONENT_BACKEND = 1;

    /**
     * 充值组件/来源,APP
     */
    public final static int RECHARGE_COMPONENT_APP = 2;

    /**
     * 充值组件/来源,终端
     */
    public final static int RECHARGE_COMPONENT_TERMINAL = 3;

    /**
     * 充值组件/来源,网站
     */
    public final static int RECHARGE_COMPONENT_MEMBER_SITE = 4;

    /**
     * 充值方式,未知
     */
    public final static int RECHARGE_TYPE_UNKNOW = 0;

    /**
     * 充值方式,人工
     */
    public final static int RECHARGE_TYPE_MAN = 1;

    /**
     * 充值方式,支付宝
     */
    public final static int RECHARGE_TYPE_ALIPAY = 2;

    /**
     * 充值方式,微信
     */
    public final static int RECHARGE_TYPE_WXPAY = 3;
    
    /**
     * 充值方式,系统
     */
    public final static int RECHARGE_TYPE_SYSTEM = 4 ;
    
    /**
     * 充值业务类型,1充值赠送
     */
    public final static int RECHARGE_BUSINESS_TYPE_GIVING = 1 ;
    /**
     * 充值业务类型,3冲账抵扣
     */
    public final static int RECHARGE_BUSINESS_TYPE_DEDUCTION = 3 ;
    /**
     * 充值业务类型,5费用返还
     */
    public final static int RECHARGE_BUSINESS_TYPE_RETURN  = 5 ;
    /**
     * 充值业务类型,7重复充值扣除
     */
    public final static int RECHARGE_BUSINESS_TYPE_REPEAT = 7 ;
    /**
     * 充值业务类型,10其他
     */
    public final static int RECHARGE_BUSINESS_TYPE_OTHER = 10 ; 

    /**
     * 交易状态:1未销售,3销售中,4加入购物车,5已订出,7,已过期,9已经回收,10已付款,11已退款,12已售出<br>
     * 1-9 未售出<br>
     * 10-15 已售
     */
    /**
     * 交易状态,销售中
     */
    public static final int TRADE_STATE_ONSALE = 3;

    /**
     * 交易状态,已订出.锁定
     */
    public static final int TRADE_STATE_LOCK = 5;

    /**
     * 交易状态,待回收
     */
    public static final int TRADE_STATE_PRE_RECYCLE = 8;

    /**
     * 交易状态,已经回收
     */
    public static final int TRADE_STATE_RECYCLE = 9;

    /**
     * 交易状态,已经支付
     */
    public static final int TRADE_STATE_PAID = 10;

    /**
     * 交易状态,已售出
     */
    public static final int TRADE_STATE_SELLOUT = 12;

    /**
     * 交易状态,退货中
     */
    public static final int TRADE_STATE_RETURNING = 14;

    /**
     * 交易状态,已经退货
     */
    public static final int TRADE_STATE_RETURNED = 16;

    /**
     * 消费方式,购物
     */
    public static final int CHARGE_MODE_BUY = 12;

    /**
     * 活动状态,运行
     */
    public static final int ACTIVITY_STATE_RUNNING = 1;

    /**
     * 活动状态,停止
     */
    public static final int ACTIVITY_STATE_STOP = 2;

    /**
     * 系统平台类型,默认,柜子运营后台
     */
    public static final int PLATFORM_DEFAULT = 1;

    /**
     * 系统平台类型,APP
     */
    public static final int PLATFORM_APP = 2;

    /**
     * 系统平台类型,会员网站
     */
    public static final int PLATFORM_MEMBER_SITE = 4;

    /**
     * 系统平台类型,终端
     */
    public static final int PLATFORM_TERMINAL = 3;

    /**
     * 系统平台类型,电商平台
     */
    public static final int PLATFORM_EBUSINESS = 5;

    /**
     * 系统平台类型,开放平台
     */
    public static final int PLATFORM_OPEN = 6;

    /**
     * 会员状态,正常
     */
    public static final int MEMBER_STATE_NORMAL = 1;

    /**
     * 会员状态,欠费冻结
     */
    public static final int MEMBER_STATE_ARREARAGE = 2;

    /**
     * 会员状态,注销
     */
    public static final int MEMBER_STATE_SHUTDOWN = 3;

    /**
     * 会员状态,未激活
     */
    public static final int MEMBER_STATE_NOACTIVATE = 4;

    /**
     * 会员类型,快递员
     */
    public static final int MEMBER_TYPE_COURIER = 1;

    /**
     * 会员类型,收件人,普通会员
     */
    public static final int MEMBER_TYPE_RECIPIENT = 2;

    /**
     * 会员类型,售货员
     */
    public static final int MEMBER_TYPE_SELLER = 5;

    /**
     * 会员类型,样品专员
     */
    public static final int MEMBER_TYPE_SPECIMEN = 9;

    /**
     * 预约状态,已预约
     */
    public static final int RESERVE_STATE_ING = 3;

    /**
     * 预约状态,放入中
     */
    public static final int RESERVE_STATE_IN = 5;

    /**
     * 预约状态,已经取出
     */
    public static final int RESERVE_STATE_OUT = 7;

    /**
     * 合作者事件类型,文件
     */
    public static final int PARTNER_EVENT_TYPE_FILE = 1;

    /**
     * 合作者事件类型,协议拒绝
     */
    public static final int PARTNER_EVENT_TYPE_REJECT = 2;

    /**
     * 合作者事件类型,预定放入
     */
    public static final int PARTNER_EVENT_TYPE_RESERVEIN = 3;

    /**
     * 合作者事件类型,预定取出
     */
    public static final int PARTNER_EVENT_TYPE_RESERVEOUT = 4;

    /**
     * 快件状态,暂存
     */
    public static final Integer EXPRESS_STATE_IN = 1;

    /**
     * 快件状态,逾期
     */
    public static final Integer EXPRESS_STATE_IN_ED = 2;

    /**
     * 快件状态,已收
     */
    public static final Integer EXPRESS_STATE_OUT = 4;

    /**
     * 快件状态,逾期已收
     */
    public static final Integer EXPRESS_STATE_OUT_ED = 5;

    /**
     * 快件状态,退回
     */
    public static final Integer EXPRESS_STATE_RETURN = 7;

    /**
     * 公共状态常量：0
     */
    public static final Integer EXPRESS_STATE_PUBLIC_ZERO = 0;

    /**
     * 公共状态常量：1
     */
    public static final Integer EXPRESS_STATE_PUBLIC_ONE = 1;

    /**
     * 公共状态常量：2
     */
    public static final Integer EXPRESS_STATE_PUBLIC_TWO = 2;

    /**
     * 公共状态常量：3
     */
    public static final Integer EXPRESS_STATE_PUBLIC_THREE = 3;
}
