package com.bootcamp.recruitmenttool.service.models;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SkillServiceModel {

    private String name;
    private List<CandidateServiceModel> candidates;

}
