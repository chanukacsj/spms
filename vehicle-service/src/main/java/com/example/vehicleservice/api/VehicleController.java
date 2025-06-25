package com.example.vehicleservice.api;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.entity.Vehicle;
import com.example.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('OWNER')")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public Vehicle addVehicle(@RequestBody VehicleDTO dto) {
        return vehicleService.addVehicle(dto);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Vehicle> getVehiclesByUser(@PathVariable Long userId) {
        return vehicleService.getVehiclesByUserId(userId);
    }
    @PutMapping("/update/{vehicleId}")
    @PreAuthorize("hasRole('USER')")
    public Vehicle updateVehicle(@PathVariable Long vehicleId, @RequestBody VehicleDTO dto) {
        return vehicleService.updateVehicle(vehicleId, dto);
    }
    @DeleteMapping("/delete/{vehicleId}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }
}
