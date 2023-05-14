import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class NumberRangeSummarizerTest {

    private NumberRangeSummarizerImplementation summarizer;

    @BeforeEach
    public void setUp() {
        summarizer = new NumberRangeSummarizerImplementation();
    }

    @Test
    public void testCollectWithValidInput() {
        String input = "1, 3, 2, 4, 6, 5";
        Collection<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(expected, result);
    }

    @Test
    public void testCollectWithEmptyInput() {
        String input = "";
        Collection<Integer> expected = Collections.emptyList();
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(expected, result);
    }

    @Test
    public void testCollectWithNullInput() {
        String input = null;
        Collection<Integer> expected = Collections.emptyList();
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(expected, result);
    }

    @Test
    public void testCollectWithNonNumericInput() {
        String input = "1, 2, 3, abc, 5";
        assertThrows(IllegalArgumentException.class, () -> summarizer.collect(input));
    }

    @Test
    public void testSummarizeCollection() {
        Collection<Integer> input = Arrays.asList(1, 2, 3, 5, 6, 8, 9);
        String expected = "1-3, 5-6, 8-9";
        String result = summarizer.summarizeCollection(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSummarizeCollectionWithSingleNumber() {
        Collection<Integer> input = Collections.singletonList(10);
        String expected = "10";
        String result = summarizer.summarizeCollection(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSummarizeCollectionWithEmptyInput() {
        Collection<Integer> input = Collections.emptyList();
        String expected = "";
        String result = summarizer.summarizeCollection(input);
        assertEquals(expected, result);
    }
}
