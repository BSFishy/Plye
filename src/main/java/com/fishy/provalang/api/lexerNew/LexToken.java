package com.fishy.provalang.api.lexerNew;

import com.fishy.provalang.api.lexerNew.TokenType.TokenData;
import lombok.Data;

@Data
public class LexToken
{
    public final TokenType type;
    public final TokenData data;
}
