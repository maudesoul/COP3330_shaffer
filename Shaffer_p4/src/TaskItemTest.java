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
        TaskItem invalidDay1 = new TaskItem("testTitle", "testDesc", "2020-02-31");
        TaskItem invalidDay2 = new TaskItem("testTitle", "testDesc", "2020-01-00");
        TaskItem invalidDay3 = new TaskItem("testTitle", "testDesc", "2022-05-0");
        TaskItem invalidDay4 = new TaskItem("testTitle", "testDesc", "2023-08-055");
        TaskItem invalidMonth1 = new TaskItem("testTitle", "testDesc", "2020-00-15");
        TaskItem invalidMonth2 = new TaskItem("testTitle", "testDesc", "2020-13-15");
        TaskItem invalidYear = new TaskItem("testTitle", "testDesc", "202-03-22");
        assertFalse(invalidDay1.validTaskItem());
        assertFalse(invalidDay2.validTaskItem());
        assertFalse(invalidDay3.validTaskItem());
        assertFalse(invalidDay4.validTaskItem());
        assertFalse(invalidMonth1.validTaskItem());
        assertFalse(invalidMonth2.validTaskItem());
        assertFalse(invalidYear.validTaskItem());
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        TaskItem noTitle = new TaskItem("", "", formatChecker.format(now));
        assertFalse(noTitle.validTaskItem());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem validDate = new TaskItem("testTitle", "testDesc", formatChecker.format(now));
        assertTrue(validDate.validTaskItem());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        TaskItem validTitle = new TaskItem("test Title", "testDesc", formatChecker.format(now));
        assertTrue(validTitle.validTaskItem());
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        TaskItem pastDueDate = new TaskItem("testTitle", "testDesc", formatChecker.format(now));
        assertTrue(pastDueDate.validTaskItem());
        assertFalse(pastDueDate.setDueDate("1995-04-21"));
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        TaskItem validDueDate = new TaskItem("testTitle", "testDesc", formatChecker.format(now));
        assertTrue(validDueDate.validTaskItem());
        assertTrue(validDueDate.setDueDate("2022-05-30"));
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {
        TaskItem invalidTitle = new TaskItem("testTitle", "testDesc", formatChecker.format(now));
        assertTrue(invalidTitle.validTaskItem());
        assertTrue(invalidTitle.setTitle(""));
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {
        TaskItem validTitle = new TaskItem("testTitle", "testDesc", formatChecker.format(now));
        assertTrue(validTitle.validTaskItem());
        assertTrue(validTitle.setTitle("testing title again"));
    }
}