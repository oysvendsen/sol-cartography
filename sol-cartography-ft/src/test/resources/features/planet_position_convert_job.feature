Feature: Convert planet position file

  Scenario: converts minimal solar ecliptic file to csv-file correctly
    Given file is "classpath:files/earth_se_single_datapoint.txt"
    And profile is "SE"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_single_datapoint_correct.csv"

  @ignore
  Scenario: converts heliographic file to csv-file correctly
    Given file is "classpath:files/earth_hg_single_datapoint.txt"
    And profile is "HG"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_single_datapoint_correct.csv"

  Scenario: converts heliographic-inertial file to csv-file correctly
    Given file is "classpath:files/earth_hgi_single_datapoint.txt"
    And profile is "HGI"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_single_datapoint_correct.csv"

  Scenario: converts multiyear-file to csv-file correctly
    Given file is "classpath:files/earth_se.txt"
    And profile is "SE"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_150994_29082019_correct.csv"

  @ignore
  Scenario: converts heliographic multiyear-file to csv-file correctly
    Given file is "classpath:files/earth_hg.txt"
    And profile is "HG"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_150994_29082019_correct.csv"

  Scenario: converts heliographic-inertial multiyear-file to csv-file correctly
    Given file is "classpath:files/earth_hgi.txt"
    And profile is "HGI"
    When PlanetPositionConvertJob is started
    Then the job returns success
    And the produced file should be equal to "classpath:files/earth_150994_29082019_correct.csv"