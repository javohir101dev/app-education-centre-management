package com.management.repository;

import com.management.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {

    boolean existsByName(String name);

}
