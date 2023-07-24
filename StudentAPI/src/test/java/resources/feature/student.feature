Feature: Testing different request on the student application

  @SMOKE
  Scenario: Check if the student application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code "200"

  @Create
  Scenario Outline:Create a new student & verify if the student is added
    When I create a new student by providing the infromation firstname "<firstname>" lastname "<lastname>" email "<email>" programme "<programme>" courses "<courses>"
    Then User must get back a valid status code "201"
    Then I verify that the student with email is created
    Examples:
      | firstname | lastname | email            | programme         | courses |
      | code1     | buster1  | test1@gmail.com  | computer Science  | JAVA    |
      | code2     | buster2  | test2@gmail.com  | API Testing       | API     |


    @Update(PUT)
    Scenario Outline: update a existing student & verify if the student
      When I give data infromation to update firstname "<firstname>" lastname "<lastname>" email"<email>" programme "<programme>" courses "<courses>"
      Then User must get back a valid status code "200"
     # Then I verify that the student with email is created
      Examples:
        | firstname | lastname | email            | programme         | courses |
        | Test      | buster2   | test1@gmail.com  | AI programming    | Promt   |


    @Update(Patch)
    Scenario Outline: update a existing student & verify if the student
      When I give data infromation to update firstname "<lastname>" email"<email>"
      Then User must get back a valid status code "200"
      #Then I verify that the student with email is updated

      Examples:
        | lastname | email              |
        | RestAPI   | mack1@gmail.com   |

    @Delete
    Scenario: Check if the student application can be accessed by users
      When User sends a DELETE request to endpoint
      Then User must get back a valid status code "204"