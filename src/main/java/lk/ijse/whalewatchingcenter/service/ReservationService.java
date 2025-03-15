package lk.ijse.whalewatchingcenter.service;

import lk.ijse.whalewatchingcenter.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    void saveReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservationById(String reservationId);

    List<ReservationDTO> getAllReservations();

    String getLastReservationId();

    void deleteReservation(Long id);

    void updateOrder(ReservationDTO reservationDTO);

    void updateReservation(ReservationDTO reservationDTO);
}
