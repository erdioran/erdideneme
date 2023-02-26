package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import supplementler.base.DriverManager;

import java.time.Duration;
import java.util.HashMap;

import static base.AutomationMethods.*;
import static base.ProjectMethods.*;
import static objectRepository.AdminOR.*;
import static objectRepository.BasketOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.UserInfoOR.*;
import static supplementler.utils.Helper.sleepInSeconds;

public class StepDefinitionsUserInfo {
    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsUserInfo.class);
    HashMap<String, String> myMap = new HashMap<>();
    @Then("üyelik bilgileri update mweb")
    public void üyelikBilgileriUpdateMweb() {
        myMap.put("ad", getValue(UYELIK_BILGILERI_ADINIZ_MWEB));
        myMap.put("soyad", getValue(UYELIK_BILGILERI_SOYADINIZ_MWEB));
        myMap.put("aktifCinsiyet", getText(UYELIK_BILGILERI_AKTIF_CINSIYET_MWEB));


        uyelikBilgileriUpadte("Erdi", "QA");



        click(UYELIK_BILGILERI_KAYDET_BUTTON);
        Assert.assertTrue(elementVisibiltyWithSize(UYELIK_BILGILERINIZ_GUNCELLENDI_MESSAGE));
        click(ALERT_TAMAM);

        Assert.assertNotEquals(getText(UYELIK_BILGILERI_ADINIZ_MWEB), myMap.get("ad"));
        Assert.assertNotEquals(getText(UYELIK_BILGILERI_SOYADINIZ_MWEB), myMap.get("soyad"));
        Assert.assertNotEquals(getText(UYELIK_BILGILERI_AKTIF_CINSIYET_MWEB), myMap.get("aktifCinsiyet"));

    }

    @And("üyelik bilgileri restore")
    public void üyelikBilgileriRestore() {
        uyelikBilgileriUpadte(myMap.get("ad"), myMap.get("soyad"));
        click(UYELIK_BILGILERI_KAYDET_BUTTON);
        Assert.assertTrue(elementVisibiltyWithSize(UYELIK_BILGILERINIZ_GUNCELLENDI_MESSAGE));
        click(ALERT_TAMAM);

        Assert.assertEquals(getText(UYELIK_BILGILERI_ADINIZ_MWEB), myMap.get("ad"));
        Assert.assertEquals(getText(UYELIK_BILGILERI_SOYADINIZ_MWEB), myMap.get("soyad"));
        Assert.assertEquals(getText(UYELIK_BILGILERI_TELEFON_MWEB), myMap.get("telefon"));
        Assert.assertEquals(getText(UYELIK_BILGILERI_AKTIF_CINSIYET_MWEB), myMap.get("aktifCinsiyet"));
    }

    @And("clear user adress - 1 address remains")
    public void clear_User_Adress() {
        clearUserAdress();
    }
}
