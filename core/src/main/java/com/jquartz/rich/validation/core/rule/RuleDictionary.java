package com.jquartz.rich.validation.core.rule;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.evaluation.TruthValue;

import java.util.Collection;

import static com.jquartz.rich.validation.core.util.Assertions.assertNotNull;

public class RuleDictionary {

    private Multimap<Class<?>, Rule<? super Object>> classToRules = HashMultimap.create();
    private Multimap<ClassField<?>, Rule<?>> fieldToRules = HashMultimap.create();

    RuleDictionary(Multimap<ClassField<?>, Rule<?>> fieldToRule) {
        this.fieldToRules = fieldToRule;
    }

    public static RuleDictionaryBuilder builder() {
        return new RuleDictionaryBuilder();
    }


    //TODO once RuleDictionary is built - build dependency trees
    //TODO Each node of

    public void validate(Object object) {
        assertNotNull(object, "Unable to validate null object");

        Collection<Rule<? super Object>> rules = classToRules.get(object.getClass());

        for (Rule<? super Object> rule : rules) {
            TruthValue validate = rule.validate(object);
        }
    }
}