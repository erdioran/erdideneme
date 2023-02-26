package io.cucumber.skeleton;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;


import java.util.HashMap;

import static base.AutomationMethods.*;
import static base.AutomationMethods.checkStringContains;
import static base.ProjectMethods.*;
import static objectRepository.AdminOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.UserInfoOR.*;
import static supplementler.utils.Helper.*;

public class StepDefinitionsProductFind {

    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsProductFind.class);


    static HashMap<String, Integer> myMap = new HashMap<String, Integer>();

    public static void storeData(String key, int value) {
        myMap.put(key, value);
    }

    public static int retrieveData(String key) {
        int retrievedValue = myMap.get(key);
        System.out.println("Value retrieved: " + retrievedValue);
        return retrievedValue;
    }

    int hediyeurun;
    String totalPriceOld;
    String totalPriceNew;



    @Then("branch prodduct add basket")
    public void branchProdductAddBasket() {
        //Bu method sadece brachte test yazarkensepete ekleme adımlarını geçmek için kullanılır.

        String urlCheck = checkStore(getTabUrl());

        if (urlCheck.equals("supplementler")) {
            pageLoad("https://dilara-m.supplementler.com/urun/supplementlercom-whey-protein-2000-gr-8349");
            click(By.xpath("//a[@id='add-product-to-cart']"));
            click(By.xpath("//a[@class='muz aromaItem']"));
            sleepInSeconds(2);
            click(By.xpath("//a[@value='6735']"));

            click(By.xpath("//a[normalize-space()='Sepete Git']"));
        } else if (urlCheck.equals("vitaminler")) {
            pageLoad("https://dilara-m.vitaminler.com/urun/natures-supreme-omega-3-1000-mg-60-kapsul-7082");
            click(By.xpath("//a[@id='add-product-to-cart']"));
            click(By.xpath("//a[normalize-space()='Sepete Git']"));
        } else if (urlCheck.equals("fitmoda")) {
            pageLoad("https://dilara-m.fitmoda.com/urun/harbinger-tri-fold-mat-siyah-10165");
            click(By.xpath("//a[@id='add-product-to-cart']"));
            click(By.xpath("//a[normalize-space()='Sepete Git']"));
        }
    }




    @And("go to a product detail from the {string} tab mweb")
    public void goToProductDetailFromTabMweb(String category) {
        By product = null;
        String urlCheck = checkStore(getTabUrl());

        for (int select = 1; select < 11; select++) {

            clickCategortInLefMenuMweb(category);

            if (urlCheck.equals("supplementler")) {
                product = By.xpath("/html/body/div[2]/div[4]/div[10]/div[" + select + "]/div[2]/a");
                pageScroollDownToTargetElement(product);
            } else if (urlCheck.equals("vitaminler")) {
                product = By.xpath("/html/body/div[2]/div[4]/div[9]/div[" + select + "]/div[2]/a");
                pageScroollDownToTargetElement(product);
            } else if (urlCheck.equals("fitmoda")) {
                product = By.xpath("/html/body/div[2]/div[3]/div[6]/div[1]/div[" + select + "]/div[2]/a");
                pageScroollDownToTargetElement(product);
            }

            String prductTitle = getText(product);

            if (!checkStringContains(prductTitle, "+")) {
                click(product);
                LOGGER.info("From the " + category + " products, the " + select + ". product was clicked.");
                break;

            } else {
                LOGGER.info("Ürün kombinasyon ürün. Başka bir ürün denenecek");
                select++;
            }
        }
    }


    @And("add a {string} products from {string} tab to the cart mweb")
    public void addAProductsFromTabToTheCartMweb(String count, String category) {
        int totalChoise = 0;
        By product = null;
        String urlCheck = checkStore(getTabUrl());

        for (int select = 1; select < 11; select++) {
            //    sleepInSeconds(1);
            clickCategortInLefMenuMweb(category);

            if (urlCheck.equals("supplementler")) {
                product = By.xpath("/html/body/div[2]/div[4]/div[10]/div[" + select + "]/div[2]/a");
                pageScroollDownToTargetElement(product);
            } else if (urlCheck.equals("vitaminler")) {
                product = By.xpath("/html/body/div[2]/div[4]/div[9]/div[" + select + "]/div[2]/a");
                pageScroollDownToTargetElement(product);
            } else if (urlCheck.equals("fitmoda")) {
                product = By.xpath("/html/body/div[2]/div[3]/div[6]/div[1]/div[" + select + "]/div[2]/a");
                pageScroollDownToTargetElement(product);
            }

            String prductTitle = getText(product);

            if (!checkStringContains(prductTitle, "+")) {
                click(product);
                LOGGER.info("From the " + category + " products, the " + select + ". product was clicked.");

                if (!elementVisibiltyWithSize(URUN_DETAY_KOMBINASYONDA_NELER_VAR_FIELD_MWEB)) {
                    LOGGER.info("Ürün kombinasyon değil. Test devam ediliyor.");
                    click(URUN_DETAY_SEPETE_EKLE_BUTTON);
                    if (elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE_MWEB) || elementVisibiltyWithSize(URUN_DETAY_BEDEN_SECINIZ_POPUP_FITMODA)) { //buraya fitmoda beden seç popupı visibility

                        sleepInSeconds(2);
                        if (urlCheck.equals("supplementler") || (urlCheck.equals("vitaminler"))) {

                            for (int i = 1; i < 4; i++) {
                                String status = getStockStatusMweb(By.xpath("/html/body/div[8]/div/div/div/div/div/div[3]/a[" + i + "]"));
                                LOGGER.info(i + ". choise stock is " + status);
                                if (status.equals("False")) {
                                    click(By.xpath("/html/body/div[8]/div/div/div/div/div/div[3]/a[" + i + "]"));
                                    LOGGER.info("Aroma veya beden seçildi.");
                                    break;
                                } else {
                                    i++;
                                }
                            }
                        } else if (urlCheck.equals("fitmoda")) {
                            click(URUN_DETAY_BEDEN_SECINIZ_POPUP_FITMODA);
                            for (int i = 1; i < 4; i++) {
                                String status = getStockStatusMweb(By.xpath("//div[@id='flavor-select']/a[" + i + "]"));
                                LOGGER.info(i + ". choise stock is " + status);
                                if (status.equals("False")) {
                                    click(By.xpath("//div[@id='flavor-select']/a[" + i + "]"));
                                    LOGGER.info("Aroma veya beden seçildi.");
                                    break;
                                } else {
                                    i++;
                                }
                            }
                        }
                    }
                    if (elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_POPUP_MWEB)) {
                        if (urlCheck.equals("supplementler") || (urlCheck.equals("vitaminler"))) {
                            if (elementVisibiltyWithSize(By.xpath("/html/body/div[8]/div/div/div/div/div/div[3]/div[1]/a"))) {
                                click(By.xpath("/html/body/div[8]/div/div/div/div/div/div[3]/div[1]/a"));
                            } else {
                                click(By.xpath("//body/div[8]/div/div/div/div/div[3]/div/a"));
                            }

                            LOGGER.info("Random gift seçildi.");

                        } else if (urlCheck.equals("fitmoda")) {
                            /////
                        }
                    }

                    sleepInSeconds(2);
                    if (elementVisibiltyWithSize(UYARI_POPUP_TAMAM_BUTTON)) {
                        click(UYARI_POPUP_TAMAM_BUTTON);
                    } else {
                        if (elementVisibiltyWithSize(URUN_DETAY_URUN_SEPETE_EKLENDI_POPUP_MWEB)) {
                            LOGGER.info("Alıverişe devam et popupı görüldü");
                            click(URUN_DETAY_SEPETE_GIT_BUTTON_MWEB);
                        } else {
                            click(URUN_DETAY_SEPETE_EKLE_BUTTON);
                            click(URUN_DETAY_SEPETE_GIT_BUTTON_MWEB);
                        }
                    }
                    totalChoise++;
                    if (totalChoise == Integer.parseInt(count)) {
                        break;
                    }
                } else {
                    LOGGER.info("Ürün kombinasyon ürün. Başka bir ürün denenecek");
                    click(TOP_SITE_LOGO);
                }
            } else {
                click(TOP_SITE_LOGO);
            }
        }
        LOGGER.info(totalChoise + " adet ürün sepete eklenebildi. Test için istenen adet:" + count);

        // if (urlCheck.equals("fitmoda")) {click(SEPET_ICON);}
    }



    @And("open {string} in left menu mweb")
    public void openInLeftMenuMweb(String page) {
        clickPageInLefMenuMweb(page);
    }

    @And("click {string} in page mweb")
    public void clickInPageMweb(String field) {
        click(By.xpath("//a[contains(text(),'" + field + "')]"));
    }




    @And("add a {string} and {string} product from the {string} tab to the cart")
    public void findUrun(String aroma, String gift, String category) {
        String home = getTabUrl();
        By product;
        boolean urlCheck = checkStringContains(getTabUrl(), "supplementler");
        for (int select = 1; select < 11; select++) {
            click(By.xpath("//li[@class='main-link-wrapper']//a[@title='" + category + "']"));
            if (urlCheck) {
                product = By.xpath("//body[1]/div[9]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[2]/div[" + select + "]/div[2]/div[1]/a[2]");
                pageScroollDownToTargetElement(product);
            } else {
                product = By.xpath("/html/body/div[9]/div/div[1]/div/div/div/div[2]/div/div/div[3]/div[2]/div[" + select + "]/div[2]/div[1]/a[2]");
                pageScroollDownToTargetElement(product);
            }

            String prductTitle = getText(product);
            if (!checkStringContains(prductTitle, "+")) {
                click(product);
                LOGGER.info("From the " + category + " products, the " + select + ". product was clicked.");

                if (!elementVisibiltyWithSize(URUN_DETAY_KOMBINASYONDA_NELER_VAR_FIELD_WEB)) {
                    LOGGER.info("Ürün kombinasyon değil. Test devam ediliyor.");
                    click(URUN_DETAY_SEPETE_EKLE_BUTTON);

                    if (aroma.equals("true")) {
                        if (elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE)) {
                            int bedenAromaSize = getListSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE);
                            int randomChoice = getRandomNumberBetween(1, bedenAromaSize);
                            click(By.xpath("//body[1]/div[22]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[" + randomChoice + "]/strong[1]"));
                            LOGGER.info("Aroma veya beden seçildi. ");
                            sleepInSeconds(3);

                            if (gift.equals("true")) {
                                if (elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    int giftSize = getListSize(URUN_DETAY_HEDIYE_SECIMI_SIZE);
                                    int randomGift = getRandomNumberBetween(1, giftSize);
                                    click(By.xpath("(//li[@class='gift-select-option-v2'])[" + randomGift + "]"));
                                    LOGGER.info("Random gift seçildi.");
                                    sleepInSeconds(3);
                                    click(URUN_DETAY_SATIN_AL_BUTTON);
                                    break;
                                } else if (!elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    LOGGER.info("Hediyeli ürün için başka bir ürün denenecek.");
                                    pageLoad(home); //bu patlayabilir

                                }

                            } else if (gift.equals("false")) {
                                if (elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    LOGGER.info("Hediyesiz ürün için başka bir ürün denenecek.");
                                    pageLoad(home); //bu patlayabilir

                                } else if (!elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    click(URUN_DETAY_SATIN_AL_BUTTON); ///burası sepette olabilir click
                                    LOGGER.info("Hediyesiz ürün seçildi");
                                    break;
                                }
                            }


                        } else if (!elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE)) {
                            LOGGER.info("Aromalı ürün için başka bir ürün denenecek.");
                            pageLoad(home);

                        }

                    } else if (aroma.equals("false")) {
                        if (elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE)) {
                            LOGGER.info("Aromasız ürün için başka bir ürün denenecek.");
                            pageLoad(home); //bu patlayabilir

                        } else if (!elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE)) {
                            if (gift.equals("true")) {
                                if (elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    int giftSize = getListSize(URUN_DETAY_HEDIYE_SECIMI_SIZE);
                                    int randomGift = getRandomNumberBetween(1, giftSize);
                                    click(By.xpath("(//li[@class='gift-select-option-v2'])[" + randomGift + "]"));
                                    LOGGER.info("Random gift seçildi.");
                                    sleepInSeconds(3);
                                    click(URUN_DETAY_SATIN_AL_BUTTON);
                                    break;
                                } else if (!elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    LOGGER.info("Hediyeli ürün için başka bir ürün denenecek.");
                                    pageLoad(home); //bu patlayabilir

                                }

                            } else if (gift.equals("false")) {
                                if (elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    LOGGER.info("Hediyesiz ürün için başka bir ürün denenecek.");
                                    pageLoad(home); //bu patlayabilir

                                } else if (!elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {
                                    click(URUN_DETAY_SATIN_AL_BUTTON); ///burası sepette olabilir click
                                    LOGGER.info("Hediyesiz ürün seçildi");
                                    break;
                                }
                            }
                        }

                    }
                } else {
                    LOGGER.info("Ürün kombinasyon ürün. Başka bir ürün denenecek");

                }
            } else {

            }
        }
    }



    @And("add a {string} products from {string} tab to the cart")
    public void addAProductsFromTabToTheCart(String count, String category) {
        int totalChoise = 0;
        By product = null;
        String urlCheck = checkStore(getTabUrl());

        for (int select = 1; select < 11; select++) {

            if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {
                click(By.xpath("//li[@class='main-link-wrapper']//a[@title='" + category + "']"));
            } else if (urlCheck.equals("fitmoda")) {
                click(By.xpath("//a[@data-label='" + category + "']"));
            }

            if (urlCheck.equals("supplementler")) {
                product = By.xpath("//body[1]/div[9]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[2]/div[" + select + "]/div[2]/div[1]/a[2]");
                pageScroollDownToTargetElement(product);
            } else if (urlCheck.equals("vitaminler")) {
                product = By.xpath("/html/body/div[9]/div/div[1]/div/div/div/div[2]/div/div/div[3]/div[2]/div[" + select + "]/div[2]/div[1]/a[2]");
                pageScroollDownToTargetElement(product);
            } else if (urlCheck.equals("fitmoda")) {
                product = By.xpath("//div[7]/div/div/div/div/div[2]/div/div/div[4]/div[2]/div/div[" + select + "]/div/div[2]/div[1]/a[2]");
                pageScroollDownToTargetElement(product);
            }

            String prductTitle = getText(product);

            if (!checkStringContains(prductTitle, "+")) {
                click(product);
                LOGGER.info("From the " + category + " products, the " + select + ". product was clicked.");

                if (!elementVisibiltyWithSize(URUN_DETAY_KOMBINASYONDA_NELER_VAR_FIELD_WEB)) {
                    LOGGER.info("Ürün kombinasyon değil. Test devam ediliyor.");
                    click(URUN_DETAY_SEPETE_EKLE_BUTTON);
                    sleepInSeconds(2);

                    if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {

                        if (elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE) || elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE_TEST)) {

                            if (elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE)) {
                                click(By.xpath("//body[1]/div[22]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/strong[1]"));
                            } else {
                                click(By.xpath("//body[1]/div[21]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/strong[1]"));
                            }
                            LOGGER.info("Aroma veya beden seçildi. ");
                            sleepInSeconds(2);
                        }
                    } else if (urlCheck.equals("fitmoda")) {
                        if (elementVisibiltyWithSize(URUN_DETAY_BEDEN_AROMA_SECIMI_SIZE_FITMODA)) {

                            click(By.xpath("//body[1]/div[16]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/strong[1]"));
                            LOGGER.info("Aroma veya beden seçildi. ");
                            sleepInSeconds(2);
                        }
                    }


                    if (elementVisibiltyWithSize(URUN_DETAY_HEDIYE_SECIMI_SIZE)) {

                        click(By.xpath("(//li[@class='gift-select-option-v2'])[1]"));
                        LOGGER.info("Hediye seçildi.");
                        sleepInSeconds(2);
                    }

                    if (urlCheck.equals("supplementler") || urlCheck.equals("vitaminler")) {

                        if (elementVisibiltyWithSize(UYARI_POPUP_TAMAM_BUTTON)) {
                            click(UYARI_POPUP_TAMAM_BUTTON);
                        } else {
                            if (elementVisibiltyWithSize(URUN_DETAY_ALISVERISE_DEVAM_ET_BUTTON)) {
                                click(URUN_DETAY_SATIN_AL_BUTTON);
                            } else if (!elementVisibiltyWithSize(URUN_DETAY_ALISVERISE_DEVAM_ET_BUTTON)) {
                                click(URUN_DETAY_SEPETE_EKLE_BUTTON);
                                click(URUN_DETAY_SATIN_AL_BUTTON);
                            }
                            totalChoise++;
                            if (totalChoise == Integer.parseInt(count)) {
                                break;
                            }
                        }


                    } else if (urlCheck.equals("fitmoda")) {

                        if (elementVisibiltyWithSize(UYARI_POPUP_TAMAM_BUTTON)) {
                            click(UYARI_POPUP_TAMAM_BUTTON);
                        } else {
                            if (elementVisibiltyWithSize(URUN_DETAY_ALISVERISE_DEVAM_ET_BUTTON)) {
                                click(URUN_DETAY_SATIN_AL_BUTTON_FITMODA);
                            } else {
                                click(URUN_DETAY_SEPETE_EKLE_BUTTON);
                                click(URUN_DETAY_SATIN_AL_BUTTON_FITMODA);
                            }
                            totalChoise++;
                            if (totalChoise == Integer.parseInt(count)) {
                                break;
                            }
                        }
                    }

                } else {
                    LOGGER.info("Ürün kombinasyon ürün. Başka bir ürün denenecek");
                }
            } else {
                LOGGER.info("Ürün kombinasyon ürün. Başka bir ürün denenecek");
            }
        }
        LOGGER.info(totalChoise + " adet ürün sepete eklenebildi. Test için istenen adet:" + count);
    }




}
