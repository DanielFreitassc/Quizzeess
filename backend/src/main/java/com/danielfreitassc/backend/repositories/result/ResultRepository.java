package com.danielfreitassc.backend.repositories.result;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.result.ResultEntity;

public interface ResultRepository extends JpaRepository<ResultEntity,Long> {
    Page<ResultEntity> findByUserEntity_Id(Long userId, Pageable pageable);

    boolean findByUserEntity_Id(Long userId);    
}
