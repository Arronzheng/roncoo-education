package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserLogInvite implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long inviteUserNo;
        private Long invitedUserNo;
        private Integer status;
        private LocalDateTime addTime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getInviteUserNo() {
            return inviteUserNo;
        }

        public void setInviteUserNo(Long inviteUserNo) {
            this.inviteUserNo = inviteUserNo;
        }
        public Long getInvitedUserNo() {
            return invitedUserNo;
        }

        public void setInvitedUserNo(Long invitedUserNo) {
            this.invitedUserNo = invitedUserNo;
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", inviteUserNo=").append(inviteUserNo);
            sb.append(", invitedUserNo=").append(invitedUserNo);
            sb.append(", status=").append(status);
            sb.append(", addTime=").append(addTime);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
