package com.bootcamp.recruitmenttool.service;


import com.bootcamp.recruitmenttool.data.entities.Candidate;
import com.bootcamp.recruitmenttool.data.entities.Skill;
import com.bootcamp.recruitmenttool.service.models.SkillServiceModel;

import java.util.List;
import java.util.Optional;

public interface SkillsService {

    Skill createAndReturnSkill(SkillServiceModel name);
    SkillServiceModel findSkillById(Long id);
    List<SkillServiceModel> findActiveSkills();
    Optional<Skill> findSkillByName(String name);
}
