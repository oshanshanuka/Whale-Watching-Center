package lk.ijse.whalewatchingcenter.repo;

import lk.ijse.whalewatchingcenter.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review,String> {
}
