package objectRepository;

import org.openqa.selenium.By;

public class LoginPageOR {
    public static final By LOGIN_PAGE_EMAIL_TEXTBOX = By.id("Email");
    public static final By LOGIN_PAGE_SIFRE_TEXTBOX = By.id("Password");
    public static final By LOGIN_PAGE_EMAIL_TEXTBOX_XPATH = By.xpath("//input[@name='Email']");
    public static final By LOGIN_PAGE_SIFRE_TEXTBOX_MWEB = By.xpath("//input[@name='Password']");
    public static final By LOGIN_PAGE_GIRIS_YAP_BUTTON = By.xpath("//button[contains(text(),'Giriş Yap')]");
    public static final By LOGIN_PAGE_GIRIS_YAP_BUTTON_MWEB = By.id("btnLogin");
    public static final By LOGIN_PAGE_SIPARISE_DEVAM_ET_BUTTON_GUEST_MWEB = By.id("btnLogin2");
    public static final By LOGIN_PAGE_SIPARISE_DEVAM_ET_BUTTON_GUEST_WEB = By.id("btnSubmitLogin2");
    public static final By LOGIN_PAGE_UYE_OLMADAN_DEVAM_ET_BUTTON = By.xpath("//input[@value='Üye Olmadan Devam Et']");
    public static final By LOGIN_PAGE_HATA_MESAJI = By.cssSelector("ul[id='errorSummaryList'] li");
    public static final By LOGIN_PAGE_HATA_MESAJI_WEB = By.xpath("//div[@class='message-error']");
    public static final By LOGIN_PAGE_UYE_GIRISI_YAPARAK_DEVAM_ET_BUTTON = By.xpath("//input[@value='Üye Girişi Yaparak Devam Et']");
}

