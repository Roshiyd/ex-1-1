package uz.pdp.ex11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ex11.entity.Address;
import uz.pdp.ex11.payload.AddressDto;
import uz.pdp.ex11.payload.Result;
import uz.pdp.ex11.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public Result addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new Result("New Address added",true);
    }

    public Result editAddress(Integer id,AddressDto addressDto){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new Result("Such address doesn't exist",false);
        }
        Address address = optionalAddress.get();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new Result("Address edited",true);
    }

    public Result deleteAddress(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new Result("Such address doesn't exist",false);
        }
        addressRepository.deleteById(id);
        return new Result("Address deleted",true);

    }

}
