package uz.pdp.ex11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ex11.entity.Address;
import uz.pdp.ex11.entity.Company;
import uz.pdp.ex11.entity.Department;
import uz.pdp.ex11.entity.Worker;
import uz.pdp.ex11.payload.CompanyDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.payload.WorkerDto;
import uz.pdp.ex11.repository.AddressRepository;
import uz.pdp.ex11.repository.CompanyRepository;
import uz.pdp.ex11.repository.DepartmentRepository;
import uz.pdp.ex11.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    public List<Worker> getWorkersByCompany(Integer companyId){
        List<Worker> allByDepartment_company_id = workerRepository.findAllByDepartment_Company_Id(companyId);
        return allByDepartment_company_id;
    }

    public Result addWorker(WorkerDto workerDto) {
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber())){
            return new Result("Such phoneNumber already exist!!!",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()){
            return new Result("Such department doesn't exist!!!",false);
        }
        Worker worker=new Worker();
        Address address=new Address();
        address.setHomeNumber(workerDto.getHomeNumber());
        address.setStreet(workerDto.getStreet());
        addressRepository.save(address);
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(address);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new Result("New Worker added", true);
    }

    public Result editWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return new Result("Such worker doesn't exist", false);
        }
        if (workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(),id)) {
            return new Result("Such phone number already exists!!!",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()){
            return new Result("Such department doesn't exist!!!",false);
        }
        Worker worker = optionalWorker.get();
        Address address=new Address();
        address.setHomeNumber(workerDto.getHomeNumber());
        address.setStreet(workerDto.getStreet());
        addressRepository.save(address);
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(address);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new Result("Worker edited", true);
    }

    public Result deleteWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return new Result("Such worker doesn't exist", false);
        }
        workerRepository.deleteById(id);
        return new Result("Worker deleted", true);
    }

}
