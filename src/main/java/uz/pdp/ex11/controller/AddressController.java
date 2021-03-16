package uz.pdp.ex11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ex11.entity.Address;
import uz.pdp.ex11.payload.AddressDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addresses = addressService.getAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id){
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Result> addAddress(@Valid @RequestBody AddressDto addressDto){
        Result result = addressService.addAddress(addressDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editAddress(@Valid @PathVariable Integer id,@RequestBody AddressDto addressDto){
        Result result = addressService.editAddress(id, addressDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteAddress(@PathVariable Integer id){
        Result result = addressService.deleteAddress(id);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
