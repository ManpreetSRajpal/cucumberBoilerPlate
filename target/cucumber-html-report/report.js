$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("com/project/cucumber/admin/Login.feature");
formatter.feature({
  "line": 1,
  "name": "Customer should be able to login",
  "description": "",
  "id": "customer-should-be-able-to-login",
  "keyword": "Feature"
});
formatter.before({
  "duration": 1021384205,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Customer can not log in if USERNAME is incorrect",
  "description": "",
  "id": "customer-should-be-able-to-login;customer-can-not-log-in-if-username-is-incorrect",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@smoke"
    },
    {
      "line": 3,
      "name": "@desktop"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "the user launches github login page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "the user tries to login as \"a\"",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "the user should see access restricted page",
  "keyword": "Then "
});
formatter.match({
  "location": "UserProfileSteps.userIsOnHomePageAs()"
});
formatter.result({
  "duration": 5875768739,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "a",
      "offset": 28
    }
  ],
  "location": "LoginPageSteps.theUserTriesToLoginAs(String)"
});
formatter.result({
  "duration": 6922043502,
  "status": "passed"
});
formatter.match({
  "location": "LoginPageSteps.theUserShouldSeeAccessRestrictedPage()"
});
formatter.result({
  "duration": 31077741,
  "status": "passed"
});
formatter.after({
  "duration": 61378,
  "status": "passed"
});
});