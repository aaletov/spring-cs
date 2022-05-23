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
            System.out.println("Started");
        };
    }

}