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


        JButton jButton = new JButton();
        Color color=new Color(200,200,200);
        jButton.setBackground(color);
        jButton.setVisible(true);
        this.add(jButton);
        jButton.setBounds(0,0,100,50);

        jButton.addActionListener((event)->{
            actionPerformed(event);
        });



    }
    private static void actionPerformed(ActionEvent event) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mori8\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("C:\\Users\\mori8\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://web.whatsapp.com/");
        driver.manage().window().maximize();
        boolean isConnected = false;
        while (!isConnected) {
            List<WebElement> sss = driver.findElements(By.id("side"));
            if (sss.isEmpty()) {
            } else {
                System.out.println("you are in");
                isConnected = true;
            }
        }
    }
    public void run(String s1,String s2){
        new Thread(()->{
            this.setFocusable(true);
            this.requestFocus();
            while (true){ //
                try {

                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }).start();

    }

}
