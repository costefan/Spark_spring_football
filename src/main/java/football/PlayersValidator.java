package football;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersValidator implements Validator{

    @Value("${Russia}")
    private List<String> russianPlayers;

    @Value("${Ukraine}")
    private List<String> ukrainianPlayers;

    @Value("${Germany}")
    private List<String> germanyPlayers;

    @Override
    public List<Integer> getInvalidRows() {
        return null;
    }
}
