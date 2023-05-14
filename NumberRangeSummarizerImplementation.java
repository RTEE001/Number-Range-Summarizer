import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImplementation implements NumberRangeSummarizer {
    @Override
    public Collection<Integer> collect(String input) {

        if (!isStrictlyNumbers(input)) {
            throw new IllegalArgumentException("Input contains non-numeric characters.");
        }
        List<Integer> numbers = input == null || input.isEmpty() ? Collections.emptyList() :
                List.of(input.split(","))
                        .stream()
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());
        Collections.sort(numbers);
        return numbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {

        StringBuilder summary = new StringBuilder();
        List<Integer> numbers = input.stream().sorted().collect(Collectors.toList());
        int size = numbers.size();

        for (int i = 0; i < size; i++) {

            int startRange = numbers.get(i);
            while (i < size - 1 && numbers.get(i + 1) - numbers.get(i) == 1) {
                i++;
            }

            int endRange = numbers.get(i);
            if (startRange == endRange) {
                summary.append(startRange);
            } else {
                summary.append(startRange).append("-").append(endRange);
            }
            if (i < size - 1) {
                summary.append(", ");
            }
        }
        return summary.toString();
    }

    public boolean isStrictlyNumbers(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }

        String[] numbers = input.split(",");
        for (String number : numbers) {
            String trimmedNumber = number.trim();
            if (!trimmedNumber.matches("\\d+")) {
                return false;
            }
        }
        return true;
    }
}