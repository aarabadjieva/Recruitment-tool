package com.bootcamp.recruitmenttool.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Length(min = 2)
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "skills")
    private List<Candidate> candidates;

    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs;

    public Skill() {
        this.candidates = new ArrayList<>();
        this.jobs = jobs = new ArrayList<>();
    }
}
