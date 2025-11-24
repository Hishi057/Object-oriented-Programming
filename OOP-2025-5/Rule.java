import java.util.HashSet;
import java.util.Set;

public class Rule {
    private final Set<Integer> born = new HashSet<>();
    private final Set<Integer> survive  = new HashSet<>();

    public Rule(String ruleStr) {
        if (!ruleStr.startsWith("B") || !ruleStr.contains("/S")) {
            throw new IllegalArgumentException("Invalid rule format: " + ruleStr);
        }
        String[] parts = ruleStr.split("/S");
        String bornPart = parts[0].substring(1);
        String survivePart = parts[1];

        for (char c : bornPart.toCharArray()) {
            if (Character.isDigit(c)) {
                born.add(Character.getNumericValue(c));
            } else {
                throw new IllegalArgumentException("Invalid character in born part: " + c);
            }
        }

        for (char c : survivePart.toCharArray()) {
            if (Character.isDigit(c)) {
                survive.add(Character.getNumericValue(c));
            } else {
                throw new IllegalArgumentException("Invalid character in survive part: " + c);
            }
        }
    }

    public int apply(int cellStatus, int neighborCount){
        if(cellStatus == 0){
            return born.contains(neighborCount) ? 1 : 0;
        } else if(cellStatus == 1){
            return survive.contains(neighborCount) ? 1 : 0;
        }
        
        return 0;
    }
}
