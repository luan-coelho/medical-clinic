package br.cliniconect.medical.rest.dto.patient;

import br.cliniconect.medical.domain.model.patient.Gender;
import br.cliniconect.medical.rest.dto.address.ResponseAddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ResponsePatientDTO {

    private Long id;
    private String name;
    private String cpf;
    private String cellPhone;
    private Gender gender;
    private String email;
    private LocalDate birthday;
    private List<ResponseAddressDTO> address;
}
