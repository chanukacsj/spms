package com.example.parkingservice.api;

import com.example.parkingservice.dto.ParkingSpaceDTO;
import com.example.parkingservice.entity.ParkingSpace;
import com.example.parkingservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/parkings")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('OWNER')")
    public ParkingSpace addSpace(@RequestBody ParkingSpaceDTO dto) {
        return parkingSpaceService.addSpace(dto);
    }

    @GetMapping("/all")
    public List<ParkingSpace> getAll() {
        return parkingSpaceService.getAll();
    }

    @GetMapping("/owner/{ownerId}")
    @PreAuthorize("hasRole('OWNER')")
    public List<ParkingSpace> getByOwner(@PathVariable Long ownerId) {
        return parkingSpaceService.getByOwner(ownerId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ParkingSpaceDTO> updateParkingSpace(
            @PathVariable Long id,
            @RequestBody ParkingSpaceDTO dto) {
        System.out.println("Updating parking space with ID: " + id + " with data: " + dto);
        return ResponseEntity.ok(parkingSpaceService.updateParkingSpace(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpace(@PathVariable Integer id) {
        return ResponseEntity.ok(parkingSpaceService.getParkingSpaceById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        System.out.println(id + " is the id to be deleted");
        parkingSpaceService.deleteParkingSpace(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<ParkingSpaceDTO>> getAvailableSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAvailableParkingSpaces());
    }

    @GetMapping("/zone/{zone}")
    public ResponseEntity<List<ParkingSpaceDTO>> getByZone(@PathVariable String zone) {
        return ResponseEntity.ok(parkingSpaceService.getParkingSpacesByZone(zone));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<ParkingSpaceDTO>> getByLocation(@PathVariable String location) {
        return ResponseEntity.ok(parkingSpaceService.getParkingSpacesByLocation(location));
    }

    @PostMapping("/reserve/{id}")
    public ResponseEntity<String> reserveSpace(@PathVariable Long id) {
        boolean result = parkingSpaceService.reserveParkingSpace(id);
        return result ?
                ResponseEntity.ok("Space reserved successfully") :
                ResponseEntity.badRequest().body("Failed to reserve space");
    }

    @PostMapping("/release/{id}")
    public ResponseEntity<String> releaseSpace(@PathVariable Long id) {
        boolean result = parkingSpaceService.releaseParkingSpace(id);
        return result ?
                ResponseEntity.ok("Space released successfully") :
                ResponseEntity.badRequest().body("Failed to release space");
    }
}
