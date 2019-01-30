package com.fishy.provalang.ast.api.value.builtins;

import com.fishy.provalang.ast.api.nodes.Type;
import com.fishy.provalang.ast.api.scope.Symbol;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class StringSymbol extends Symbol<StringSymbol.StringType>
{
    public StringSymbol(StringType type)
    {
        super(type.getName(), type);
    }

    @Data
    public static class StringType implements Type
    {
        public String value;
        public UUID uuid;

        public StringType(String value)
        {
            this.value = value;
            this.uuid = UUID.fromString(value);
        }

        @Override
        public String getName()
        {
            return "string-" + uuid.toString();
        }
    }
}
