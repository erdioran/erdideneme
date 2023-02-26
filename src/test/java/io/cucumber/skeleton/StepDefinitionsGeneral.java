package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static base.AutomationMethods.checkStringContains;
import static base.ProjectMethods.*;
import static objectRepository.AdminOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.UserInfoOR.*;
import static supplementler.utils.DataManager.getData;
import static supplementler.utils.Helper.sleepInSeconds;
import static supplementler.utils.UrlManager.getTestUrl;

public class StepDefinitionsGeneral {

    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsGeneral.class);

    @Given("open login page in {string}")
    public void loginPage(String base) {
        pageLoad(getTestUrl(base) + getData("url.login"));
        LOGGER.info("user is on login page " + base);
    }

    @Given("load home page in {string}")
    public void homePage(String base) {
        pageLoad(getTestUrl(base));
        LOGGER.info("user is on home page " + base);
    }

    @Given("login with {string} user")
    public void loginWithTestUser(String type) {
        login(getData("userData.email" + type + ""), getData("userData.password" + type + ""));
     //   Assert.assertEquals(getData("userData.userShortName" + type + "")"Test Test", getText(USER_SHORT_NAME));
        LOGGER.info("user is login with : " + type + " user");
    }

    @Given("login with {string} user mweb")
    public void loginWithTestUserMweb(String type) {
        loginMweb(getData("userData.email" + type + ""), getData("userData.password" + type + ""));
        String tabUrl=getTabUrl();
        Assert.assertTrue(checkStringContains(tabUrl,getData("url.mwebLogin")));
        LOGGER.info("user is login with : " + type + " user");
    }

    @And("load {string} page in {string}")
    public void loadPageWithData(String page, String base) {
        pageLoad(getTestUrl(base) + getData("url." + page + ""));
    }


    @Given("load {string} url in {string}")
    public void loadPageUrl(String url, String site) {
        pageLoad(getTestUrl(site) + getData("testUrl." + url + ""));
    }

    @Given("open admin page in {string}")
    public void openAdminPage(String base) {
        pageLoad(getTestUrl(base) + getData("url.admin"));
        LOGGER.info("Environment is: " + base);
    }

    @And("wait {int} second")
    public void waitMinMin(int second) {
        sleepInSeconds(second);
    }


    @Then("check test popup close icon visibility")
    public void checkTestPopupCloseIconVisibility() {
        elementVisibiltyWithSize(FIT_TEST_POPUP_CLOSE_ICON);
    }

    @And("check tab url is {string} in {string}")
    public void checkTabUrlIs(String url, String base) {
        String currenturl = getTabUrl();
        System.out.println("current url:" + currenturl);
        String testurl = getTestUrl(base);
        Assert.assertEquals(currenturl, testurl + url);
    }

}
