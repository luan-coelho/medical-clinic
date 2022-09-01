package br.cliniconect.medical;

import br.cliniconect.medical.util.ScriptSql;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicalClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalClinicApplication.class, args);
        ScriptSql.run("import.sql");
    }

}
