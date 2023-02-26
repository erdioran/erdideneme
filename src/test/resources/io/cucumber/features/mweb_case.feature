Feature: MWEB VA BOARD - UI Mweb

  @MWEB @REGRESSION @PRODUCT_DETAIL
  Scenario Outline: 1) Mweb Sticky Bar Fonksiyon (VA-211)
    Given load home page in "<base>"
    And go to a product detail from the "<category>" tab mweb
    Then check sticky bar visibility "<status1>"
    And scroll down until the for sticky bar
    Then check sticky bar visibility "<status2>"
    And check sticky background color is "<colorCode>" and sticky discount background is "<colorCodeDiscount>"
    And check sticky font colors is "<fontColor>"
    And scroll down until the MASAUSTU GORONUM field
    Then check sticky bar visibility "<status2>"

    Examples:
      | base           | category            | status1 | status2 | colorCode                        | colorCodeDiscount                        | fontColor                       |
      | msupplementler | Sporcu Vitaminleri  | false   | true    | stickyBarBackgroundSupplementler | stickyBarDiscountBackgroundSupplementler | stickyBarFontColorSupplementler |
      | mvitaminler    | Diğer Takviyeler    | false   | true    | stickyBarBackgroundVitaminler    | stickyBarDiscountBackgroundVitaminler    | stickyBarFontColorVitaminler    |
      | mfitmoda       | Fitness Ekipmanları | false   | true    | stickyBarBackgroundFitmoda       | stickyBarDiscountBackgroundFitmoda       | stickyBarFontColorFitmoda       |


  @MWEB @REGRESSION @GUEST @SIPARIS
  Scenario Outline: 2) Mweb Guest Checkout Sipariş Tamamlanan (VA-170)
    Given load home page in "<base>"
    And add a "<count>" products from "<category>" tab to the cart mweb
    Then add guest address and proceed to checkout mweb
    And complete the guest order with the "<payment>" method
    And hotmail login with "<type>" user and check "<store>" mail - guest

    Examples:
      | base           | category            | count | type  | store         | payment     |
      | msupplementler | Sporcu Vitaminleri  | 1     | Guest | Supplementler | havale      |
      | mvitaminler    | Diğer Takviyeler    | 1     | Guest | Vitaminler    | havale      |
      | mfitmoda       | Fitness Ekipmanları | 1     | Guest | Fitmoda       | havale      |


  @MWEB @REGRESSION @GUEST @SIPARIS
  Scenario Outline: 3) Mweb Guest Checkout Sipariş Tamamlanmayan (VA-170)
    Given load home page in "<base>"
    And add a "<count>" products from "<category>" tab to the cart mweb
    Then add guest address and proceed to checkout mweb
    And complete the guest order with the "<payment>" method

    Examples:
      | base           | category            | count | type  | store         | payment     |
      | msupplementler | Sporcu Vitaminleri  | 1     | Guest | Supplementler | kredi kartı |
      | mvitaminler    | Diğer Takviyeler    | 1     | Guest | Vitaminler    | kredi kartı |
      | mfitmoda       | Fitness Ekipmanları | 1     | Guest | Fitmoda       | kredi kartı |


  @MWEB @REGRESSION @GUEST
  Scenario Outline: 4) Mweb Guest Checkout Kayıtlı Kullanıcı Maili Hatası (VA-170)
    Given load home page in "<base>"
    And add a "<count>" products from "<category>" tab to the cart mweb
    When click DEVAM ET button in checkout
    And guest trial with registered mail mweb
    And try member login after error mweb
    And clear basket mweb

    Examples:
      | base           | category            | count |
      | msupplementler | Sporcu Vitaminleri  | 1     |
      | mvitaminler    | Diğer Takviyeler    | 1     |
      | mfitmoda       | Fitness Ekipmanları | 1     |


  @CANCEL
  Scenario Outline: Mweb Guest Checkout Token Süresi
    Given load home page in "<base>"
    And add a "<count>" products from "<category>" tab to the cart mweb
    When click DEVAM ET button in checkout
    And if the gift option is seen
    And click UYE OLMADAN DEVAM ET button
    And enter "<type>" mail
    And wait <second> second
    And click SIPARISE DEVAM ET button guest
    Then guest timeout error
    And page refresh mweb
    And click UYE OLMADAN DEVAM ET button
    And enter "<type2>" mail
    And ADRESS PAGE page appears

    Examples:
      | base           | category            | count | type  | second | type2 |
      | msupplementler | Sporcu Vitaminleri  | 1     | Guest | 620    | Guest |
      | mvitaminler    | Diğer Takviyeler    | 1     | Guest | 620    | Guest |
      | mfitmoda       | Fitness Ekipmanları | 1     | Guest | 620    | Guest |


  @erdi @REGRESSION @CHECKOUT
  Scenario Outline: 5) Mweb Checkout V2 Geliştirmesi Tasarım ve Fonksiyon (VA-124)
    Given open login page in "<base>"
    And login with "<type>" user mweb
    And clear user adress - 1 address remains
    And clear basket mweb
    And add a "<count>" products from "<category>" tab to the cart mweb
    When SEPETIM page check
    Then ADRES page check
    And ODEME KREDI KARTI page check
    And ODEME HAVALE page check
    And ODEME KAPIDA KREDI KARTI page check
    And ODEME KAPIDA NAKIT page check
    And ODEME ALISVERIS KREDISI check
    And progress bar click check mweb

    Examples:
      | base           | category            | count | type     |
      | msupplementler | Sporcu Vitaminleri  | 3     | Customer |
      | mvitaminler    | Diğer Takviyeler    | 3     | Customer |
      | mfitmoda       | Fitness Ekipmanları | 3     | Customer |


  @MWEB @REGRESSION @AGT @SIPARIS
  Scenario Outline:  6) Mweb AGT Sipariş Tamamlanan
    Given open login page in "<base>"
    And login with "<type>" user mweb
    And clear basket mweb
    And add a "<count>" products from "<category>" tab to the cart mweb
    And go to address step in basket mweb
    And AGT delivery selection and go to payment step MWEB
    And complete AGT order by "<payment>" transfer mweb

    Examples:
      | base           | category            | count | type     | payment |
      | msupplementler | Sporcu Vitaminleri  | 1     | Customer | havale  |
      | mvitaminler    | Diğer Takviyeler    | 1     | Customer | havale  |
      | mfitmoda       | Fitness Ekipmanları | 1     | Customer | havale  |
