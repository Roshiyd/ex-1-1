package uz.pdp.ex11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ex11.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
