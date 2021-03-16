package uz.pdp.ex11.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "street field shouldn't be empty!")
    private String street;

    @NotNull(message = "homeNumber field shouldn't be empty!")
    private String homeNumber;
}
