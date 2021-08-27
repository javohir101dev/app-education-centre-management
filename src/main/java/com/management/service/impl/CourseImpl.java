package com.management.service.impl;

import com.management.common.MapstructMapper;
import com.management.model.dtos.CourseDto;
import com.management.model.entity.Branch;
import com.management.model.entity.Course;
import com.management.model.response.ApiResponse;
import com.management.repository.BranchRepo;
import com.management.repository.CourseRepo;
import com.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final BranchRepo branchRepo;
    private final MapstructMapper mapper;

    @Autowired
    public CourseImpl(CourseRepo courseRepo, BranchRepo branchRepo, MapstructMapper mapper) {
        this.courseRepo = courseRepo;
        this.branchRepo = branchRepo;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<ApiResponse<Course>> addCourse(CourseDto courseDto) {
        Set<Long> branchIds = courseDto.getBranchesIds();
        Set<Branch> branches = new HashSet<>();

        for (Long branchId : branchIds) {
            Optional<Branch> optionalBranch = branchRepo.findById(branchId);
            if (!optionalBranch.isPresent()) {
                return new ResponseEntity<>(new ApiResponse<>(String.format("Branch with id %s is not found", branchId)), HttpStatus.NOT_FOUND);
            }
            Integer count = courseRepo.countByNameAndBranch(courseDto.getName(), branchId);
            if (count > 0) {
                return new ResponseEntity<>(new ApiResponse<>(String.format("This course is added to branch with id %s ", branchId)), HttpStatus.CONFLICT);
            }
            branches.add(optionalBranch.get());
        }
        Course course = mapper.toCourse(courseDto);
        course.setBranches(branches);
        courseRepo.save(course);
        return new ResponseEntity<>(new ApiResponse<>(course), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<Course>> getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if (!optionalCourse.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("Course with id %s is not found", id)), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(optionalCourse.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        return new ResponseEntity<>(new ApiResponse<>(courseRepo.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Course>> editCourse(Long id, CourseDto courseDto) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if (!optionalCourse.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("Course with id %s is not found", id)), HttpStatus.NOT_FOUND);
        }

        Course course = optionalCourse.get();

        Set<Branch> branches = null;
        if (courseDto.getBranchesIds() != null) {
            Set<Long> branchIds = courseDto.getBranchesIds();
            branches = new HashSet<>();

            for (Long branchId : branchIds) {
                Optional<Branch> optionalBranch = branchRepo.findById(branchId);
                if (!optionalBranch.isPresent()) {
                    return new ResponseEntity<>(new ApiResponse<>(String.format("Branch with id %s is not found", branchId)), HttpStatus.NOT_FOUND);
                }
                Integer count = courseRepo.countByNameAndBranch(courseDto.getName(), branchId);
                if (count > 0) {
                    return new ResponseEntity<>(new ApiResponse<>(String.format("This course is added to branch with id %s ", branchId)), HttpStatus.CONFLICT);
                }
                branches.add(optionalBranch.get());
            }
        }


        if (courseDto.getName() != null) {
            course.setName(courseDto.getName());
        }
        if (courseDto.getPrice() != null) {
            course.setPrice(courseDto.getPrice());
        }
        if (courseDto.getDescription() != null) {
            course.setDescription(courseDto.getDescription());
        }

        course.setBranches(branches);

        courseRepo.save(course);
        return new ResponseEntity<>(new ApiResponse<>(course), HttpStatus.OK);
    }
}
