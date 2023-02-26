package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Objects;

import static base.AutomationMethods.*;
import static objectRepository.AdminOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.UserInfoOR.*;
import static supplementler.utils.DataManager.getData;
import static supplementler.utils.Helper.sleepInSeconds;

public class StepDefinitionsProductDetail {

    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsProductDetail.class);
    SoftAssert softAssert = new SoftAssert();

    @Then("check {string} visibility false")
    public void checkVisibility(String element) {
        By element1 = By.id(element);
        Assert.assertFalse(elementVisibiltyWithSize(element1));
        LOGGER.info(element + " element could not be displayed on the page.");

    }

    @And("scroll down until the for sticky bar")
    public void scrollDownDetayliAciklamaOrFooterCard() {
        String urlCheck = checkStore(getTabUrl());
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            pageScroollDownToTargetElement(URUN_DETAY_DETAYLI_ACIKLAMA_FIELD);
        } else if (urlCheck.equals("fitmoda")) {
            pageScroollDownToTargetElement(URUN_DETAY_FOOTER_ORJUNAL_URUN_FIELD_FITMODA);
            sleepInSeconds(2);
        }

    }

    @And("scroll down until the FOOTER CARD field")
    public void scrollDownUntilTheFOOTERCARDField() throws InterruptedException {
        pageScroollDownToTargetElement(URUN_DETAY_FOOTER_ORJUNAL_URUN_FIELD_FITMODA);
        Thread.sleep(5000);
    }

    @And("scroll down until the MASAUSTU GORONUM field")
    public void scrollDownUsteCik() {
        pageScroollDownToTargetElement(URUN_DETAY_MASAUSTU_GORUNUM_LINK);
    }


    @Then("check sticky bar visibility {string}")
    public void checkVisibilityStickyBar(String check) {

        if (Objects.equals(check, "true")) {
            Assert.assertTrue(elementVisibiltyWithSize(STICKY_BAR_URUN_DETAY),"Sticky bar görülmedi");
            LOGGER.info("Sticky bar element could not displayed on the page.");
        } else if (Objects.equals(check, "false")) {
            Assert.assertFalse(elementVisibiltyWithSize(STICKY_BAR_URUN_DETAY),"Sticky bar görülmedi");
            LOGGER.info("Sticky bar element could displayed on the page.");
        }
    }

    @And("scroll up until the SEPETE EKLE field")
    public void scrollUpSepeteEkle() {
        pageScroollUpToTargetElement(URUN_DETAY_SEPETE_EKLE_BUTTON_ID);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("check sticky background color is {string} and sticky discount background is {string}")
    public void checkStickyBarBackground(String colorCode, String discountColor) {
        Assert.assertEquals(getData("colorCode." + colorCode + ""), getElementBackgroundColor(STICKY_BAR_URUN_DETAY));
        Assert.assertEquals(getData("colorCode." + discountColor + ""), getElementBackgroundColor(STICKY_BAR_SEPETE_EKLE_BUTTON));
        if (elementVisibilty(STICKY_BAR_INDIRIM_URUN_DETAY)) {
            Assert.assertEquals(getData("colorCode." + discountColor + ""), getElementBackgroundColor(STICKY_BAR_INDIRIM_URUN_DETAY));
        }
        LOGGER.info("Sticky Bar background color is: " + colorCode + " / Sticky Bar discount background color is: " + discountColor + " / Sticky Bar discount background color is: " + discountColor);
    }

    @And("check sticky font colors is {string}")
    public void checkStickyFontColorsIs(String fontColor) {
        Assert.assertEquals(getData("colorCode." + fontColor + ""), getElementFontColor(STICKY_BAR_SEPETE_EKLE_BUTTON));
        if (elementVisibilty(STICKY_BAR_INDIRIM_URUN_DETAY) == true) {
            Assert.assertEquals(getData("colorCode." + fontColor + ""), getElementFontColor(STICKY_BAR_INDIRIMLI_FIYAT));
            Assert.assertEquals(getData("colorCode." + fontColor + ""), getElementFontColor(STICKY_BAR_INDIRIMSIZ_FIYAT));
            Assert.assertEquals(getData("colorCode." + fontColor + ""), getElementFontColor(STICKY_BAR_INDIRIM_URUN_DETAY));
        }

        LOGGER.info("All font color is: " + fontColor);

    }



    @Then("check video location")
    public void checkVideoLocation() {

        String title = getText(URUN_DETAY_VIDEO_POSITION_FITMODA);
        LOGGER.info("video title: " + title);

        Assert.assertTrue(elementVisibiltyWithSize(URUN_DETAY_VIDEO_POSITION_FITMODA));
        Assert.assertTrue(title.length() > 10);

    }

}