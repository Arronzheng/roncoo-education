package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Poster implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private LocalDateTime gmtCreate;
        private LocalDateTime gmtModified;
        private Integer statusId;
        private String poster;
        private Integer posterType;
        private String remark;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public LocalDateTime getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(LocalDateTime gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
        public LocalDateTime getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(LocalDateTime gmtModified) {
            this.gmtModified = gmtModified;
        }
        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }
        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }
        public Integer getPosterType() {
            return posterType;
        }

        public void setPosterType(Integer posterType) {
            this.posterType = posterType;
        }
        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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
            sb.append(", statusId=").append(statusId);
            sb.append(", poster=").append(poster);
            sb.append(", posterType=").append(posterType);
            sb.append(", remark=").append(remark);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
