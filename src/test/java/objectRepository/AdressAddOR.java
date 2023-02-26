package objectRepository;

import org.openqa.selenium.By;

public class AdressAddOR {
    public static final By ADRES_EKLE_AD = By.id("Address_FirstName");
    public static final By ADRES_EKLE_AD_WEB = By.xpath("//div[@id='AddAddressGeneric']//input[@id='Name']");
    public static final By ADRES_EKLE_SOYAD = By.id("Address_LastName");
    public static final By ADRES_EKLE_SOYAD_WEB = By.xpath("//div[@id='AddAddressGeneric']//input[@id='Surname']");
    public static final By ADRES_EKLE_IL = By.id("Address_CityId");
    public static final By ADRES_EKLE_IL_WEB = By.xpath("//div[@class='row']//select[@id='CityId']");
    public static final By ADRES_EKLE_ILCE = By.id("Address_CityCountyId");
    public static final By ADRES_EKLE_ILCE_WEB = By.xpath("//div[@id='AddAddressGeneric']//select[@id='CountyId']");
    public static final By ADRES_EKLE_SEMT = By.id("Address_CityCountyDistrictId");
    public static final By ADRES_EKLE_SEMT_WEB = By.xpath("//div[@id='AddAddressGeneric']//select[@id='DistrictId']");
    public static final By ADRES_EKLE_ADRES = By.id("Address_Address1");
    public static final By ADRES_EKLE_ADRES_WEB = By.xpath("(//textarea[@id='AddressLine1'])[2]");
    public static final By ADRES_EKLE_MAIL = By.id("Address_Email");
    public static final By ADRES_EKLE_MAIL_WEB = By.xpath("//div[@id='AddAddressGeneric']//input[@id='Email']");
    public static final By ADRES_EKLE_TELEFON = By.id("Address_MobilePhoneNumber");
    public static final By ADRES_EKLE_TELEFON_WEB = By.xpath("//div[@id='AddAddressGeneric']//input[@id='MobilePhoneNumber']");
    public static final By ADRES_EKLE_BAŞLIK = By.id("Address_Title");
    public static final By ADRES_EKLE_BAŞLIK_WEB = By.xpath("//div[@id='AddAddressGeneric']//input[@id='Title']");
    public static final By ADRES_EKLE_KAYDET_BUTTON = By.xpath("//input[@id='addaddress']");
    public static final By ADRES_EKLE_KAYDET_BUTTON_WEB_ID = By.id("AddAddressButton");

}
