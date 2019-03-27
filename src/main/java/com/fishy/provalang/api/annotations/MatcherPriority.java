package com.fishy.provalang.api.annotations;


import com.fishy.provalang.api.lexer.match.Matcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MatcherPriority
{
    /**
     * The priority of the token. Defaults to normal priority.
     *
     * @return the priority of the token
     */
    Priority priority() default Priority.Normal;

    /**
     * A list of matchers that this matcher overrides. This determines the order in which matchers are sorted, and which matcher will be able to cast first.
     *
     * @return a list of matchers that this matcher overrides
     */
    Class<? extends Matcher>[] overrides() default {};

    /**
     * A list that represents the matchers that this matcher replaces. This is useful when you want to change the syntax of a token without completely creating a new token and matcher to support it.
     *
     * @return a list of matchers that this matcher replace
     */
    Class<? extends Matcher>[] replaces() default {};
}
