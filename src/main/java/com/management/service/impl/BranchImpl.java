package com.management.service.impl;

import com.management.common.MapstructMapper;
import com.management.model.dtos.BranchDto;
import com.management.model.entity.Branch;
import com.management.model.response.ApiResponse;
import com.management.repository.BranchRepo;
import com.management.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BranchImpl implements BranchService {

    private final BranchRepo branchRepo;
    private final MapstructMapper mapper;

    @Autowired
    public BranchImpl(BranchRepo branchRepo, MapstructMapper mapper) {
        this.branchRepo = branchRepo;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ApiResponse<Branch>> addBranch(BranchDto branchDto) {
        boolean existsByName = branchRepo.existsByName(branchDto.getName());
        if (existsByName) {
            return new ResponseEntity<>(new ApiResponse<>("Banch with name " + branchDto.getName() + " has already exists"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new ApiResponse<>(branchRepo.save(mapper.toBranch(branchDto))), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse<Branch>> getBranchById(Long id) {
        Optional<Branch> optionalBranch = branchRepo.findById(id);
        return optionalBranch.map(
                branch -> new ResponseEntity<>(new ApiResponse<>(branch), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>(String.format("Branch with id %s is not found", id)), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<List<Branch>>> getAllBranches() {
        return new ResponseEntity<>(new ApiResponse<>(branchRepo.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Branch>> editBranchById(Long id, BranchDto branchDto) {
        Optional<Branch> optionalBranch = branchRepo.findById(id);
        if (!optionalBranch.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("Brach with id %s is not found", id)), HttpStatus.NOT_FOUND);
        }
        boolean existsByName = branchRepo.existsByName(branchDto.getName());
        if (existsByName) {
            return new ResponseEntity<>(new ApiResponse<>("Banch with name " + branchDto.getName() + " has already exists"), HttpStatus.CONFLICT);
        }
        Branch branch = Branch.toBrachSet(optionalBranch.get(), branchDto);
        branchRepo.save(branch);
        return new ResponseEntity<>(new ApiResponse<>(branch), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<ApiResponse<Boolean>> deleteBranchById(Long id) {
//        Optional<Branch> optionalBranch = branchRepo.findById(id);
//        if (!optionalBranch.isPresent()) {
//            return new ResponseEntity<>(new ApiResponse<>(String.format("Brach with id %s is not found", id)), HttpStatus.NOT_FOUND);
//        }
//        try {
//            branchRepo.deleteById(id);
//            return new ResponseEntity<>(new ApiResponse<>(true), HttpStatus.NOT_FOUND);
//        }catch (Exception e){
//            return new ResponseEntity<>(new ApiResponse<>("Error in deleting branch"), HttpStatus.CONFLICT);
//        }
//    }


}
