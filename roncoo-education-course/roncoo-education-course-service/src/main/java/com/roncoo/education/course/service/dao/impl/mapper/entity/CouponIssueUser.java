package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CouponIssueUser implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long uid;
        private Long issueCouponId;
        private LocalDateTime addTime;

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }
        public Long getIssueCouponId() {
            return issueCouponId;
        }

        public void setIssueCouponId(Long issueCouponId) {
            this.issueCouponId = issueCouponId;
        }
        public LocalDateTime getAddTime() {
            return addTime;
        }

        public void setAddTime(LocalDateTime addTime) {
            this.addTime = addTime;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", uid=").append(uid);
            sb.append(", issueCouponId=").append(issueCouponId);
            sb.append(", addTime=").append(addTime);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
