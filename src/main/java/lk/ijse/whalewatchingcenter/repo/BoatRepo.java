package lk.ijse.whalewatchingcenter.repo;

import lk.ijse.whalewatchingcenter.entity.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoatRepo extends JpaRepository<Boat, UUID> {
    BoatRepo findByUserName(String userName);
    boolean existsByUserName(String userName);

    int countByUserName(String userName);

    List<Boat> findByCategory(String category);

    List<Boat> findByActive(boolean active);
}