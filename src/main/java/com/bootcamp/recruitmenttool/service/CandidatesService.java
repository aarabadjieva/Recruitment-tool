package com.bootcamp.recruitmenttool.service;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import com.bootcamp.recruitmenttool.service.models.CandidateCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.CandidateServiceModel;

import java.util.List;

public interface CandidatesService {

    List<CandidateServiceModel> allCandidates();

    CandidateServiceModel createCandidate(CandidateCreateServiceModel candidate) throws Exception;

    void updateCandidate(CandidateCreateServiceModel candidate, Long id);

    void deleteCandidate(Long id);

    CandidateServiceModel getById(Long id);
}
