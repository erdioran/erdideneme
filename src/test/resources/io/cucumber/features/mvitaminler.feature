Feature: mVitaminler - UI Mweb

  @ghost @aaa
  Scenario Outline: Uye Bilgileri Guncelle
    Given open login page in "<base>"
    And login with "<type>" user mweb
    And open "<page>" in left menu mweb
    And click "<field>" in page mweb
    Then üyelik bilgileri update mweb
    And üyelik bilgileri restore


    Examples:
      | base        | type       | page    | field                |
      | mvitaminler | Customer | Hesabım | KULLANICI BİLGİLERİM |
