package com.iwne.dorm.info.mangement.model;

import java.io.Serializable;

/**
 * tab_room
 * @author 
 */
public class TabRoom implements Serializable {
    private Integer id;

    private String roomAlias;

    private Integer floorId;

    private Integer roomLong;

    private Integer peopleNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomAlias() {
        return roomAlias;
    }

    public void setRoomAlias(String roomAlias) {
        this.roomAlias = roomAlias;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getRoomLong() {
        return roomLong;
    }

    public void setRoomLong(Integer roomLong) {
        this.roomLong = roomLong;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
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
        TabRoom other = (TabRoom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomAlias() == null ? other.getRoomAlias() == null : this.getRoomAlias().equals(other.getRoomAlias()))
            && (this.getFloorId() == null ? other.getFloorId() == null : this.getFloorId().equals(other.getFloorId()))
            && (this.getRoomLong() == null ? other.getRoomLong() == null : this.getRoomLong().equals(other.getRoomLong()))
            && (this.getPeopleNum() == null ? other.getPeopleNum() == null : this.getPeopleNum().equals(other.getPeopleNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomAlias() == null) ? 0 : getRoomAlias().hashCode());
        result = prime * result + ((getFloorId() == null) ? 0 : getFloorId().hashCode());
        result = prime * result + ((getRoomLong() == null) ? 0 : getRoomLong().hashCode());
        result = prime * result + ((getPeopleNum() == null) ? 0 : getPeopleNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomAlias=").append(roomAlias);
        sb.append(", floorId=").append(floorId);
        sb.append(", roomLong=").append(roomLong);
        sb.append(", peopleNum=").append(peopleNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}