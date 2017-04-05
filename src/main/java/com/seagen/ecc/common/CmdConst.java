package com.seagen.ecc.common;

public interface CmdConst {

    /**
     * 请求
     */
    public final static int CMD_TYPE_REQUEST = 1;

    /**
     * 请求回复
     */
    public final static int CMD_TYPE_REPLY = 2;

    /**
     * 上报
     * 
     */
    public final static int CMD_TYPE_REPORT = 3;

    /**
     * 上报答复
     */
    public final static int CMD_TYPE_REPORT_REPLY = 4;

    /**
     * 心跳
     */
    public final static int CMD_TYPE_HEARTBEAT = 5;

    /**
     * 心跳答复
     */
    public final static int CMD_TYPE_HEARTBEAT_REPLY = 6;

    /**
     * 返回码
     */
    public final static String RETURN_CODE = "ReturnCode";

    /**
     * 返回结果
     */
    public final static String RETURN_RESULT = "Result";

    public final static String RETURN_ERROR_MESSAGE = "ErrorDescription";

    /**
     * 返回详细
     */
    public final static String RETURN_DETAIL = "Detail";

    /**
     * 返回码：手机号码未注册
     */
    public final static String MEMBER_TEL_NO = "0";

    /**
     * 返回码：手机号码重复
     */
    public final static String MEMBER_TEL_REPEAT = "1";

    /**
     * 返回码：失败
     */
    public final static String RETURN_CODE_FAILURE = "0";

    /**
     * 返回码：成功
     */
    public final static String RETURN_CODE_SUCCESS = "1";

    /**
     * 返回码：未知错误
     */
    public final static String RETURN_CODE_ERROR = "10";

    /**
     * 返回结果码：否/失败/错误/异常
     */
    public final static String RESULT_CODE_FAILURE = "0";

    /**
     * 返回结果码：是/成功/正确/正常
     */
    public final static String RESULT_CODE_SUCCESS = "1";

    /**
     * 返回结果码：网络中断
     */
    public final static String RESULT_CODE_NET_OFF = "404";

    /**
     * 操作码: 心跳
     */
    public final static int FUN_CODE_HEARTBEAT = 2;

    /**
     * 操作码: 网关故障
     */
    public final static int FUN_CODE_NET_ALARM = 4;

    /**
     * 操作码：运营系统会员变更广播通知
     */
    public final static int RT_CODE_SERVICE_MEMBER = 11;

    /**
     * 是否收费通知
     */
    public final static int CABINET_CHARGE_STATE = 12;

    /**
     * 操作码：获取会员信息（通过手机号）
     */
    public final static int FUN_CODE_GET_MEM_TEL = 101;

    /**
     * 操作码：获取会员信息（通过会员卡号）
     */
    public final static int FUN_CODE_GET_MEM_CARD = 102;

    /**
     * 操作码：获取会员信息（通过会员账号）
     */
    public final static int FUN_CODE_GET_MEM_ACC = 103;

    /**
     * 操作码：查询会员是否存在（通过手机号）
     */
    public final static int FUN_CODE_QUERY_MEM_TEL = 111;

    /**
     * 操作码：查询会员是否存在（通过会员卡号）
     */
    public final static int FUN_CODE_QUERY_MEM_CARD = 112;

    /**
     * 操作码：查询会员是否存在（通过会员账号）
     */
    public final static int FUN_CODE_QUERY_MEM_ACC = 113;

    /**
     * 操作码：会员与快递柜建立绑定
     */
    public final static int FUN_CODE_CREATE_BINDING = 121;

    /**
     * 操作码：会员与快递柜建立绑定
     */
    public final static int FUN_CODE_RECIPIENT_BINDING = 122;

    /**
     * 操作码：交易（取件）
     */
    public final static int FUN_CODE_EXP_GET = 201;

    /**
     * 操作码：交易（放件）
     */
    public final static int FUN_CODE_EXP_PUT = 202;

    /**
     * 操作码：交易（逾期退回快件）
     */
    public final static int FUN_CODE_EXP_DEL = 203;

    /**
     * 操作码：交易（重新短信通知收件人取件码）
     */
    public final static int FUN_CODE_EXP_NOTIFY = 204;

    /**
     * 远程交易：取件（通过远程控制，收件人取走快件）
     */
    public final static int FUN_CODE_EXP_MSTSC_GET = 211;

    /**
     * 远程交易：退件（通过远程控制，快递员取走快件）
     */
    public final static int FUN_CODE_EXP_MSTSC_DEL = 212;

    /**
     * 远程交易: 重新生成取件码,相当于回收+重放
     */
    public final static int FUN_CODE_RENEW_TRADE = 213;

    /**
     * 操作码：自助服务（注册会员）
     */
    public final static int FUN_CODE_SERVICE_REG = 301;

    /**
     * 操作码：自助服务（会员激活）
     */
    public final static int FUN_CODE_SERVICE_ACT = 302;

    /**
     * 操作码：自助服务（修改登录方式）
     */
    public final static int FUN_CODE_SERVICE_LOG = 311;

    /**
     * 操作码：自助服务（修改密码）
     */
    public final static int FUN_CODE_SERVICE_PWD = 312;

    /**
     * 操作码：自助服务（修改手机号）
     */
    public final static int FUN_CODE_SERVICE_TEL = 313;

    /**
     * 操作码：运营（修改手机号码）
     */
    public final static int RT_CODE_SERVICE_TEL = 314;

    /**
     * 操作码：运营（重置会员密码）
     */
    public final static int RT_CODE_SERVICE_MEMBER_PWD = 315;

    /**
     * 操作码：运营（修改卡号）
     */
    public final static int RT_CODE_SERVICE_MEMBER_CARD = 316;

    /**
     * 操作码：运营（修改登录方式）
     */
    public final static int RT_CODE_SERVICE_LOGIN_MODEL = 317;

    /**
     * 操作码：运营（修改会员类型）
     */
    public final static int RT_CODE_SERVICE_MEMBER_TYPE = 318;

    /**
     * 操作码：运营（修改手机号码）
     */
    public final static int RT_CODE_SERVICE_MEMBER_TEL = 319;

    /**
     * 操作码：自助服务（会员充值）
     */
    public final static int FUN_CODE_SERVICE_REC = 321;

    /**
     * 操作码：发送验证码
     */
    public final static int FUN_CODE_SENT_ID_CODE = 401;

    /**
     * 操作码：发送随机密码
     */
    public final static int FUN_CODE_SENT_RAN_PWD = 402;

    /**
     * 操作码：移除柜子上的会员信息
     */
    public final static int FUN_CODE_REMOVE_MEMBER = 403;

    /**
     * 操作码：重置密码
     */
    public final static int FUN_CODE_SERVICE_RESET_PWD = 404;

    /**
     * 操作码：快递员撤销快件
     */
    public final static int FUN_CODE_EXP_CANCEL = 405;

    /**
     * 操作码：修改格口状态
     */
    public final static int FUN_CODE_UPDATE_CELLSTATE = 501;

    /**
     * 操作码：运营系统快递公司变动通知
     */
    public final static int RT_CODE_SERVICE_COMPANY = 601;

    /**
     * 操作码：运营系统远程打开格口通知
     */
    public final static int RT_CODE_OPEN_CELL = 602;

    /**
     * 终端同步快递公司
     */
    public final static int FUN_CODE_COMPANY = 605;

    /**
     * 活动信息下发
     */
    public final static int FUN_CODE_ACTIVITY_UPDATE = 606;

    /**
     * 活动二维码生成
     */
    public final static int FUN_CODE_ACTIVITY_QRCODE = 607;

    /**
     * 刷新活动数据
     */
    public final static int FUN_CODE_ACTIVITY_REFRESH = 608;

    /**
     * 参数列表
     */
    public final static int FUN_CODE_PARAM_LIST = 10001;

    /**
     * 查询参数值
     */
    public final static int FUN_CODE_PARAM_QUERY = 10002;

    /**
     * 设置参数
     */
    public final static int FUN_CODE_PARAM_SETTING = 10003;

    /**
     * 告警上报
     */
    public final static int FUN_CODE_ALARM_REPORT = 10004;

    /**
     * 模块程序更新
     */
    public final static int FUN_CODE_MODULE_UPGRADE = 10005;

    /**
     * 日志上传
     */
    public final static int FUN_CODE_LOG_UPLOAD = 10006;

    /**
     * 打开格口
     */
    public final static int FUN_CODE_OPEN_CELL_LOCK = 10007;

    /**
     * 程序重启
     */
    public final static int FUN_CODE_PROGRAM_RESTART = 10008;

    /**
     * 系统重启
     */
    public final static int FUN_CODE_SYSTEM_RESTART = 10009;

    /**
     * 程序恢复默认设置并清空所有数据，
     */
    public final static int FUN_CODE_PROGRAM_RESET = 10010;

    /**
     * 上传中间摄像头的图片
     */
    public final static int FUN_CODE_IMAGE_UPLOAD = 10011;

    /**
     * 开启或禁用某一个模块
     */
    public final static int FUN_CODE_MODULE_ENABLE = 10012;

    /**
     * 数据备份
     */
    public final static int FUN_CODE_DATA_BACKUP = 10013;

    /**
     * 数据恢复
     */
    public final static int FUN_CODE_DATA_RECOVERY = 10014;

    /**
     * 数据同步
     */

    public final static int FUN_CODE_DATA_SYNC = 10015;

    /**
     * 上线通知
     */
    public final static int FUN_CODE_ONLINE = 10016;

    /**
     * 下线通知
     */
    public final static int FUN_CODE_OFFLINE = 10017;

    /**
     * 查询是否在线
     */
    public final static int FUN_CODE_IFLINE = 10018;

    /**
     * 长时间失去联系
     */
    public final static int FUN_CODE_ONLINE_TIMEOUT = 10019;

    /**
     * 数据同步
     */
    public final static int FUN_CODE_FILE_UPLOAD = 10020;

    /**
    * 业务终端多次启动看门狗失败事件上报
    */
    public final static int FUN_CODE_WATCHDOG_START_ERROR_REPORT = 10300;

    /**
     * 查询在线列表
     */
    public final static int FUN_CODE_ONLINE_LIST = 10021;

    /**
     * 上报配置列表
     */
    public final static int FUN_CODE_REPORT_PARAMS = 10022;

    /**
     * 执行数据库脚本
     */
    public final static int FUN_CODE_EXECUTE_SQL = 10023;

    /**
     * 执行模块指令
     */
    public final static int FUN_CODE_MODULE_COMMAND = 10024;

    /**
     * 根据卡号获取维护人员信息
     */
    public final static int FUN_CODE_GET_MAINTAINER = 10025;

    /**
     * 同步模块状态
     */
    public final static int FUN_CODE_SYNC_MODULE_STATE = 10026;

    /**
     * 事件上报
     */
    public final static int FUN_CODE_EVENT_REPORT = 10027;

    // 修改格口状态
    public final static int FUN_CODE_CHANGE_CELL_STATE = 10028;

    // 检测格口货物
    public final static int FUN_CODE_DETECT_CELL_STUFF = 10029;

    // 售货柜LED灯时间设置
    public final static int FUN_CODE_SET_CELL_LIGHT = 10030;

    // 售货柜LED灯开启关闭控制
    public final static int FUN_CODE_TURNON_CELL_LIGHT = 10031;

    // 售货柜LED灯控制
    public final static int FUN_CODE_TURNOFF_CELL_LIGHT = 10032;

    // 同步格口状态
    public final static int FUN_CODE_SYN_CELL_STATE = 10033;

    // 禁用/取消禁用 主柜
    public final static int FUN_CODE_FORBID_CABINET = 10034;
    
    /**
     * 执行bat命令
     */
    public final static int FUN_CODE_EXECUTE_BAT = 10035;
    
    /**
     * 终端请求更新
     */
    public final static int FUN_CODE_TERMINAL_UPGRADE = 100036;
    
    /**
     * 请求终端分析日志
     */
    public final static int FUN_CODE_ANALYSE_LOG = 10037;
    

    public final static int FUN_CODE_REQUEST_CABINET_MODEL = 10100;

    public final static int FUN_CODE_REQUEST_SLAVE_MODEL = 10101;

    public final static int FUN_CODE_REQUEST_LOG_SIZE = 10200;

    /**
     * 设置业务终端系统时间
     * */
    public final static int FUN_CODE_SET_SYSTEM_TIME = 10301;

    /**
     * 上报底层控制板格口打开异常告警
     */
    public final static int FUN_CODE_CELL_OPEN_EXCEPTION = 10302;

    /**
     * 设置分发柜灯颜色
     */
    public final static int FUN_CODE_SET_CABINET_COLOR = 10303;

    /* ***20001-29999 表示售货柜相关业务(终端<->售货柜运营系统) ******************* */
    /**
     * 链路时间检测,接收方收到该消息后立即回复,发送方计算出往返时间来判断当前通信质量<br>
     * 实时性较高的业务是可以使用
     */
    public final static int FUNC_NET_CHECK = 20001;

    /**
     * 修改/添加商品信息
     */
    public final static int FUNC_GOODS_UPDATE = 20002;

    /**
     * 商品补货
     */
    public final static int FUNC_TRADE_ADD = 20003;

    /**
     * 商品扣款
     */
    public final static int FUNC_TRADE_PAY = 20004;

    /**
     * 买家取出
     */
    public final static int FUNC_TRADE_OUT = 20005;

    /**
     * 卖家回收
     */
    public final static int FUNC_TRADE_RECYCLE = 20006;

    /**
     * 获取二维码
     */
    public final static int FUNC_QRCODE_GET = 20007;

    /**
     * 支付成功通知
     */
    public final static int FUNC_PAY_NOTIFY = 20008;

    /**
     * 支付订单结果查询
     */
    public final static int FUNC_PAY_QUERY = 20009;

    /**
     * 会员推送
     */
    public final static int FUNC_MEMBER_PUT = 20010;

    /**
     * 远程发货
     */
    public final static int FUNC_REMOTE_DELIVER = 20011;

    /**
     * 退款退货
     */
    public final static int FUNC_REMOTE_REFUND = 20012;

    /**
     * 解除商品锁定
     */
    public final static int FUNC_LOCK_TRADING = 20013;

    /**
     * 商品下架
     */
    public final static int FUNC_GOODS_RECYCLE = 20014;

    /**
     * 禁用/删除
     */
    public final static int FUNC_GOODS_DISABLE = 20015;

    /* ***30001-39999 表示寄存/寄件相关业务 ******************* */
    /**
     * 格口锁定
     */
    public final static int FUNC_CELL_LOCK = 30001;

    /**
     * 格口锁定取消
     */
    public final static int FUNC_CELL_LOCK_CANCELL = 30002;

    /**
     * 寄存放入
     */
    public final static int FUNC_DEPOSIT_IN = 30003;

    /**
     * 寄存取出
     */
    public final static int FUNC_DEPOSIT_OUT = 30004;

    /**
     * 格口预定
     */
    public final static int FUNC_CELL_RESERVE = 30005;

    /**
     * 格口预定放入
     */
    public final static int FUNC_CELL_RESERVE_IN = 30006;

    /**
     * 格口预定取出
     */
    public final static int FUNC_CELL_RESERVE_OUT = 30007;

    /**
     * 第三方应用设置
     */
    public final static int FUNC_PARTNER_SET = 30008;

    /**
     * 第三方应用事件
     */
    public final static int FUNC_PARTNER_EVENT = 30009;

    /****40001-49999 广告相关业务 ********************/
    /**
     * 广告下发
     */
    public final static int FUNC_PUSH_AD = 40001;

    /****50001-59999 社区通APP相关业务 ********************/

    /**获取活动信息*/
    public final static int FUNC_APP_ACTIVITY_GET  = 50002;

    /**抽奖*/
    public final static int FUNC_APP_LUCKY_DRAW = 50010;

    /**获取当日剩余抽奖次数*/
    public final static int FUNC_APP_LUCKY_DRAW_LEFT = 50011;
    
    /**重发短信*/
    public final static int FUNC_APP_SMS_RESEND = 50012; 

}