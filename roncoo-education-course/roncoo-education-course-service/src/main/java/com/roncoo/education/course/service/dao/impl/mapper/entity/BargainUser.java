package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BargainUser implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long uid;
        private Long bargainId;
        private BigDecimal bargainPriceMin;
        private BigDecimal bargainPrice;
        private BigDecimal price;
        private Integer status;
        private Date addTime;
        private Integer isDel;

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
        public Long getBargainId() {
            return bargainId;
        }

        public void setBargainId(Long bargainId) {
            this.bargainId = bargainId;
        }
        public BigDecimal getBargainPriceMin() {
            return bargainPriceMin;
        }

        public void setBargainPriceMin(BigDecimal bargainPriceMin) {
            this.bargainPriceMin = bargainPriceMin;
        }
        public BigDecimal getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(BigDecimal bargainPrice) {
            this.bargainPrice = bargainPrice;
        }
        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }
        public Integer getIsDel() {
            return isDel;
        }

        public void setIsDel(Integer isDel) {
            this.isDel = isDel;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", uid=").append(uid);
            sb.append(", bargainId=").append(bargainId);
            sb.append(", bargainPriceMin=").append(bargainPriceMin);
            sb.append(", bargainPrice=").append(bargainPrice);
            sb.append(", price=").append(price);
            sb.append(", status=").append(status);
            sb.append(", addTime=").append(addTime);
            sb.append(", isDel=").append(isDel);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
