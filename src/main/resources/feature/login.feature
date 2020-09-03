Feature: check login
    To check login success or not.
    @Tag0
    Scenario: Network is enable then login
    Given setupDevice "Android"
    When click 122,220
    When clickElement "hello"
    When doubleClick 100,300
    When doubleClickElement "你好"

    @tag1
    Scenario: Network is not enable then login
    Given setupDevice "IOS"
    When click 122,220
    When clickElement "hello"
    When doubleClickElement "你好"
