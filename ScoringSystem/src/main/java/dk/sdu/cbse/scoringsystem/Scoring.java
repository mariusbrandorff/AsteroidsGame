package dk.sdu.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Scoring {

    private int scoreSum = 0;

    public static void main(String[] args) {
        SpringApplication.run(Scoring.class, args);
    }

    @GetMapping("/score")
    public String score(@RequestParam(value = "points") Integer points) {
        scoreSum += points;
        return String.valueOf(scoreSum);
    }

    @GetMapping(value = "/score/current")
    public String scoreCurrent() {
        return String.valueOf(scoreSum);
    }
}
