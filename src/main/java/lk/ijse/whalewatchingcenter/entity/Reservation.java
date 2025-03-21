package lk.ijse.whalewatchingcenter.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservations")
public class Reservation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID rid;
    @Column(nullable = false)
    private Date reservationDate;
    private double totalAmount;
    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User user;
}
