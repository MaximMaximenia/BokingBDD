package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.SearchPage;


public class BookingSteps {
    SearchPage searchPage = new SearchPage();

    @Before
    public void initializeBrowser() {
        Configuration.clickViaJs = true;
        Configuration.startMaximized = true;
    }

    @Given("User open search page")
    public void userOpenSearchPage() {
        searchPage.openPage();

    }

    @When("User input {string}")
    public void userInput(String hotelName) {
        searchPage.fillHotelInput(hotelName);
    }

    @And("Press 'Search'")
    public void userDoesSearch() {
        searchPage.pressFind();
    }

    @Then("Name of hotel is {string} on page {string}")
    public void nameOfHotelIs(String expectedHotelName, String page) {

        Assert.assertTrue(searchPage.findHotelOnPage(expectedHotelName, "1"), page);

    }

    @And("Hotel {string} should have rating {string}")
    public void hotelIsOnFirstPage(String hotel, String rating) {
        Assert.assertEquals(searchPage.getHotelRating(hotel), rating);
    }

}