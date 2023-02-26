package objectRepository;

import org.openqa.selenium.By;

public class ProductDetailOR {

    public static final By STICKY_BAR_URUN_DETAY = By.id("sticky-element-2423");
    public static final By STICKY_BAR_INDIRIM_URUN_DETAY = By.id("sticky-discount-percentage-2423");
    public static final By STICKY_BAR_SEPETE_EKLE_BUTTON = By.id("sticky-add-to-cart-btn-2423");
    public static final By STICKY_BAR_INDIRIMLI_FIYAT = By.cssSelector(".new-price-2423");
    public static final By STICKY_BAR_INDIRIMSIZ_FIYAT = By.cssSelector(".old-price-2423");
    public static final By URUN_DETAY_DETAYLI_ACIKLAMA_FIELD = By.xpath("//a[normalize-space()='DETAYLI AÇIKLAMA']");
    public static final By URUN_DETAY_MASAUSTU_GORUNUM_LINK = By.id("showDesktopLink");
    public static final By URUN_DETAY_SEPETE_EKLE_BUTTON_ID = By.id("add-product-to-cart");
    public static final By URUN_DETAY_FOOTER_ORJUNAL_URUN_FIELD_FITMODA = By.xpath("//a[@href='/t/orijinal-urun']");
    public static final By URUN_DETAY_KOMBINASYONDA_NELER_VAR_FIELD_MWEB = By.xpath("//div[@class='product-combination']");
    public static final By URUN_SEPETE_EKLENDI_POPUP = By.xpath("//span[contains(text(),'BİLGİLENDİRME')]");
    public static final By URUN_DETAY_BEDEN_SECINIZ_POPUP_FITMODA = By.xpath("//button[normalize-space()='Tamam']");
    public static final By URUN_DETAY_VIDEO_POSITION_FITMODA = By.cssSelector("div[class='col-lg-12 product-specifications-area'] div[class='title']");
    public static final By URUN_DETAY_SEPETE_EKLE_BUTTON = By.xpath("//a[@id='add-product-to-cart']");
    public static final By URUN_DETAY_ALISVERISE_DEVAM_ET_BUTTON = By.xpath("//div[@id='add-to-cart-after']//div[@class='pop-button-container']");
    public static final By URUN_DETAY_URUN_SEPETE_EKLENDI_POPUP_MWEB = By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html fancybox-opened']");
    public static final By URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE = By.xpath("//body[1]/div[22]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li");
    public static final By URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE_MWEB = By.xpath("/html/body/div[8]/div/div/div/div/div/div[3]/a[1]");
    public static final By URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE_TEST = By.xpath("//body[1]/div[21]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li");
    public static final By URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE_FITMODA = By.xpath("//body[1]/div[16]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li");
    public static final By BEDEN_AROMA_SECIMI_POPUP_NWEB = By.xpath("/html[1]/body[1]/div[8]/div[1]/div[1]/div[1]/div[1]/div");
    public static final By URUN_DETAY_HEDIYE_SECIMI_POPUP_MWEB = By.xpath("//span[contains(text(),'HEDİYE SEÇİMİ')]");
    public static final By URUN_DETAY_HEDIYE_SECIMI_SIZE = By.xpath("(//li[@class='gift-select-option-v2'])");
    public static final By URUN_DETAY_SATIN_AL_BUTTON = By.xpath("//a[normalize-space()='SATIN AL']");
    public static final By URUN_DETAY_SEPETE_GIT_BUTTON_MWEB = By.xpath("//a[normalize-space()='Sepete Git']");
    public static final By URUN_DETAY_SATIN_AL_BUTTON_FITMODA = By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html wrapper-add-to-cart-after fancybox-opened']//a[2]");
    public static final By URUN_DETAY_KOMBINASYONDA_NELER_VAR_FIELD_WEB = By.xpath("//div[@class='bundleproduct child-product-list clearfix']");
}
