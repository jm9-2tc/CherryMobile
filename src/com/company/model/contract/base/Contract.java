package com.company.model.contract.base;

import java.util.Date;

public class Contract {
    private Integer id;
    private Date creationDate;
    private Date endingDate;

    public Contract(Date creationDate, Date endingDate) {
        this.creationDate = creationDate;
        this.endingDate = endingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }
}
