package br.cliniconect.medical.datafilter;

import br.cliniconect.medical.domain.model.patient.Patient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PatientFieldsFilter {

    private String name;
    private String email;
    private String cpf;

    public Specification<Patient> toSpec() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            name = validateFieldString(name);
            email = validateFieldString(email);
            cpf = validateFieldString(cpf);

            Expression<String> nameField = builder.lower(root.get("name"));
            Predicate namePredicate = builder.like(nameField, "%" + name + "%");
            predicates.add(namePredicate);

            Expression<String> emailField = builder.lower(root.get("email"));
            Predicate emailPredicate = builder.like(emailField, "%" + email + "%");
            Predicate andEmail = builder.and(emailPredicate);
            predicates.add(andEmail);

            Expression<String> cpfField = builder.lower(root.get("cpf"));
            Predicate cpfPredicate = builder.like(cpfField, "%" + cpf + "%");
            Predicate andCpf = builder.and(cpfPredicate);
            predicates.add(andCpf);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static String validateFieldString(String value) {
        return value == null ? "" : value.toLowerCase();
    }
}

