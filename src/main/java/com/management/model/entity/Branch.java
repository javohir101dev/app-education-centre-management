package com.management.model.entity;

import com.management.model.dtos.BranchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String address;

    public static Branch toBrachSet(Branch branch, BranchDto branchDto) {

        if (branchDto.getName() != null) {
            branch.setName(branchDto.getName());
        }

        if (branchDto.getAddress() != null) {
            branch.setAddress(branchDto.getAddress());
        }
        return branch;
    }

}
