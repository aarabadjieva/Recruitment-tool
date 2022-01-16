package com.bootcamp.recruitmenttool.web.controllers;

import com.bootcamp.recruitmenttool.data.entities.Skill;
import com.bootcamp.recruitmenttool.service.SkillsService;
import com.bootcamp.recruitmenttool.web.models.SkillViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    private final SkillsService skillsService;
    private final ModelMapper mapper;

    public SkillsController(SkillsService skillsService, ModelMapper mapper) {
        this.skillsService = skillsService;
        this.mapper = mapper;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public SkillViewModel getSkillById(@PathVariable Long id){
        return mapper.map(skillsService.findSkillById(id), SkillViewModel.class);
    }

    @GetMapping("/active")
    public List<SkillViewModel> getActiveSkills (){
        return skillsService.findActiveSkills()
                .stream()
                .map(s->mapper.map(s, SkillViewModel.class))
                .collect(Collectors.toList());
    }
}
