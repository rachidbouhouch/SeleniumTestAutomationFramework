Feature: Add a capsule
  As a customer
  I want to add capsule with a specific quantity to my cart from Nespresso

 @Ready
Scenario: Choose capsule order
    Given I'm on the page home
    When I click on the "capsules" order link
    Then the "capsules" page is displayed

Scenario: search the capsule
  Given I'm on the "capsules" page
  When I search for the "Nocciola"
  And I click on the button add to bag capsule "Nocciola"
  Then the product is displayed

Scenario: Enter a Valid quantity and verify it in the cart
    When I specify the quantity as "40" and I add the selected quantity of capsules to my cart
    Then The product named "Nocciola" with the quantity "40" have been added to my cart

#Scenario: Enter a invalid quantity
#    When I specify the quantity as "-40"
#    Then Error message is displayed
