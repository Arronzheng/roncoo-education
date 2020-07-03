package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserLogCommission implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long userNo;
        private Long sourceUserNo;
        private Long orderNo;
        private BigDecimal commission;
        private LocalDateTime addTime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getUserNo() {
            return userNo;
        }

        public void setUserNo(Long userNo) {
            this.userNo = userNo;
        }
        public Long getSourceUserNo() {
            return sourceUserNo;
        }

        public void setSourceUserNo(Long sourceUserNo) {
            this.sourceUserNo = sourceUserNo;
        }
        public Long getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Long orderNo) {
            this.orderNo = orderNo;
        }
        public BigDecimal getCommission() {
            return commission;
        }

        public void setCommission(BigDecimal commission) {
            this.commission = commission;
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
            sb.append(", id=").append(id);
            sb.append(", userNo=").append(userNo);
            sb.append(", sourceUserNo=").append(sourceUserNo);
            sb.append(", orderNo=").append(orderNo);
            sb.append(", commission=").append(commission);
            sb.append(", addTime=").append(addTime);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
