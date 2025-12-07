package tests.ui;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import tests.ui.pages.LoginPage;
import tests.ui.pages.ProductsPage;
public class LoginTests extends BaseTest {
    @Test(groups = {"smoke", "ui"})
    public void validUserShouldLoginSuccessfully() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage();
        Assert.assertEquals(productsPage.getProductsTitle(), "Products",
                "User should be navigated to Products page");
    }
    @Test(groups = {"regression", "ui"})
    public void invalidPasswordShouldShowError() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.getErrorText().contains("Username and password do not match"),
                "Proper error message should be shown");
    }
    @Test(groups = {"regression", "ui"})
    public void addItemToCartShouldUpdateBadge() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage();
        productsPage.addFirstItemToCart();
        productsPage.openCart();
        Assert.assertEquals(productsPage.getCartCount(), "1",
                "Cart badge should show 1 item");
    }
}
