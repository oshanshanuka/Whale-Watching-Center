package lk.ijse.whalewatchingcenter.service.impl;

import lk.ijse.whalewatchingcenter.dto.PaymentDTO;
import lk.ijse.whalewatchingcenter.repository.PaymentRepo;
import lk.ijse.whalewatchingcenter.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void save(PaymentDTO paymentDTO) {

    }

    @Override
    public List<PaymentDTO> getAll() {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(PaymentDTO paymentDTO) {

    }
}
