package football;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodesValidator implements Validator {

    @Override
    public List<Integer> getInvalidRows() {
        return null;
    }
}
