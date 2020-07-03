package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BargainCourse implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long productId;
        private String title;
        private String image;
        private String unitName;
        private Integer stock;
        private Integer sales;
        private String images;
        private Date startTime;
        private Date stopTime;
        private String storeName;
        private BigDecimal price;
        private BigDecimal minPrice;
        private Integer num;
        private BigDecimal bargainMaxPrice;
        private BigDecimal bargainMinPrice;
        private Integer bargainNum;
        private Integer status;
        private String description;
        private BigDecimal giveIntegral;
        private String info;
        private BigDecimal cost;
        private Integer sort;
        private Integer isHot;
        private Date addTime;
        private Integer isPostage;
        private BigDecimal postage;
        private String rule;
        private Integer look;
        private Integer share;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }
        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }
        public Integer getSales() {
            return sales;
        }

        public void setSales(Integer sales) {
            this.sales = sales;
        }
        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }
        public Date getStopTime() {
            return stopTime;
        }

        public void setStopTime(Date stopTime) {
            this.stopTime = stopTime;
        }
        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }
        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
        public BigDecimal getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
        }
        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
        public BigDecimal getBargainMaxPrice() {
            return bargainMaxPrice;
        }

        public void setBargainMaxPrice(BigDecimal bargainMaxPrice) {
            this.bargainMaxPrice = bargainMaxPrice;
        }
        public BigDecimal getBargainMinPrice() {
            return bargainMinPrice;
        }

        public void setBargainMinPrice(BigDecimal bargainMinPrice) {
            this.bargainMinPrice = bargainMinPrice;
        }
        public Integer getBargainNum() {
            return bargainNum;
        }

        public void setBargainNum(Integer bargainNum) {
            this.bargainNum = bargainNum;
        }
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public BigDecimal getGiveIntegral() {
            return giveIntegral;
        }

        public void setGiveIntegral(BigDecimal giveIntegral) {
            this.giveIntegral = giveIntegral;
        }
        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
        public BigDecimal getCost() {
            return cost;
        }

        public void setCost(BigDecimal cost) {
            this.cost = cost;
        }
        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }
        public Integer getIsHot() {
            return isHot;
        }

        public void setIsHot(Integer isHot) {
            this.isHot = isHot;
        }
        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }
        public Integer getIsPostage() {
            return isPostage;
        }

        public void setIsPostage(Integer isPostage) {
            this.isPostage = isPostage;
        }
        public BigDecimal getPostage() {
            return postage;
        }

        public void setPostage(BigDecimal postage) {
            this.postage = postage;
        }
        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }
        public Integer getLook() {
            return look;
        }

        public void setLook(Integer look) {
            this.look = look;
        }
        public Integer getShare() {
            return share;
        }

        public void setShare(Integer share) {
            this.share = share;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", productId=").append(productId);
            sb.append(", title=").append(title);
            sb.append(", image=").append(image);
            sb.append(", unitName=").append(unitName);
            sb.append(", stock=").append(stock);
            sb.append(", sales=").append(sales);
            sb.append(", images=").append(images);
            sb.append(", startTime=").append(startTime);
            sb.append(", stopTime=").append(stopTime);
            sb.append(", storeName=").append(storeName);
            sb.append(", price=").append(price);
            sb.append(", minPrice=").append(minPrice);
            sb.append(", num=").append(num);
            sb.append(", bargainMaxPrice=").append(bargainMaxPrice);
            sb.append(", bargainMinPrice=").append(bargainMinPrice);
            sb.append(", bargainNum=").append(bargainNum);
            sb.append(", status=").append(status);
            sb.append(", description=").append(description);
            sb.append(", giveIntegral=").append(giveIntegral);
            sb.append(", info=").append(info);
            sb.append(", cost=").append(cost);
            sb.append(", sort=").append(sort);
            sb.append(", isHot=").append(isHot);
            sb.append(", addTime=").append(addTime);
            sb.append(", isPostage=").append(isPostage);
            sb.append(", postage=").append(postage);
            sb.append(", rule=").append(rule);
            sb.append(", look=").append(look);
            sb.append(", share=").append(share);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
