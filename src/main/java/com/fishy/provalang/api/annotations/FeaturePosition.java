package com.fishy.provalang.api.annotations;

import com.fishy.provalang.api.ast.feature.Feature;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FeaturePosition
{
    /**
     * The node to focus the position of this feature onto. If this is null, it will be positioned relative the the entire match, i.e. in the beginning of the entire thing, or
     * on the end of it.
     *
     * @return the focused node
     */
    Class<? extends Feature> node();

    /**
     * The position of the feature relative to {@link FeaturePosition#node()}.
     *
     * @return where to check for the direction
     */
    Direction direction() default Direction.Infront;

    /**
     * The priority of the feature. This will define where this feature will be positioned relative to other features on the same {@link FeaturePosition#node()}.
     *
     * @return the priority
     */
    Priority priority() default Priority.Normal;

    enum Direction
    {
        Infront("Infront", "infront"),
        Behind("Behind", "behind");

        private final String name;
        private final String slug;

        Direction(String name, String slug)
        {
            this.name = name;
            this.slug = slug;
        }

        public String getName()
        {
            return name;
        }

        public String getSlug()
        {
            return slug;
        }

        @Nullable
        public static Direction fromName(String name)
        {
            for (Direction d : values())
                if (d.name.equals(name))
                    return d;
            return null;
        }

        @Nullable
        public static Direction fromSlug(String slug)
        {
            for (Direction d : values())
                if (d.slug.equals(slug))
                    return d;
            return null;
        }

        @Override
        public String toString()
        {
            return "Direction[name=" + name + "]";
        }
    }
}
