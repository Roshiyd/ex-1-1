package uz.pdp.ex11.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {
    @NotNull(message = "name field shouldn't be empty!")
    private String name;

    @NotNull(message = "phoneNumber field shouldn't be empty!")
    private String phoneNumber;

    @NotNull(message = "street field shouldn't be empty!")
    private String street;

    @NotNull(message = "homeNumber field shouldn't be empty!")
    private String homeNumber;

    @NotNull(message = "departmentId  shouldn't be empty!")
    private Integer departmentId;

}
