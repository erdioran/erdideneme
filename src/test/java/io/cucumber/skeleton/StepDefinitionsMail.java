package io.cucumber.skeleton;

import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import supplementler.base.DriverManager;

import static base.AutomationMethods.*;
import static base.AutomationMethods.getText;
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

public class StepDefinitionsMail {
    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsMail.class);


    @Then("hotmail login with {string} user and check {string} mail")
    public void hotmailLoginWithUser(String type, String store) {
        pageLoad("https://outlook.live.com/owa/?nlp=1");
        click(HOTMAIL_EMAIL_TEXTBOX);
        enterText(HOTMAIL_EMAIL_TEXTBOX, getData("userData.email" + type + ""));
        click(HOTMAIL_EMAIL_NEXT);
        click(HOTMAIL_PASSWORD_TEXTBOX);
        enterText(HOTMAIL_PASSWORD_TEXTBOX, getData("userData.password" + type + ""));
        click(HOTMAIL_EMAIL_NEXT);
        if (elementVisibiltyWithSize(HOTMAIL_EMAIL_NEXT)) {
            click(HOTMAIL_EMAIL_NEXT);
        }
        if (elementVisibiltyWithSize(HOTMAIL_ACCEPT_BUTTON)) {
            click(HOTMAIL_ACCEPT_BUTTON);
        }
        sleepInSeconds(60);
        enterText(HOTMAIL_SEARCH_BAR, store);
        keyboardEnter();


        click(HOTMAIL_LAST_MAIL_FOR_CLICK);
        sleepInSeconds(2);

        if (!elementVisibiltyWithSize(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI)) {
            refreshPage();
        }

        Assert.assertNotEquals(getData("textControl.siparisAlindiMailGuest"),getText(By.xpath("//tbody/tr/td/p[2]")));
        Assert.assertTrue(elementVisibilty(HOTMAIL_DETAY_ICIN_TIKLAYINIZ));

        if (store.equals("supplementler")) {
            String bgColor = DriverManager.getDriver().findElement(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI).getAttribute("bgcolor");
            LOGGER.info("bgColor is: " + bgColor);
            LOGGER.info("Sipariş notu: " + getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
            Assert.assertEquals(getData("colorCode.siparisAlindiMail" + store + ""), bgColor);
            Assert.assertEquals(getData("textControl.siparisAlindiMail" + store + ""), getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
        } else if (store.equals("fitmoda")) {
            String bgColor = DriverManager.getDriver().findElement(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI).getAttribute("bgcolor");
            LOGGER.info("bgColor is: " + bgColor);
            LOGGER.info("Sipariş notu: " + getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_FITMODA));
            Assert.assertEquals(getData("colorCode.siparisAlindiMail" + store + ""), bgColor);
            Assert.assertEquals(getData("textControl.siparisAlindiMail" + store + ""), getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_FITMODA));
        } else if (store.equals("vitamiler")) {
            String bgColor = DriverManager.getDriver().findElement(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI).getAttribute("bgcolor");
            LOGGER.info("bgColor is: " + bgColor);
            LOGGER.info("Sipariş notu: " + getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
            Assert.assertEquals(getData("colorCode.siparisAlindiMail" + store + ""), bgColor);
            Assert.assertEquals(getData("textControl.siparisAlindiMail" + store + ""), getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
        }
        //deleteMail();
    }


    @Then("hotmail login with {string} user and check {string} mail - guest")
    public void hotmailLoginWithUserGuest(String type, String store) {
        refreshPage();

        pageLoad("https://outlook.live.com/owa/?nlp=1");
        click(HOTMAIL_EMAIL_TEXTBOX);
        enterText(HOTMAIL_EMAIL_TEXTBOX, getData("userData.email" + type + ""));
        click(HOTMAIL_EMAIL_NEXT);
        click(HOTMAIL_PASSWORD_TEXTBOX);
        enterText(HOTMAIL_PASSWORD_TEXTBOX, getData("userData.password" + type + ""));
        click(HOTMAIL_EMAIL_NEXT);
        click(HOTMAIL_EMAIL_NEXT);
        if (elementVisibiltyWithSize(HOTMAIL_ACCEPT_BUTTON)) {
            click(HOTMAIL_ACCEPT_BUTTON);
        }
        sleepInSeconds(60);
        enterText(HOTMAIL_SEARCH_BAR, store);
        keyboardEnter();


        click(HOTMAIL_LAST_MAIL_FOR_CLICK);
        sleepInSeconds(2);

        if (!elementVisibiltyWithSize(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI)) {
            refreshPage();
        }
        Assert.assertTrue(elementVisibiltyWithSize(By.xpath("//p[normalize-space()='Merhaba,']")));

        Assert.assertEquals(getData("textControl.siparisAlindiMailGuest"),getText(By.xpath("//tbody/tr/td/p[2]")));
        Assert.assertFalse(elementVisibiltyWithSize(HOTMAIL_DETAY_ICIN_TIKLAYINIZ));
        Assert.assertFalse(elementVisibiltyWithSize(HOTMAIL_ODUL_PUANI_LINK));
        Assert.assertFalse(elementVisibiltyWithSize(HOTMAIL_ODUL_PUANI_FIELD_IMAGE));
        Assert.assertFalse(elementVisibiltyWithSize(HOTMAIL_ODUL_PUANI_BANNER_IMAGE));


        if (store.equals("supplementler")) {
            String bgColor = DriverManager.getDriver().findElement(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI).getAttribute("bgcolor");
            LOGGER.info("bgColor is: " + bgColor);
            LOGGER.info("Sipariş notu: " + getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
            Assert.assertEquals(getData("colorCode.siparisAlindiMail" + store + ""), bgColor);
            Assert.assertEquals(getData("textControl.siparisAlindiMail" + store + ""), getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
        } else if (store.equals("fitmoda")) {
            String bgColor = DriverManager.getDriver().findElement(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI).getAttribute("bgcolor");
            LOGGER.info("bgColor is: " + bgColor);
            LOGGER.info("Sipariş notu: " + getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_FITMODA));
            Assert.assertEquals(getData("colorCode.siparisAlindiMail" + store + ""), bgColor);
            Assert.assertEquals(getData("textControl.siparisAlindiMail" + store + ""), getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_FITMODA));
        } else if (store.equals("vitamiler")) {
            String bgColor = DriverManager.getDriver().findElement(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI).getAttribute("bgcolor");
            LOGGER.info("bgColor is: " + bgColor);
            LOGGER.info("Sipariş notu: " + getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
            Assert.assertEquals(getData("colorCode.siparisAlindiMail" + store + ""), bgColor);
            Assert.assertEquals(getData("textControl.siparisAlindiMail" + store + ""), getText(HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER));
        }
        deleteMail();
    }



}
