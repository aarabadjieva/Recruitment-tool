package com.bootcamp.recruitmenttool.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecruiterViewModel {

    private String lastName;
    private String email;
    private String country;
    private int availableSlots;
    private int level;
    @JsonIgnore
    List<CandidateViewModel> candidates;

}
