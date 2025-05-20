package dk.sdu.cbse.scoringsender;

import dk.sdu.cbse.common.services.IScoreSender;
import org.springframework.web.client.RestTemplate;

public class ScoreSenderImpl implements IScoreSender {

    private final RestTemplate restTemplate;
    private static final String httpUrl = "http://localhost:8080/score?points={points}";
    private static final String getHttpUrl = "http://localhost:8080/score/current";

    public ScoreSenderImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void sendScore(int score) {
        try {
            String response = restTemplate.getForObject(httpUrl, String.class, score);
            Integer newScore = Integer.valueOf(response);
            System.out.println("new score: " + newScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getScore() {
        try {
            String response = restTemplate.getForObject(getHttpUrl, String.class);
            return Integer.valueOf(response);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
