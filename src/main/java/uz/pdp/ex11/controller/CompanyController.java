package uz.pdp.ex11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ex11.entity.Company;
import uz.pdp.ex11.entity.Department;
import uz.pdp.ex11.payload.CompanyDto;
import uz.pdp.ex11.payload.DepartmentDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.service.CompanyService;
import uz.pdp.ex11.service.DepartmentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Integer id){
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<Result> addCompany(@Valid @RequestBody CompanyDto companyDto){
        Result result = companyService.addCompany(companyDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editCompany(@Valid @PathVariable Integer id,@RequestBody CompanyDto companyDto){
        Result result = companyService.editCompany(id, companyDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteCompany(@PathVariable Integer id){
        Result result = companyService.deleteCompany(id);
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
