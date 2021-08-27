package com.management.controller;

import com.management.model.dtos.CourseDto;
import com.management.model.entity.Course;
import com.management.model.response.ApiResponse;
import com.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody CourseDto courseDto){
        return courseService.addCourse(courseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping
    public  ResponseEntity<ApiResponse<List<Course>>> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> editCourse(Long id, CourseDto courseDto){
        return courseService.editCourse(id, courseDto);
    }


}
