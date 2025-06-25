package com.example.parkingservice.service.impl;

import com.example.parkingservice.dto.ParkingSpaceDTO;
import com.example.parkingservice.entity.ParkingSpace;
import com.example.parkingservice.repository.ParkingSpaceRepository;
import com.example.parkingservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepo;

    @Override
    public ParkingSpace addSpace(ParkingSpaceDTO dto) {
        ParkingSpace space = new ParkingSpace();
        space.setOwnerId(dto.getOwnerId());
        space.setLocation(dto.getLocation());
        space.setAvailable(dto.isAvailable());
        space.setZone(dto.getZone());
        space.setPricePerHour(dto.getPricePerHour());
        return parkingSpaceRepo.save(space);
    }

    @Override
    public List<ParkingSpace> getAll() {
        return parkingSpaceRepo.findAll();
    }

    @Override
    public List<ParkingSpace> getByOwner(Long ownerId) {
        return parkingSpaceRepo.findByOwnerId(ownerId);
    }

    @Override
    public ParkingSpaceDTO updateParkingSpace(Long spaceId, ParkingSpaceDTO dto) {
        ParkingSpace existing = parkingSpaceRepo.findById(Long.valueOf(spaceId))
                .orElseThrow(() -> new RuntimeException("Parking space not found with id " + spaceId));

        existing.setLocation(dto.getLocation());
        existing.setZone(dto.getZone());
        existing.setAvailable(dto.isAvailable());
        existing.setOwnerId(dto.getOwnerId());
        System.out.println("price per hour: " + dto.getPricePerHour());
        existing.setPricePerHour(dto.getPricePerHour());
        existing.setId(spaceId);
        return mapToDTO(parkingSpaceRepo.save(existing));
    }

    @Override
    public ParkingSpaceDTO getParkingSpaceById(Integer spaceId) {
        ParkingSpace space = parkingSpaceRepo.findById(Long.valueOf(spaceId))
                .orElseThrow(() -> new RuntimeException("Parking space not found with id " + spaceId));
        return mapToDTO(space);
    }


    @Override
    public void deleteParkingSpace(Long spaceId) {
        if (!parkingSpaceRepo.existsById(Long.valueOf(spaceId))) {
            throw new RuntimeException("Cannot delete. Parking space not found with id " + spaceId);
        }
        parkingSpaceRepo.deleteById(Long.valueOf(spaceId));
    }

    @Override
    public List<ParkingSpaceDTO> getAvailableParkingSpaces() {
        return parkingSpaceRepo.findByAvailableTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getParkingSpacesByZone(String zone) {
        return parkingSpaceRepo.findByZone(zone)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getParkingSpacesByLocation(String location) {
        return parkingSpaceRepo.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public boolean reserveParkingSpace(Long spaceId) {
        Optional<ParkingSpace> optional = parkingSpaceRepo.findById(Long.valueOf(spaceId));
        if (optional.isPresent() && optional.get().isAvailable()) {
            ParkingSpace space = optional.get();
            space.setAvailable(false);
            parkingSpaceRepo.save(space);
            return true;
        }
        return false;
    }

    @Override
    public boolean releaseParkingSpace(Long spaceId) {
        Optional<ParkingSpace> optional = parkingSpaceRepo.findById(Long.valueOf(spaceId));
        if (optional.isPresent() && !optional.get().isAvailable()) {
            ParkingSpace space = optional.get();
            space.setAvailable(true);
            parkingSpaceRepo.save(space);
            return true;
        }
        return false;
    }

    private ParkingSpaceDTO mapToDTO(ParkingSpace space) {
        ParkingSpaceDTO dto = new ParkingSpaceDTO();
        dto.setId(space.getId());
        dto.setLocation(space.getLocation());
        dto.setZone(space.getZone());
        dto.setAvailable(space.isAvailable());
        dto.setOwnerId(space.getOwnerId());
        return dto;
    }

    private ParkingSpace mapToEntity(ParkingSpaceDTO dto) {
        ParkingSpace space = new ParkingSpace();
        space.setId(dto.getId());
        space.setLocation(dto.getLocation());
        space.setZone(dto.getZone());
        space.setAvailable(dto.isAvailable());
        space.setOwnerId(dto.getOwnerId());
        return space;
    }
}
