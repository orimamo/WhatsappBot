import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.Document;
import org.w3c.dom.css.RGBColor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Set;

public class Main extends JFrame {

    public static final int HEIGHT = 600 ;
    public static final int WIDTH = 800;
    private Document document;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        WorkPanel workPanel = new WorkPanel(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(workPanel);
        this.setSize(WIDTH, HEIGHT);
    }
}
