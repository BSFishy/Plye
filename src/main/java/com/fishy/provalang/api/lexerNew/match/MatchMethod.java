package com.fishy.provalang.api.lexerNew.match;

import com.fishy.provalang.api.lexerNew.data.MatchData;
import com.fishy.provalang.api.lexerNew.data.PersistentMatchData;

public interface MatchMethod
{
    MatchData match(MatchData data, PersistentMatchData pdata, char currentCharacter, int position);
}
