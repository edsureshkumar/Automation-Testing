package tests.ui.pages;
import framework.core.DriverFactory;
import framework.core.BasePage;
import framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ProductsPage extends BasePage {
    private final By productsTitle = By.cssSelector("span.title");
    private final By addToCartFirst = By.cssSelector(".inventory_item button");
    private final By cartIcon = By.id("shopping_cart_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    public String getProductsTitle() {
        return WaitUtils.waitForVisible(productsTitle, 10).getText();
    }
    public void addFirstItemToCart() {
        WaitUtils.waitForVisible(addToCartFirst, 10).click();
    }
    public void openCart() {
        DriverFactory.getDriver().findElement(cartIcon).click();
    }
    public String getCartCount() {
        return DriverFactory.getDriver().findElement(cartBadge).getText();
    }
}
