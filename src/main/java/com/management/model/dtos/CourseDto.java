package com.management.model.dtos;

import lombok.Data;


import java.util.Set;

@Data
public class CourseDto {


    private String name;

    private Double price;

    private String description;

    private Set<Long> branchesIds;


}
