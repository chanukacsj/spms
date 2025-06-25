package com.example.parkingservice.service;

import com.example.parkingservice.dto.ParkingSpaceDTO;
import com.example.parkingservice.entity.ParkingSpace;

import java.util.List;

public interface ParkingSpaceService {
    ParkingSpace addSpace(ParkingSpaceDTO dto);

    List<ParkingSpace> getAll();

    List<ParkingSpace> getByOwner(Long ownerId);

    ParkingSpaceDTO updateParkingSpace(Long spaceId, ParkingSpaceDTO parkingSpaceDTO);

    ParkingSpaceDTO getParkingSpaceById(Integer spaceId);

    void deleteParkingSpace(Long spaceId);

    List<ParkingSpaceDTO> getAvailableParkingSpaces();

    List<ParkingSpaceDTO> getParkingSpacesByZone(String zone);

    List<ParkingSpaceDTO> getParkingSpacesByLocation(String location);

    boolean reserveParkingSpace(Long spaceId);

    boolean releaseParkingSpace(Long spaceId);

}
