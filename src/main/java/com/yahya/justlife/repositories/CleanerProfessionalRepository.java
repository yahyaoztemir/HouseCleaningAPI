package com.yahya.justlife.repositories;

import com.yahya.justlife.entities.CleanerProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CleanerProfessionalRepository extends JpaRepository<CleanerProfessional,Long> {

    int countByIdIn(List<Long> ids);

}
