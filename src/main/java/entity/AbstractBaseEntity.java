package entity;


import annotation.Column;

import java.util.Date;

public class AbstractBaseEntity {

    @Column(name = "id")
    private Long id;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name= "modifiedby")
    private String modifiedBy;

    @Column(name = "createddate")
    private Date createdDate;

    @Column(name= "modifieddate")
    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
