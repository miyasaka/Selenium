package Selenium;

import java.io.File;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// import org.openqa.selenium.firefox.FirefoxDriver;
// class 

public class ChromeDriverSetup {
    private WebDriver driver;
    
    /**
     * 現在の環境に応じたchromedriverのインストールパスを取得する。
     */
    String chromeDriverPath() {
        String path;
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
            path = "chromedriver/mac/chromedriver"; // Mac環境の場合
        } else {
            path = "chromedriver/win/chromedriver.exe"; // Windows環境の場合
        }
        File file = new File(path);
        return file.getAbsolutePath();
    }
    
    /**
     * 初期処理。
     * 「@Before」をつけたメソッドは、各テストメソッドの開始前に毎回実行される。
     */
    @Before
    public WebDriver setUp() {
        // chromedriverのインストール場所を指定
        System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        
        // WebDriverのオプションを定義 2014.12.16 appended
    	ChromeOptions options = new ChromeOptions();
    	//--ignore-certificate-errorの出力を抑止
    	options.addArguments("test-type");
    	
        // WebDriverのインスタンスを生成しブラウザを起動
        driver = new ChromeDriver(options);
        // driver = new FirefoxDriver();
        return driver;
    }
    
    /**
     * 終了処理。
     * 「@After」をつけたメソッドは、各テストメソッドの終了後に毎回実行される。    
     */
    @After
    public void tearDown(WebDriver driver) {
        // ブラウザを閉じ、WebDriverを終了する
        driver.quit();
    }
}