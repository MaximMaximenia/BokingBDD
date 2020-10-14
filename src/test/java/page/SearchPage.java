package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class SearchPage {
    public static final String ONE_HOTEL = "//div[contains(@class, 'sr_item  sr_item')]";
    public static final String GET_HOTEL_NAME = "//a[@class='js-sr-hotel-link hotel_name_link url']//span[1]";
    public static final String GET_HOTEL_RATING = "//div[@class='bui-review-score__badge']";
    public static final String SELECTING_PAGE = "//div[@class='bui-pagination results-paging ']//div[text()='%s']";
    public static final String SEARCH_BUTTON = ".sb-searchbox__button";
    public static final String SEARCH_INPUT = "#ss";
    public static final String URL = "https://www.booking.com/searchresults.en-gb.html";

    public void openPage() {
        open(URL);
    }

    public void pressFind() {
        $(SEARCH_BUTTON).click();
    }

    public void fillHotelInput(String hotelName) {
        $(SEARCH_INPUT).sendKeys(hotelName);
    }

    public ElementsCollection initializePageHotels(String page) {

        if (page.equals("1")) {
            log.info("Initialize all hotels on page:" + page + "\nSize collection :" + $$(By.xpath(ONE_HOTEL)).size());
            return $$(By.xpath(ONE_HOTEL));
        }

        $(By.xpath(String.format(SELECTING_PAGE, page))).click();
        log.info("Initialize all hotels on page:" + page + "\nSize collection :" + $$(By.xpath(ONE_HOTEL)).size());
        return $$(By.xpath(ONE_HOTEL));

    }

    public boolean findHotelOnPage(String hotelName,String page) {
        log.info("Find hotel on page : "+page+ " by name " + hotelName);
        $(".sb-date-field__display").click();
        ElementsCollection allHotels = initializePageHotels(page);
        for (SelenideElement oneHotel : allHotels) {
            if (oneHotel.findElement(By.xpath(GET_HOTEL_NAME)).getText().equals(hotelName)) {
                return true;
            }


        }
        return false;
    }

    public String getHotelRating(String hotel) {
        log.info("Find hotel on page by name " + hotel);
        log.info("Get hotel rating");
        ElementsCollection allHotels = initializePageHotels("1");

        for (SelenideElement oneHotel : allHotels) {
            if (oneHotel.findElement(By.xpath(GET_HOTEL_NAME)).getText().equals(hotel)) {
                String rating = oneHotel.findElement(By.xpath(GET_HOTEL_RATING)).getText();
                log.info("Rating this hotel:" + rating);
                return rating;
            }
            Assert.fail("Отель с таким именем не найден");
        }
        return null;
    }
}