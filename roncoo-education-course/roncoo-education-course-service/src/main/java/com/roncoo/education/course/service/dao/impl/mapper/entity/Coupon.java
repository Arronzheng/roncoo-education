package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Coupon implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private String title;
        private Integer integral;
        private BigDecimal couponPrice;
        private BigDecimal useMinPrice;
        private Integer couponTime;
        private Integer sort;
        private Integer status;
        private LocalDateTime addTime;
        private Integer isDel;
        private Integer isConvert;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public Integer getIntegral() {
            return integral;
        }

        public void setIntegral(Integer integral) {
            this.integral = integral;
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
        public Integer getCouponTime() {
            return couponTime;
        }

        public void setCouponTime(Integer couponTime) {
            this.couponTime = couponTime;
        }
        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
        public LocalDateTime getAddTime() {
            return addTime;
        }

        public void setAddTime(LocalDateTime addTime) {
            this.addTime = addTime;
        }
        public Integer getIsDel() {
            return isDel;
        }

        public void setIsDel(Integer isDel) {
            this.isDel = isDel;
        }
        public Integer getIsConvert() {
            return isConvert;
        }

        public void setIsConvert(Integer isConvert) {
            this.isConvert = isConvert;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", title=").append(title);
            sb.append(", integral=").append(integral);
            sb.append(", couponPrice=").append(couponPrice);
            sb.append(", useMinPrice=").append(useMinPrice);
            sb.append(", couponTime=").append(couponTime);
            sb.append(", sort=").append(sort);
            sb.append(", status=").append(status);
            sb.append(", addTime=").append(addTime);
            sb.append(", isDel=").append(isDel);
            sb.append(", isConvert=").append(isConvert);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
