package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityCourse implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Date gmtCreate;
        private Date gmtModified;
        private Integer statusId;
        private Integer sort;
        private Long activityId;
        private Integer activityLocation;
        private Long courseId;
        private Integer activityCategory;
        private BigDecimal coursePrice;
        private Integer groupNum;
        private Integer courseStock;
        private Integer knifeNum;
        private Integer limitTime;

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
        public Long getActivityId() {
            return activityId;
        }

        public void setActivityId(Long activityId) {
            this.activityId = activityId;
        }
        public Integer getActivityLocation() {
            return activityLocation;
        }

        public void setActivityLocation(Integer activityLocation) {
            this.activityLocation = activityLocation;
        }
        public Long getCourseId() {
            return courseId;
        }

        public void setCourseId(Long courseId) {
            this.courseId = courseId;
        }
        public Integer getActivityCategory() {
            return activityCategory;
        }

        public void setActivityCategory(Integer activityCategory) {
            this.activityCategory = activityCategory;
        }
        public BigDecimal getCoursePrice() {
            return coursePrice;
        }

        public void setCoursePrice(BigDecimal coursePrice) {
            this.coursePrice = coursePrice;
        }
        public Integer getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(Integer groupNum) {
            this.groupNum = groupNum;
        }
        public Integer getCourseStock() {
            return courseStock;
        }

        public void setCourseStock(Integer courseStock) {
            this.courseStock = courseStock;
        }
        public Integer getKnifeNum() {
            return knifeNum;
        }

        public void setKnifeNum(Integer knifeNum) {
            this.knifeNum = knifeNum;
        }
        public Integer getLimitTime() {
            return limitTime;
        }

        public void setLimitTime(Integer limitTime) {
            this.limitTime = limitTime;
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
            sb.append(", activityId=").append(activityId);
            sb.append(", activityLocation=").append(activityLocation);
            sb.append(", courseId=").append(courseId);
            sb.append(", activityCategory=").append(activityCategory);
            sb.append(", coursePrice=").append(coursePrice);
            sb.append(", groupNum=").append(groupNum);
            sb.append(", courseStock=").append(courseStock);
            sb.append(", knifeNum=").append(knifeNum);
            sb.append(", limitTime=").append(limitTime);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
