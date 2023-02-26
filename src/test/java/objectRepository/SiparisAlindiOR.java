package objectRepository;

import org.openqa.selenium.By;

public class SiparisAlindiOR {
    public static final By SIPARIS_ALINDI_SIPARIS_DETAYI_BUTTON = By.xpath("//a[@id='orderDetail']");
    public static final By SIPARIS_ALINDI_SIPARISINIZI_BURADAN_TAKIP_EDEBILIRSINIZ_TEXT = By.xpath("//div[@class='thank-you order-completed-page']");
    public static final By SIPARIS_ALINDI_SIPARISINIZI_BURADAN_TAKIP_EDEBILIRSINIZ_LINK = By.xpath("//a[normalize-space()='buradan']");
    public static final By SIPARIS_ALINDI_PAGE_SIPARIS_NUMARASI=By.xpath("//tr[@class='order-number']//td[4]/strong");
    public static final By SIPARIS_ALINDI_PAGE_HAVALE_SIPARIS_NO_INFO=By.xpath("//div[@class='order-completed-page-remittance-wrapper order-quick-info-div-padding']");
    public static final By SIPARIS_ALINDI_PAGE_SIPARIS_ODEME_YONTEMI=By.cssSelector("td[class='order-completed-page-payment-method'] strong[class='order-completed-information-values']");
    public static final By SIPARIS_ALINDI_PAGE_SIPARIS_ODEME_YONTEMI_FITMODA=By.cssSelector("td[class='order-completed-page-payment-method'] div[class='order-completed-information-values']");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_FIELD=By.xpath("//div[@class='bank-detail']");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_IBAN=By.xpath("(//div)[33]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_IBAN_GUEST=By.xpath("(//div)[30]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_NO=By.xpath("(//div)[37]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_NO_GUEST=By.xpath("(//div)[34]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_SUBE_KODU=By.xpath("(//div)[40]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_SUBE_KODU_GUEST=By.xpath("(//div)[37]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_ADI=By.cssSelector("body > div:nth-child(6) > div:nth-child(5) > div:nth-child(3) > div:nth-child(4) > div:nth-child(2) > div:nth-child(3) > strong:nth-child(2)");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_ADI_GUEST=By.cssSelector("body > div:nth-child(6) > div:nth-child(5) > div:nth-child(3) > div:nth-child(3) > div:nth-child(2) > div:nth-child(3) > strong:nth-child(2)");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_ADI_FITMODA=By.cssSelector("body > div:nth-child(6) > div:nth-child(4) > div:nth-child(4) > div:nth-child(4) > div:nth-child(2) > div:nth-child(3) > strong:nth-child(2)");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_ADI_FITMODA_GUEST=By.cssSelector("body > div:nth-child(6) > div:nth-child(4) > div:nth-child(4) > div:nth-child(3) > div:nth-child(2) > div:nth-child(3) > strong:nth-child(2)");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_SUBE_ADI=By.xpath("//div[@class='bank-detail']//div[4]//strong[1]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_IBAN_VITAMINLER=By.xpath("//body[1]/div[2]/div[4]/div[2]/div[4]/div[2]/div[2]/div[2]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_IBAN_VITAMINLER_GUEST=By.xpath("(//div)[29]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_IBAN_FITMODA=By.xpath("/html/body/div[2]/div[3]/div[2]/div[4]/div[2]/div[2]/div[2]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_IBAN_FITMODA_GUEST=By.xpath("(//div)[34]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_NO_VITAMINLER=By.xpath("//body[1]/div[2]/div[4]/div[2]/div[4]/div[2]/div[2]/div[3]/div[1]/div[2]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_NO_VITAMINLER_GUEST=By.xpath("(//div)[33]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_NO_FITMODA=By.xpath("/html/body/div[2]/div[3]/div[2]/div[4]/div[2]/div[2]/div[3]/div[1]/div[2]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_HESAP_NO_FITMODA_GUEST=By.xpath("(//div)[38]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_SUBE_KODU_VITAMINLER=By.xpath("//body[1]/div[2]/div[4]/div[2]/div[4]/div[2]/div[2]/div[3]/div[2]/div[2]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_SUBE_KODU_VITAMINLER_GUEST=By.xpath("(//div)[36]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_SUBE_KODU_FITMODA=By.xpath("/html/body/div[2]/div[3]/div[2]/div[4]/div[2]/div[2]/div[3]/div[2]/div[2]");
    public static final By SIPARIS_ALINDI_PAGE_BANKA_BILGILERI_SUBE_KODU_FITMODA_GUEST=By.xpath("(//div)[41]");
    public static final By SIPARIS_ALINDI_PAGE_URUNLER_FIELD=By.xpath("//ul[@class='order-items']");
    public static final By SIPARIS_ALINDI_PAGE_URUNLER_1_ITEM=By.xpath("//div[@class='order-complete']//li[1]");
    public static final By SIPARIS_ALINDI_PAGE_ADRES_2_FIELD=By.xpath("(//div[@class='completed-address'])[2]");
    public static final By SIPARISLARIM_PAGE_BACK_BUTTON=By.xpath("//a[contains(text(),'SİPARİŞLERİM')]");
    public static final By SIPARIS_ALINDI_PAGE_GUEST_SIPARISINIZ_TAMAMLANDI_TEXT_MWEB = By.xpath("//h1[contains(text(),'SİPARİŞİNİZ TAMAMLANDI')]");
}
