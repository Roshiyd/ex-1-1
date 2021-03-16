package uz.pdp.ex11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ex11.entity.Address;
import uz.pdp.ex11.entity.Company;
import uz.pdp.ex11.entity.Department;
import uz.pdp.ex11.payload.CompanyDto;
import uz.pdp.ex11.payload.DepartmentDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.repository.AddressRepository;
import uz.pdp.ex11.repository.CompanyRepository;
import uz.pdp.ex11.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public Result addCompany(CompanyDto companyDto) {
        Company company=new Company();
        Address address=new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        addressRepository.save(address);
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(address);
        companyRepository.save(company);
        return new Result("New Company added", true);
    }

    public Result editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new Result("Such company doesn't exist", false);
        }
        Company company = optionalCompany.get();
        Address address=new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        addressRepository.save(address);
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(address);
        companyRepository.save(company);
        return new Result("Company edited", true);

    }

    public Result deleteCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new Result("Such company doesn't exist", false);
        }
        companyRepository.deleteById(id);
        return new Result("Company deleted", true);
    }

}
