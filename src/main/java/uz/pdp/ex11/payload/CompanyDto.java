package uz.pdp.ex11.payload;

import lombok.Data;
import uz.pdp.ex11.entity.Address;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {
    @NotNull(message = "corpName field shouldn't be empty!")
    private String corpName;

    @NotNull(message = "directorName field shouldn't be empty!")
    private String directorName;

    @NotNull(message = "street field shouldn't be empty!")
    private String street;

    @NotNull(message = "homeNumber field shouldn't be empty!")
    private String homeNumber;
}
