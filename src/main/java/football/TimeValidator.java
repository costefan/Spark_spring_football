package football;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeValidator implements Validator {

    @Override
    public List<Integer> getInvalidRows() {
        return null;
    }
}
