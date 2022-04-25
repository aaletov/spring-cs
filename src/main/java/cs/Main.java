package cs;

import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.repos.DiagnosisRepository;
import cs.repos.PeopleRepository;
import cs.repos.WardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

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

            Diagnosis tonsillitis = diagnosisRepository.findDiagnosisByName("Tonsillitis").get();
            Diagnosis tuberculosis = diagnosisRepository.findDiagnosisByName("Tuberculosis").get();
            Diagnosis cancer = diagnosisRepository.findDiagnosisByName("Cancer").get();
            Diagnosis rhinitis = diagnosisRepository.findDiagnosisByName("Rhinitis").get();

            Ward ward1 = wardRepository.findWardByName("Ward 1").get();
            Ward ward2 = wardRepository.findWardByName("Ward 2").get();
            Ward ward3 = wardRepository.findWardByName("Ward 3").get();

            ArrayList<People> people = new ArrayList<>(Arrays.asList(
                    new People("Ivan", "Smirnov", "Eugenevich", tonsillitis, ward1),
                    new People("Sergey", "Mitrakov", "Vladivladovich", tonsillitis, ward3),
                    new People("Islam", "Ivanov", "Inalovich", tuberculosis, ward1),
                    new People("Ekaterina", "Brown", "Ekaterinovna", cancer, ward3),
                    new People("Faizullo", "Valiev", "Shakhrierovich", cancer, ward1),
                    new People("Ivan", "Bochkov","Alexeevich", rhinitis, ward2),
                    new People("Nikita", "Gaevoy", "Sergeevich", cancer, ward2),
                    new People("Boris", "Zolotov", "Alexeevich", rhinitis, ward2),
                    new People("Constantin", "Kalinin", "Mihailovivch", tuberculosis, ward2)
                    ));
            peopleRepository.saveAll(people);

        };
    }

}