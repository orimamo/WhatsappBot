import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WorkPanel extends JPanel {

    public static final int LENGTH_FORM1 = 12;
    public static final int LENGTH_FORM2 = 10;
    public static final int MIDDEL_OF_WINDOWS = 400;


    public static final String FORMAT1 = "972";
    public static final String FORMAT2 = "05";

    private ImageIcon background;
    private JTextField phoneNumber;
    private JTextField text;
    private JLabel phoneNumerText, textLabe, valueLabel;
//    ,,textLabel,phoneNumberLabel

    public WorkPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setRequestFocusEnabled(true);
        this.background = new ImageIcon("whatsapp2.jpeg");
        JButton start = new JButton("start");
        phoneNumber = new JTextField();
        text = new JTextField();

        Color color = new Color(200, 200, 200);
        start.setBackground(color);
        start.setVisible(true);
        this.add(start);
        start.setBounds(MIDDEL_OF_WINDOWS - 50, 240, 100, 50);
        JLabel phoneNumberLabel = new JLabel("phone number:");
        phoneNumberLabel.setBounds(MIDDEL_OF_WINDOWS + 50, 100, 120, 50);
        this.add(phoneNumberLabel);
        phoneNumberLabel.setOpaque(true);
        phoneNumberLabel.setBackground(Color.GRAY);
        phoneNumber.setBounds(MIDDEL_OF_WINDOWS + 50, 150, 150, 50);
        phoneNumber.setVisible(true);
        this.add(phoneNumber);
        JLabel textLabel = new JLabel("message text:");
        textLabel.setBounds(MIDDEL_OF_WINDOWS - 200, 100, 120, 50);
        this.add(textLabel);
        textLabel.setOpaque(true);
        textLabel.setBackground(Color.GRAY);
        text.setBounds(MIDDEL_OF_WINDOWS - 200, 150, 150, 50);
        text.setVisible(true);
        this.add(text);
        valueLabel = new JLabel("");
        valueLabel.setBounds(MIDDEL_OF_WINDOWS - 200, 400, 120, 50);
        this.add(valueLabel);
        valueLabel.setOpaque(true);
        valueLabel.setBackground(Color.GRAY);


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
            } else {
                try {
                    actionPerformed(event);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        });


    }

    private void actionPerformed(ActionEvent event) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mori8\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("C:\\Users\\ADMIN\\AppData\\Local\\Temp\\scoped_dir11132_1172441705\\Default");
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
        afterConnection(driver);
    }

    public void copyMessage(ChromeDriver driver) {
        boolean inChat = false;
        while (!inChat) {
            List<WebElement> newWebElement = driver.findElements
                    (By.xpath
                            ("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
            if (!newWebElement.isEmpty()) {
                newWebElement.get(0).sendKeys(text.getText());
                inChat = true;
            }
        }
    }

    public void sendMessage(ChromeDriver driver) {
        boolean elementExists = false;
        while (!elementExists) {
            List<WebElement> webElements = driver.findElements
                    (By.xpath("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[2]/button"));
            if (!webElements.isEmpty()) {
                webElements.get(0).click();
                elementExists = true;
            }
        }
    }

    public boolean msgCheck(ChromeDriver driver) {
        boolean msgCheck = false;
        boolean msgWasRead = false;
        List<WebElement> lastMsg1 = lastMsg(driver);
        int sizeOfList = lastMsg1.size();
        while (!msgWasRead) {
            String value =null;
            try {
                while (value==null) {
                    WebElement webElement=lastMsg1.get(lastMsg1.size() - 1).findElement(By.cssSelector("span[data-testid='msg-dblcheck']"));
                    value = webElement.getAttribute("aria-label");
                    if (value.equals(" נמסרה ")) {
                        repaint();
                        valueLabel.setText(value);
//                        System.out.println("done1");
                    } else if (value.equals(" נשלחה ")) {
                        valueLabel.setText(value);
                        repaint();
//                        System.out.println("done2");
                    } else {
                        if (value.equals(" נקראה ")) {
                            valueLabel.setText(value);
//                            System.out.println("done3");
//                            checkIfAnswer(driver);
                            msgWasRead = true;
                        }
                    }
                }
            } catch (Exception e) {
            }


        }

        return msgWasRead;

    }
    public void checkIfAnswer(ChromeDriver driver){
        boolean result=false;
        while (!result) {
            List<WebElement> webElement = driver.findElements(By.cssSelector("div[class='Nm1g1 _22AX6']"));
            WebElement webElement1 = webElement.get(webElement.size() - 1).findElement(By.cssSelector("span"));
            String s= webElement1.getAttribute("aria-label");
            if (!s.equals("את/ה:")) {
                JOptionPane.showMessageDialog(null,
                        "new massage received",
                        "new massage received",
                        JOptionPane.INFORMATION_MESSAGE);
                String a=webElement.get(webElement.size() - 1).findElement(By.cssSelector("span[dir='rtl']")).getText();
                System.out.println(a);
                valueLabel.setText(a);
                repaint();
                driver.close();
                result=true;
            }

        }
    }

    public List<WebElement> lastMsg(ChromeDriver driver) {
        List<WebElement> lastMsgCheck = driver.findElements(By.cssSelector("div[class='Nm1g1 _22AX6']"));
        return lastMsgCheck;
    }

    public void afterConnection(ChromeDriver driver) {
        new Thread(()->{
            try {
                if (this.phoneNumber.getText().substring(0, 2).equals(FORMAT2)) {//i0jNr selectable-text copyable-text
                    driver.get("https://web.whatsapp.com/send?phone=972" +
                            this.phoneNumber.getText().substring(1, this.phoneNumber.getText().length()));//את/ה:
                    copyMessage(driver);
                    sendMessage(driver);
                    if (msgCheck(driver)) {
                        repaint();
                        checkIfAnswer(driver);
                        Thread.sleep(10000);
                    }
                } else {
                    driver.get("https://web.whatsapp.com/send?phone=" + this.phoneNumber.getText());
                    copyMessage(driver);
                    sendMessage(driver);
                    if (msgCheck(driver)){
                        repaint();
                        checkIfAnswer(driver);
                        Thread.sleep(10000);
                    }

                }

            }catch (Exception e){
                System.out.println(e);
            }
        }).start();


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