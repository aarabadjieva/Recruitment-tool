package com.bootcamp.recruitmenttool.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job extends BaseEntity{

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private BigDecimal salary;

    @ManyToMany
    @JoinTable(
            name = "jobs_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Interview> interviews;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "jobs_candidates",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private List<Candidate> candidates;

    public Job() {
        this.candidates = new ArrayList<>();
    }
}
