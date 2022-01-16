package com.bootcamp.recruitmenttool.service.impl;

import com.bootcamp.recruitmenttool.data.entities.Skill;
import com.bootcamp.recruitmenttool.data.repositories.SkillsRepository;
import com.bootcamp.recruitmenttool.service.SkillsService;
import com.bootcamp.recruitmenttool.service.models.SkillServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {

    private final SkillsRepository skillsRepository;
    private final ModelMapper mapper;

    public SkillsServiceImpl(SkillsRepository skillsRepository, ModelMapper mapper) {
        this.skillsRepository = skillsRepository;
        this.mapper = mapper;
    }

    @Override
    public Skill createAndReturnSkill(SkillServiceModel model) {
        if (!skillsRepository.existsByName(model.getName())) {
            Skill skill = new Skill(model.getName());
            skillsRepository.saveAndFlush(skill);
        }
        return skillsRepository.findByName(model.getName()).orElse(null);
    }

    @Override
    public SkillServiceModel findSkillById(Long id) {
        return mapper.map(skillsRepository.getById(id), SkillServiceModel.class);
    }

    @Override
    public List<SkillServiceModel> findActiveSkills() {
        return skillsRepository.findAll()
                .stream()
                .filter((Skill s) -> s.getCandidates().size() > 0)
                .map(s -> mapper.map(s, SkillServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Skill> findSkillByName(String name) {
        return skillsRepository.findByName(name);
    }
}