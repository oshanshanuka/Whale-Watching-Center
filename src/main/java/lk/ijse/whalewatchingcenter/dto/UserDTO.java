package lk.ijse.whalewatchingcenter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO<T> implements Serializable {
    private UUID uid;
    @Email(message = "Invalid email")
    private String email;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "Invalid phone number")
    private String phone;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid password")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Invalid name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Invalid role")
    private String role;
    private T profilePicture;


    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public T getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(T profilePicture) {
        this.profilePicture = profilePicture;
    }
}
