import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class TaskItemTest {
    private Date now = new Date();
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat formatChecker = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {

    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {

    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {

    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {

    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {

    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {

    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {

    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {

    }
}
