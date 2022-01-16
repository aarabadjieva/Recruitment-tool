package com.bootcamp.recruitmenttool.service.models;

import com.bootcamp.recruitmenttool.web.models.CandidateViewModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecruiterServiceModel {

    private String lastName;
    private String email;
    private String country;
    private int availableSlots;
    private int level;
    List<CandidateServiceModel> candidates;

}
