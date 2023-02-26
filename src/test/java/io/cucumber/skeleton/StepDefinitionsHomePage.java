package io.cucumber.skeleton;

import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static base.AutomationMethods.*;
import static objectRepository.AdminOR.*;
import static objectRepository.GeneralOR.*;
import static objectRepository.HomePageOR.*;
import static objectRepository.LoginPageOR.*;
import static objectRepository.AdressAddOR.*;
import static objectRepository.MailOR.*;
import static objectRepository.ProductDetailOR.*;
import static objectRepository.ProductFindOR.*;
import static objectRepository.SiparisAlindiOR.*;
import static objectRepository.UserInfoOR.*;

public class StepDefinitionsHomePage {
    private static final Logger LOGGER = LogManager.getLogger(StepDefinitionsHomePage.class);
    @When("click TEST")
    public void clickFITTEST() {
        String urlCheck = checkStore(getTabUrl());
        if (urlCheck.equals("supplementler")) {
            click(TOP_BAR_FIT_TEST_SUPPLEMENTLER);
        } else if (urlCheck.equals("vitaminler")) {
            click(TOP_BAR_KISISEL_TAVSIYE_VITAMINLER);
        }

    }

}
