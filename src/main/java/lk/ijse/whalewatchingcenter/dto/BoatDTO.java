package lk.ijse.whalewatchingcenter.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoatDTO<T> implements Serializable {
    private UUID id;

    private String name;

    private String description;


    private String category;


    private Double price;


    private int capacity;

    private T imageURL;
    private Long sellerId;
    private boolean active;
}
