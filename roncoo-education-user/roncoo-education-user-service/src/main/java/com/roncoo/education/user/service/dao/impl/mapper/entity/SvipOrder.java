package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SvipOrder implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Date gmtCreate;
        private Date gmtModified;
        private Long userNo;
        private String mobile;
        private Date registerTime;
        private Long orderNo;
        private Long serialNo;
        private String orderName;
        private BigDecimal pricePayable;
        private BigDecimal priceDiscount;
        private BigDecimal pricePaid;
        private Integer pricePaidSource;
        private Integer tradeType;
        private Integer payType;
        private Integer channelType;
        private Integer orderStatus;
        private Integer isShowUser;
        private String remarkCus;
        private String remark;
        private Date payTime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
        public Date getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Date gmtModified) {
            this.gmtModified = gmtModified;
        }
        public Long getUserNo() {
            return userNo;
        }

        public void setUserNo(Long userNo) {
            this.userNo = userNo;
        }
        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public Date getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(Date registerTime) {
            this.registerTime = registerTime;
        }
        public Long getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Long orderNo) {
            this.orderNo = orderNo;
        }
        public Long getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(Long serialNo) {
            this.serialNo = serialNo;
        }
        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }
        public BigDecimal getPricePayable() {
            return pricePayable;
        }

        public void setPricePayable(BigDecimal pricePayable) {
            this.pricePayable = pricePayable;
        }
        public BigDecimal getPriceDiscount() {
            return priceDiscount;
        }

        public void setPriceDiscount(BigDecimal priceDiscount) {
            this.priceDiscount = priceDiscount;
        }
        public BigDecimal getPricePaid() {
            return pricePaid;
        }

        public void setPricePaid(BigDecimal pricePaid) {
            this.pricePaid = pricePaid;
        }
        public Integer getPricePaidSource() {
            return pricePaidSource;
        }

        public void setPricePaidSource(Integer pricePaidSource) {
            this.pricePaidSource = pricePaidSource;
        }
        public Integer getTradeType() {
            return tradeType;
        }

        public void setTradeType(Integer tradeType) {
            this.tradeType = tradeType;
        }
        public Integer getPayType() {
            return payType;
        }

        public void setPayType(Integer payType) {
            this.payType = payType;
        }
        public Integer getChannelType() {
            return channelType;
        }

        public void setChannelType(Integer channelType) {
            this.channelType = channelType;
        }
        public Integer getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(Integer orderStatus) {
            this.orderStatus = orderStatus;
        }
        public Integer getIsShowUser() {
            return isShowUser;
        }

        public void setIsShowUser(Integer isShowUser) {
            this.isShowUser = isShowUser;
        }
        public String getRemarkCus() {
            return remarkCus;
        }

        public void setRemarkCus(String remarkCus) {
            this.remarkCus = remarkCus;
        }
        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
        public Date getPayTime() {
            return payTime;
        }

        public void setPayTime(Date payTime) {
            this.payTime = payTime;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", gmtCreate=").append(gmtCreate);
            sb.append(", gmtModified=").append(gmtModified);
            sb.append(", userNo=").append(userNo);
            sb.append(", mobile=").append(mobile);
            sb.append(", registerTime=").append(registerTime);
            sb.append(", orderNo=").append(orderNo);
            sb.append(", serialNo=").append(serialNo);
            sb.append(", orderName=").append(orderName);
            sb.append(", pricePayable=").append(pricePayable);
            sb.append(", priceDiscount=").append(priceDiscount);
            sb.append(", pricePaid=").append(pricePaid);
            sb.append(", pricePaidSource=").append(pricePaidSource);
            sb.append(", tradeType=").append(tradeType);
            sb.append(", payType=").append(payType);
            sb.append(", channelType=").append(channelType);
            sb.append(", orderStatus=").append(orderStatus);
            sb.append(", isShowUser=").append(isShowUser);
            sb.append(", remarkCus=").append(remarkCus);
            sb.append(", remark=").append(remark);
            sb.append(", payTime=").append(payTime);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}