package com.glodon.seckilladmin.domain;

import java.io.Serializable;

/**
 * user_admin
 * @author 
 */
public class UserAdmin implements Serializable {
    private Integer rootId;

    private String rootName;

    private String rootPassword;

    private static final long serialVersionUID = 1L;

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getRootPassword() {
        return rootPassword;
    }

    public void setRootPassword(String rootPassword) {
        this.rootPassword = rootPassword;
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
        UserAdmin other = (UserAdmin) that;
        return (this.getRootId() == null ? other.getRootId() == null : this.getRootId().equals(other.getRootId()))
            && (this.getRootName() == null ? other.getRootName() == null : this.getRootName().equals(other.getRootName()))
            && (this.getRootPassword() == null ? other.getRootPassword() == null : this.getRootPassword().equals(other.getRootPassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRootId() == null) ? 0 : getRootId().hashCode());
        result = prime * result + ((getRootName() == null) ? 0 : getRootName().hashCode());
        result = prime * result + ((getRootPassword() == null) ? 0 : getRootPassword().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rootId=").append(rootId);
        sb.append(", rootName=").append(rootName);
        sb.append(", rootPassword=").append(rootPassword);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}