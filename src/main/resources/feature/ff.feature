Feature: 自动登陆
  是否可以自动登陆.

  Background: 背景变量
    Given initCmdList
      | click        | 122, 220                                           |
      | clickElement | "fff"                                              |
      | loop         | [ click (12 , "a", 1.5) , wait(1), click(100,100)] |

  @Tag0
  Scenario: Network is enable then login
    Given setupDevice "Android"
    When click(122, 220)
    When clickElement("fff")
    When loop([ click (12 , "a", 1.5) , wait(1), click(100,100)], 1)


  Scenario Outline: eating
    Given there are <start> cucumbers
    When I eat <eat> cucumbers
    Then I should have <left> cucumbers
    Examples:
      | start | eat | left |
      | 12    | 5   | 7    |
      | 20    | 5   | 15   |

  Scenario: execute table cmds
    When executeCmd("cmd1")


