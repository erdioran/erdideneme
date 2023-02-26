package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static base.AutomationMethods.*;
import static base.ProjectMethods.sepetSonrasiHediyeEkraniGecMweb;
import static objectRepository.AdminOR.*;
import static objectRepository.BasketOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.UserInfoOR.*;
import static supplementler.utils.DataManager.getData;
import static supplementler.utils.Helper.sleepInSeconds;

public class StepDefinitionsLoginPage {
    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsLoginPage.class);



    @And("click UYE OLMADAN DEVAM ET button")
    public void clickUYEOLMADANDEVAMETButton() {
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON), "Üye Olmadan Devam Et buttonu görülmedi.");
        click(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON);
    }

    @And("enter {string} mail")
    public void enterMailType(String type) {
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_GIRISI_YAPARAK_DEVAM_ET_BUTTON), "Üye Girişi Yaparak Devam Et buttonu görülmedi.");
        enterText(LOGIN_PAGE_EMAIL_TEXTBOX_XPATH, getData("userData.email" + type + ""));

    }

    @Then("email belongs to a customer error")
    public void emailBelongsToACustomerError() {
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_HATA_MESAJI));
        String errorMessage = getText(LOGIN_PAGE_HATA_MESAJI);
        Assert.assertEquals(getData("errorMessage.MailUyelikMevcut"), errorMessage);
    }

    @And("click SIPARISE DEVAM ET button guest")
    public void clickSIPARISEDEVAMETButtonGuest() {
        click(LOGIN_PAGE_SIPARISE_DEVAM_ET_BUTTON_GUEST_MWEB);
    }

    @Then("guest timeout error")
    public void guestTimeoutError() {
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_HATA_MESAJI));
        String errorMessage = getText(LOGIN_PAGE_HATA_MESAJI);
        Assert.assertEquals(getData("errorMessage.GuesTokenTimeOut"), errorMessage);
    }



    @And("guest trial with registered mail")
    public void guestTrialWithRegisteredMail() {

        String urlCheck = checkStore(getTabUrl());
        click(SEPET_SEPETI_ONAYLA_BUTTON_WEB_ID_GUEST);

        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON), "Üye Olmadan Devam Et buttonu görülmedi.");
        click(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON);
        sleepInSeconds(2);
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_GIRISI_YAPARAK_DEVAM_ET_BUTTON), "Üye Girişi Yaparak Devam Et buttonu görülmedi.");
        enterText(LOGIN_PAGE_EMAIL_TEXTBOX_XPATH, getData("userData.emailCustomer"));

        click(LOGIN_PAGE_SIPARISE_DEVAM_ET_BUTTON_GUEST_WEB);

        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_HATA_MESAJI_WEB),"Yakalanan hata mesajı: "+getText(LOGIN_PAGE_HATA_MESAJI_WEB));
        String errorMessage = getText(LOGIN_PAGE_HATA_MESAJI_WEB);
        Assert.assertEquals(errorMessage,getData("errorMessage.MailUyelikMevcutWeb"));
    }

    @And("guest trial with registered mail mweb")
    public void guestTrialWithRegisteredMailMweb() {
        sepetSonrasiHediyeEkraniGecMweb();
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON), "Üye Olmadan Devam Et buttonu görülmedi.");
        click(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON);
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_GIRISI_YAPARAK_DEVAM_ET_BUTTON), "Üye Girişi Yaparak Devam Et buttonu görülmedi.");
        enterText(LOGIN_PAGE_EMAIL_TEXTBOX_XPATH, getData("userData.emailCustomer"));
        click(LOGIN_PAGE_SIPARISE_DEVAM_ET_BUTTON_GUEST_MWEB);
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_HATA_MESAJI));
        String errorMessage = getText(LOGIN_PAGE_HATA_MESAJI);
        Assert.assertEquals(errorMessage,getData("errorMessage.MailUyelikMevcut"));

    }

    @And("try member login after error")
    public void tryMemberLoginAfterError() {
        enterText(LOGIN_PAGE_SIFRE_TEXTBOX,getData("userData.passwordCustomer"));
        click(LOGIN_PAGE_GIRIS_YAP_BUTTON);
        Assert.assertEquals(getText(TOP_BAR_KULLANICI_ADI),"QA Team");
    }

    @And("try member login after error mweb")
    public void tryMemberLoginAfterErrorMweb() {
        click(LOGIN_PAGE_UYE_GIRISI_YAPARAK_DEVAM_ET_BUTTON);
        enterText(LOGIN_PAGE_SIFRE_TEXTBOX_MWEB,getData("userData.passwordCustomer"));
        click(LOGIN_PAGE_GIRIS_YAP_BUTTON_MWEB);
        click(LEFT_MENU_MWEB);
        Assert.assertTrue(elementVisibiltyWithSize(LEFT_MENU_HESABIM_MWEB));
    }


}
