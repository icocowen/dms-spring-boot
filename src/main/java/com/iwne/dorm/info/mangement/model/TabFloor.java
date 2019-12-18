package com.iwne.dorm.info.mangement.model;

import java.io.Serializable;

/**
 * tab_floor
 * @author 
 */
public class TabFloor implements Serializable {
    private Integer id;

    private Integer roomNum;

    private Integer peopleNum;

    private Integer buildId;

    private String floorAlias;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public String getFloorAlias() {
        return floorAlias;
    }

    public void setFloorAlias(String floorAlias) {
        this.floorAlias = floorAlias;
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
        TabFloor other = (TabFloor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomNum() == null ? other.getRoomNum() == null : this.getRoomNum().equals(other.getRoomNum()))
            && (this.getPeopleNum() == null ? other.getPeopleNum() == null : this.getPeopleNum().equals(other.getPeopleNum()))
            && (this.getBuildId() == null ? other.getBuildId() == null : this.getBuildId().equals(other.getBuildId()))
            && (this.getFloorAlias() == null ? other.getFloorAlias() == null : this.getFloorAlias().equals(other.getFloorAlias()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomNum() == null) ? 0 : getRoomNum().hashCode());
        result = prime * result + ((getPeopleNum() == null) ? 0 : getPeopleNum().hashCode());
        result = prime * result + ((getBuildId() == null) ? 0 : getBuildId().hashCode());
        result = prime * result + ((getFloorAlias() == null) ? 0 : getFloorAlias().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomNum=").append(roomNum);
        sb.append(", peopleNum=").append(peopleNum);
        sb.append(", buildId=").append(buildId);
        sb.append(", floorAlias=").append(floorAlias);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}