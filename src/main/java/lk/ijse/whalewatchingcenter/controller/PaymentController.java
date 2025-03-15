package lk.ijse.whalewatchingcenter.controller;

import lk.ijse.whalewatchingcenter.dto.PaymentDTO;
import lk.ijse.whalewatchingcenter.service.impl.PaymentServiceImpl;
import lk.ijse.whalewatchingcenter.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "http://localhost:63342")
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;
    @PostMapping(path = "/save")
    public ResponseUtil savePayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.save(paymentDTO);
        return new ResponseUtil(201,"Payment saved successfully",null);
    }
    @GetMapping(path = "/get")
    public ResponseUtil getAllPayments(){
        return new ResponseUtil(201,"Payment saved successfully", paymentService.getAll());
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseUtil deletePayment(@PathVariable String id){
        paymentService.delete(id);
        return new ResponseUtil(201,"Payment Deleted Successfully",null);
    }
    @PutMapping(path = "/update")
    public ResponseUtil updatePayment(@RequestBody PaymentDTO paymentDTO){
        paymentService.update(paymentDTO);
        return new ResponseUtil(201,"Payment Updated Successfully",null);
    }
}
