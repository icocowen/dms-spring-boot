package com.iwne.dorm.info.mangement.model;

import java.io.Serializable;

/**
 * tab_student
 * @author 
 */
public class TabStudent implements Serializable {
    private Integer id;

    private String stuNum;

    private String roomNo;

    private String name;

    private Boolean gender;

    private String phoneNum;

    private String stuClass;

    private String stuCoachNo;

    private String badNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuCoachNo() {
        return stuCoachNo;
    }

    public void setStuCoachNo(String stuCoachNo) {
        this.stuCoachNo = stuCoachNo;
    }

    public String getBadNum() {
        return badNum;
    }

    public void setBadNum(String badNum) {
        this.badNum = badNum;
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
        TabStudent other = (TabStudent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStuNum() == null ? other.getStuNum() == null : this.getStuNum().equals(other.getStuNum()))
            && (this.getRoomNo() == null ? other.getRoomNo() == null : this.getRoomNo().equals(other.getRoomNo()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getPhoneNum() == null ? other.getPhoneNum() == null : this.getPhoneNum().equals(other.getPhoneNum()))
            && (this.getStuClass() == null ? other.getStuClass() == null : this.getStuClass().equals(other.getStuClass()))
            && (this.getStuCoachNo() == null ? other.getStuCoachNo() == null : this.getStuCoachNo().equals(other.getStuCoachNo()))
            && (this.getBadNum() == null ? other.getBadNum() == null : this.getBadNum().equals(other.getBadNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStuNum() == null) ? 0 : getStuNum().hashCode());
        result = prime * result + ((getRoomNo() == null) ? 0 : getRoomNo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getPhoneNum() == null) ? 0 : getPhoneNum().hashCode());
        result = prime * result + ((getStuClass() == null) ? 0 : getStuClass().hashCode());
        result = prime * result + ((getStuCoachNo() == null) ? 0 : getStuCoachNo().hashCode());
        result = prime * result + ((getBadNum() == null) ? 0 : getBadNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stuNum=").append(stuNum);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", stuClass=").append(stuClass);
        sb.append(", stuCoachNo=").append(stuCoachNo);
        sb.append(", badNum=").append(badNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}