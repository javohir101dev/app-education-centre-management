package com.management.repository;

import com.management.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {

    boolean existsByNumberAndBranchId(String number, Long branch_id);

}
