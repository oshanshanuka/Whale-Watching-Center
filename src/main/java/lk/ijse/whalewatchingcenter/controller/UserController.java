package lk.ijse.whalewatchingcenter.controller;
//
//import jakarta.validation.Valid;
//import lk.ijse.whalewatchingcenter.dto.AuthDTO;
//import lk.ijse.whalewatchingcenter.dto.ResponseDTO;
//import lk.ijse.whalewatchingcenter.dto.UserDTO;
//import lk.ijse.whalewatchingcenter.util.VarList;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.whalewatchingcenter.dto.AuthDTO;
import lk.ijse.whalewatchingcenter.dto.UserDTO;
import lk.ijse.whalewatchingcenter.entity.User;
import lk.ijse.whalewatchingcenter.service.UserService;
import lk.ijse.whalewatchingcenter.util.AppUtil;
import lk.ijse.whalewatchingcenter.util.JwtUtil;
import lk.ijse.whalewatchingcenter.util.ResponseUtil;
import lk.ijse.whalewatchingcenter.util.VarList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

//    @PostMapping(value = "/register")
//    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
//        try {
//            int res = userService.saveUser(userDTO);
//            switch (res) {
//                case VarList.Created -> {
//                    String token = jwtUtil.generateToken(userDTO);
//                    AuthDTO authDTO = new AuthDTO();
//                    authDTO.setEmail(userDTO.getEmail());
//                    authDTO.setToken(token);
//                    return ResponseEntity.status(HttpStatus.CREATED)
//                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
//                }
//                case VarList.Not_Acceptable -> {
//                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
//                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
//                }
//                default -> {
//                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
//                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
//                }
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
//        }

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // Constructor injection
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil registerUser(@RequestPart("user") String userJson, @RequestPart("file") MultipartFile file) throws JsonProcessingException {
        try {
            UserDTO userDTO = new ObjectMapper().readValue(userJson, UserDTO.class);
            log.info("Received request to register user: {}", userDTO.getName());
            userDTO.setProfilePicture(AppUtil.toBase64(file));

            UserDTO<String> res = userService.saveUser(userDTO, file);
            if (res.equals(VarList.Created)) {
                String token = jwtUtil.generateToken(userDTO);
                AuthDTO authDTO = new AuthDTO();
                authDTO.setEmail(userDTO.getEmail());
                authDTO.setToken(token);
                return new ResponseUtil(VarList.Created, "User Registered Successfully", authDTO);
            } else if (res.equals(VarList.Not_Acceptable)) {
                return new ResponseUtil(VarList.Not_Acceptable, "Email Already Used", null);
            } else {
                return new ResponseUtil(VarList.Bad_Gateway, "Error", null);
            }
        } catch (Exception e) {
            log.error("Error registering user", e);
            return new ResponseUtil(VarList.Internal_Server_Error, e.getMessage(), null);
        }
    }

    @GetMapping("/profile")
    public ResponseUtil getUserProfile(@RequestHeader("Authorization") String token) {
        try {
            // Extracting the email from the "Bearer" prefix
            String jwtToken = token.substring(7);
            // Assuming you have a method to get the logged-in user's email
            String email = jwtUtil.extractUsernameFromToken(jwtToken);
            UserDTO userDTO = userService.loadUserDetailsByUsername(email);

            return new ResponseUtil(VarList.Accepted, "User details fetched successfully", userDTO);
        } catch (Exception e) {
            log.error("Error fetching user details", e);
            return new ResponseUtil(VarList.Internal_Server_Error, e.getMessage(), null);
        }
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateUser(@RequestPart("user") String userJson, @RequestPart("file") MultipartFile file) throws JsonProcessingException {
        try {
            UserDTO userDTO = new ObjectMapper().readValue(userJson, UserDTO.class);
            log.info("Received request to update user: {}", userDTO.getName());
            userDTO.setProfilePicture(AppUtil.toBase64(file));
//Assuming the userDTO contains the user ID
            UUID userid = userDTO.getUid();
            UserDTO<String> res = userService.updateUser(userid,userDTO, file);
            if (res.equals(VarList.Created)) {
                return new ResponseUtil(VarList.Created, "User Updated Successfully", null);
            } else if (res.equals(VarList.Not_Acceptable)) {
                return new ResponseUtil(VarList.Not_Acceptable, "Email Already Used", null);
            } else {
                return new ResponseUtil(VarList.Bad_Gateway, "Error", null);
            }
        } catch (Exception e) {
            log.error("Error updating user", e);
            return new ResponseUtil(VarList.Internal_Server_Error, e.getMessage(), null);
        }
    }
    @GetMapping(path = "/getByEmail")
    public ResponseUtil getUserByEmail(@RequestParam String email) {
        try {
            User user = userService.findByEmail(email);
            if (user == null) {
                log.error("User not found with email: {}", email);
                return new ResponseUtil(404, "User not found", null);
            }
            return new ResponseUtil(200, "User retrieved successfully", user);
        } catch (Exception e) {
            log.error("Error retrieving user", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }
}
