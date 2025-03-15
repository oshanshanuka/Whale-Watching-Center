package lk.ijse.whalewatchingcenter.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private UUID Rid;
    @NotBlank(message = "Reservation date is required")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Invalid date")
    private Date ResDate;
    @NotBlank(message = "Total amount is required")
    @Pattern(regexp = "^[0-9]*$", message = "Invalid total amount")
    @Size(max = 255)
    private double totalAmount;
    @NotNull
    private UUID userId;
    @NotNull
    private List<PaymentDTO> payments;
}
