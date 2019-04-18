package com.fishy.plye.api.lexer;

import com.fishy.plye.api.lexer.TokenType.TokenData;
import lombok.Data;

@Data
public class LexToken
{
    public final TokenType type;
    public final TokenData data;
}
