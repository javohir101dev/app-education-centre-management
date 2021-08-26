package com.management.service.impl;

import com.management.common.MapstructMapper;
import com.management.model.dtos.RoomDto;
import com.management.model.entity.Branch;
import com.management.model.entity.Room;
import com.management.model.response.ApiResponse;
import com.management.repository.BranchRepo;
import com.management.repository.RoomRepo;
import com.management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomImpl  implements RoomService {
    private final RoomRepo roomRepo;
    private final BranchRepo branchRepo;
    private final MapstructMapper mapper;


    @Autowired
    public RoomImpl(RoomRepo roomRepo, BranchRepo branchRepo, MapstructMapper mapper) {
        this.roomRepo = roomRepo;
        this.branchRepo = branchRepo;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<ApiResponse<Room>> addRoom(RoomDto roomDto) {
        boolean exists = roomRepo.existsByNumberAndBranchId(roomDto.getNumber(), roomDto.getBranchId());
        if (exists){
            return new ResponseEntity<>(new ApiResponse<>("This room is already exists in this branch"), HttpStatus.CONFLICT);
        }

        Optional<Branch> optionalBranch = branchRepo.findById(roomDto.getBranchId());
        if (!optionalBranch.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>(String.format("Branch with id %s is not found", roomDto.getBranchId())), HttpStatus.NOT_FOUND);
        }
        Room room = mapper.toRoom(roomDto);
        room.setBranch(optionalBranch.get());
        roomRepo.save(room);
        return new ResponseEntity<>(new ApiResponse<>(room), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<Room>> getRoomById(Long id) {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (!optionalRoom.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>(String.format("Room with id %s is not found", id)), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(optionalRoom.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List<Room>>> getAllRoom() {
        return new ResponseEntity<>(new ApiResponse<>(roomRepo.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Room>> editRoomById(Long id, RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (!optionalRoom.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>(String.format("Room with id %s is not found", id)), HttpStatus.NOT_FOUND);
        }
        boolean exists = roomRepo.existsByNumberAndBranchId(roomDto.getNumber(), roomDto.getBranchId());
        if (exists){
            return new ResponseEntity<>(new ApiResponse<>("This room is already exists in this branch"), HttpStatus.CONFLICT);
        }
        Room room = optionalRoom.get();


        if (roomDto.getBranchId()!=null){
            Optional<Branch> optionalBranch = branchRepo.findById(roomDto.getBranchId());
            if (!optionalBranch.isPresent()){
                return new ResponseEntity<>(new ApiResponse<>(String.format("Branch with id %s is not found", roomDto.getBranchId())), HttpStatus.NOT_FOUND);
            }
            room.setBranch(optionalBranch.get());
        }
        if (roomDto.getName()!=null){
            room.setName(roomDto.getName());
        }

        if (roomDto.getNumber()!=null){
            room.setNumber(roomDto.getNumber());
        }

        roomRepo.save(room);
        return new ResponseEntity<>(new ApiResponse<>(room), HttpStatus.OK);

    }



}
