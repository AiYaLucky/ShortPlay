package com.aiyalucky.onlinevideo.Data;

/**
 * 充值记录信息
 * @ClassName TaskInfo
 * @Description
 * @Author xu xiao wei
 * @Date 2023/6/25 17:01
 * @Version 1.0
 */
public class VideoPoint {

    /**
     * 自增id
     */
    Integer id;

    /**
     * 订单id
     */
    Integer orderId;

    /**
     * 充值时间
     */
    long rechargeTime;

    /**
     * 充值金额
     */
    Integer rechargeMoney;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public long getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(long rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public Integer getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Integer rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }
}
