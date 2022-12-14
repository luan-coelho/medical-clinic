package br.cliniconect.medical.rest.dto.patient;

import br.cliniconect.medical.domain.model.patient.Address;
import br.cliniconect.medical.domain.model.patient.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreatePatientDTO {

    @NotBlank(message = "inform the name")
    private String name;
    @NotBlank(message = "Inform a CPF")
    @CPF(message = "Please provide a valid CPF")
    private String cpf;
    @NotBlank(message = "inform the cell phone number")
    private String cellPhone;
    @NotNull(message = "inform the gender")
    private Gender gender;
    @Email(message = "Please provide a valid email")
    private String email;
    @NotNull(message = "Enter date of birth")
    @Past(message = "Enter a date of birth earlier than today")
    private LocalDate birthday;
    private List<Address> address;
}
