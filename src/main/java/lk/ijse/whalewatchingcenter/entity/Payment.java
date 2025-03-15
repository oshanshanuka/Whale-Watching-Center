package lk.ijse.whalewatchingcenter.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID pid;
    private double amount;
    private Date paymentdate;
    private String paymentmethod;

    @ManyToOne
    @JoinColumn(name = "rid")
    private Reservation reservation;
}
