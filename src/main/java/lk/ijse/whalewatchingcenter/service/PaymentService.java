package lk.ijse.whalewatchingcenter.service;

import lk.ijse.whalewatchingcenter.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    void save(PaymentDTO paymentDTO);
    List<PaymentDTO> getAll();
    void delete(String id);
    void update(PaymentDTO paymentDTO);
}