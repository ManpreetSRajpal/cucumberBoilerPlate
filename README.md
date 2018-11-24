### Run tests
Tests can be run against different **envs** and different **browser** by passing right property variable 

**Command use to run tests**
`mvn clean test -Dcucumber.options="--tags @smoke" -DBROWSER="chrome" -DENV="qa"`
