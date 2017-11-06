package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by uphilji on 4/18/17.
 */
@Entity
public class Scoring extends RelationalEntity {

    /** The name. */
    @Column
    private String name;

    /** The acronym. */
    @Column
    private String acronym;

    /** The scoring engine id. */
    @Column
    private String scoringEngineId;

    /** The scoring type id. */
    @Column
    private String scoringTypeId;

    /** The scoring definition. */
    @Column
    private String scoringDefinition;

    /** The description. */
    @Column
    private String description;

    /** The status. */
    @Column
    private String statusId;

    /*
     *   Getters and Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getScoringEngineId() {
        return scoringEngineId;
    }

    public void setScoringEngineId(String scoringEngineId) {
        this.scoringEngineId = scoringEngineId;
    }

    public String getScoringTypeId() {
        return scoringTypeId;
    }

    public void setScoringTypeId(String scoringTypeId) {
        this.scoringTypeId = scoringTypeId;
    }

    public String getScoringDefinition() {
        return scoringDefinition;
    }

    public void setScoringDefinition(String scoringDefinition) {
        this.scoringDefinition = scoringDefinition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
