package uz.pdp.ex11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ex11.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
