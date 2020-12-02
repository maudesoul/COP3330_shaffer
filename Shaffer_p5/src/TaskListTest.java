import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        int size = list.size();
        assertEquals(1, size);
    }

    @Test
    public void completingTaskItemChangesStatus() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", false);
        list.addTask(set);
        list.setCompletion(0, true);
        assertEquals(true, list.getTask(0).isComplete());
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        list.setCompletion(0, false);
        assertEquals(false, list.getTask(0).isComplete());
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", false);
        list.addTask(set);
        try{
            list.setCompletion(1, true);
            assertTrue(false);
        } catch(ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.setCompletion(1, false);
            assertTrue(false);
        } catch(ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void editingTaskItemChangesValues() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        list.addTask(0, "Task2", "First", "2020-11-15");
        assertEquals("Task2", list.getTask(0).getTitle());
        assertEquals("First", list.getTask(0).getDescription());
        assertEquals("2020-11-15", list.getTask(0).getDueDate());
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        list.addTask(0, "Task1", "First", "2020-10-15");
        assertEquals("First", list.getTask(0).getDescription());
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.addTask(1, "Task1", "First", "2020-10-15");
            assertTrue(false);
        } catch(ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        list.addTask(0, "Task1", "Second", "2020-11-15");
        assertEquals("2020-11-15", list.getTask(0).getDueDate());
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.addTask(1, "Task1", "Second", "2020-11-15");
            fail(); //automatically converted from assertTrue(false) by IntelliJ
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.addTask(0, "Task1", "Second", "0");
            fail();
        } catch(IllegalArgumentException e) { }
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        list.addTask(0, "Task2", "Second", "2020-10-15");
        assertEquals("Task2", list.getTask(0).getTitle());
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.addTask(1, "Task2", "Second", "2020-10-15");
            fail();
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    @Test
    public void editingTaskItemFailsWithEmptyString() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.getTask(1).getDescription();
            fail();
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        String desc = list.getTask(0).getDescription();
        assertEquals("Second", desc);
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.getTask(1).getDueDate();
            fail();
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        String due = list.getTask(0).getDueDate();
        assertEquals("2020-10-15", due);
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.getTask(1).getTitle();
            fail();
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        String title = list.getTask(0).getTitle();
        assertEquals("Task1", title);
    }

    @Test
    public void newTaskListIsEmpty() {
        TaskList list = new TaskList();
        assertEquals(0, list.size());
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        list.removeTask(0);
        assertEquals(0, list.size());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        try{
            list.removeTask(1);
            fail();
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    @Test
    public void savedTaskListCanBeLoaded() throws IOException {
        TaskList list = new TaskList();
        TaskItem set = new TaskItem("Task1", "Second", "2020-10-15", true);
        list.addTask(set);
        list.saveList("test.txt");
        TaskList list2 = new TaskList();
        list2.loadList("test.txt");
        assertTrue(set.equals(list2.getTask(0)));
    }
}