package com.bigbigrain.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@TableName("tb_pay_log")
public class PayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @TableId(value = "out_trade_no", type = IdType.AUTO)
    private String outTradeNo;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 支付完成时间
     */
    private Date payTime;
    /**
     * 支付金额（分）
     */
    private Long totalFee;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 交易号码
     */
    private String transactionId;
    /**
     * 交易状态
     */
    private String tradeState;
    /**
     * 订单编号列表
     */
    private String orderList;
    /**
     * 支付类型
     */
    private String payType;


    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String orderList) {
        this.orderList = orderList;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "PayLog{" +
        ", outTradeNo=" + outTradeNo +
        ", createTime=" + createTime +
        ", payTime=" + payTime +
        ", totalFee=" + totalFee +
        ", userId=" + userId +
        ", transactionId=" + transactionId +
        ", tradeState=" + tradeState +
        ", orderList=" + orderList +
        ", payType=" + payType +
        "}";
    }
}
