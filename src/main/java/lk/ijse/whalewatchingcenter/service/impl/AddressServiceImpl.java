package lk.ijse.whalewatchingcenter.service.impl;

import lk.ijse.whalewatchingcenter.dto.AddressDTO;
import lk.ijse.whalewatchingcenter.repository.AddressRepo;
import lk.ijse.whalewatchingcenter.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
   @Autowired
   private AddressRepo addressRepo;

   @Autowired
   private ModelMapper modelMapper;
    @Override
    public void save(AddressDTO addressDTO) {

    }

    @Override
    public List<AddressDTO> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(AddressDTO addressDTO) {

    }
}
