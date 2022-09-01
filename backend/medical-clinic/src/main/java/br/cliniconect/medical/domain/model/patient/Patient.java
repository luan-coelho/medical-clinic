package br.cliniconect.medical.domain.model.patient;

import br.cliniconect.medical.domain.model.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Patient extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "inform the name")
    private String name;
    @NotNull(message = "inform the gender")
    private Gender gender;
    @CPF(message = "Please provide a valid CPF")
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    @Past(message = "Enter a date of birth earlier than today")
    private LocalDate birthday;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> address;
}
