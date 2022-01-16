package com.bootcamp.recruitmenttool.service.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class JobCreateServiceModel {

    private String title;
    private String description;
    private BigDecimal salary;
    private List<SkillServiceModel> skills;

}
