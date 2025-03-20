package lk.ijse.whalewatchingcenter.service;

import jakarta.transaction.Transactional;
import lk.ijse.whalewatchingcenter.dto.BoatDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface BoatService {
    BoatDTO<String> save(BoatDTO boatDTO, MultipartFile file);
    List<BoatDTO<String>> getAllBoats();
    void delete(UUID id);
    BoatDTO<String> updateBoat(BoatDTO boatDTO, MultipartFile file);

    List<BoatDTO<String>> getByUserId(UUID userId);

    BoatDTO<String> getBoatById(String id);

    @Transactional
    BoatDTO<String> update(UUID id, BoatDTO boatDTO, MultipartFile file);

    boolean deleteBoatById(String id);

}
