package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class SvipBuyLog implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Date startTime;
        private Date dueTime;
        private Long userNo;
        private String nickname;
        private String mobile;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }
        public Date getDueTime() {
            return dueTime;
        }

        public void setDueTime(Date dueTime) {
            this.dueTime = dueTime;
        }
        public Long getUserNo() {
            return userNo;
        }

        public void setUserNo(Long userNo) {
            this.userNo = userNo;
        }
        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", startTime=").append(startTime);
            sb.append(", dueTime=").append(dueTime);
            sb.append(", userNo=").append(userNo);
            sb.append(", nickname=").append(nickname);
            sb.append(", mobile=").append(mobile);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}