package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import supplementler.base.DriverManager;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static base.AutomationMethods.*;
import static base.ProjectMethods.*;
import static base.ProjectMethods.krediKartiSecenegiFieldCheck;
import static objectRepository.AdminOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.BasketOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.UserInfoOR.*;
import static supplementler.utils.ConfigManager.getTestUrlConfig;
import static supplementler.utils.DataManager.*;
import static supplementler.utils.Helper.*;

public class StepDefinitionsBasket {

    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsBasket.class);
    SoftAssert softAssert = new SoftAssert();
    HashMap<String, String> myMap = new HashMap<>();

    String totalPriceOld;
    String totalPriceNew;


    @And("get total cart amount mweb")
    public void getTotalCartAmountMweb() {
        String totalPriceFull = getText(SEPET_TOPLAM_TUTAR_MWEB_V2);
        totalPriceOld = totalPriceFull.split(" ")[0];
        LOGGER.info("İndirim öncesi toplam tutar: " + totalPriceOld);
    }

    @When("click KUPON KODU checkbox mweb")
    public void clickKUPONKODUCheckboxMweb() {
        click(SEPET_KUPON_KODU_CHECKBOX_MWEB);
    }

    @Then("check that the {string} alert message appears mweb")
    public void checkThatTheAlertMessageAppearsMweb(String message) {
        String errorMessage = getText(SEPET_HATA_MESAJI_MWEB);
        Assert.assertEquals(errorMessage, getData("errorMessage." + message + ""));
    }


    @And("check total cart amount is same as previous step mweb")
    public void checkTotalCartAmountIsSameAsPreviousStepMweb() {
        String totalPriceFull = getText(SEPET_TOPLAM_TUTAR_MWEB_V2);
        totalPriceNew = totalPriceFull.split(" ")[0];
        Assert.assertEquals(totalPriceNew, totalPriceOld);
    }

    @And("pass adress page mweb")
    public void passAdressPageMweb() {
        click(ADRES_EKLE_PAGE_BU_ADRESI_KULLAN_BUTTON);
        enterText(ADRES_EKLE_PAGE_CEP_TELEFONU_TEXTBOX, getData("userData.phoneCustomer"));
        click(ADRES_EKLE_PAGE_KAYDET_BUTTON);
        click(ADRES_PAGE_DEVAM_ET_BUTTON);
    }

    @And("add guest address and proceed to checkout mweb")
    public void addGuestAddressAndProceedToCheckoutMweb() {

        String urlCheck = checkStore(getTabUrl());
        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);

        sepetSonrasiHediyeEkraniGecMweb();

        //Üye olmadan devam et buttonunu ardından üye girişi yaparak devam et buttonunun görüldüğünü test eder.
    //    Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON), "Üye Olmadan Devam Et buttonu görülmedi.");
        click(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON);
        sleepInSeconds(2);
     //   Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_GIRISI_YAPARAK_DEVAM_ET_BUTTON), "Üye Girişi Yaparak Devam Et buttonu görülmedi.");

        // Guest maili girer
        enterText(LOGIN_PAGE_EMAIL_TEXTBOX_XPATH, getData("userData.emailGuest"));

        click(LOGIN_PAGE_SIPARISE_DEVAM_ET_BUTTON_GUEST_MWEB);

        if (elementVisibiltyWithSize(COOKIE_BAR_CLOSE)) {
            click(COOKIE_BAR_CLOSE);
        }

        adressRandomCreateMweb(randomAdressDataGuest());
        DriverManager.getDriver().switchTo().activeElement().sendKeys(Keys.TAB); // Fitmoda otomasyon sorunu için.
        click(ADRES_EKLE_KAYDET_BUTTON);

        sepetNotu("#test#");
        DriverManager.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);


        click(ADRES_PAGE_DEVAM_ET_BUTTON_ID);

        if (urlCheck.equals("fitmoda")) {
            scrollPageToTop();
        }

    }


    @And("complete test order with guest web")
    public void completeTestOrderWithGuestWeb() {

        String urlCheck = checkStore(getTabUrl());

        click(SEPET_SEPETI_ONAYLA_BUTTON_WEB_ID_GUEST);

        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON), "Üye Olmadan Devam Et buttonu görülmedi.");
        click(LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON);
        sleepInSeconds(2);
        Assert.assertTrue(elementVisibiltyWithSize(LOGIN_PAGE_UYE_GIRISI_YAPARAK_DEVAM_ET_BUTTON), "Üye Girişi Yaparak Devam Et buttonu görülmedi.");
        enterText(LOGIN_PAGE_EMAIL_TEXTBOX_XPATH, getData("userData.emailGuest"));

        click(LOGIN_PAGE_SIPARISE_DEVAM_ET_BUTTON_GUEST_WEB);

        click(SEPET_SATIN_AL_BUTTON_WEB_ID);
        if (elementVisibiltyWithSize(SEPET_HEDIYE_SECIM_POPUP_1)) {
            click(SEPET_HEDIYE_SECIM_POPUP_1); // Supplementlerde ve vitaminlerde de çalışır
        }
        if (elementVisibiltyWithSize(SEPET_HEDIYE_STOK_UYARISI)) {
            click(SEPET_HEDIYE_STOK_UYARISI);
            click(SEPET_BOTTOM_HEDIYE_SECIMI_2);
            click(SEPET_SATIN_AL_BUTTON_WEB_ID);
            if (elementVisibiltyWithSize(SEPET_HEDIYE_STOK_UYARISI)) {
                click(SEPET_HEDIYE_STOK_UYARISI);
                click(SEPET_BOTTOM_HEDIYE_SECIMI_3);
                click(SEPET_SATIN_AL_BUTTON_WEB_ID);
            }
        }


        click(COOKIE_BAR_CLOSE);

        click(SEPET_NOTU_TEXTBOX);
        enterText(SEPET_NOTU_TEXTBOX, "#test#");
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            click(SEPET_DEVAM_ET_BUTTON);
        } else if (urlCheck.equals("fitmoda")) {
            click(SEPET_DEVAM_ET_BUTTON_FITMODA);
        }
        Assert.assertTrue(elementVisibiltyWithSize(SEPET_ODEME_ADIMINA_GECMEK_ICIN_ADRES_EKLE_UYARISI));
        click(UYARI_POPUP_TAMAM_BUTTON);

        click(ADRES_PAGE_YENI_ADRES_EKLE_BUTTON_WEB);

        String env = getTestUrlConfig();
        if (env.equals("stage") || env.equals("prod")) {
            adressCreateWeb("TESTAD", "TESTNSOYAD", "İstanbul", "Kadıköy", "1059", "TEST ADRES DETAY", "test automation", getData("userData.emailGuest"), "375778414");
        } else {
            adressCreateWeb("TESTAD", "TESTNSOYAD", "İstanbul", "Kadıköy", "1062", "TEST ADRES DETAY", "test automation", getData("userData.emailGuest"), "375778414");
        }
        DriverManager.getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

        click(ADRES_EKLE_KAYDET_BUTTON_WEB_ID);
        Assert.assertEquals(getText(ADRES_PAGE_TESLIMAT_ADRES), "test automation - TESTAD TESTNSOYAD");
        Assert.assertEquals(getText(ADRES_PAGE_FATURA_ADRES), "test automation - TESTAD TESTNSOYAD");

        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            click(SEPET_DEVAM_ET_BUTTON);
        } else if (urlCheck.equals("fitmoda")) {
            click(SEPET_DEVAM_ET_BUTTON_FITMODA);
        }

        Assert.assertFalse(elementVisibiltyWithSize(ODEME_PAGE_KREDI_KARTINI_KAYDET_FIELD));
        Assert.assertFalse(elementVisibiltyWithSize(ODEME_PAGE_KAYITLI_KARTLARIM_BUTTON));

        Assert.assertFalse(elementVisibiltyWithSize(SEPET_ODEME_ADIMI_KVKK_CHECKBOX));
        Assert.assertFalse(elementVisibiltyWithSize(SEPET_ODEME_ADIMI_KVKK_TEXT));

        click(ODEME_PAGE_HAVALE_SECENEK_WEB);
        click(ODEME_PAGE_HAVALE_BANKA_SECIM);
        click(ODEME_PAGE_HAVALE_BANKA_SECIM_GARANTI);
        click(ODEME_PAGE_SOZLESME_ONAY_CHECKBOX);
        click(SEPET_SIPARISI_TAMAMLA_BUTTON);

        Assert.assertTrue(elementVisibiltyWithSize(SIPARIS_ALINDI_SIPARISINIZ_TAMAMLANDI_TEXT));
        Assert.assertFalse(elementVisibiltyWithSize(SIPARIS_ALINDI_ODUL_POPUP_MWEB));
        Assert.assertFalse(elementVisibiltyWithSize(SIPARIS_ALINDI_PAGE_KAZANILAN_PUAN_FIELD_WEB), "Kazanılan puan alanı guest müşteri için görünmemeli.");
        Assert.assertFalse(elementVisibiltyWithSize(SIPARIS_ALINDI_SIPARISINIZI_BURADAN_TAKIP_EDEBILIRSINIZ_LINK), "Siparişinizi buradan takip edebilirsiniz. linki görüldü.Guest müşteri görmemeli.");


    }


    @Then("click DEVAM ET button in checkout")
    public void clickDEVAMETButtonInCheckout() {
        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
    }


    @And("page refresh mweb")
    public void pageRefreshMweb() {
        refreshPage();
    }

    @And("ADRESS PAGE page appears")
    public void adressPAGEPageAppears() {
        elementVisibiltyWithSize(ADRES_EKLE_PAGE_BU_ADRESI_KULLAN_BUTTON);
    }

    @And("if the gift option is seen")
    public void ifTheGiftOptionIsSeen() {
        sepetSonrasiHediyeEkraniGecMweb();
    }


    @And("SEPETIM page check")
    public void sepetimPageCheck() {
        String urlCheck = checkStore(getTabUrl());
        click(TOP_SEPET_ICON); //silinecek

        // Cookie bar görürse kapatır
        if (elementVisibilty(COOKIE_BAR_CLOSE)) {
            click(COOKIE_BAR_CLOSE);
        }


        progressBarBasketCheckMweb();

        // Ilk urunun fiyatını alır ve fiyat gösterim standartına uygunluğunu test eder
        String price = null;
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            price = getText(SEPET_FIYAT_TYPE);
        } else if (urlCheck.equals("fitmoda")) {
            price = getText(SEPET_FIYAT_TYPE);
        }
        softAssert.assertTrue(checkPriceType(price), "Fiyat type standartlarına uygun değil.Görülen fiyat: "+price);


        newIconCheck();

        //Sepet toplam tutar alanı text ve price type test eder
        softAssert.assertEquals(getText(SEPET_TOPLAM_TEXT), "Toplam");
        String toplamTutar = getText(SEPET_TOPLAM_TUTAR_MWEB_V2);
        softAssert.assertTrue(checkPriceType(toplamTutar), "Toplam tutar type standartlarına uygun değil.Görülen fiyat: "+toplamTutar);

        myMap.put("sepetToplamTutar", getText(SEPET_TOPLAM_TUTAR_MWEB_V2)); //Sepet toplam tutarı diğer steplerde karşılaştırmak için saklar


        // Bu block sepetteki summary alanının kapalı geldiğini clicklendikten sonra açıldığını test eder.
        softAssert.assertEquals(getStyleValue(SEPET_SIPARIS_OZETI_FIELD), "","Sepet summary alanı kapalı gelmedi.");
        click(SEPET_TOPLAM_TUTAR_FIELD_MWEB_V2);
        Assert.assertEquals(getStyleValue(SEPET_SIPARIS_OZETI_FIELD), "display: block;");

        //Sepet summary içeriğini kontrol eder
        softAssert.assertEquals(getText(SEPET_ARA_TOPLAM_TEXT), "aaAra Toplam");
        softAssert.assertEquals(getText(SEPET_KARGO_TEXT), "aaKargo");
        String araToplam = getText(SEPET_ARA_TOPLAM_TUTAR_MWEB_V2);
        softAssert.assertTrue(checkPriceType(araToplam), "Ara toplam tutar type standartlarına uygun değil.Görülen fiyat: "+araToplam);
        softAssert.assertTrue(getText(SEPET_KARGO_BILGI).length() > 3);

        // AH-21 kapsamında hatalı kupon kodu test eder
      //  hataliKuponKoduTest("test");

        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);

        sepetSonrasiHediyeEkraniGecMweb();

    }

    @And("ADRES page check")
    public void adresPageCheck() {
        String urlCheck = checkStore(getTabUrl());

        // Cookie bar görürse kapatır
        if (elementVisibilty(COOKIE_BAR_CLOSE)) {
            click(COOKIE_BAR_CLOSE);
        }

        progressBarBasketCheckMweb();

        //Bu method adress alanlarının görünürlük durumlarını test eder
        adresPageAdresFieldCheck();

        // Bu method yeni adres ekler ve geri dönüp eklenen adresin göründüğünü test eder
        adresPageTeslimatAdresDuzenle();

        // Farklı fatura adresi seçer, seçildiğini adres başlığı ile test eder
        faturaTeslimatAdresleriFarkliSecim();

        // Teslimat-fatura adresleri düzenle buttonu test eder
        adresPageAdresDuzenleButtonTest();

        softAssert.assertEquals(getText(ADRES_PAGE_TESLIMAT_SECIMI_TEXT), "Teslimat Seçimi");

        softAssert.assertEquals(getClassValue(ADRES_PAGE_ADRESINE_HEMEN_TESLIM_WRAPPER_HIDDEN), "shipping-column sameday-delivery","AGT wrapper açık olarak görüldü.");
        click(ADRES_PAGE_ADRESINE_HEMEN_TESLIM_WRAPPER_HIDDEN);
        softAssert.assertEquals(getClassValue(ADRES_PAGE_ADRESINE_HEMEN_TESLIM_WRAPPER_OPEN), "shipping-column sameday-delivery active","AGT wrapper açılmadı.");

        sepetNotu("#test#");

        click(ADRES_PAGE_DEVAM_ET_BUTTON_ID);

        softAssert.assertAll();
    }


    @And("ODEME KREDI KARTI page check")
    public void odemeKARTPageCheck() {
        String urlCheck = checkStore(getTabUrl());

        progressBarBasketCheckMweb();

        odemeSecenekleriSecimOpen("Kredi Kartı");

        sleepInSeconds(2);
        krediKartiSecenegiFieldCheck();

        // Form onaylanma kuralını test eder
        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
        softAssert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.formOnayla"),"Form onaylanmadan devam edilemez metni beklenenden farklı.");
        click(ALERT_TAMAM);

        click(ODEME_PAGE_SATIS_FORM_FIELD);

        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
            softAssert.assertTrue(elementVisibiltyWithSize(ODEME_PAGE_KREDI_KARTI_EKSIK_ALANLAR_HATA_MESAJI),"Kredi kartı eksik giriş hata metni görülmedi.");
            softAssert.assertEquals(getText(ODEME_PAGE_KREDI_KARTI_EKSIK_ALANLAR_HATA_MESAJI), getData("errorMessage.krediKartiEksikAlanlar"),"Kredi kartı eksik giriş hata metni beklenenden farklı.\"");
        } else if (urlCheck.equals("fitmoda")) {
            //BURADA KART ALANLARI HATA MESAJI YOK. BAKACAĞIZ
        }

        iyzitoTestKart();
        softAssert.assertEquals(getCardTypeValue(ODEME_PAGE_KREDI_KART_NO_TEXTBOX), "mastercard","Mastercard logosu görülmedi."); //MASTERCARD LOGO VISIBILITY

        // SEPET ILK ADIMI ILE BU ADIMDAKI FIYATI KARŞILAŞTIRIR
        String toplamTutar = getText(SEPET_TOPLAM_TUTAR_MWEB_V2);
        softAssert.assertEquals(toplamTutar, myMap.get("sepetToplamTutar"),"Sepet toplam tutarı, sepet adımı ile farklı.");


        // Kredi kartı bilgileri girilince, taksit seçenekleri alanlarının görüldüğünü test eder
        softAssert.assertTrue(elementVisibilty(ODEME_PAGE_KREDI_KARTI_TAKSIT_SECENEKLERI_TEXT));
        softAssert.assertTrue(elementVisibilty(ODEME_PAGE_KREDI_KARTI_TAKSIT_SECENEKLERI_FIELD),"Kredi kartı taksit seçenekleri alanı görülmedi.");
        softAssert.assertTrue(elementVisibilty(ODEME_PAGE_KREDI_KARTI_TAKSIT_SECENEKLERI_ACTIVE_TAKSIT),"Kredi kartı taksit seçenekleri, aktif taksit görülmedi.");
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            Assert.assertEquals(getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_1), getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_2),"Kredi kartı tek çekim tutarı farklı.");
        } else if (urlCheck.equals("fitmoda")) {
            Assert.assertEquals(getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_1_FITMODA), getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_2_FITMODA),"Kredi kartı tek çekim tutarı farklı.");
        }

        // Ön bilgilendirme ve mesafeli satış sözleşmelerinin açıldığını ve formun doğru olduğunu test eder.
        odemeSayfaFormsCheck();

        scrollPageToTop();
        odemeSecenekleriSecimClose("Kredi Kartı");
    }

    @And("ODEME HAVALE page check")
    public void odemeHAVALEPageCheck() {
        String urlCheck = checkStore(getTabUrl());

        progressBarBasketCheckMweb();

        odemeSecenekleriSecimOpen("Havale");

        havaleSecenegiFieldCheck();

        // Banka seçilmeden tamamlanmak istenediğinde görülecek uyarıyı test eder
        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
        softAssert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.havaleBankaSecin"),"Banka seçin uyarısı beklenenden veya görülmedi.");
        click(ALERT_TAMAM);


        // Garanti bankasını seçer
        click(ODEME_PAGE_HAVALE_BANKA_SECIM_V2);

        havaleBankaLogoCheck();  // Havale bankalarının logolarının kırık olup olmadığını test eder.

        click(ODEME_PAGE_HAVALE_BANKA_SECIM_GARANTI_V2);

        // Seçilen aktif bankanın logosunun göründüğünü test eder
        String bankaLogoImage = null;
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            bankaLogoImage = getImageUrl(ODEME_PAGE_HAVALE_BANKA_LOGO);
        } else if (urlCheck.equals("fitmoda")) {
            bankaLogoImage = getImageUrl(ODEME_PAGE_HAVALE_BANKA_LOGO_FITMODA);
        }
        softAssert.assertEquals(getBankNameFromLogoUrl(bankaLogoImage), "garanti","Seçilen banka logo url'i farklı.");

        // Garanti bankası için havale bilgilerinin doğruluğunu test eder
        havaleBilgileriCheck();

        // Form onaylanmadan tamamlanmak istendiğinde görülecek uyarıyı test eder.
        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
        Assert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.formOnayla"));
        click(ALERT_TAMAM);

        click(ODEME_PAGE_SATIS_FORM_FIELD);

        // Ön bilgilendirme ve mesafeli satış sözleşmelerinin açıldığını ve formun doğru olduğunu test eder.
        odemeSayfaFormsCheck();

        scrollPageToTop();
        odemeSecenekleriSecimClose("Havale");
    }

    @And("ODEME KAPIDA KREDI KARTI page check")
    public void odemeKAPIDAKREDIKARTIPageCheck() {
        progressBarBasketCheckMweb();

        odemeSecenekleriSecimOpen("Kapıda Kredi Kartı");

        softAssert.assertEquals(getText(ODEME_PAGE_KAPIDA_KREDI_KARTI_METIN), getData("textControl.kapidaKrediKarti"));

        // Form onaylanmadan tamamlanmak istendiğinde görülecek uyarıyı test eder.
        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
        softAssert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.formOnayla"),"Form onaylanmadan devam edilemez metni beklenenden farklı.");
        click(ALERT_TAMAM);

        click(ODEME_PAGE_SATIS_FORM_FIELD);

        // Ön bilgilendirme ve mesafeli satış sözleşmelerinin açıldığını ve formun doğru olduğunu test eder.
        odemeSayfaFormsCheck();

        scrollPageToTop();
        odemeSecenekleriSecimClose("Kapıda Kredi Kartı");

    }

    @And("ODEME KAPIDA NAKIT page check")
    public void odemeKAPIDANAKITPageCheck() {
        progressBarBasketCheckMweb();

        odemeSecenekleriSecimOpen("Kapıda Nakit");

        softAssert.assertEquals(getText(ODEME_PAGE_KAPIDA_NAKIT_METIN), getData("textControl.kapidaNakit"));

        // Form onaylanmadan tamamlanmak istendiğinde görülecek uyarıyı test eder.
        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
        softAssert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.formOnayla"),"Form onaylanmadan devam edilemez metni beklenenden farklı.");
        click(ALERT_TAMAM);

        click(ODEME_PAGE_SATIS_FORM_FIELD);

        // Ön bilgilendirme ve mesafeli satış sözleşmelerinin açıldığını ve formun doğru olduğunu test eder.
        odemeSayfaFormsCheck();

        scrollPageToTop();
        odemeSecenekleriSecimClose("Kapıda Nakit");

    }

    @And("ODEME ALISVERIS KREDISI check")
    public void odemeALISVERISKREDISICheck() {
        progressBarBasketCheckMweb();

        alisverisKredisiLimitPass();

    }

    @And("progress bar click check mweb")
    public void progressBarClickCheckMweb() {
        String urlCheck = checkStore(getTabUrl());
        if (urlCheck.equals("supplementler")||urlCheck.equals("vitaminler")){
            click(PROGRESS_BAR_ADRES);
            progressBarBasketCheckMweb();

            click(PROGRESS_BAR_SEPETIM);
            progressBarBasketCheckMweb();

            click(PROGRESS_BAR_ODEME);
            progressBarBasketCheckMweb();
        }else if (urlCheck.equals("fitmoda")){
            LOGGER.info("Fitmoda mweb için progress bar otomasyonda sayfa altında kaldığını için tıklanamadı.");
        }


    }

    @And("go to address step in basket mweb")
    public void goToAddressStepInBasketMweb() {

        click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);

        sepetSonrasiHediyeEkraniGecMweb();
    }

    @And("AGT delivery selection and go to payment step MWEB")
    public void agtDeliverySelectionGoToPaymentMWEB() {
        String urlCheck = checkStore(getTabUrl());

        if (elementVisibilty(COOKIE_BAR_CLOSE)) {
            click(COOKIE_BAR_CLOSE);
        }
        click(ADRES_PAGE_ADRESINE_HEMEN_TESLIM_WRAPPER_HIDDEN);
        Assert.assertEquals(getClassValue(ADRES_PAGE_ADRESINE_HEMEN_TESLIM_WRAPPER_OPEN), "shipping-column sameday-delivery active");
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            click(ADRES_PAGE_AGT_TESLIMAT_1);
        } else if (urlCheck.equals("fitmoda")) {
            click(ADRES_PAGE_AGT_TESLIMAT_1_FITMODA);
        }

        Assert.assertTrue(elementVisibiltyWithSize(ADRES_PAGE_AGT_TESLIMAT_INFO_MESSAGE));

        click(ADRES_PAGE_SEPET_NOTU_EKLEMEK_ISTIYORUM_TEXT_MWEB);
        Assert.assertEquals(getPlaceholderValue(SEPET_NOTU_TEXTBOX), "Mesajınızı Bu Alana Ekleyebilirsiniz");
        enterText(SEPET_NOTU_TEXTBOX, "#test#");

        click(ADRES_PAGE_DEVAM_ET_BUTTON_ID);
    }

    @And("complete AGT order by {string} transfer mweb")
    public void completeTheOrderByTransfer(String payment) {
        if (payment.equals("havale")) {
            click(ODEME_PAGE_HAVALE_SECENEK);
            sleepInSeconds(1);
            Assert.assertEquals(getDataToogleValue(ODEME_PAGE_HAVALE_SECENEK_BUTTON), "open");

            //      siparisTamamlaFormCheckBoxClick();
            click(ODEME_PAGE_SATIS_FORM_FIELD);
            click(ODEME_PAGE_HAVALE_BANKA_SECIM_V2);
            click(ODEME_PAGE_HAVALE_BANKA_SECIM_GARANTI_V2);


            click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
            sleepInSeconds(2);
            siparisAlindiPageCheck();

            click(SIPARIS_ALINDI_SIPARISINIZI_BURADAN_TAKIP_EDEBILIRSINIZ_LINK);

            Assert.assertTrue(elementVisibiltyWithSize(SIPARISLARIM_PAGE_BACK_BUTTON));

        } else if (payment.equals("kapıda")) {
            click(ODEME_PAGE_KAPIDA_KREDI_KARTI_SECENEK);
            Assert.assertTrue(elementVisibiltyWithSize(UYARI_POPUP));
            Assert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.agtKapidaKrediKarti"));
            click(ALERT_TAMAM);

            click(ODEME_PAGE_KAPIDA_NAKIT_SECENEK);
            Assert.assertTrue(elementVisibiltyWithSize(UYARI_POPUP));
            Assert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.agtKapidaNakit"));
            click(ALERT_TAMAM);

            clearBasketMweb();

        } else if (payment.equals("alışveriş kredisi")) {
            click(ODEME_PAGE_ALISVERIS_KREDISI_SECENEK);
            sleepInSeconds(1);

            alisverisKredisiLimitPass();

            if (elementVisibiltyWithSize(ODEME_PAGE_ALISVERIS_KREDISI_IMAGE)) {
                click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
                Assert.assertTrue(elementVisibiltyWithSize(ALISVERIS_KREDISI_TEST_BANK));
                backPage();
                clearBasketMweb();
            }
        }
    }


    @And("get total cart amount")
    public void getTotalCartAmount() {
        String urlCheck = checkStore(getTabUrl());
        String totalPriceFull = null;
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            totalPriceFull = getText(SEPET_TOPLAM_TUTAR_WEB);
        } else if (urlCheck.equals("fitmoda")) {
            totalPriceFull = getText(SEPET_TOPLAM_TUTAR_WEB_FITMODA);
        }

        totalPriceOld = totalPriceFull.split(" ")[0];
        LOGGER.info("İndirim öncesi toplam tutar: " + totalPriceOld);
    }

    @And("get total cart amount fitmoda")
    public void getTotalCartAmountFitmoda() {
        String totalPriceFull = getText(SEPET_TOPLAM_TUTAR_WEB_FITMODA);
        totalPriceOld = totalPriceFull.split(" ")[0];
        LOGGER.info("İndirim öncesi toplam tutar: " + totalPriceOld);
    }

    @When("click KUPON KODU checkbox")
    public void clickKUPONKODUCheckbox() {
        click(SEPET_KUPON_KODU_CHECKBOX);
    }

    @And("enter {string} in coupon code")
    public void enterInCouponCode(String couponCode) {
        enterText(SEPET_KUPON_KODU_TEXTBOX, couponCode);
        LOGGER.info("Kupon kodu olarak girildi: " + couponCode);
    }

    @And("click INDIRIMI KULLAN")
    public void clickINDIRIMIKULLAN() {
        click(SEPET_INDIRIMI_KULLAN_BUTTON);
    }

    @Then("check that the {string} alert message appears")
    public void checkThatTheAlertMessageAppears(String message) {
        String errorMessage = getText(SEPET_HATA_MESAJI);
        Assert.assertEquals(errorMessage, getData("errorMessage." + message + ""));
    }

    @And("check total cart amount is same as previous step")
    public void checkTotalCartAmountIsSameAsPreviousStep() {
        String urlCheck = checkStore(getTabUrl());
        String totalPriceFull = null;
        sleepInSeconds(2);
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            totalPriceFull = getText(SEPET_TOPLAM_TUTAR_WEB);
        } else if (urlCheck.equals("fitmoda")) {
            totalPriceFull = getText(SEPET_TOPLAM_TUTAR_WEB_FITMODA);
        }
        totalPriceNew = totalPriceFull.split(" ")[0];
        Assert.assertEquals(totalPriceNew, totalPriceOld);
    }

    @And("check total cart amount is same as previous step fitmoda")
    public void checkTotalCartAmountIsSameAsPreviousStepFitmoda() {
        String totalPriceFull = getText(SEPET_TOPLAM_TUTAR_WEB_FITMODA);
        totalPriceNew = totalPriceFull.split(" ")[0];
        Assert.assertEquals(totalPriceNew, totalPriceOld);
    }


    @And("clear basket")
    public void clearBasketAfterTest() {
        refreshPage();
        click(TOP_SEPET_BUTTON_WEB);
        clearBasket();
        int baskteItems = getListSize(SEPET_URUN_DELETE_ICON_SIZE);
        Assert.assertEquals(baskteItems, 0);
        LOGGER.info("Basket empty");
    }

    @And("clear basket mweb")
    public void clearBasketAfterTestMweb() {
        clearBasketMweb();
    }


    @And("increase the quantity of the {int} product in the cart as {int} mweb")
    public void increaseTheQuantityOfTheProductInTheCartAsMweb(int seq, int num) {
        String urlCheck = checkStore(getTabUrl());

        By element = null;
        By activeCount = null;
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            if (elementVisibiltyWithSize(By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/span"))) {
                element = By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/a[2]");
                activeCount = By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/span");
                for (int i = 1; i <= num; i++) {
                    click(element);
                }
                Assert.assertEquals(getText(activeCount), num);
                LOGGER.info("Ürün adeti: " + seq + " olarak güncellendi.");
            } else {
                LOGGER.info(seq + ". ürün hediye. Adet değiştirilemedi.");
                storeData("quantity" + seq, 1);
            }

        } else if (urlCheck.equals("fitmoda")) {
            if (elementVisibiltyWithSize(By.xpath("/html/body/div[2]/div[3]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/span"))) {
                element = By.xpath("/html/body/div[2]/div[3]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/a[2]");
                activeCount = By.xpath("/html/body/div[2]/div[3]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/span");
                for (int i = 1; i <= num; i++) {
                    click(element);
                }
                Assert.assertEquals(getText(activeCount), num);
                LOGGER.info("Ürün adeti: " + seq + " olarak güncellendi.");
            } else {
                LOGGER.info(seq + ". ürün hediye. Adet değiştirilemedi.");
                storeData("quantity" + seq, 1);
            }

        }

    }

    @And("reduce the quantity of the {int} product in the cart as {int} mweb")
    public void reduceTheQuantityOfTheProductInTheCartAsMweb(int seq, int num) {
        String urlCheck = checkStore(getTabUrl());

        By element = null;
        By activeCount = null;
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            if (elementVisibiltyWithSize(By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/span"))) {
                element = By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/a[1]");
                activeCount = By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/span");
                int activeCountSize = Integer.parseInt(getText(activeCount));
                for (int i = activeCountSize; i == num; i--) {
                    click(element);
                }
                Assert.assertEquals(getText(activeCount), num);
                LOGGER.info("Ürün adeti: " + seq + " olarak güncellendi.");
            } else {
                LOGGER.info(seq + ". ürün hediye. Adet değiştirilemedi.");
                storeData("quantity" + seq, 1);
            }

        } else if (urlCheck.equals("fitmoda")) {
            if (elementVisibiltyWithSize(By.xpath("/html/body/div[2]/div[3]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div"))) {
                element = By.xpath("/html/body/div[2]/div[3]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/a[1]");
                activeCount = By.xpath("/html/body/div[2]/div[3]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div");
                int activeCountSize = Integer.parseInt(getText(activeCount));
                for (int i = activeCountSize; i == num; i--) {
                    click(element);
                }
                Assert.assertEquals(getText(activeCount), num);
                LOGGER.info("Ürün adeti: " + seq + " olarak güncellendi.");
            } else {
                LOGGER.info(seq + ". ürün hediye. Adet değiştirilemedi.");
                storeData("quantity" + seq, 1);
            }

        }

    }

    @And("delete the {int} product from the basket by clicking the trash icon and check product deleted")
    public void deleteTheSequenceProductFromTheBasketByClickingTheTrashIcon(int seq) {
        String urlCheck = checkStore(getTabUrl());
        String prdocutName = getText(By.xpath("(//div[@class='cc-head'])[" + seq + "]/a"));
        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            click(By.xpath("//body/div[2]/div[4]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/a[1]/img"));
        } else if (urlCheck.equals("fitmoda")) {
            click(By.xpath("//body/div[2]/div[3]/div[1]/div[3]/form/div[1]/div[" + seq + "]/div[2]/div[2]/div[2]/div/div/a[1]/img"));
        }
        Assert.assertNotEquals(getText((By.xpath("(//div[@class='cc-head'])[" + seq + "]/a"))), prdocutName);
        LOGGER.info("Ürün başarıla silindi.");

    }


    @And("update the quantity of the {int} product in the cart as {int}")
    public void updateTheQuantityOfTheProductInTheCartAs(int seq, int num) {

        boolean urlCheck = checkStringContains(getTabUrl(), "fitmoda");
        By element;
        int sequence = seq * 2;
        if (!urlCheck) {
            if (elementVisibiltyWithSize(By.xpath("//body/div[9]/div/form/div/div[1]/div[2]/div[" + sequence + "]/div[3]/span/input"))) {
                element = By.xpath("//body/div[9]/div/form/div/div[1]/div[2]/div[" + sequence + "]/div[3]/span/input");
                click(element);
                DriverManager.getDriver().findElement(element).clear();
                enterText(element, String.valueOf(num));
                DriverManager.getDriver().findElement(element).sendKeys(Keys.RETURN);
                Assert.assertEquals(getProductQuantityInBasket(element), Integer.toString(seq));
                storeData("quantity" + seq, seq);
            } else {
                LOGGER.info(seq + ". ürün hediye. Adet değiştirilemedi.");
                storeData("quantity" + seq, 1);
            }

        } else {
            if (elementVisibiltyWithSize(By.xpath("/html/body/div[7]/div[3]/form/div/div[1]/div[2]/div[" + sequence + "]/div[3]/span/input"))) {
                element = By.xpath("/html/body/div[7]/div[3]/form/div/div[1]/div[2]/div[" + sequence + "]/div[3]/span/input");
                click(element);
                DriverManager.getDriver().findElement(element).clear();
                enterText(element, String.valueOf(num));
                DriverManager.getDriver().findElement(element).sendKeys(Keys.RETURN);
                Assert.assertEquals(getProductQuantityInBasket(element), Integer.toString(seq));
                storeData("quantity" + seq, seq);
            } else {
                LOGGER.info(seq + ". ürün hediye. Adet değiştirilemedi.");
                storeData("quantity" + seq, 1);
            }

        }

    }


    @And("order description field {int} product quantity badge")
    public void orderDescriptionFieldProductQuantityBadge(int seq) {
        String urlCheck = checkStore(getTabUrl());
        int num = retrieveData("quantity" + seq);

        if (num == 1) {
            if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
                String x1icon = getText(By.xpath("/html/body/div[9]/div/div[3]/div[2]/div[2]/div/div/div[1]/div/ul/li[" + seq + "]/div[1]/span"));
                Assert.assertEquals(x1icon, "x" + 1);
            } else if (urlCheck.equals("fitmoda")) {
                String x1icon = getText(By.xpath("/html/body/div[7]/div[3]/div[2]/div/div[2]/div/div/div[1]/div/ul/li[" + seq + "]/div[1]/span"));
                Assert.assertEquals(x1icon, "x" + 1);
            }

        } else {
            if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
                String xIcon = getText(By.xpath("/html/body/div[9]/div/div[3]/div[2]/div[2]/div/div/div[1]/div/ul/li[" + seq + "]/div[1]/span"));
                Assert.assertEquals(xIcon, "x" + seq);
            } else if (urlCheck.equals("fitmoda")) {
                String xIcon = getText(By.xpath("/html/body/div[7]/div[3]/div[2]/div/div[2]/div/div/div[1]/div/ul/li[" + seq + "]/div[1]/span"));
                Assert.assertEquals(xIcon, "x" + seq);
            }
        }
    }


    @And("click SEPETI ONAYLA")
    public void clickSEPETIONAYLA() {
        boolean urlCheck = checkStringContains(getTabUrl(), "m.");
        if (!urlCheck) {
            click(SEPET_SEPETI_ONAYLA_BUTTON);
        } else {
            click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB);
        }
    }


    @And("choose gift action is {string} mweb")
    public void chooseGiftActionIsMweb(String action) {
        String urlCheck = checkStore(getTabUrl());
        if (elementVisibiltyWithSize(SEPET_SONRASI_HEDIYE_SECIM_PAGE_HEDIYE_1_WEB)) {
            LOGGER.info("Hediye seçimi popupo görüldü.");
            if (action.equals("true")) {

                if (urlCheck.equals("supplementler")) {
                    click(SEPET_SONRASI_HEDIYE_SECIM_PAGE_HEDIYE_1_MWEB_SUPP);
                    LOGGER.info("1. hediye seçildi.");
                } else {
                    // click(GIFT_POPUP_IN_BASKET_PAGE_FIRST_GIFT_VITAMINLER);
                    //LOGGER.info("1. hediye seçildi.");
                }
            }
            if (elementVisibiltyWithSize(UYARI_POPUP_TAMAM_BUTTON)) {
                click(UYARI_POPUP_TAMAM_BUTTON);
                click(SEPET_SONRASI_HEDIYE_SECIM_PAGE_HEDIYE_2_MWEB_SUPP);
                click(SEPET_SEPETI_ONAYLA_BUTTON);
            }
        }
    }

    @And("choose gift popup action is {string} fitmoda")
    public void chooseGiftPopupActionIsFitmoda(String action) {

        if (elementVisibiltyWithSize(SEPET_HEDIYE_SECIM_POPUP_SIZE)) {
            LOGGER.info("Hediye seçimi popupo görüldü.");
            if (action.equals("true")) {
                // click(GIFT_POPUP_IN_BASKET_PAGE_FIRST_GIFT_SUPPLEMENTLER); EKLENMEDİ
                //LOGGER.info("1. hediye seçildi.");
            }
            if (elementVisibiltyWithSize(UYARI_POPUP_TAMAM_BUTTON)) {
                click(UYARI_POPUP_TAMAM_BUTTON);
                click(SEPET_HEDIYE_SECIM_2);
                click(SEPET_SEPETI_ONAYLA_BUTTON);
            }
        }
    }

    @When("check that the {string} is visible in order steps")
    public void checkThatElementIsVisibleIsOrderSteps(String element) {
        Assert.assertTrue(elementVisibilty(By.xpath("//a[normalize-space()='" + element + "']")));
    }

    @And("click SIPARIS OZETINI GENISLET icon")
    public void clickSIPARISOZETINIGENISLETIcon() {
        sleepInSeconds(2);
        click(ADRES_PAGE_SIPARIS_OZETI_FIELD);
    }


    @And("check that all products have a quantity symbol")
    public void checkThatAllProductsHaveAQuantitySymbol() {
        int iconSize = getListSize(ADRES_PAGE_SIPARIS_OZETI_ICON_SIZE);
        int productSize = getListSize(ADRES_PAGE_SIPARIS_OZETI_URUN_SIZE);
        Assert.assertEquals(iconSize, productSize);

    }


    @And("complete test order")
    public void completeTestOrder() {
        String urlCheck = checkStore(getTabUrl());
        click(SEPET_SEPETI_ONAYLA_BUTTON);
        if (elementVisibiltyWithSize(SEPET_HEDIYE_SECIM_POPUP_1)) {
            click(SEPET_HEDIYE_SECIM_POPUP_1); // Supplementlerde ve vitaminlerde de çalışır
        }
        if (elementVisibiltyWithSize(SEPET_HEDIYE_STOK_UYARISI)) {
            click(SEPET_HEDIYE_STOK_UYARISI);
            click(SEPET_BOTTOM_HEDIYE_SECIMI_2);
            click(SEPET_SATIN_AL_BUTTON_WEB_ID);
            if (elementVisibiltyWithSize(SEPET_HEDIYE_STOK_UYARISI)) {
                click(SEPET_HEDIYE_STOK_UYARISI);
                click(SEPET_BOTTOM_HEDIYE_SECIMI_3);
                click(SEPET_SATIN_AL_BUTTON_WEB_ID);
            }
        }

        if (urlCheck.equals("supplementler") || urlCheck.equals("fitmoda")) {
            enterText(SEPET_NOTU_TEXTBOX, "#test#");
        } else if (urlCheck.equals("vitaminler")) {
            enterText(SEPET_NOTU_TEXTBOX_CSS, "#test#");
        }

        if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
            click(SEPET_DEVAM_ET_BUTTON);
        } else if (urlCheck.equals("fitmoda")) {
            click(SEPET_DEVAM_ET_BUTTON_FITMODA);
        }

        click(ODEME_PAGE_HAVALE_SECENEK_WEB);
        click(ODEME_PAGE_HAVALE_BANKA_SECIM);
        click(ODEME_PAGE_HAVALE_BANKA_SECIM_GARANTI);
        click(ODEME_PAGE_SOZLESME_ONAY_CHECKBOX);
        click(SEPET_SIPARISI_TAMAMLA_BUTTON);
        Assert.assertTrue(elementVisibiltyWithSize(SIPARIS_ALINDI_SIPARISINIZ_TAMAMLANDI_TEXT));
    }


    @And("choose gift popup action is {string}")
    public void chooseGiftPopupActionIs(String action) {

        String urlCheck = checkStore(getTabUrl());

        if (elementVisibiltyWithSize(SEPET_HEDIYE_SECIM_POPUP_SIZE)) {
            LOGGER.info("Hediye seçimi popupı görüldü.");
            if (action.equals("true")) {

                if (urlCheck.equals("supplementler")) {
                    click(SEPET_HEDIYE_SECIM_1_SUPPLEMENTLER);
                    LOGGER.info("1. hediye seçildi.");
                } else if (urlCheck.equals("vitaminler")) {
                    click(SEPET_HEDIYE_SECIM_1_VITAMINLER);
                    LOGGER.info("1. hediye seçildi.");
                }
            }
            if (elementVisibiltyWithSize(UYARI_POPUP_TAMAM_BUTTON)) {
                LOGGER.info("Seçilen hediye için stok uyarısı alındı.");
                click(UYARI_POPUP_TAMAM_BUTTON);
                click(SEPET_HEDIYE_SECIM_2);
                click(SEPET_SEPETI_ONAYLA_BUTTON);
            }
        } else {
            LOGGER.info("Sepete hediye popupı görülmedi.");
        }

    }


    @And("complete the guest order with the {string} method")
    public void completeTheGuestOrderWithTheMethod(String payment) {
        String urlCheck = checkStore(getTabUrl());
        switch (payment) {
            case "kredi kartı":
                odemeSecenekleriSecimOpen("Kredi Kartı");

                sleepInSeconds(2);
                krediKartiSecenegiFieldCheckGuest();

                Assert.assertFalse(elementVisibiltyWithSize(ODEME_PAGE_KREDI_KARTINI_KAYDET_FIELD));

                iyzitoTestKart();
                softAssert.assertEquals(getCardTypeValue(ODEME_PAGE_KREDI_KART_NO_TEXTBOX), "mastercard","Mastercard logosu görülmedi."); //MASTERCARD LOGO VISIBILITY

                // SEPET ILK ADIMI ILE BU ADIMDAKI FIYATI KARŞILAŞTIRIR
                String toplamTutar = getText(SEPET_TOPLAM_TUTAR_MWEB_V2);
                softAssert.assertEquals(toplamTutar, myMap.get("sepetToplamTutar"),"Sepet toplam tutarı, sepet adımı ile farklı.");

                // Kredi kartı bilgileri girilince, taksit seçenekleri alanlarının görüldüğünü test eder
                softAssert.assertTrue(elementVisibilty(ODEME_PAGE_KREDI_KARTI_TAKSIT_SECENEKLERI_TEXT));
                softAssert.assertTrue(elementVisibilty(ODEME_PAGE_KREDI_KARTI_TAKSIT_SECENEKLERI_FIELD),"Kredi kartı taksit seçenekleri alanı görülmedi.");
                softAssert.assertTrue(elementVisibilty(ODEME_PAGE_KREDI_KARTI_TAKSIT_SECENEKLERI_ACTIVE_TAKSIT),"Kredi kartı taksit seçenekleri, aktif taksit görülmedi.");
                if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
                    Assert.assertEquals(getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_1), getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_2),"Kredi kartı tek çekim tutarı farklı.");
                } else if (urlCheck.equals("fitmoda")) {
                    Assert.assertEquals(getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_1_FITMODA), getText(ODEME_PAGE_KREDI_KARTI_TEK_TEKIM_TUTAR_2_FITMODA),"Kredi kartı tek çekim tutarı farklı.");
                }

                // kredi kartı ödeme tamamla eklenecek
                scrollPageToTop();

                break;
            case "havale":
                progressBarBasketCheckMweb();

                odemeSecenekleriSecimOpen("Havale");

                havaleSecenegiFieldCheck();

                click(ODEME_PAGE_HAVALE_BANKA_SECIM_V2);
                click(ODEME_PAGE_HAVALE_BANKA_SECIM_GARANTI_V2);
                sleepInSeconds(2);

                click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);
                Assert.assertEquals(getText(UYARI_POPUP), getData("uyariMessage.formOnayla"));
                click(ALERT_TAMAM);
                click(ODEME_PAGE_SATIS_FORM_FIELD);

                click(SEPET_SIPARISI_TAMAMLA_BUTTON_MWEB_V2);

                siparisAlindiPageCheckGuest();

                break;
            case "kapıda":

                break;
            case "alışveriş kredisi":

                break;
        }
    }
}