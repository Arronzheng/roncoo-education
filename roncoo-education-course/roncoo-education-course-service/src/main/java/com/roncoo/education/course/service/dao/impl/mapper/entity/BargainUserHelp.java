package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BargainUserHelp implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long uid;
        private Long bargainId;
        private Long bargainUserId;
        private BigDecimal price;
        private Date addTime;

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
        public Long getBargainUserId() {
            return bargainUserId;
        }

        public void setBargainUserId(Long bargainUserId) {
            this.bargainUserId = bargainUserId;
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", uid=").append(uid);
            sb.append(", bargainId=").append(bargainId);
            sb.append(", bargainUserId=").append(bargainUserId);
            sb.append(", price=").append(price);
            sb.append(", addTime=").append(addTime);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
