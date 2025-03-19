package lk.ijse.whalewatchingcenter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourDTO {
    private UUID id;

    @NotBlank(message = "Tour name is required")
    @Size(max = 100, message = "Tour name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotNull(message = "Schedule is required")
    private Timestamp schedule;

    @NotNull(message = "Boat ID is required")
    private UUID boatId;
}
