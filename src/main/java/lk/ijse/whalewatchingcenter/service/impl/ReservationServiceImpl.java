package lk.ijse.whalewatchingcenter.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.whalewatchingcenter.dto.ReservationDTO;
import lk.ijse.whalewatchingcenter.repo.BoatRepo;
import lk.ijse.whalewatchingcenter.repo.ReservationRepo;
import lk.ijse.whalewatchingcenter.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private BoatRepo boatRepo;
    @Autowired
    private ModelMapper modelMapper;
    private static final Logger LOGGER = Logger.getLogger(ReservationServiceImpl.class.getName());
    @Override
    public void saveReservation(ReservationDTO reservationDTO) {

    }

    @Override
    public ReservationDTO getReservationById(String reservationId) {
        return null;
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return null;
    }

//    @Override
//    public boolean checkItemsInStock(List<BidSpiceListningDTO> bidSpiceListnings) {
//        return false;
//    }

    @Override
    public String getLastReservationId() {
        return null;
    }

    @Override
    public void deleteReservation(Long id) {

    }

    @Override
    public void updateOrder(ReservationDTO reservationDTO) {

    }

    @Override
    public void updateReservation(ReservationDTO reservationDTO) {

    }
}