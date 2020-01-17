package com.roncoo.education.user.service.dao.impl.mapper.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Svip implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date gmtCreate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date dueTime;
        private Integer statusId;
        private Long userNo;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
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

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public Long getUserNo() {
            return userNo;
        }

        public void setUserNo(Long userNo) {
            this.userNo = userNo;
        }

}