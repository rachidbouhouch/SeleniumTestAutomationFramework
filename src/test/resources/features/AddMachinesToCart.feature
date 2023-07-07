Feature: Add a machine
  As a customer
  I want to add machine with a specific quantity to my cart from Nespresso

Scenario: Choose machine order
    Given I'm on the page home
    When I click on the "machines" order link
    Then the "machines" page is displayed

  Scenario: search the machine
    Given I'm on the "machines" page
    When I search for the "Inissia"
    And  I click on view details machine button named "Inissia"
    Then the product is displayed

  Scenario: Enter a Valid quantity with color
    When I choose a machine color "Red"
    And I add the selected quantity as "10" of machine to my cart
    Then The product named "Inissia" with the quantity "10" have been added to my cart



