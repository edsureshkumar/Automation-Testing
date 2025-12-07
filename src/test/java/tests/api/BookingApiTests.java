package tests.api;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class BookingApiTests {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
    @Test(groups = {"api", "smoke"})
    public void createBookingShouldReturnId() {
        String payload = """
                {
                  "firstname" : "Suresh",
                  "lastname" : "Kumar",
                  "totalprice" : 120,
                  "depositpaid" : true,
                  "bookingdates" : {
                      "checkin" : "2025-01-01",
                      "checkout" : "2025-01-05"
                  },
                  "additionalneeds" : "Breakfast"
                }
                """;
        int bookingId =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post("/booking")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("bookingid");
        Assert.assertTrue(bookingId > 0, "Booking id should be greater than 0");
    }
}
