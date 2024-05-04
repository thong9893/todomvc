package testcase;

import base.TestBase;
import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ToDoPage;

import java.awt.*;

public class ToDoTest extends TestBase {
    ToDoPage toDoPage;
    @BeforeClass
    void setUp(){
        Browser.open("chrome");
         toDoPage = new ToDoPage();
         toDoPage.open();
    }

    @Test
    void verifyFyUserAbleCreateToDo(){
        String taskName = "task 1";
        toDoPage.createTask(taskName);
        Assert.assertEquals(toDoPage.getItemLeft(),1);

        toDoPage.filterTask("All");
        Assert.assertTrue(toDoPage.isTaskDisplayed(taskName));

        toDoPage.filterTask("Active");
        Assert.assertTrue(toDoPage.isTaskDisplayed(taskName));

        toDoPage.filterTask("Completed");
        Assert.assertFalse(toDoPage.isTaskDisplayed(taskName));
    }

    @Test
    void verifyUserAbleMarkCompleteTask(){
        String taskName = "task 1";
        toDoPage.filterTask("All");
        toDoPage.createTask(taskName);
        int itemLeftBefore = toDoPage.getItemLeft();
        toDoPage.markComplete(taskName);

        int itemLeftAfter = toDoPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore - itemLeftAfter,1);
    }

    @Test
    void verifyUserAbleDeleteTask(){
        String taskName = "task 1";
        toDoPage.filterTask("All");
        toDoPage.createTask(taskName);

        int itemLeftBefore = toDoPage.getItemLeft();
        toDoPage.markComplete(taskName);

        toDoPage.deleteCompleted(taskName);

        int itemLeftAfter = toDoPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore - itemLeftAfter,1);
    }

    @Test
    void verifyUserAbleChangTaskName(){
        String taskName = "task 1";
        toDoPage.filterTask("All");
        toDoPage.createTask(taskName);
        int itemLeftBefore = toDoPage.getItemLeft();

        Assert.assertTrue(toDoPage.isTaskDisplayed(taskName));

        toDoPage.updateTaskName(taskName,"task 2");
        Assert.assertTrue(toDoPage.isTaskDisplayed("task 2"));
        int itemLeftAfter = toDoPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore,itemLeftAfter);
    }

}
