package objectRepository;

import org.openqa.selenium.By;

public class UserInfoOR {
    public static final By UYELIK_BILGILERI_ADINIZ_MWEB = By.id("FirstName");
    public static final By UYELIK_BILGILERI_SOYADINIZ_MWEB = By.id("LastName");
    public static final By UYELIK_BILGILERI_TELEFON_MWEB = By.id("ApprovedMobilePhoneNumber");

    public static final By UYELIK_BILGILERI_AKTIF_CINSIYET_MWEB = By.xpath("(//div[@class='col-sm-6 col-xs-6 text-center border-right'])[1]/label");
    public static final By UYELIK_BILGILERI_INAKTIF_CINSIYET_MWEB = By.xpath("(//div[@class='col-sm-6 col-xs-6 text-center'])[1]/label");

    public static final By UYELIK_BILGILERI_GUN_MWEB = By.xpath("//select[@name='DateOfBirthDay']");
    public static final By UYELIK_BILGILERI_AY_MWEB = By.xpath("//select[@name='DateOfBirthMonth']");
    public static final By UYELIK_BILGILERI_YIL_MWEB = By.xpath("//select[@name='DateOfBirthYear']");
    public static final By UYELIK_BILGILERI_KVKK = By.xpath("//label[@for='KVKKPermission']");
    public static final By UYELIK_BILGILERI_KAYDET_BUTTON = By.id("save-info-button");
    public static final By UYELIK_BILGILERINIZ_GUNCELLENDI_MESSAGE = By.xpath("//div[@class='ajs-content']");
    public static final By ADRESLERIM_DELETE_ICON_2_MWEB = By.xpath("(//i[@class='fa fa-times'])[2]");
    public static final By ADRESLERIM_SIZE_MWEB = By.xpath("(//div[@class='flex'])");
}
