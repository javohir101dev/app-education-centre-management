package com.management.common;

import com.management.model.dtos.BranchDto;
import com.management.model.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

//      @Mapping(expression = "")
      Branch toBranch(BranchDto branchDto);
}