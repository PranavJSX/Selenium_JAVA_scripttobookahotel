package Section08;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class L01_TravelWebsitePt1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\\\\\\\New folder\\\\\\\\Udemy\\\\\\\\selenium\\\\\\\\driver_v1\\\\\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.makemytrip.com/hotels/");
		driver.findElement(By.cssSelector(".commonModal__close")).click();
		driver.findElement(By.cssSelector("input#city")).click();
		System.out.println("Reached here1");
		functions fun = new functions();
		fun.addsleep();
		driver.findElement(By.cssSelector("input[title='Where do you want to stay?']")).sendKeys("Bhopal");
		fun.addsleep();
		List<WebElement> places_list = driver.findElements(By.cssSelector("div#react-autowhatever-1 div>ul>li>div>div>div>p>span"));
		for(WebElement places:places_list) {
			if(places.getText().contains("Bhopal")) {
				places.click();
				break;
			}
		}
		String date = fun.getDate();
		int dateofmonth = Integer.parseInt(date.split("/",2)[0]);
		System.out.println("today's day: "+dateofmonth);
		DayOfWeek dayofweek = LocalDate.now().getDayOfWeek();
		int dayofweek_inint = dayofweek.getValue();
		System.out.println("Today's day in integer : "+dayofweek_inint);
		System.out.println(dayofweek);
		int checkindate = dateofmonth + (6-dayofweek_inint);
		System.out.println("Hotel to be booked at : "+checkindate);
		int checkoutdate = checkindate+1;
		
		//setting the checkin date and checkout dates
		List<WebElement> dates_list = driver.findElements(By.cssSelector(".DayPicker-Week div"));
		int i=0;
		for(WebElement dates:dates_list) {
//			dates.getText();
			String temp = dates.getText();
			if(temp.contains(Integer.toString(checkindate))) {
				dates.click();
				i=1;
			}
			if(i==1) {
				if(temp.contains(Integer.toString(checkoutdate))) {
					dates.click();
					break;
				}
			}
		}
		fun.addsleep();
		driver.findElement(By.cssSelector(".btnApplyNew.capText.primaryBtn.pushRight")).click();
		fun.addsleep();
		driver.findElement(By.cssSelector("button#hsw_search_button")).click();
		
		//Selecting the hotel
		Scanner obj = new Scanner(System.in);
		System.out.print("Please enter the hotel name : ");
		String hotel_name = obj.nextLine();
		List<WebElement> hotels_list = driver.findElements(By.cssSelector(".infinite-scroll-component div>div>div>div>div>div>p"));
		for(WebElement elements:hotels_list) {
			if(elements.getText().contains(hotel_name)) {
				elements.click();
				break;
			}
		}
	
	}
}

class functions{
	public void addsleep() throws InterruptedException {
		Thread.sleep(1000);
	}
	public String getDate() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String todays_date = format.format(date);
		return todays_date;
	}
}


