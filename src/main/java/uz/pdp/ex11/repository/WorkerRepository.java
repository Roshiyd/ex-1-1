package uz.pdp.ex11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ex11.entity.Worker;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
    boolean existsByPhoneNumber(String phoneNumber);
    List<Worker> findAllByDepartment_Company_Id(Integer department_company_id);
}
