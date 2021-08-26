package com.management.model.dtos;

import com.management.model.entity.Room;
import lombok.Data;


@Data
public class RoomDto {

    private String name;

    private String number;

    private Long branchId;


}
