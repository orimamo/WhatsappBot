import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class WorkPanel extends JPanel {

    public static final int LENGTH_FORM1 = 12;
    public static final int LENGTH_FORM2 = 10;


    public static final String FORMAT1 = "972";
    public static final String FORMAT2 = "05";

    private ImageIcon background;
    private JTextField phoneNumber, text;
    private JLabel phoneNumerText, textLabe;

    public WorkPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setRequestFocusEnabled(true);
        this.background = new ImageIcon("whatsapp2.jpeg");
        JButton start = new JButton();
        phoneNumber = new JTextField();
        text = new JTextField();

        Color color = new Color(200, 200, 200);
        start.setBackground(color);
        start.setVisible(true);
        this.add(start);
        start.setBounds(320, 240, 100, 50);
        phoneNumber.setBounds(400, 150, 150, 50);
        phoneNumber.setVisible(true);
        this.add(phoneNumber);
        text.setBounds(200, 150, 150, 50);
        text.setVisible(true);
        this.add(text);


        start.addActionListener((event) -> {
            if (phoneNumber.getText().isEmpty() || text.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "One or more text box is empty",
                        "",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!validPhoneForm(phoneNumber.getText())) {
                JOptionPane.showMessageDialog(null,
                        "wrong number",
                        "phone value missing",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                actionPerformed(event);
            }


        });


    }

    private void actionPerformed(ActionEvent event) {
        System.setProperty("webdriver.chrome.driver", "/Users/ranhazan/Downloads/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("C:\\Users\\mori8\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://web.whatsapp.com/");
        driver.manage().window().maximize();
        boolean isConnected = false;
        while (!isConnected) {
            List<WebElement> elementAfterConnection = driver.findElements(By.id("side"));
            if (!elementAfterConnection.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "you are in",
                        "connection succses",
                        JOptionPane.INFORMATION_MESSAGE);
                isConnected = true;
            }
        }
        afterConnection(isConnected, driver);
    }

    public void afterConnection (boolean isConnected, ChromeDriver driver) {
        if (isConnected){
            if (phoneNumber.getText().substring(0,2).equals(FORMAT2)){
                driver.navigate().to("https://web.whatsapp.com/send?phone=972" +
                        phoneNumber.getText().substring(1, phoneNumber.getText().length()));

            }
            else {
                driver.navigate().to("https://web.whatsapp.com/send?phone=" + phoneNumber.getText());
//                WebElement element = driver.findElement(By.cssSelector("[title=\"הקלדת ההודעה\"]"));
               List <WebElement> elements = driver.findElements(By.className("_2vbn4"));
                System.out.println(elements.size());
                elements.get(0).click();
                elements.get(0).sendKeys(text.getText());
            }
        }
    }


    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.background.paintIcon(this, graphics, 0, 0);
    }

    public boolean validPhoneForm(String phoneNum) {
        boolean validPhoneNum = false;

        try {
            if (phoneNum.substring(0, 3).equals(FORMAT1) && phoneNum.length() == 12) {
                validPhoneNum = true;
            } else if (phoneNum.substring(0, 2).equals(FORMAT2) &&
                    (phoneNum.length() == LENGTH_FORM2)) {
                validPhoneNum = true;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("error");
        }
        return validPhoneNum;
    }
}