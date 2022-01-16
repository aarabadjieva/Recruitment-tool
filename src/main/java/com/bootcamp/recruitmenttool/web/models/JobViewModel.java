package com.bootcamp.recruitmenttool.web.models;

import com.bootcamp.recruitmenttool.service.models.SkillServiceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class JobViewModel {

    private String title;
    private String description;
    private BigDecimal salary;
    @JsonIgnore
    private List<SkillViewModel> skills;
}
