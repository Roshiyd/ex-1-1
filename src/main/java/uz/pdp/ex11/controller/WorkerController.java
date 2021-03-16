package uz.pdp.ex11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ex11.entity.Company;
import uz.pdp.ex11.entity.Worker;
import uz.pdp.ex11.payload.CompanyDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.payload.WorkerDto;
import uz.pdp.ex11.service.CompanyService;
import uz.pdp.ex11.service.WorkerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> getWorkers(){
        List<Worker> workers = workerService.getWorkers();
        return ResponseEntity.ok(workers);
    }
    //Workers by company Id
    @GetMapping("/{companyId}")
    public ResponseEntity<List<Worker>> getWorkers(@PathVariable Integer companyId){
        List<Worker> workersByCompany = workerService.getWorkersByCompany(companyId);
        return ResponseEntity.ok(workersByCompany);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Integer id){
        Worker workerById = workerService.getWorkerById(id);
        return ResponseEntity.ok(workerById);
    }

    @PostMapping
    public ResponseEntity<Result> addWorker(@Valid @RequestBody WorkerDto workerDto){
        Result result = workerService.addWorker(workerDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editWorker(@Valid @PathVariable Integer id,@RequestBody WorkerDto workerDto){
        Result result = workerService.editWorker(id, workerDto);
        if (result.isSuccess()){
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteWorker(@PathVariable Integer id){
        Result result = workerService.deleteWorker(id);
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
