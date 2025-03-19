package lk.ijse.whalewatchingcenter.service;

import lk.ijse.whalewatchingcenter.dto.AddressDTO;
import java.util.List;

public interface AddressService {
    void save(AddressDTO addressDTO);
    List<AddressDTO> getAll();
    void delete(int id);
    void update(AddressDTO addressDTO);
}