package com.management.service;

import com.management.model.dtos.BranchDto;
import com.management.model.entity.Branch;
import com.management.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BranchService {

    ResponseEntity<ApiResponse<Branch>> addBranch(BranchDto branchDto);

    ResponseEntity<ApiResponse<Branch>> getBranchById(Long id);

    ResponseEntity<ApiResponse<List<Branch>>> getAllBranches();

    ResponseEntity<ApiResponse<Branch>> editBranchById(Long id, BranchDto branchDto);

//    ResponseEntity<ApiResponse<Boolean>> deleteBranchById(Long id);


}
