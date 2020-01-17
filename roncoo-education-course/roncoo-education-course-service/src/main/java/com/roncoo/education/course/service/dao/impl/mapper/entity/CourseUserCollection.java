package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class CourseUserCollection implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long userNo;
        private Long courseId;
        private String courseName;
        private String courseLogo;
        private Date gmtCreate;

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
        public Long getCourseId() {
            return courseId;
        }

        public void setCourseId(Long courseId) {
            this.courseId = courseId;
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
        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", userNo=").append(userNo);
            sb.append(", courseId=").append(courseId);
            sb.append(", courseName=").append(courseName);
            sb.append(", courseLogo=").append(courseLogo);
            sb.append(", gmtCreate=").append(gmtCreate);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}