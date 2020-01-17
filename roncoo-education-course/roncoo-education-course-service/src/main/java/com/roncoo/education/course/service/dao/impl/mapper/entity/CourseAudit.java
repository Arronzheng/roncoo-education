package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CourseAudit implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Date gmtCreate;
        private Date gmtModified;
        private Integer statusId;
        private Integer sort;
        private Integer auditStatus;
        private String auditOpinion;
        private Long lecturerUserNo;
        private Long categoryId1;
        private Long categoryId2;
        private Long categoryId3;
        private String courseName;
        private String courseLogo;
        private Long introduceId;
        private Integer isFree;
        private Integer isVipFree;
        private Integer isOnlyVipBuy;
        private BigDecimal courseOriginal;
        private BigDecimal courseDiscount;
        private BigDecimal courseSvipDiscount;
        private BigDecimal courseAssembleDiscount;
        private Integer isPutaway;
        private Integer courseSort;
        private Integer countBuy;
        private Integer countStudy;
        private Integer periodTotal;

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

        public Date getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Date gmtModified) {
            this.gmtModified = gmtModified;
        }

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public Integer getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(Integer auditStatus) {
            this.auditStatus = auditStatus;
        }

        public String getAuditOpinion() {
            return auditOpinion;
        }

        public void setAuditOpinion(String auditOpinion) {
            this.auditOpinion = auditOpinion;
        }

        public Long getLecturerUserNo() {
            return lecturerUserNo;
        }

        public void setLecturerUserNo(Long lecturerUserNo) {
            this.lecturerUserNo = lecturerUserNo;
        }

        public Long getCategoryId1() {
            return categoryId1;
        }

        public void setCategoryId1(Long categoryId1) {
            this.categoryId1 = categoryId1;
        }

        public Long getCategoryId2() {
            return categoryId2;
        }

        public void setCategoryId2(Long categoryId2) {
            this.categoryId2 = categoryId2;
        }

        public Long getCategoryId3() {
            return categoryId3;
        }

        public void setCategoryId3(Long categoryId3) {
            this.categoryId3 = categoryId3;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseLogo() {
            return courseLogo;
        }

        public void setCourseLogo(String courseLogo) {
            this.courseLogo = courseLogo;
        }

        public Long getIntroduceId() {
            return introduceId;
        }

        public void setIntroduceId(Long introduceId) {
            this.introduceId = introduceId;
        }

        public Integer getIsFree() {
            return isFree;
        }

        public void setIsFree(Integer isFree) {
            this.isFree = isFree;
        }

        public Integer getIsVipFree() {
            return isVipFree;
        }

        public void setIsVipFree(Integer isVipFree) {
            this.isVipFree = isVipFree;
        }

        public Integer getIsOnlyVipBuy() {
            return isOnlyVipBuy;
        }

        public void setIsOnlyVipBuy(Integer isOnlyVipBuy) {
            this.isOnlyVipBuy = isOnlyVipBuy;
        }

        public BigDecimal getCourseOriginal() {
            return courseOriginal;
        }

        public void setCourseOriginal(BigDecimal courseOriginal) {
            this.courseOriginal = courseOriginal;
        }

        public BigDecimal getCourseDiscount() {
            return courseDiscount;
        }

        public void setCourseDiscount(BigDecimal courseDiscount) {
            this.courseDiscount = courseDiscount;
        }

        public BigDecimal getCourseSvipDiscount() {
            return courseSvipDiscount;
        }

        public void setCourseSvipDiscount(BigDecimal courseSvipDiscount) {
            this.courseSvipDiscount = courseSvipDiscount;
        }

        public BigDecimal getCourseAssembleDiscount() {
            return courseAssembleDiscount;
        }

        public void setCourseAssembleDiscount(BigDecimal courseAssembleDiscount) {
            this.courseAssembleDiscount = courseAssembleDiscount;
        }

        public Integer getIsPutaway() {
            return isPutaway;
        }

        public void setIsPutaway(Integer isPutaway) {
            this.isPutaway = isPutaway;
        }

        public Integer getCourseSort() {
            return courseSort;
        }

        public void setCourseSort(Integer courseSort) {
            this.courseSort = courseSort;
        }

        public Integer getCountBuy() {
            return countBuy;
        }

        public void setCountBuy(Integer countBuy) {
            this.countBuy = countBuy;
        }

        public Integer getCountStudy() {
            return countStudy;
        }

        public void setCountStudy(Integer countStudy) {
            this.countStudy = countStudy;
        }

        public Integer getPeriodTotal() {
            return periodTotal;
        }

        public void setPeriodTotal(Integer periodTotal) {
            this.periodTotal = periodTotal;
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
            sb.append(", sort=").append(sort);
            sb.append(", auditStatus=").append(auditStatus);
            sb.append(", auditOpinion=").append(auditOpinion);
            sb.append(", lecturerUserNo=").append(lecturerUserNo);
            sb.append(", categoryId1=").append(categoryId1);
            sb.append(", categoryId2=").append(categoryId2);
            sb.append(", categoryId3=").append(categoryId3);
            sb.append(", courseName=").append(courseName);
            sb.append(", courseLogo=").append(courseLogo);
            sb.append(", introduceId=").append(introduceId);
            sb.append(", isFree=").append(isFree);
            sb.append(", isVipFree=").append(isVipFree);
            sb.append(", isOnlyVipBuy=").append(isOnlyVipBuy);
            sb.append(", courseOriginal=").append(courseOriginal);
            sb.append(", courseDiscount=").append(courseDiscount);
            sb.append(", courseSvipDiscount=").append(courseSvipDiscount);
            sb.append(", courseAssembleDiscount=").append(courseAssembleDiscount);
            sb.append(", isPutaway=").append(isPutaway);
            sb.append(", courseSort=").append(courseSort);
            sb.append(", countBuy=").append(countBuy);
            sb.append(", countStudy=").append(countStudy);
            sb.append(", periodTotal=").append(periodTotal);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }

        public void transferSet(int i,Long l){
            if(i == 0){
                this.setCategoryId1(l);
            }else if(i == 1){
                this.setCategoryId2(l);
            }else{
                this.setCategoryId3(l);
            }
        }

}