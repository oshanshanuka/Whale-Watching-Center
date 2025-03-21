package lk.ijse.whalewatchingcenter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.whalewatchingcenter.dto.BoatDTO;
import lk.ijse.whalewatchingcenter.entity.Boat;
import lk.ijse.whalewatchingcenter.service.BoatService;
import lk.ijse.whalewatchingcenter.util.ResponseUtil;
import lk.ijse.whalewatchingcenter.util.VarList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boat")
@CrossOrigin(origins = "http://localhost:63342")
public class BoatController {

    private static final Logger log = LoggerFactory.getLogger(BoatController.class);

    @Autowired
    private BoatService boatService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil save(@RequestPart("boat") String boatJson,
                                 @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            BoatDTO boatDTO = new ObjectMapper().readValue(boatJson, BoatDTO.class);
            log.info("Received boat: {}", boatDTO.getName());

            BoatDTO savedBoat = boatService.save(boatDTO, file);
            return new ResponseUtil(201, "Boat saved successfully", savedBoat);

        } catch (Exception e) {
            log.error("Error saving boat", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @GetMapping(path = "/getAll")
    public ResponseUtil getAllBoats() {
        try {
            List<BoatDTO<String>> boats = boatService.getAllBoats();
            return new ResponseUtil(200, "Boats retrieved successfully", boats);
        } catch (Exception e) {
            log.error("Error retrieving boats", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @GetMapping(path = "/getById")
    public ResponseUtil getBoatById(@RequestParam String id) {
        try {
            BoatDTO boat = boatService.getBoatById(id);
            if (boat == null) {
                return new ResponseUtil(404, "Boat not found", null);
            }
            return new ResponseUtil(200, "Boat retrieved successfully", boat);
        } catch (Exception e) {
            log.error("Error retrieving boat", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseUtil deleteBoat(@RequestParam String id) {
        try {
            boolean isDeleted = boatService.deleteBoatById(id);
            if (isDeleted) {
                return new ResponseUtil(200, "Boat deleted successfully", null);
            } else {
                return new ResponseUtil(404, "Boat not found", null);
            }
        } catch (Exception e) {
            log.error("Error deleting boat", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil updateBoat(@RequestPart("boat") String boatJson,
                                   @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            BoatDTO boatDTO = new ObjectMapper().readValue(boatJson, BoatDTO.class);
            log.info("Updating boat: {}", boatDTO.getName());

            BoatDTO updatedBoat = boatService.updateBoat(boatDTO, file);
            return new ResponseUtil(200, "Boat updated successfully", updatedBoat);

        } catch (Exception e) {
            log.error("Error updating boat", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }
}
