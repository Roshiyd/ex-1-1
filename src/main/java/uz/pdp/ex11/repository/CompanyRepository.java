package uz.pdp.ex11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ex11.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
