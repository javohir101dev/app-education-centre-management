package com.management.repository;

import com.management.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query(nativeQuery = true, value = "select count(*) from course\n" +
            "                         join course_branches cb on course.id = cb.course_id\n" +
            "where course.name= :name and cb.branches_id = :branchId")
    Integer countByNameAndBranch(String name, Long branchId);

}
