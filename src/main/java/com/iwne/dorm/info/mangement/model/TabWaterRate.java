package com.iwne.dorm.info.mangement.model;

import java.io.Serializable;

/**
 * tab_water_rate
 * @author 
 */
public class TabWaterRate implements Serializable {
    private Integer id;

    private String inDate;

    private Integer waterRate;

    private Integer adminiId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public Integer getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Integer waterRate) {
        this.waterRate = waterRate;
    }

    public Integer getAdminiId() {
        return adminiId;
    }

    public void setAdminiId(Integer adminiId) {
        this.adminiId = adminiId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TabWaterRate other = (TabWaterRate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInDate() == null ? other.getInDate() == null : this.getInDate().equals(other.getInDate()))
            && (this.getWaterRate() == null ? other.getWaterRate() == null : this.getWaterRate().equals(other.getWaterRate()))
            && (this.getAdminiId() == null ? other.getAdminiId() == null : this.getAdminiId().equals(other.getAdminiId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInDate() == null) ? 0 : getInDate().hashCode());
        result = prime * result + ((getWaterRate() == null) ? 0 : getWaterRate().hashCode());
        result = prime * result + ((getAdminiId() == null) ? 0 : getAdminiId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", inDate=").append(inDate);
        sb.append(", waterRate=").append(waterRate);
        sb.append(", adminiId=").append(adminiId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}