package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import static base.ProjectMethods.*;
import static objectRepository.AdminOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.UserInfoOR.*;
import static supplementler.utils.DataManager.getData;
import static supplementler.utils.UrlManager.getTestUrl;

public class StepDefinitionsAdmin {

    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsAdmin.class);
    public String activeEnvironment;


    @When("select {string} page in top menu")
    public void selectPageInTopMenu(String categoryName) {
        clickTopMenu(categoryName);
        LOGGER.info("user is redirect to " + categoryName + " page");
    }


    @Then("{string} text is on the product list title")
    public void textIsOnTheProductListTitle(String title) {
        Assert.assertTrue(elementVisibiltyWithSize(By.xpath("//h1[normalize-space()='" + title + "']")));
    }


    @And("search {string} user in list with email")
    public void searchUserInListWithEmail(String mail) {
        enterText(SEARCH_EMAIL, mail);
        click(SEARCH_BUTTON_CUSTOMER_PAGE);
        Assert.assertTrue(elementVisibiltyWithSize(By.xpath("(//a[contains(text(),'" + mail + "')])[2]")));
        LOGGER.info("The right user listened.");
    }

    @And("click {string} in customer page")
    public void clickInCustomerPage(String button) {
        click(By.xpath("//a[normalize-space()='" + button + "']"));
        Assert.assertTrue(elementVisibiltyWithSize(MUSTERI_BILGILERI_TAB));
    }

    @And("click {string} in customer detail")
    public void clickInCustomerDetail(String tab) {
        click(By.xpath("//a[contains(text(),'" + tab + "')]"));
    }

    @When("select {string} checkbox in list")
    public void selectCheckboxInList(String roleCode) {
        click(By.xpath("//input[@value='" + roleCode + "']"));
    }

    @And("click KAYDET BUTTON in customer detail")
    public void clickKAYDETBUTTONInCustomerDetail() {
        click(KAYDET_CUSTOMER_DETAIL);
    }

    @And("customer has the role {string}")
    public void customerHasTheRole(String role) {
        String roles = getText(CUSTOMER_ROLES_TAB);
        Assert.assertTrue(checkStringContains(roles, role));

    }

    @And("remove customer {string} role and check {string}")
    public void removeCustomerRoleAncCheck(String roleCode, String role) {
        //Role kaldırma işlemini tek methodda yapar.
        click(DUZENLE_BUTTON_CUSTOMER_LIST);
        click(MUSTERI_ROLLERI_BUTTON_CUSTOMER_DETAIL);
        click(By.xpath("//input[@value='" + roleCode + "']"));
        click(KAYDET_CUSTOMER_DETAIL);
        String roles = getText(CUSTOMER_ROLES_TAB);
        Assert.assertFalse(checkStringContains(roles, role));
        LOGGER.info("The test user's role has been cleared.");
    }


    @And("Enter {string} in the AYAR ADI textbox")
    public void enterInTheAYARADITextbox(String ayar) {
        enterText(AYAR_ADI_TEXTBOX, ayar);
    }

    @And("click search button")
    public void clickSearchButton() {
        click(SEARCH_BUTTON);
    }

    @Then("check that the value is {string}")
    public void checkThatTheValueIs(String limit) {
        int size = getListSize(By.xpath("(//tbody)[3]"));
        LOGGER.info("List size is :" + size);
        Assert.assertEquals(size, 1);
        Assert.assertTrue(elementVisibiltyWithSize(By.xpath("//td[normalize-space()='"+limit+"']")));
    }


    @And("click {string} in categories page")
    public void clickInCategoriesPage(String button) {
        click(By.xpath("(//a[@class='t-button t-grid-edit'][normalize-space()='"+button+"'])[1]"));
    }

    @Then("MARKA ADI field update status is {string}")
    public void fieldUpdateStatusIs(String status) {
        if (status.equals("true")){
            Assert.assertTrue(elementVisibiltyWithSize(MARKA_FIELD_FIRST_ITEM));
            LOGGER.info("The brand name field can be updated.");
        }else if (status.equals("false")){
            Assert.assertFalse(elementVisibiltyWithSize(MARKA_FIELD_FIRST_ITEM));
            LOGGER.info("The brand name field cant be updated.");
        }

    }


    @And("check discount supplementler-nestle in {string}")
    public void checkDiscountSupplementlerNestle(String base) {
        String[] role = { "sitesettings.monthlydiscountamount", "sitesettings.nestlemonthlydiscountamount", "sitesettings.yearlydiscountamount", "sitesettings.nestleyearlydiscountamount" };
        String[] discounts = { "2500", "2500", "25000", "25000" };

        for (int i = 0; i < discounts.length; i++) {
            pageLoad(getTestUrl(base) + getData("url.TümAyarlar"));
            enterText(AYAR_ADI_TEXTBOX, role[i]);
            click(SEARCH_BUTTON);
            checkThatTheValueIs(discounts[i]);
        }

    }

    @And("check discount count supplementler-nestle in {string}")
    public void checkDiscountCountSupplementlerNestle(String base) {
        String[] role = { "sitesettings.monthlydiscountcount", "sitesettings.nestlemonthlydiscountcount", "sitesettings.yearlydiscountcount", "sitesettings.nestleyearlydiscountcount" };
        String[] discounts = { "2", "2", "10", "10" };

        for (int i = 0; i < discounts.length; i++) {
            pageLoad(getTestUrl(base) + getData("url.TümAyarlar"));
            enterText(AYAR_ADI_TEXTBOX, role[i]);
            click(SEARCH_BUTTON);
            checkThatTheValueIs(discounts[i]);
        }

    }
}
