package com.bootcamp.recruitmenttool.web.models;

import com.bootcamp.recruitmenttool.service.models.RecruiterCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.SkillServiceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CandidateViewModel {

    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String birthDate;
    @JsonIgnore
    private List<SkillViewModel> skills;
    @JsonIgnore
    private RecruiterCreateServiceModel recruiter;
}
