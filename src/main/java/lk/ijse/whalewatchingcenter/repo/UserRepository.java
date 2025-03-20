package lk.ijse.whalewatchingcenter.repo;

import lk.ijse.whalewatchingcenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User,String> {

    User findByEmail(String userName);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}