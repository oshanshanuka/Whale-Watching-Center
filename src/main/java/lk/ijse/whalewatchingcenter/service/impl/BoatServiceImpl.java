package lk.ijse.whalewatchingcenter.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.whalewatchingcenter.dto.BoatDTO;
import lk.ijse.whalewatchingcenter.entity.Boat;
import lk.ijse.whalewatchingcenter.enums.ImageType;
import lk.ijse.whalewatchingcenter.repo.BoatRepo;
import lk.ijse.whalewatchingcenter.repo.UserRepository;
import lk.ijse.whalewatchingcenter.service.BoatService;
import lk.ijse.whalewatchingcenter.util.ImageUtil;
import org.hibernate.StaleObjectStateException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoatServiceImpl implements BoatService {
    private static final Logger logger = LoggerFactory.getLogger(BoatServiceImpl.class);

    @Autowired
    private BoatRepo boatRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ImageUtil imageUtil;
    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public BoatDTO<String> save(BoatDTO boatDTO, MultipartFile file) {
        String base64Image = imageUtil.saveImage(ImageType.Boat, file);
        logger.info("Base64 image for boat: {}", base64Image);

        Boat boat = modelMapper.map(boatDTO, Boat.class);
        boat.setImageURL(base64Image);

        try {
            Boat savedBoat = boatRepo.save(boat);
            BoatDTO<String> boatDTOSaved = modelMapper.map(savedBoat, BoatDTO.class);
            boatDTOSaved.setImageURL(base64Image);
            return boatDTOSaved;
        } catch (Exception e) {
            logger.error("Failed to save boat: {}", boat, e);
            throw new RuntimeException("Failed to save boat");
        }
    }

    @Override
    public List<BoatDTO<String>> getAllBoats() {
        List<Boat> boats = boatRepo.findAll();
        List<BoatDTO<String>> boatDTOList = modelMapper.map(boats, new TypeToken<List<BoatDTO<String>>>() {}.getType());

        for (BoatDTO<String> boatDTO : boatDTOList) {
            boats.stream().filter(boat -> boat.getId().equals(boatDTO.getId()))
                    .findFirst()
                    .ifPresent(boat -> boatDTO.setImageURL(imageUtil.getImage(boat.getImageURL())));
        }
        return boatDTOList;
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        logger.info("Deleting boat with id: {}", id);
        if (boatRepo.existsById(id)) {
            boatRepo.deleteById(id);
            logger.info("Boat deleted successfully");
        } else {
            logger.warn("Boat with id {} not found", id);
            throw new RuntimeException("Boat Not Found");
        }
    }

    @Override
    public BoatDTO<String> updateBoat(BoatDTO boatDTO, MultipartFile file) {
        return null;
    }

    @Transactional
    @Override
    public BoatDTO<String> update(UUID id, BoatDTO boatDTO, MultipartFile file) {
        Optional<Boat> boatOptional = boatRepo.findById(id);
        if (boatOptional.isPresent()) {
            Boat boat = boatOptional.get();
            String imageName = boat.getImageURL();
            if (!file.isEmpty()) {
                imageName = imageUtil.updateImage(boat.getImageURL(), ImageType.Boat, file);
            }
            boat.setImageURL(imageName);
            boat.setName(boatDTO.getName());
            boat.setCapacity(boatDTO.getCapacity());
            boat.setDescription(boatDTO.getDescription());

            try {
                boatRepo.save(boat);
                logger.info("Boat updated successfully: {}", boat);
                return modelMapper.map(boat, BoatDTO.class);
            } catch (StaleObjectStateException e) {
                logger.error("Failed to update boat: {}", boat, e);
                throw new RuntimeException("Failed to update boat");
            }
        } else {
            logger.warn("Boat with id {} not found", id);
            throw new RuntimeException("Boat Not Found");
        }
    }

    @Override
    public List<BoatDTO<String>> getByUserId(UUID userId) {
        return null;
    }

//    @Override
//    public List<BoatDTO<String>> getByUserId(UUID userId) {
//        List<Boat> boats = boatRepo.findByUserUid(userId);
//        List<BoatDTO<String>> boatDTOList = modelMapper.map(boats, new TypeToken<List<BoatDTO<String>>>() {}.getType());
//        for (BoatDTO<String> boatDTO : boatDTOList) {
//            boats.stream().filter(boat -> boat.getId().equals(boatDTO.getId()))
//                    .findFirst()
//                    .ifPresent(boat -> boatDTO.setImageURL(imageUtil.getImage(boat.getImageURL())));
//        }
//        return boatDTOList;
//    }

    @Override
    public BoatDTO<String> getBoatById(String id) {
        Optional<Boat> boat = boatRepo.findById(UUID.fromString(id));
        if (boat.isPresent()) {
            BoatDTO<String> boatDTO = modelMapper.map(boat.get(), BoatDTO.class);
            boatDTO.setImageURL(imageUtil.getImage(boat.get().getImageURL()));
            return boatDTO;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteBoatById(String id) {
        if (boatRepo.existsById(UUID.fromString(id))) {
            boatRepo.deleteById(UUID.fromString(id));
            return true;
        } else {
            return false;
        }
    }
}
