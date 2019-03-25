package com.fishy.provalang.api.annotations;


import com.fishy.provalang.api.lexerNew.match.Matcher;

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

    enum Priority
    {
        Lowest(0, "lowest", "Lowest"),
        Low(1, "low", "Low"),
        Normal(2, "normal", "Normal"),
        High(3, "high", "High"),
        Highest(4, "highest", "Highest");

        private final int    id;
        private final String name;
        private final String value;

        Priority(int id, String name, String value)
        {
            this.id = id;
            this.name = name;
            this.value = value;
        }

        public int id()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public String getValue()
        {
            return value;
        }

        public static Priority fromName(String name)
        {
            for (Priority p : values())
            {
                if (p.name.equals(name))
                    return p;
            }

            return null;
        }

        public static Priority fromValue(String value)
        {
            for (Priority p : values())
            {
                if (p.value.equals(value))
                    return p;
            }

            return null;
        }

        @Override
        public String toString()
        {
            return "Priority[value=" + value + "]";
        }
    }
}
