# Addressline

**Simple Java app converting address defined as String to JSON object.**

**Input**: string of address e.g.
```
221B Baker St.
```

**Output**: string of street and string of street-number as JSON object e.g. 
```
{
    "street": "Baker St.", 
    "housenumber": "221B"
}
```

* In order to build project & run tests, please run `./gradlew clean build` in project root directory.
* In order to get familiar with API use cases, please take a look at `com.winio94.AppTest` test class located in `algo` module.
* In order to perform conversion, please use `com.winio94.App` class located in `algo` module. 