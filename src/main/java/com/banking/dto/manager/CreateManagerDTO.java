package com.banking.dto.manager;

import com.banking.entity.entityenumerations.DeletedStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateManagerDTO {
    private String firstName;
    private String lastName;
    private String status;
    private String description;
    private String deletedStatus;

}
