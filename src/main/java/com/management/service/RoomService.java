package com.management.service;

import com.management.model.dtos.RoomDto;
import com.management.model.entity.Room;
import com.management.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    ResponseEntity<ApiResponse<Room>> addRoom(RoomDto roomDto);

    ResponseEntity<ApiResponse<Room>> getRoomById(Long id);

    ResponseEntity<ApiResponse<List<Room>>> getAllRoom( );

    ResponseEntity<ApiResponse<Room>> editRoomById(Long id, RoomDto roomDto);


}
