Sample story

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development

Scenario: Perform a search in Google
Given I navigate to <url> with the <body>
When I perform a search for <word> in the <body>
Then I click <search> Button
Then A link Welcome to Facebook - Log In, Sign Up or Learn More exists in the results	

Examples:
{ignorableSeparator=!--}
!--Following are the data set which will inject to each story by specified row number in relevant line --!
|url                     |body          |word      |search       |
|https://www.google.lk/  |lst-ib        |facebook  |Google Search|
