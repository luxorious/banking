package com.banking.dto.agreement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgreementListDTO {
    private List<AgreementDTO> agreementDTOList;
}
