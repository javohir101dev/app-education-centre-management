package com.management.service;

import com.management.model.dtos.CourseDto;
import com.management.model.entity.Course;
import com.management.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {


    ResponseEntity<ApiResponse<Course>> addCourse(CourseDto courseDto);


    ResponseEntity<ApiResponse<Course>> getCourseById(Long id);
    ResponseEntity<ApiResponse<List<Course>>> getAllCourses();
    ResponseEntity<ApiResponse<Course>> editCourse(Long id, CourseDto courseDto);

}
