package uz.pdp.ex11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ex11.entity.Address;
import uz.pdp.ex11.entity.Department;
import uz.pdp.ex11.payload.AddressDto;
import uz.pdp.ex11.payload.DepartmentDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.service.AddressService;
import uz.pdp.ex11.service.DepartmentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments(){
        List<Department> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id){
        Department departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentById);
    }

    @PostMapping
    public ResponseEntity<Result> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        Result result = departmentService.addDepartment(departmentDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editDepartment(@Valid @PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        Result result = departmentService.editDepartment(id, departmentDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteDepartment(@PathVariable Integer id){
        Result result = departmentService.deleteDepartment(id);
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
