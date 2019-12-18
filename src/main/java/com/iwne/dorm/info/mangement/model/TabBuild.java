package com.iwne.dorm.info.mangement.model;

import java.io.Serializable;

/**
 * tab_build
 * @author 
 */
public class TabBuild implements Serializable {
    private Integer id;

    private String buildName;

    private String buildPosition;

    private Integer adminiId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildPosition() {
        return buildPosition;
    }

    public void setBuildPosition(String buildPosition) {
        this.buildPosition = buildPosition;
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
        TabBuild other = (TabBuild) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBuildName() == null ? other.getBuildName() == null : this.getBuildName().equals(other.getBuildName()))
            && (this.getBuildPosition() == null ? other.getBuildPosition() == null : this.getBuildPosition().equals(other.getBuildPosition()))
            && (this.getAdminiId() == null ? other.getAdminiId() == null : this.getAdminiId().equals(other.getAdminiId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBuildName() == null) ? 0 : getBuildName().hashCode());
        result = prime * result + ((getBuildPosition() == null) ? 0 : getBuildPosition().hashCode());
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
        sb.append(", buildName=").append(buildName);
        sb.append(", buildPosition=").append(buildPosition);
        sb.append(", adminiId=").append(adminiId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}