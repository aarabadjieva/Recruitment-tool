package com.bootcamp.recruitmenttool.data.repositories;

import com.bootcamp.recruitmenttool.data.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, Long> {
    boolean existsByName(String name);

    Optional<Skill> findByName(String name);
}
