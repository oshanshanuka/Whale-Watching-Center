package lk.ijse.whalewatchingcenter.controller;

import jakarta.validation.constraints.Pattern;
import lk.ijse.whalewatchingcenter.dto.ReservationDTO;
import lk.ijse.whalewatchingcenter.service.impl.ReservationServiceImpl;
import lk.ijse.whalewatchingcenter.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@CrossOrigin(origins = "http://localhost:63342")
public class ReservationController {
    @Autowired
    private ReservationServiceImpl reservationService;

    @PostMapping(path = "/save")
    public ResponseUtil saveReservation(@RequestBody ReservationDTO reservationDTO){
        reservationService.saveReservation(reservationDTO);
        return new ResponseUtil(201,"Reservation save succesfully",null);
    }

    @GetMapping(path = "/get")
    public ResponseUtil getAllReservations(){
        return new ResponseUtil(201,"Reservation saved succesfully",reservationService.getAllReservations());
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseUtil deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
        return new ResponseUtil(201,"Reservation Delete Successfully",null);
    }

    @PutMapping(path = "/update")
    public ResponseUtil updateReservation(@RequestBody ReservationDTO reservationDTO){
        reservationService.updateReservation(reservationDTO);
        return new ResponseUtil(201,"Reservation Updated Successfully",null);
    }
}
