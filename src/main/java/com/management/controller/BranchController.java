package com.management.controller;

import com.management.model.dtos.BranchDto;
import com.management.model.entity.Branch;
import com.management.model.response.ApiResponse;
import com.management.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/branch")
public class BranchController {


    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Branch>> addBranch(@RequestBody BranchDto branchDto){
        return branchService.addBranch(branchDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Branch>> getBranchById(@PathVariable Long id){
        return branchService.getBranchById(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Branch>>> getAllBranches(){
        return branchService.getAllBranches();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Branch>> editBranchById(@PathVariable Long id, @RequestBody BranchDto branchDto){
        return branchService.editBranchById(id,branchDto);
    }
}
