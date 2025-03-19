package lk.ijse.whalewatchingcenter.repo;

import lk.ijse.whalewatchingcenter.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepo extends JpaRepository<Tour, Long> {
}
