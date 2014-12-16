package Selenium;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import BizIQ.ChromeDriverSetup;

/**
 * Selenium」
*/
public class GetFriendList {
    /**
     * メインスト処理。
     * JUnitは、「@Test」がついたメソッドをテストメソッドとして実行する。
     */
    @Test
    public void test() {
    	ChromeDriverSetup cds = new ChromeDriverSetup();
		WebDriver driver = cds.setUp();
        
        // URLを設定
        String url = "https://facebook.com/";
        String BaseURL ="https://www.facebook.com/friends.php?id=";
        System.out.println(url);
        
        // 指定したURLのウェブページに移動
        driver.get(url);
        // Assert.assertEquals(driver.getTitle(),"てすと");
        
        // 文字列入力・クリックなどの処理
        try {
            // facebook にログインする.
            WebElement userName = driver.findElement(By.id("email"));
            userName.sendKeys("isamu_0330@hotmail.co.jp");
            // test
             //System.out.println(userName.getTagName());
            // test
            WebElement password = driver.findElement(By.id("pass"));
            password.sendKeys("12345678q");
            WebElement login = driver.findElement(By.id("loginbutton"));
            login.click();
            Thread.sleep(1000);
            
            String prof_url;
            String fbid;
            int i = 0;
            
           	Actions act = new Actions(driver);
            FriendList fl = new FriendList();
            
            while ((fbid = fl.Get_FB_Account(i)) != null) {
            	prof_url = BaseURL.concat(fbid);
            	System.out.println(prof_url);
            	driver.get(prof_url);
           	
            	List<WebElement> element;
            	// 初期画面でdefault40件表示される。
            	Thread.sleep(5000);
           	  	//System.out.println(driver.findElement(By.id("pagelet_timeline_main_column")).getText());
            	//System.out.println(driver.findElement(By.className("fsl fwb fcb")).getText());
            
           		Boolean scroll_flg = true;
            	while(scroll_flg == true){            		
            		//ページダウンを押下してスクロールさせる
            		// element = driver.findElements(By.className("uiHeaderTitle"));
            		element = driver.findElements(By.cssSelector("div[class='clearfix uiHeaderTop']"));
            		for(int j=0; j < element.size();j++){
            			// System.out.println(element.get(j).getText());
                	    if(Pattern.compile("さんについてもっと見る").matcher(element.get(j).getText()).find()){
                	    	scroll_flg = false;
                	    	System.out.println("Found!! Bottom line of friends list");
                	    	break;
                	    }
            		}
            		act.sendKeys(Keys.PAGE_DOWN).perform();
            		Thread.sleep(2000);
            	}
            	i++;
            	Thread.sleep(1000);
            
            	System.out.println("Miya-1");
       
            	// System.out.println(driver.findElement(By.id("pagelet_timeline_medley_friends")).getText());
            	//友達一覧を取得　classを指定してcontentsから取得
            	element = driver.findElements(By.cssSelector("a[class='_5q6s _8o _8t lfloat _ohe']"));
            	//element.get(0).click();
            	for(int k = 0; k < element.size(); k++){
            		System.out.println(String.valueOf(k+1).concat(":").concat(element.get(k).getAttribute("href")));
            		   // driver.get(element.get(k).getAttribute("href"));
            		  // Thread.sleep(10000);
            	}
            	///System.out.println(driver.findElement(By.className("System.out.println(driver.findElement(By.className("_5q6s _8o _8t lfloat _ohe")).getText());
         	   	System.out.println("Miya-3");
            } // while
     	   	System.out.println("Miya-3-1");
            // driver.switchTo().alert().accept();
            Thread.sleep(5000); 
            // close Chrome
            cds.tearDown(driver);
        } catch (InterruptedException e) {
        	throw new RuntimeException(e);
        } // try()
    } //test()

} // class
    