# RichValidation
[![Build Status](https://travis-ci.org/kotovdv/RichValidation.svg?branch=development)](https://travis-ci.org/kotovdv/RichValidation)
[![codecov.io](https://codecov.io/github/kotovdv/RichValidation/coverage.svg?branch=development)](https://codecov.io/github/kotovdv/RichValidation)



## About

RichValidation is a library for rule based validation of a domain model.

## Core idea

At current version RichValidation provides only Java DSL API to create validation rules.

<b>WARNING</b>: Current API is a subject of frequent changes.

Consider situation, where we have an attraction. 

Attraction visitors are represented by the following class:
```
  public class AttractionVisitor {
    public final int height;
    public final int weight;

    public AttractionVisitor(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }
}
```

Visitors must meet specific requirements to be allowed to enjoy it. 
Requirements are:

* Visitor must have <b>height</b> GREATER OR EQUAL to 150 and LESS OR EQUAL TO 190 CM.
* Visitor must have <b>weight</b> LESS THAN 100 KG.

These requirements can easily be pictured in form of a rules, using RichValidation API.

```
import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;
...

Rule<AttractionVisitor> heightTestRule = ensureThat(AttractionVisitor.class)
                .field("height")
                .isGreaterOrCoequalTo(150)
                .and()
                .isLessOrCoequalTo(190)
                .build();

Rule<AttractionVisitor> weightTestRule = ensureThat(AttractionVisitor.class)
                .field("weight")
                .isLessThan(100)
                .build();
```                

Using these rules user can validate <b>AttractionVisitor</b> instances.

```
heightTestRule.validate(new AttractionVisitor(100, 30)); //RESULTS IN FALSE
heightTestRule.validate(new AttractionVisitor(170, 60)); //RESULTS IN TRUE
weightTestRule.validate(new AttractionVisitor(190, 120)); //RESULTS IN FALSE
weightTestRule.validate(new AttractionVisitor(190, 90)); //RESULTS IN TRUE

```


Rules, while being combined in <b>RuleDictionary</b> provide an ability to validate various objects, using all known rules.
<b>CURRENTLY IN PROGRESS</b>


## License

MIT License

Copyright (c) 2017 Dmitry Kotov 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE
