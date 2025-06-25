package com.example.reservationservice.api;

import com.example.reservationservice.dto.ReservationDTO;
import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public Reservation create(@RequestBody ReservationDTO dto) {
        return reservationService.createReservation(dto);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Reservation> getByUser(@PathVariable Long userId) {
        return reservationService.getReservationsByUser(userId);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('OWNER')")
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
