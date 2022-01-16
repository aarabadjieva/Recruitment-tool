package com.bootcamp.recruitmenttool.data.repositories;

import com.bootcamp.recruitmenttool.data.entities.Job;
import com.bootcamp.recruitmenttool.data.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {

    List<Job> findAllBySkills(Skill skill);
}
