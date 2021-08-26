package com.management.controller;

import com.management.model.dtos.RoomDto;
import com.management.model.entity.Room;
import com.management.model.response.ApiResponse;
import com.management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Room>> addRoom(@RequestBody RoomDto roomDto){
        return roomService.addRoom(roomDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Room>> getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
    }

    @GetMapping
    public   ResponseEntity<ApiResponse<List<Room>>> getAllRoom( ){
        return roomService.getAllRoom();
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<Room>> editRoomById(@PathVariable Long id, @RequestBody RoomDto roomDto){
        return roomService.editRoomById(id, roomDto);
    }

}
