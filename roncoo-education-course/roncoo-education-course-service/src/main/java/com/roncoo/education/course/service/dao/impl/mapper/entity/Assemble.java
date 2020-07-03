package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Assemble implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long uid;
        private Long assembleId;
        private Long orderId;
        private Integer totalNum;
        private BigDecimal totalPrice;
        private Long cid;
        private Long pid;
        private Integer people;
        private BigDecimal price;
        private Date addTime;
        private Date stopTime;
        private Integer isAsmer;
        private Integer isTpl;
        private Integer isRefund;
        private Integer status;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUid() {
                return uid;
            }

        public void setUid(Long uid) {
            this.uid = uid;
        }
        public Long getAssembleId() {
            return assembleId;
        }

        public void setAssembleId(Long assembleId) {
            this.assembleId = assembleId;
        }
        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }
        public Integer getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(Integer totalNum) {
            this.totalNum = totalNum;
        }
        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }
        public Long getCid() {
            return cid;
        }

        public void setCid(Long cid) {
            this.cid = cid;
        }
        public Long getPid() {
            return pid;
        }

        public void setPid(Long pid) {
            this.pid = pid;
        }
        public Integer getPeople() {
            return people;
        }

        public void setPeople(Integer people) {
            this.people = people;
        }
        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }
        public Date getStopTime() {
            return stopTime;
        }

        public void setStopTime(Date stopTime) {
            this.stopTime = stopTime;
        }
        public Integer getIsAsmer() {
            return isAsmer;
        }

        public void setIsAsmer(Integer isAsmer) {
            this.isAsmer = isAsmer;
        }
        public Integer getIsTpl() {
            return isTpl;
        }

        public void setIsTpl(Integer isTpl) {
            this.isTpl = isTpl;
        }
        public Integer getIsRefund() {
            return isRefund;
        }

        public void setIsRefund(Integer isRefund) {
            this.isRefund = isRefund;
        }
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", uid=").append(uid);
            sb.append(", assembleId=").append(assembleId);
            sb.append(", orderId=").append(orderId);
            sb.append(", totalNum=").append(totalNum);
            sb.append(", totalPrice=").append(totalPrice);
            sb.append(", cid=").append(cid);
            sb.append(", pid=").append(pid);
            sb.append(", people=").append(people);
            sb.append(", price=").append(price);
            sb.append(", addTime=").append(addTime);
            sb.append(", stopTime=").append(stopTime);
            sb.append(", isAsmer=").append(isAsmer);
            sb.append(", isTpl=").append(isTpl);
            sb.append(", isRefund=").append(isRefund);
            sb.append(", status=").append(status);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
