package com.fishy.provalang.api.lexer;

import com.fishy.provalang.api.lexer.TokenType.TokenData;
import lombok.Data;

@Data
public class LexToken
{
    public final TokenType type;
    public final TokenData data;
}
