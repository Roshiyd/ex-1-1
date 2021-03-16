package uz.pdp.ex11.payload;

import lombok.Data;
import uz.pdp.ex11.entity.Company;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {
    @NotNull(message = "name field shouldn't be empty!!")
    private String name;

    @NotNull(message = "companyId shouldn't be empty!!")
    private Integer companyId;
}
