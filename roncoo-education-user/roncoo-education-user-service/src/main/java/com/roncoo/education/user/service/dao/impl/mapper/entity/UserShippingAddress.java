package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class UserShippingAddress implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Date gmtCreate;
        private Date gmtModified;
        private Integer statusId;
        private Long userNo;
        private String receiverName;
        private String receiverPhone;
        private String receiverProvince;
        private String receiverCity;
        private String receiverDistrict;
        private String receiverAddress;
        private String receiverZip;
        private Integer isToleration;

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
        public Long getUserNo() {
            return userNo;
        }

        public void setUserNo(Long userNo) {
            this.userNo = userNo;
        }
        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }
        public String getReceiverPhone() {
            return receiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
        }
        public String getReceiverProvince() {
            return receiverProvince;
        }

        public void setReceiverProvince(String receiverProvince) {
            this.receiverProvince = receiverProvince;
        }
        public String getReceiverCity() {
            return receiverCity;
        }

        public void setReceiverCity(String receiverCity) {
            this.receiverCity = receiverCity;
        }
        public String getReceiverDistrict() {
            return receiverDistrict;
        }

        public void setReceiverDistrict(String receiverDistrict) {
            this.receiverDistrict = receiverDistrict;
        }
        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }
        public String getReceiverZip() {
            return receiverZip;
        }

        public void setReceiverZip(String receiverZip) {
            this.receiverZip = receiverZip;
        }
        public Integer getIsToleration() {
            return isToleration;
        }

        public void setIsToleration(Integer isToleration) {
            this.isToleration = isToleration;
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
            sb.append(", userNo=").append(userNo);
            sb.append(", receiverName=").append(receiverName);
            sb.append(", receiverPhone=").append(receiverPhone);
            sb.append(", receiverProvince=").append(receiverProvince);
            sb.append(", receiverCity=").append(receiverCity);
            sb.append(", receiverDistrict=").append(receiverDistrict);
            sb.append(", receiverAddress=").append(receiverAddress);
            sb.append(", receiverZip=").append(receiverZip);
            sb.append(", isToleration=").append(isToleration);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}