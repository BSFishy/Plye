package com.fishy.plye.parser.pass.containerization.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupToken extends ContainerizationToken
{
    public GroupToken(@Nullable ContainerizationToken parent)
    {
        super(parent);
    }
}
