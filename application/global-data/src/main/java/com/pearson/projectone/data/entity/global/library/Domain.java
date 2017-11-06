package com.pearson.projectone.data.entity.global.library;

import com.pearson.projectone.core.support.data.RelationalEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by uphilji on 4/13/17.
 */
@Entity
public class Domain extends RelationalEntity {

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private String name;

    @Column
    private Long ordering;

    @Column
    private String type;


    /*
     Getters and Setters...
     */

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrdering() {
        return ordering;
    }

    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
