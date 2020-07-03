package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssembleCourse implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long productId;
        private String image;
        private String images;
        private String title;
        private String attr;
        private Integer people;
        private String info;
        private BigDecimal price;
        private Integer sort;
        private Integer sales;
        private Integer stock;
        private Date addTime;
        private Integer isHost;
        private Integer isShow;
        private Integer merUse;
        private Integer isPostage;
        private BigDecimal postage;
        private String description;
        private Date startTime;
        private Date stopTime;
        private Integer effectiveTime;
        private Integer cost;
        private Integer browse;
        private String unitName;

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
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }
        public Integer getPeople() {
            return people;
        }

        public void setPeople(Integer people) {
            this.people = people;
        }
        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }
        public Integer getSales() {
            return sales;
        }

        public void setSales(Integer sales) {
            this.sales = sales;
        }
        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }
        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }
        public Integer getIsHost() {
            return isHost;
        }

        public void setIsHost(Integer isHost) {
            this.isHost = isHost;
        }
        public Integer getIsShow() {
            return isShow;
        }

        public void setIsShow(Integer isShow) {
            this.isShow = isShow;
        }
        public Integer getMerUse() {
            return merUse;
        }

        public void setMerUse(Integer merUse) {
            this.merUse = merUse;
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
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
        public Integer getEffectiveTime() {
            return effectiveTime;
        }

        public void setEffectiveTime(Integer effectiveTime) {
            this.effectiveTime = effectiveTime;
        }
        public Integer getCost() {
            return cost;
        }

        public void setCost(Integer cost) {
            this.cost = cost;
        }
        public Integer getBrowse() {
            return browse;
        }

        public void setBrowse(Integer browse) {
            this.browse = browse;
        }
        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" [");
            sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", productId=").append(productId);
            sb.append(", image=").append(image);
            sb.append(", images=").append(images);
            sb.append(", title=").append(title);
            sb.append(", attr=").append(attr);
            sb.append(", people=").append(people);
            sb.append(", info=").append(info);
            sb.append(", price=").append(price);
            sb.append(", sort=").append(sort);
            sb.append(", sales=").append(sales);
            sb.append(", stock=").append(stock);
            sb.append(", addTime=").append(addTime);
            sb.append(", isHost=").append(isHost);
            sb.append(", isShow=").append(isShow);
            sb.append(", merUse=").append(merUse);
            sb.append(", isPostage=").append(isPostage);
            sb.append(", postage=").append(postage);
            sb.append(", description=").append(description);
            sb.append(", startTime=").append(startTime);
            sb.append(", stopTime=").append(stopTime);
            sb.append(", effectiveTime=").append(effectiveTime);
            sb.append(", cost=").append(cost);
            sb.append(", browse=").append(browse);
            sb.append(", unitName=").append(unitName);
            sb.append(", serialVersionUID=").append(serialVersionUID);
            sb.append("]");
            return sb.toString();
        }
}
