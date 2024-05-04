package pages;

import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.security.Key;

import static common.Browser.*;

public class ToDoPage {
    public void open(){
        Browser.visit("https://todomvc.com/examples/vue/dist/#/");
    }

    public void createTask(String name){
        Browser.fill(By.xpath("//input[@class='new-todo']"),String.format("%s\n",name));
    }

    public int getItemLeft(){
        if (getElement(By.xpath("//span[@class='todo-count']/strong")).isDisplayed()){
            return Integer.parseInt(Browser.getText(By.xpath("//span[@class='todo-count']/strong")));
        }else return 0;
    }

    public void markComplete(String byStatus){
        Browser.click(By.xpath(String.format("//label[.='%s']/preceding-sibling::input",byStatus)));
    }

    public void filterTask(String byStatus){
        Browser.click(By.xpath(String.format("//a[.='%s']",byStatus)));
    }

    public boolean isTaskDisplayed(String name){
        return Browser.isDisplayed(By.xpath(String.format("//label[.='%s']",name)));
    }
    public void deleteCompleted(String name){
        hover(By.xpath(String.format("//label[.='%s']",name)));
        click(By.xpath(String.format("//label[.='%s']/../button",name)));
    }

    public void updateTaskName(String oldName, String newName){
        doubleClick(By.xpath(String.format("//label[.='%s']",oldName)));
        WebElement editTaskNameTextBox = getElement(By.cssSelector(".todo-list")).findElement(By.cssSelector("input.edit"));
        excuteScript("arguments[0].value = ''",editTaskNameTextBox);
        editTaskNameTextBox.sendKeys(newName + Keys.ENTER);
    }
}
