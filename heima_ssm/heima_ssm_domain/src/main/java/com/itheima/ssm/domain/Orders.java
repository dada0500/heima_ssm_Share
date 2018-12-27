package com.itheima.ssm.domain;

import com.itheima.ssm.utils.DateUtils;

import java.util.Date;
import java.util.List;

//订单
public class Orders {
    private String id;
    private String orderNum;
    private Date orderTime;
    private String orderTimeStr;
    private Integer peopleCount;
    private String orderDesc;
    private Integer payType;
    private String payTypeStr;
    private Integer orderStatus;
    private String orderStatusStr;
    private String productId;
    private String memberId;
    private Product product;
    private List<Traveller> travellers;
    private Member member;

    public String getOrderStatusStr () {
//        订单状态(0 未支付 1 已支付)
        if(orderStatus == 0){
            orderStatusStr = "未支付";
        }else if(orderStatus == 1) {
            orderStatusStr = "已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr (String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getOrderTimeStr () {
        if(orderTime != null){
            orderTimeStr = DateUtils.date2String (orderTime,"yyyy-MM-dd HH:mm:ss");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr (String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getPayTypeStr () {
//        支付方式(0 支付宝 1 微信 2其它)
        if(payType == 0){
            payTypeStr = "支付宝";
        }else if(payType == 1){
            payTypeStr = "微信";
        }else if(payType == 2){
            payTypeStr = "其它";
        }
        return payTypeStr;
    }

    public void setPayTypeStr (String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public Product getProduct () {
        return product;
    }

    public void setProduct (Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers () {
        return travellers;
    }

    public void setTravellers (List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember () {
        return member;
    }

    public void setMember (Member member) {
        this.member = member;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getOrderNum () {
        return orderNum;
    }

    public void setOrderNum (String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime () {
        return orderTime;
    }

    public void setOrderTime (Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getPeopleCount () {
        return peopleCount;
    }

    public void setPeopleCount (Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getOrderDesc () {
        return orderDesc;
    }

    public void setOrderDesc (String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getPayType () {
        return payType;
    }

    public void setPayType (Integer payType) {
        this.payType = payType;
    }

    public Integer getOrderStatus () {
        return orderStatus;
    }

    public void setOrderStatus (Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductId () {
        return productId;
    }

    public void setProductId (String productId) {
        this.productId = productId;
    }

    public String getMemberId () {
        return memberId;
    }

    public void setMemberId (String memberId) {
        this.memberId = memberId;
    }
}
