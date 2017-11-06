package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by uphilji on 4/18/17.
 */
@Entity
public class FormOSAMap extends RelationalEntity {


    private String formId;

    @Lob
    private String osaFormMap;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getOsaFormMap() {
        return osaFormMap;
    }

    public void setOsaFormMap(String osaFormMap) {
        this.osaFormMap = osaFormMap;
    }
}
