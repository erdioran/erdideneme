Feature: WEB VA BOARD - UI Web

  @WEB @REGRESSION @PRODUCT_DETAIL
  Scenario Outline: VA-187 - Fitmoda-Ürün Detay Video Konumlandırması
    Given load "<url>" url in "<base>"
    Then check video location

    Examples:
      | base    | url                 |
      | fitmoda | productYoutubeVideo |


  @WEB @REGRESSION
  Scenario Outline: VA-194 - Fittest,Kişisel Tavsiye- URL'lere Parametre Eklenmesi - SUPPLEMENTLER / VITAMINLER
    Given load home page in "<base>"
    When click TEST
    Then check test popup close icon visibility
    And check tab url is "<url>" in "<base>"

    Examples:
      | base          | url              |
      | supplementler | /?fittest        |
      | vitaminler    | /?kisiseltavsiye |


  @WEB @REGRESSION
  Scenario Outline: AH-21 - Geçersiz kupon kodu, uyarı mesajı - 3 store
    Given open login page in "<base>"
    And login with "<type>" user
    And clear basket
    And add a "<count>" products from "<category>" tab to the cart
    And get total cart amount
    When click KUPON KODU checkbox
    And enter "<coupon>" in coupon code
    And click INDIRIMI KULLAN
    Then check that the "<alert>" alert message appears
    And check total cart amount is same as previous step
    And clear basket

    Examples:
      | base          | type     | count | category            | coupon | alert           |
      | supplementler | Customer | 1     | Sporcu Vitaminleri  | test   | KuponKoduHatası |
      | vitaminler    | Customer | 1     | Diğer Takviyeler    | test   | KuponKoduHatası |
      | fitmoda       | Customer | 1     | Fitness Ekipmanları | test   | KuponKoduHatası |


  @WEB @REGRESSION
  Scenario Outline: VA-212 - Desktop - Sipariş Özeti Ürün Adedi Gösterimi - 3 store
    Given open login page in "<base>"
    And login with "<type>" user
    And clear basket
    And add a "<count>" products from "<category>" tab to the cart
    When update the quantity of the <sequence> product in the cart as <num>
    And update the quantity of the <sequence2> product in the cart as <num2>
    And update the quantity of the <sequence3> product in the cart as <num3>
    And click SEPETI ONAYLA
    And choose gift popup action is "<action>"
    And check that the "<element>" is visible in order steps
    Then click SIPARIS OZETINI GENISLET icon
    And order description field <sequence> product quantity badge
    And order description field <sequence2> product quantity badge
    And order description field <sequence3> product quantity badge
    And check that all products have a quantity symbol
    And clear basket

    Examples:
      | base          | type     | category            | count | sequence | num | sequence2 | num2 | sequence3 | num3 | action | element         |
      | supplementler | Customer | Sporcu Vitaminleri  | 3     | 1        | 1   | 2         | 2    | 3         | 3    | true   | Yeni Adres Ekle |
      | vitaminler    | Customer | Diğer Takviyeler    | 3     | 1        | 1   | 2         | 2    | 3         | 3    | true   | Yeni Adres Ekle |
      | fitmoda       | Customer | Fitness Ekipmanları | 3     | 1        | 1   | 2         | 2    | 3         | 3    | true   | Yeni Adres Ekle |


  @WEB @REGRESSION
  Scenario Outline: VA 192 - Siparişiniz alınmıştır mailindeki indirim alanının renginin değiştirilmesi ve not kısmındaki metnin güncellenmesi - 3 store
    Given open login page in "<base>"
    And login with "<type>" user
    And clear basket
    And add a "<count>" products from "<category>" tab to the cart
    When complete test order
    Then hotmail login with "<type>" user and check "<store>" mail

    Examples:
      | base          | type    | count | category            | store         |
      | supplementler | Hotmail | 1     | Sporcu Vitaminleri  | Supplementler |
      | vitaminler    | Hotmail | 1     | Diğer Takviyeler    | Vitaminler    |
      | fitmoda       | Hotmail | 1     | Fitness Ekipmanları | Fitmoda       |


  @WEB @REGRESSION
  Scenario Outline: VA-164 - Guest Checkout Web - 3 store
    Given load home page in "<base>"
    And add a "<count>" products from "<category>" tab to the cart
    Then complete test order with guest web
    And hotmail login with "<type>" user and check "<store>" mail - guest

    Examples:
      | base          | count | category            | type  | store         |
      | supplementler | 1     | Sporcu Vitaminleri  | Guest | Supplementler |
      | vitaminler    | 1     | Diğer Takviyeler    | Guest | Vitaminler    |
      | fitmoda       | 1     | Fitness Ekipmanları | Guest | Fitmoda       |


  @WEB @REGRESSION
  Scenario Outline: VA-164 - Guest Checkout Web - WEB - Üye girişi yapmadan devam et butonuna tıklandığında, üyelik olan bir mail girilir ise, uyarı alınır -3 Store
    Given load home page in "<base>"
    And add a "<count>" products from "<category>" tab to the cart
    And guest trial with registered mail
    And try member login after error

    Examples:
      | base          | count | category            |
      | supplementler | 1     | Sporcu Vitaminleri  |
      | vitaminler    | 1     | Diğer Takviyeler    |
      | fitmoda       | 1     | Fitness Ekipmanları |