package lk.ijse.whalewatchingcenter.repository;

import lk.ijse.whalewatchingcenter.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,String> {
    @Query("SELECT r.rid FROm Reservation r ORDER BY r.rid DESC")
    String findLastReservationId();
}
