Feature: Convert planet position file

  Scenario: converts minimal file to csv-file correctly
    Given file is "classpath:files/earth_se_single_datapoint.txt"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_single_datapoint_correct.csv"

  Scenario: converts multiyear-file to csv-file correctly
    Given file is "classpath:files/earth_se.txt"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_150994_29082019_correct.csv"