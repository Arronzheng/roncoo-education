package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CouponIssue implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long cid;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer totalCount;
        private Integer remainCount;
        private Integer isPermanent;
        private Integer status;
        private Integer isDel;
        private LocalDateTime addTime;

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
        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }
        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }
        public Integer getRemainCount() {
            return remainCount;
        }

        public void setRemainCount(Integer remainCount) {
            this.remainCount = remainCount;
        }
        public Integer getIsPermanent() {
            return isPermanent;
        }

        public void setIsPermanent(Integer isPermanent) {
            this.isPermanent = isPermanent;
        }
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
        public Integer getIsDel() {
            return isDel;
        }

        public void setIsDel(Integer isDel) {
            this.isDel = isDel;
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
            sb.append(", cid=").append(cid);
            sb.append(", startTime=").append(startTime);
            sb.append(", endTime=").append(endTime);
            sb.append(", totalCount=").append(totalCount);
            sb.append(", remainCount=").append(remainCount);
            sb.append(", isPermanent=").append(isPermanent);
            sb.append(", status=").append(status);
            sb.append(", isDel=").append(isDel);
            sb.append(", addTime=").append(addTime);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
