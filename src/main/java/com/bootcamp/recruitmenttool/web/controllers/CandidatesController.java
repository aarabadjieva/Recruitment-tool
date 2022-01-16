package com.bootcamp.recruitmenttool.web.controllers;

import com.bootcamp.recruitmenttool.service.CandidatesService;
import com.bootcamp.recruitmenttool.service.models.CandidateCreateServiceModel;
import com.bootcamp.recruitmenttool.web.models.CandidateViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates")
public class CandidatesController {

    private final CandidatesService candidatesService;
    private final ModelMapper mapper;

    public CandidatesController(CandidatesService candidatesService, ModelMapper mapper) {
        this.candidatesService = candidatesService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CandidateViewModel> list() {
        return candidatesService.allCandidates().stream()
                .map(c -> mapper.map(c, CandidateViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("/{id}")
    public CandidateViewModel get(@PathVariable Long id) {
        return mapper.map(candidatesService.getById(id), CandidateViewModel.class);
    }

    @PostMapping
    public CandidateViewModel create(@RequestBody final CandidateCreateServiceModel model) throws Exception {
        return mapper.map(candidatesService.createCandidate(model), CandidateViewModel.class);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        candidatesService.deleteCandidate(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody CandidateCreateServiceModel model) {
        candidatesService.updateCandidate(model, id);
    }

}