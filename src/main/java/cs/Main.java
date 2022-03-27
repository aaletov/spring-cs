package cs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.repos.DiagnosisRepository;
import cs.repos.PeopleRepository;
import cs.repos.WardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {

    private final DiagnosisRepository diagnosisRepository;
    private final PeopleRepository peopleRepository;
    private final WardRepository wardRepository;

    public Main(DiagnosisRepository diagnosisRepository, PeopleRepository peopleRepository, WardRepository wardRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.peopleRepository = peopleRepository;
        this.wardRepository = wardRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ArrayList<Diagnosis> diagnoses = new ArrayList<>(Arrays.asList(
                    new Diagnosis("Cancer"),
                    new Diagnosis("Tuberculosis"),
                    new Diagnosis("Pneumonia"),
                    new Diagnosis("Tonsillitis"),
                    new Diagnosis("Rhinitis")
            ));
            diagnosisRepository.saveAll(diagnoses);

            ArrayList<Ward> wards = new ArrayList<>(Arrays.asList(
                    new Ward("Ward 1", 3),
                    new Ward("Ward 2", 4),
                    new Ward("Ward 3", 5),
                    new Ward("Ward 4", 6)
            ));
            wardRepository.saveAll(wards);

            Diagnosis tonsillitis = diagnosisRepository.findDiagnosisByName("Tonsillitis");
            Diagnosis tuberculosis = diagnosisRepository.findDiagnosisByName("Tuberculosis");
            Diagnosis cancer = diagnosisRepository.findDiagnosisByName("Cancer");
            Diagnosis rhinitis = diagnosisRepository.findDiagnosisByName("Rhinitis");

            Ward ward1 = wardRepository.findWardByName("Ward 1");
            Ward ward3 = wardRepository.findWardByName("Ward 3");

            ArrayList<People> people = new ArrayList<>(Arrays.asList(
                    new People("Ivan", "Smirnov", "Eugenevich", tonsillitis, ward1),
                    new People("Sergey", "Mitrakov", "Vladivladovich", tonsillitis, ward3),
                    new People("Islam", "Ivanov", "Inalovich", tuberculosis, ward1),
                    new People("Ekaterina", "Brown", "Ekaterinovna", cancer, ward3),
                    new People("Faizullo", "Valiev", "Shakhrierovich", cancer, ward1)
            ));
            peopleRepository.saveAll(people);

            // 9

            System.out.println("Find people with tuberculosis");
            peopleRepository.findPeopleByDiagnosis(tuberculosis)
                    .forEach(System.out::println);

            System.out.println("Find people with tonsillitis");
            peopleRepository.findPeopleByDiagnosis(tonsillitis)
                    .forEach(System.out::println);

            System.out.println("Find people with rhinitis");
            peopleRepository.findPeopleByDiagnosis(rhinitis)
                    .forEach(System.out::println);

            // one-table queries update
            System.out.println("Find people in Ward 2");
            Ward ward2 = wardRepository.findWardByName("Ward 2");
            System.out.println(peopleRepository.findPeopleByWard(ward2));
            People guy = peopleRepository.findPeopleByFirstName("Islam").get();
            System.out.println("Updating table");
            peopleRepository.updateWard(guy, ward2);
            System.out.println(peopleRepository.findPeopleByWard(ward2));

            // 17

            //
            System.out.println("Find people in Ward 2");
            peopleRepository.findPeopleByWardName("Ward 2")
                    .forEach(System.out::println);
            System.out.println("Find people in Ward 4");
            peopleRepository.findPeopleByWardName("Ward 4")
                    .forEach(System.out::println);

            Ward ward4 = wardRepository.findWardByName("Ward 4");
            peopleRepository.updateWardAll(ward2, ward4);

            System.out.println("Find people in Ward 2");
            peopleRepository.findPeopleByWardName("Ward 2")
                    .forEach(System.out::println);
            System.out.println("Find people in Ward 4");
            peopleRepository.findPeopleByWardName("Ward 4")
                    .forEach(System.out::println);

            System.out.println("Count people in Ward 1");
            System.out.println(peopleRepository.countPeopleByWardName("Ward 1"));

            System.out.println("Find full wards");
            System.out.println(peopleRepository.findFullWards());

            guy = new People("Alexey", "Martynov", "Valerievich", cancer, ward1);
            peopleRepository.save(guy);
            System.out.println("Find full wards");
            System.out.println(peopleRepository.findFullWards());
        };
    }

}