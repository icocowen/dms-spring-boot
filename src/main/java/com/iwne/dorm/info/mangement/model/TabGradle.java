package com.iwne.dorm.info.mangement.model;

import java.io.Serializable;

/**
 * tab_gradle
 * @author 
 */
public class TabGradle implements Serializable {
    private Integer id;

    private Integer roomId;

    private String sanitation;

    private String bed;

    private String lavatory;

    private String desktop;

    private Integer adminiId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getSanitation() {
        return sanitation;
    }

    public void setSanitation(String sanitation) {
        this.sanitation = sanitation;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getLavatory() {
        return lavatory;
    }

    public void setLavatory(String lavatory) {
        this.lavatory = lavatory;
    }

    public String getDesktop() {
        return desktop;
    }

    public void setDesktop(String desktop) {
        this.desktop = desktop;
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
        TabGradle other = (TabGradle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
            && (this.getSanitation() == null ? other.getSanitation() == null : this.getSanitation().equals(other.getSanitation()))
            && (this.getBed() == null ? other.getBed() == null : this.getBed().equals(other.getBed()))
            && (this.getLavatory() == null ? other.getLavatory() == null : this.getLavatory().equals(other.getLavatory()))
            && (this.getDesktop() == null ? other.getDesktop() == null : this.getDesktop().equals(other.getDesktop()))
            && (this.getAdminiId() == null ? other.getAdminiId() == null : this.getAdminiId().equals(other.getAdminiId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getSanitation() == null) ? 0 : getSanitation().hashCode());
        result = prime * result + ((getBed() == null) ? 0 : getBed().hashCode());
        result = prime * result + ((getLavatory() == null) ? 0 : getLavatory().hashCode());
        result = prime * result + ((getDesktop() == null) ? 0 : getDesktop().hashCode());
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
        sb.append(", roomId=").append(roomId);
        sb.append(", sanitation=").append(sanitation);
        sb.append(", bed=").append(bed);
        sb.append(", lavatory=").append(lavatory);
        sb.append(", desktop=").append(desktop);
        sb.append(", adminiId=").append(adminiId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}