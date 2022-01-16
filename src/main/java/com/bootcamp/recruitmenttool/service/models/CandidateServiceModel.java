package com.bootcamp.recruitmenttool.service.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CandidateServiceModel {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String birthDate;
    private List<SkillServiceModel> skills;
    private RecruiterCreateServiceModel recruiter;
}
