package com.seagen.ecc.common.constant;

/**
 * 电商业务常量
 * 
 * @author kuangjianbo
 * 
 */
public interface BussinessConst {
	/**
	 * 数据状态,删除
	 */
	public final static int DATA_STATE_DELETE = 14;

	/**
	 * 商品状态,创建中
	 */
	public final static int GOODS_STATE_CREATE = 1;
	/**
	 * 商品状态,上架,销售中
	 */
	public final static int GOODS_STATE_INSELL = 2;
	/**
	 * 商品状态,下架
	 */
	public final static int GOODS_STATE_OUTSELL = 3;
	/**
	 * 商品状态,卖完
	 */
	public final static int GOODS_STATE_SELLFINISH = 4;

	/**
	 * 购物车物品状态,正常
	 */
	public final static int GOODSCART_STATE_NORMAL = 1;
	/**
	 * 购物车物品状态,已经提交
	 */
	public final static int GOODSCART_STATE_COMMIT = 2;

	/**
	 * 支付类型,支付宝
	 */
	public final static int PAY_TYPE_ALIPAY = 1;
	/**
	 * 支付类型,微信
	 */
	public final static int PAY_TYPE_WEIXIN = 2;
	/**
	 * 支付类型,余额
	 */
	public final static int PAY_TYPE_BALANCE = 3;
	/**
	 * 订单状态,提交中
	 */
	public final static int ORDER_STATE_SUBMITTING = 1;
	/**
	 * 订单状态,已经提交,待支付
	 */
	public final static int ORDER_STATE_SUBMITTED = 2;
	/**
	 * 订单状态,已经支付
	 */
	public final static int ORDER_STATE_PAID = 4;
	/**
	 * 订单状态,已经发货
	 */
	public final static int ORDER_STATE_DELIVER = 6;
	/**
	 * 订单状态,已经完成
	 */
	public final static int ORDER_STATE_FINISH = 8;
	/**
	 * 订单状态,已经关闭
	 */
	public final static int ORDER_STATE_CLOSE = 9;
	/**
	 * 订单状态,已经取消
	 */
	public final static int ORDER_STATE_CANCEL = 10;
	/**
	 * 发货方式,快递
	 */
	public final static int DELIVER_TYPE_EXPERSS = 1;
	/**
	 * 订单类型,普通
	 */
	public final static int ORDER_TYPE_NOMAL = 1;

}
