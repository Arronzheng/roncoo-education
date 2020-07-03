package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CouponUser implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long cid;
        private Long uid;
        private String couponTitle;
        private BigDecimal couponPrice;
        private BigDecimal useMinPrice;
        private LocalDateTime addTime;
        private LocalDateTime endTime;
        private LocalDateTime useTime;
        private String type;
        private Integer status;
        private Integer isFail;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getCid() {
            return cid;
        }

        public void setCid(Long cid) {
            this.cid = cid;
        }
        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }
        public String getCouponTitle() {
            return couponTitle;
        }

        public void setCouponTitle(String couponTitle) {
            this.couponTitle = couponTitle;
        }
        public BigDecimal getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(BigDecimal couponPrice) {
            this.couponPrice = couponPrice;
        }
        public BigDecimal getUseMinPrice() {
            return useMinPrice;
        }

        public void setUseMinPrice(BigDecimal useMinPrice) {
            this.useMinPrice = useMinPrice;
        }
        public LocalDateTime getAddTime() {
            return addTime;
        }

        public void setAddTime(LocalDateTime addTime) {
            this.addTime = addTime;
        }
        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
        public LocalDateTime getUseTime() {
            return useTime;
        }

        public void setUseTime(LocalDateTime useTime) {
            this.useTime = useTime;
        }
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
        public Integer getIsFail() {
            return isFail;
        }

        public void setIsFail(Integer isFail) {
            this.isFail = isFail;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", cid=").append(cid);
            sb.append(", uid=").append(uid);
            sb.append(", couponTitle=").append(couponTitle);
            sb.append(", couponPrice=").append(couponPrice);
            sb.append(", useMinPrice=").append(useMinPrice);
            sb.append(", addTime=").append(addTime);
            sb.append(", endTime=").append(endTime);
            sb.append(", useTime=").append(useTime);
            sb.append(", type=").append(type);
            sb.append(", status=").append(status);
            sb.append(", isFail=").append(isFail);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
