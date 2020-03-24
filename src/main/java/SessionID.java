import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SessionID {

	public static void main(String[] args) throws InterruptedException {

		DesiredCapabilities capabilities;
		String session1, session2, url = "https://localhost";
		Cookie cookie;

		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		System.setProperty("webdriver.ie.driver", "./library/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(capabilities);

		driver.get(url);
		driver.manage().window().maximize();
		driver.navigate().to("javascript:document.getElementById('overridelink').click()");

		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("00102");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("1234");

		driver.findElement(By.xpath("//button[@title='Sign In']")).click();
		cookie = driver.manage().getCookieNamed("JSESSIONID");

		Thread.sleep(2000);
		session1 = cookie.getValue().toString();
		System.out.println(session1);

		//		String sessionId1 = ((RemoteWebDriver) driver).getSessionId().toString();
		//		System.out.println("\n SessionID is : " +sessionId1);

		driver.findElement(By.xpath("(//span[text()='Calendar'])[1]")).click();
		session2 = cookie.getValue().toString();
		System.out.println(session2);
		
		Thread.sleep(30000);

		driver.quit();
	}
}
