package objectRepository;

import org.openqa.selenium.By;

public class AdminOR {


    //*******************************************************************************************************************
    // ADMIN - CUSTOMER
    //*******************************************************************************************************************

    public static final By SEARCH_EMAIL = By.id("SearchEmail");
    public static final By SEARCH_BUTTON_CUSTOMER_PAGE = By.id("search-customers");
    public static final By MUSTERI_BILGILERI_TAB = By.xpath("//a[contains(text(),'Müşteri Bilgileri')]");
    public static final By KAYDET_CUSTOMER_DETAIL = By.xpath("//button[normalize-space()='Kaydet']");
    public static final By CUSTOMER_ROLES_TAB = By.cssSelector("tbody tr td:nth-child(7)");
    public static final By TAB_HEADER = By.xpath("//div[@class='title']");
    public static final By MUSTERI_ROLLERI_BUTTON_CUSTOMER_DETAIL = By.xpath("//a[contains(text(),'Müşteri Rolleri')]");
    public static final By DUZENLE_BUTTON_CUSTOMER_LIST = By.xpath("//a[normalize-space()='Düzenle']");


    //*******************************************************************************************************************
    // ADMIN - AYARLAR
    //*******************************************************************************************************************

    public static final By AYAR_ADI_TEXTBOX = By.id("Name");
    public static final By SEARCH_BUTTON = By.id("btnSearch");
    public static final By MARKA_FIELD_FIRST_ITEM=By.xpath("//body/div[1]/div/div[4]/div/div[2]/div/table[2]/tbody/tr/td/div/form/table/tbody/tr[1]/td[3]/input");
}