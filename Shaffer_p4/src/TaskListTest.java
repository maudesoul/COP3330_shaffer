import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private Date now = new Date();
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat formatChecker = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskList list = new TaskList();
        int prevSize = list.size();
        list.addTask(new TaskItem("test", "desc", formatChecker.format(now)));
        assertEquals(prevSize, list.size() -1);
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        TaskList list = new TaskList();
        int prevSize = list.size();
        list.addTask(new TaskItem("test", "desc", formatChecker.format(now)));
        list.removeTask(0);
        assertEquals(prevSize - 1, list.size());
    }

    @Test
    public void completingTaskItemChangesStatus() {
        TaskList list = new TaskList();
        boolean addSuccess = list.addTask(new TaskItem("test", "desc", formatChecker.format(now)));
        boolean originalStatus = list.getTask(0).isComplete();
        boolean setSuccess = list.setCompletion(0, TaskItem.COMPLETE);
        assertTrue(addSuccess && setSuccess);
        assertTrue(!originalStatus && list.getTask(0).isComplete());

        assertEquals(list.sizeByStatus(TaskItem.COMPLETE), 1);
        assertEquals(list.sizeByStatus(TaskItem.INCOMPLETE), 0);
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        boolean success = list.setCompletion(0, TaskItem.COMPLETE);
        assertFalse(success);
    }

    @Test
    public void editingTaskItemChangesValues() {

    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {

    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {

    }

    @Test
    public void editingTaskItemDueDateChangesValue() {

    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {

    }

    @Test
    public void editingTaskItemTitleChangesValue() {

    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {

    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {

    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {

    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {

    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {

    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {

    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {

    }

    @Test
    public void newTaskListIsEmpty() {
        assertEquals(new TaskList().size(), 0);
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        assertFalse(list.removeTask(0));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        TaskList original = new TaskList();
        TaskItem firstTask = new TaskItem("title", "desc", formatChecker.format(now));
        original.addTask(firstTask);
        boolean saveSuccess = original.saveList("loadTest");

        TaskList fromSave = new TaskList();
        boolean loadSuccess = fromSave.loadList("loadTest");

        TaskList constructorFromSave = new TaskList("loadTest");
        boolean constructorLoadSuccess = constructorFromSave.successfullyLoaded();

        assertTrue(loadSuccess && constructorLoadSuccess);
        assertTrue(fromSave.size() == 1 && fromSave.getTask(0).equals(firstTask));
        assertTrue(constructorFromSave.size() == 1 && constructorFromSave.getTask(0).equals(firstTask));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList list = new TaskList();
        TaskItem firstTask = new TaskItem("title", "desc", formatChecker.format(now));
        firstTask.setComplete(TaskItem.COMPLETE);
        list.addTask(firstTask);

        boolean success = list.setCompletion(0, TaskItem.INCOMPLETE);
        assertTrue(success && !list.getTask(0).isComplete());
        assertEquals(list.sizeByStatus(TaskItem.INCOMPLETE), 1);
        assertEquals(list.sizeByStatus(TaskItem.COMPLETE), 0);
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem firstTask = new TaskItem("title", "desc", formatChecker.format(now));
        firstTask.setComplete(TaskItem.COMPLETE);
        list.addTask(firstTask);

        boolean success = list.setCompletion(1, TaskItem.INCOMPLETE);
        assertFalse(success);
    }
}
