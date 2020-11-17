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
        TaskList testList = new TaskList();
        int prevSize = testList.size();
        testList.addTask(new TaskItem("test", "desc", formatChecker.format(now)));
        assertEquals(prevSize, testList.size() -1);
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        TaskList testList = new TaskList();
        int prevSize = testList.size();
        testList.addTask(new TaskItem("test", "desc", formatChecker.format(now)));
        testList.removeTask(0);
        assertEquals(prevSize - 1, prevSize - 1);
    }

    @Test
    public void completingTaskItemChangesStatus() {
        TaskList testList = new TaskList();
        boolean addSuccess = testList.addTask(new TaskItem("test", "desc", formatChecker.format(now)));
        boolean originalStatus = testList.getTask(0).isComplete();
        boolean setSuccess = testList.setCompletion(0, TaskItem.COMPLETE);
        assertTrue(addSuccess && setSuccess);
        assertTrue(!originalStatus && testList.getTask(0).isComplete());

        assertEquals(testList.sizeByStatus(TaskItem.COMPLETE), 1);
        assertEquals(testList.sizeByStatus(TaskItem.INCOMPLETE), 0);
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList testList = new TaskList();
        boolean success = testList.setCompletion(0, TaskItem.COMPLETE);
        assertFalse(success);
    }

    @Test
    public void editingTaskItemChangesValues() {
        TaskItem firstTask = new TaskItem("firstTitle", "firstDesc", formatChecker.format(now));
        TaskItem newTask = new TaskItem("newTitle", "newDesc", formatChecker.format(now));
        TaskList testList = new TaskList();
        boolean addFirstTask = testList.addTask(firstTask);
        assertTrue(addFirstTask);
        TaskItem toEdit = testList.getTask(0);
        assertNotNull(toEdit);
        boolean editFirstTask = testList.addTask(newTask);
        assertTrue(editFirstTask);
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        TaskItem firstTask = new TaskItem("firstTitle", "firstDesc", formatChecker.format(now));
        TaskItem newTask = new TaskItem("firstTitle", "newDesc", formatChecker.format(now));
        TaskList testList = new TaskList();
        boolean addFirstTask = testList.addTask(firstTask);
        assertTrue(addFirstTask);
        TaskItem toEdit = testList.getTask(0);
        assertNotNull(toEdit);
        boolean editFirstTask = testList.addTask(newTask);
        assertFalse(editFirstTask);
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskItem firstTask = new TaskItem("firstTitle", "firstDesc", formatChecker.format(now));
        TaskItem newTask = new TaskItem("firstTitle", "newDesc", formatChecker.format(now));
        TaskList testList = new TaskList();
        boolean addFirstTask = testList.addTask(firstTask);
        assertTrue(addFirstTask);
        TaskItem toEdit = testList.getTask(-1);
        assertNull(toEdit);
        boolean editFirstTask = testList.addTask(newTask);
        assertFalse(editFirstTask);
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskItem firstTask = new TaskItem("firstTitle", "firstDesc", "2020-12-10");
        TaskItem newTask = new TaskItem("firstTitle", "firstDesc", "2020-12-21");
        TaskList testList = new TaskList();
        boolean addFirstTask = testList.addTask(firstTask);
        assertTrue(addFirstTask);
        TaskItem toEdit = testList.getTask(0);
        assertNotNull(toEdit);
        boolean addNewTask = testList.addTask(newTask);
        assertFalse(addNewTask);
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskItem firstTask = new TaskItem("firstTitle", "firstDesc", formatChecker.format(now));
        TaskItem newTask = new TaskItem("firstTitle", "firstDesc", formatChecker.format(now));
        TaskList testList = new TaskList();
        boolean addFirstTask = testList.addTask(firstTask);
        assertTrue(addFirstTask);
        TaskItem toEdit = testList.getTask(-1);
        assertNull(toEdit);
        boolean editFirstTask = testList.addTask(newTask);
        assertTrue(newTask.setDueDate("2020-12-21"));
        assertFalse(editFirstTask);
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        TaskItem firstTask = new TaskItem("firstTitle", "firstDesc", formatChecker.format(now));
        TaskItem newTask = new TaskItem("newTitle", "firstDesc", formatChecker.format(now));
        TaskList testList = new TaskList();
        boolean addFirstTask = testList.addTask(firstTask);
        assertTrue(addFirstTask);
        TaskItem toEdit = testList.getTask(0);
        assertNotNull(toEdit);
        boolean editFirstTask = testList.addTask(newTask);
        assertTrue(editFirstTask);
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskItem firstTask = new TaskItem("firstTitle", "firstDesc", formatChecker.format(now));
        TaskItem newTask = new TaskItem("newTitle", "firstDesc", formatChecker.format(now));
        TaskList testList = new TaskList();
        boolean addFirstTask = testList.addTask(firstTask);
        assertTrue(addFirstTask);
        TaskItem toEdit = testList.getTask(-1);
        assertNull(toEdit);
        boolean editFirstTask = testList.addTask(newTask);
        assertTrue(newTask.setDueDate("2020-12-21"));
        assertTrue(editFirstTask);
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList testList = new TaskList();
        assertNull(testList.getTask(-1));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskList testList = new TaskList();
        assertNull(testList.getTask(0));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList testList = new TaskList();
        assertNull(testList.getTask(-1));
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        TaskList testList = new TaskList();
        assertNull(testList.getTask(0));
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskList testList = new TaskList();
        assertNull(testList.getTask(-1));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        TaskList testList = new TaskList();
        assertNull(testList.getTask(0));
    }

    @Test
    public void newTaskListIsEmpty() {
        assertEquals(new TaskList().size(), 0);
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList testList = new TaskList();
        assertFalse(testList.removeTask(0));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        TaskList original = new TaskList();
        TaskItem firstTask = new TaskItem("title", "desc", formatChecker.format(now));
        original.addTask(firstTask);
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
        TaskList testList = new TaskList();
        TaskItem firstTask = new TaskItem("title", "desc", formatChecker.format(now));
        firstTask.setComplete(TaskItem.COMPLETE);
        testList.addTask(firstTask);
        boolean success = testList.setCompletion(0, TaskItem.INCOMPLETE);
        assertTrue(success && !testList.getTask(0).isComplete());
        assertEquals(testList.sizeByStatus(TaskItem.INCOMPLETE), 1);
        assertEquals(testList.sizeByStatus(TaskItem.COMPLETE), 0);
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList testList = new TaskList();
        TaskItem firstTask = new TaskItem("title", "desc", formatChecker.format(now));
        firstTask.setComplete(TaskItem.COMPLETE);
        testList.addTask(firstTask);
        boolean success = testList.setCompletion(1, TaskItem.INCOMPLETE);
        assertFalse(success);
    }
}
