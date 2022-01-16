package com.bootcamp.recruitmenttool.service.models;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import com.bootcamp.recruitmenttool.data.entities.Interview;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
public class RecruiterCreateServiceModel {

    private String lastName;
    private String email;
    private String country;
    private int availableSlots;
    private int level;

    public RecruiterCreateServiceModel() {
        availableSlots = 5;
        level = 1;
    }
}

