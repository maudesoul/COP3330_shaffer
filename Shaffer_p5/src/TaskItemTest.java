import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskItemTest {
    private Date now = new Date();
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat formatChecker = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    @Test
    public void constructorFailsWithInvalidDueDate() {
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "202-11-01"));
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "2020-13-01"));
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "2020-00-01"));
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "2020-13-01"));
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "2020-12-0"));
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "2020-02-31"));
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "2020-11-31"));
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("Test", "TestDesc", "2019-01-01"));
    }

    @Test
    public void constructorFailsWithInvalidTitle() {
        Assertions.assertThrows(IllegalStateException.class, () -> new TaskItem("", "", formatChecker.format(now)));
    }

    @Test
    public void constructorSucceedsWithValidDueDate() {
        Assertions.assertDoesNotThrow(() -> {new TaskItem("Test", "", formatChecker.format(now));});
    }

    @Test
    public void constructorSucceedsWithValidTitle() {
        Assertions.assertDoesNotThrow(() -> new TaskItem("Title", "", formatChecker.format(now)));
    }

    @Test
    public void editingDescriptionSucceedsWithExpectedValue() {
        Assertions.assertThrows(IllegalStateException.class, () -> {TaskItem pastDate = new TaskItem("Test", "", formatChecker.format(now));
        pastDate.setDueDate("2019-12-31");
        });

        Assertions.assertThrows(IllegalStateException.class, () -> {TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
        badDate.setDueDate("201-11-30");
        });

        Assertions.assertThrows(IllegalStateException.class, () -> {TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
        badDate.setDueDate("2020-13-31");
        });

        Assertions.assertThrows(IllegalStateException.class, () -> {TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
        badDate.setDueDate("2020-01-32");
        });
    }

    @Test
    public void editingDueDateFailsWithInvalidDateFormat() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem pastDate = new TaskItem("Test", "", formatChecker.format(now));
            pastDate.setDueDate("2019-12-31");
        });

        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
            badDate.setDueDate("201-12-31");
        });

        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
            badDate.setDueDate("2020-13-31");
        });

        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
            badDate.setDueDate("2020-01-32");
        });
    }

    @Test
    public void editingDueDateFailsWithInvalidYYYMMDD() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
            badDate.setDueDate("01-30-2020");
        });
        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
            badDate.setDueDate("30-01-2020");
        });
        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
            badDate.setDueDate("01-2020-30");
        });
        Assertions.assertThrows(IllegalStateException.class, ()->{
            TaskItem badDate = new TaskItem("Test", "", formatChecker.format(now));
            badDate.setDueDate("30-2020-01");
        });
    }

    @Test
    public void editingDueDateSucceedsWithExpectedValue() {
        Assertions.assertDoesNotThrow(() -> {TaskItem validDate = new TaskItem("Test", "", formatChecker.format(now));
            validDate.setDueDate("2021-12-25");
        });
    }

    @Test
    public void editingTitleFailsWithEmptyString() {
        Assertions.assertThrows(IllegalStateException.class, () -> {TaskItem badTitle = new TaskItem("Title", "", formatChecker.format(now));
            badTitle.setTitle("");
        });
    }

    @Test
    public void editingTitleSucceedsWithExpectedValue() {
        Assertions.assertDoesNotThrow(() -> {TaskItem goodTitle = new TaskItem("Title", "", formatChecker.format(now));
            goodTitle.setTitle("Good");
        });
    }
}
