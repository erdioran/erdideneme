package io.cucumber.skeleton;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Given;

public class StepDefinitionsAndroid {

    AppiumDriver driver;

    @Given("start android test")
    public void loginPage() throws InterruptedException {


        MobileElement categories = (MobileElement) driver.findElementById("com.dikeyvitamin.fitmoda:id/navigation_bar_item_icon_view");
        categories.click();
        Thread.sleep(3000);

        MobileElement myAccount = (MobileElement) driver.findElementById("com.dikeyvitamin.fitmoda:id/navigation_bar_item_icon_view");
        myAccount.click();
        Thread.sleep(3000);

        MobileElement homePage = (MobileElement) driver.findElementById("com.dikeyvitamin.fitmoda:id/navigation_bar_item_icon_view");
        homePage.click();
        Thread.sleep(3000);

        MobileElement producId = (MobileElement) driver.findElementById("com.dikeyvitamin.fitmoda:id/img_product");
        producId.click();
        Thread.sleep(5000);

        MobileElement addCart = (MobileElement) driver.findElementById("com.dikeyvitamin.fitmoda:id/btn_add_to_cart");
        addCart.click();
        Thread.sleep(3000);



    }

}

