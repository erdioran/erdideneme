Feature: ADMIN - UI Admin


  @ADMIN @REGRESSION
  Scenario Outline: İndirim Kısıtlamaları (Supplementler çalışan rolü Tanımlaması) / (Nestle çalışan rolü Tanımlaması)
    Given open login page in "<base>"
    And login with "<type>" user
    And open admin page in "<base>"
    And load "<page>" page in "<base>"
    And search "<mail>" user in list with email
    And click "<button>" in customer page
    And click "<tab>" in customer detail
    When select "<roleCode>" checkbox in list
    And click KAYDET BUTTON in customer detail
    Then search "<mail>" user in list with email
    And customer has the role "<role>"
    And remove customer "<roleCode>" role and check "<role>"

    Examples:
      | base          | type  | page       | mail                       | button  | tab             | roleCode | role                   |
      | supplementler | Admin | Müşteriler | erdioran@supplementler.com | Düzenle | Müşteri Rolleri | 45433    | Supplementler Çalışanı |


  @ADMIN @REGRESSION
  Scenario Outline: İndirim Kısıtlamaları (Aylık/Yıllık fiyat limit tanımlaması)
    Given open login page in "<base>"
    And login with "<type>" user
    And open admin page in "<base>"
    And check discount supplementler-nestle in "<base>"

    Examples:
      | base          | type  |
      | supplementler | Admin |


  @ADMIN @REGRESSION
  Scenario Outline: İndirim Kısıtlamaları (Aylık/Yıllık ürün limiti)
    Given open login page in "<base>"
    And login with "<type>" user
    And open admin page in "<base>"
    And check discount count supplementler-nestle in "<base>"

    Examples:
      | base          | type  |
      | supplementler | Admin |


  @ADMIN @REGRESSION
  Scenario Outline: VA-162 - Marka Skoru Ekranındaki "Marka Adı" Alanı Güncellenmesi - 3 Store
    Given open login page in "<base>"
    And login with "<type>" user
    And open admin page in "<base>"
    And load "<page>" page in "<base>"
    When click "<button>" in categories page
    Then MARKA ADI field update status is "<boolean>"

    Examples:
      | base          | type  | page          | button  | boolean |
      | supplementler | Admin | MarkaSkorları | Düzenle | false   |
      | vitaminler    | Admin | MarkaSkorları | Düzenle | false   |
      | fitmoda       | Admin | MarkaSkorları | Düzenle | false   |