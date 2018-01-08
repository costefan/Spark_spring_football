package football.broadcasted_configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class PlayersConfig implements Serializable {
    public Map<String, List<String>> playersMap = new HashMap<String, List<String>>();
    public List<String> allPlayers;
    public List<String> russianPlayers;
    private List<String> ukrainianPlayers;
    private List<String> germanyPlayers;

    @Value("${Russia}")
    private void setRussianPlayers(String[] players) {
        this.russianPlayers = Arrays.stream(players).map(String::toLowerCase).collect(Collectors.toList());
    }

    @Value("${Ukraine}")
    private void setUkrainianPlayers(String[] players) {
        this.ukrainianPlayers = Arrays.stream(players).map(String::toLowerCase).collect(Collectors.toList());
    }

    @Value("${Germany}")
    private void setGermanyPlayers(String[] players) {
        this.germanyPlayers = Arrays.stream(players).map(String::toLowerCase).collect(Collectors.toList());
    }


    @PostConstruct
    public void initPlayersMap() {
        playersMap.put("Russia", russianPlayers);
        playersMap.put("Ukraine", ukrainianPlayers);
        playersMap.put("Russia", germanyPlayers);
        allPlayers = Stream.of(russianPlayers, ukrainianPlayers, germanyPlayers)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
    }
}
