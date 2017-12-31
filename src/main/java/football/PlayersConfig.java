package football;

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
    public List<String> allPlayers = new ArrayList<>();

    @Value("${Russia}")
    private List<String> russianPlayers;

    @Value("${Ukraine}")
    private List<String> ukrainianPlayers;

    @Value("${Germany}")
    private List<String> germanyPlayers;


    @PostConstruct
    public void initPlayersMap() {
        playersMap.put("Russia", russianPlayers);
        playersMap.put("Ukraine", ukrainianPlayers);
        playersMap.put("Russia", germanyPlayers);
        System.out.println(russianPlayers.getClass());
        allPlayers.addAll(russianPlayers);
        allPlayers.addAll(ukrainianPlayers);
        allPlayers.addAll(germanyPlayers);
    }
}
