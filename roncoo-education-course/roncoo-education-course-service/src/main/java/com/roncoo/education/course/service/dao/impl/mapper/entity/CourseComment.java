package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class CourseComment implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Date createdAt;
        private Date updatedAt;
        private Integer star;
        private Integer topping;
        private Long userId;
        private String content;
        private Integer likeNumber;
        private Long pid;
        private Long parentNo;
        private Long courseId;
        private Long chapterId;
        private Long periodId;
        private Integer commentType;
        private Integer status;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getStar() {
            return star;
        }

        public void setStar(Integer star) {
            this.star = star;
        }

        public Integer getTopping() {
            return topping;
        }

        public void setTopping(Integer topping) {
            this.topping = topping;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getLikeNumber() {
            return likeNumber;
        }

        public void setLikeNumber(Integer likeNumber) {
            this.likeNumber = likeNumber;
        }

        public Long getPid() {
            return pid;
        }

        public void setPid(Long pid) {
            this.pid = pid;
        }

        public Long getParentNo() {
            return parentNo;
        }

        public void setParentNo(Long parentNo) {
            this.parentNo = parentNo;
        }

        public Long getCourseId() {
            return courseId;
        }

        public void setCourseId(Long courseId) {
            this.courseId = courseId;
        }

        public Long getChapterId() {
            return chapterId;
        }

        public void setChapterId(Long chapterId) {
            this.chapterId = chapterId;
        }

        public Long getPeriodId() {
            return periodId;
        }

        public void setPeriodId(Long periodId) {
            this.periodId = periodId;
        }

        public Integer getCommentType() {
            return commentType;
        }

        public void setCommentType(Integer commentType) {
            this.commentType = commentType;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

}