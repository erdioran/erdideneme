package objectRepository;

import org.openqa.selenium.By;

public class MailOR {
    public static final By HOTMAIL_EMAIL_TEXTBOX = By.id("i0116");
    public static final By HOTMAIL_EMAIL_NEXT = By.id("idSIButton9");
    public static final By HOTMAIL_PASSWORD_TEXTBOX = By.id("i0118");
    public static final By HOTMAIL_ACCEPT_BUTTON = By.id("id__227");
    public static final By HOTMAIL_LAST_MAIL = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]");
    public static final By HOTMAIL_DETAY_ICIN_TIKLAYINIZ = By.xpath("//a[contains(text(),'Detay için tıklayınız')]");
    public static final By HOTMAIL_ODUL_PUANI_LINK = By.xpath("//a[contains(text(),'Ödül Puanı Nedir?')]");
    public static final By HOTMAIL_ODUL_PUANI_FIELD_IMAGE = By.xpath("//img[@src='https://supplementler.mncdn.com/Themes/Supplementler/Content/images/mailing/sup-odul-puan-title.jpg']");
    public static final By HOTMAIL_ODUL_PUANI_BANNER_IMAGE = By.xpath("//img[@src='https://supplementler.mncdn.com/Themes/Supplementler/Content/images/mailing/sup-odul-banner.jpg']");
    public static final By HOTMAIL_DELETED_ITEMS = By.xpath("//div[@title='Deleted Items']");
    public static final By HOTMAIL_SEARCH_BAR = By.xpath("//input[@id='topSearchInput']");
    public static final By HOTMAIL_LAST_MAIL_FOR_CLICK = By.xpath("(//div[@aria-label='Select a conversation'])[1]");
    public static final By BU_MAIL_KAYITLI_ERROR = By.xpath("ul[id='errorSummaryList'] li");
    public static final By HOTMAIL_DELETE_LAST_MAIL = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/button[1]");
    public static final By HOTMAIL_DELETE_LAST_MAIL_2 = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/button[1]");
    public static final By HOTMAIL_DELETE_LAST_MAIL_OK = By.cssSelector("#ok-1");
    public static final By HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_FITMODA = By.xpath("(//p)[8]");
    public static final By HOTMAIL_SIPARISINIZ_ALINDI_MAIL_NOT_ALANI_SUPPLEMETNLER = By.cssSelector("tbody tr td p:nth-child(2)");
    public static final By HOTMAIL_SIPARISINIZ_ALINDI_MAIL_INDIRIM_ALANI = By.xpath("//td[contains(text(),'Sipariş Toplam:')]");

}
