import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LogSportActivitiesTest {
    private LogSportActivities logSportActivities;

    @BeforeEach
    void setUp() {
        logSportActivities = new LogSportActivities();
    }

    @Test
    void testAddNewActivity() {
        // Adding an activity
        LogSportActivities.Activity activity = new LogSportActivities.Activity("Running", 2, LocalDateTime.now());
        logSportActivities.getActivities().add(activity);

        List<LogSportActivities.Activity> activities = logSportActivities.getActivities();

        assertEquals(1, activities.size());
        assertEquals("Running", activities.get(0).sportName);
        assertEquals(2, activities.get(0).hours);
    }

}
