
package football.broadcasted_configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Component
public class CodesConfig implements Serializable {
    public Map<Integer, Integer> codesPlayersCountMap = new HashMap<Integer, Integer>();
    public Map<Integer, String> codesDescriptionsMap = new HashMap<>();


    @Value("${1}")
    private void setFirst(String descr) {
        codesPlayersCountMap.put(1, 1);
        codesDescriptionsMap.put(1, descr);
    }

    @Value("${2}")
    private void setSecond(String descr) {
        codesPlayersCountMap.put(2, 1);
        codesDescriptionsMap.put(2, descr);
    }

    @Value("${3}")
    private void setThird(String descr) {
        codesPlayersCountMap.put(3, 2);
        codesDescriptionsMap.put(3, descr);
    }

    @Value("${4}")
    private void setFourth(String descr) {
        codesPlayersCountMap.put(4, 2);
        codesDescriptionsMap.put(4, descr);
    }

    @Value("${5}")
    private void setFifth(String descr) {
        codesPlayersCountMap.put(5, 2);
        codesDescriptionsMap.put(5, descr);
    }
    @Value("${6}")
    private void setSixes(String descr) {
        codesPlayersCountMap.put(6, 2);
        codesDescriptionsMap.put(6, descr);
    }
    @Value("${7}")
    private void setSeventh(String descr) {
        codesPlayersCountMap.put(7, 1);
        codesDescriptionsMap.put(7, descr);
    }

    @Value("${9}")
    private void setNineth(String descr) {
        codesPlayersCountMap.put(9, 2);
        codesDescriptionsMap.put(9, descr);
    }
    @Value("${10}")
    private void setTenth(String descr) {
        codesPlayersCountMap.put(10, 1);
        codesDescriptionsMap.put(10, descr);
    }
}
