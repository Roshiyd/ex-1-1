package uz.pdp.ex11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ex11.entity.Address;
import uz.pdp.ex11.entity.Company;
import uz.pdp.ex11.entity.Department;
import uz.pdp.ex11.payload.AddressDto;
import uz.pdp.ex11.payload.DepartmentDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.repository.AddressRepository;
import uz.pdp.ex11.repository.CompanyRepository;
import uz.pdp.ex11.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public Result addDepartment(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()){
            return new Result("Such company doesn't exist", false);
        }
        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new Result("New Department added", true);
    }

    public Result editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new Result("Such department doesn't exist", false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()){
            return new Result("Such company doesn't exist", false);
        }
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new Result("New Department edited", true);
    }

    public Result deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new Result("Such department doesn't exist", false);
        }
        departmentRepository.deleteById(id);
        return new Result("Department deleted", true);
    }

}
