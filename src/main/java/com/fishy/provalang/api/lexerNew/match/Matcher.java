package com.fishy.provalang.api.lexerNew.match;

import com.fishy.provalang.api.lexerNew.LexToken;
import com.fishy.provalang.api.lexerNew.TokenType;
import com.fishy.provalang.api.lexerNew.data.MatchData;
import com.fishy.provalang.api.lexerNew.data.MatchData.MatchType;
import com.fishy.provalang.api.lexerNew.data.PersistentMatchData;
import lombok.Data;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NonNls;

//@SuppressWarnings("unused")
@Data
public abstract class Matcher<T extends TokenType>
{
    // Type stuff

    public final T type;

    public abstract MatchData run(PersistentMatchData data, char currentCharacter, int position);

    public LexToken run(char[] buffer)
    {
        return new LexToken(type, type.cast(buffer));
    }

    // Helper stuff

    protected boolean checkData(MatchData data, PersistentMatchData pdata)
    {
        boolean value = data.getMatchNumber() == pdata.getPreviousMatch();
        data.increment();
        return value;
    }

    protected void endData(MatchData data, PersistentMatchData pdata)
    {
        if(!data.isHasMatch())
            pdata.increment();
    }

    // Helper matching stuff

    protected MatchData linearMatch(char currentCharacter, int position, MatchMethod... methods)
    {
        return linearMatch(new MatchData(), currentCharacter, position, methods);
    }

    protected MatchData linearMatch(MatchData data, char currentCharacter, int position, MatchMethod... methods)
    {
        return linearMatch(data, new PersistentMatchData(0), currentCharacter, position, methods);
    }

    protected MatchData linearMatch(MatchData data, PersistentMatchData pdata, char currentCharacter, int position, MatchMethod... methods)
    {
        for (MatchMethod method : methods)
        {
            data = method.match(data, pdata, currentCharacter, position);
        }

        return data;
    }

    protected MatchData matchMultiple(char currentCharacter, int position, MatchMethod method)
    {
        return matchMultiple(new MatchData(), currentCharacter, position, method);
    }

    protected MatchData matchMultiple(MatchData data, char currentCharacter, int position, MatchMethod method)
    {
        return matchMultiple(data, new PersistentMatchData(0), currentCharacter, position, method);
    }

    protected MatchData matchMultiple(MatchData data, PersistentMatchData pdata, char currentCharacter, int position, MatchMethod method)
    {
        data = method.match(data, pdata, currentCharacter, position);

        pdata.decrement();
        endData(data, pdata);

        return data;
    }

    // Match Methods

    protected MatchMethod charMatcher(char c)
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> match(data, pdata, c, currentCharacter);
    }

    protected MatchMethod stringMatch(String s)
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> match(data, pdata, s, currentCharacter, pos);
    }

    protected MatchMethod regexMatch(@Language("RegExp") @NonNls String regex)
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> matchRegex(data, pdata, regex, currentCharacter);
    }

    protected MatchMethod finalMatch(MatchMethod method)
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> {
            data = method.match(data, pdata, currentCharacter, pos);
            data.setFullMatch(data.isHasMatch());
            return data;
        };
    }

    protected MatchMethod identifierMatch()
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> matchIdentifier(data, pdata, currentCharacter);
    }

    protected MatchMethod numberMatch()
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> matchNumber(data, pdata, currentCharacter);
    }

    protected MatchMethod fullNumberMatch()
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> matchFullNumber(data, pdata, currentCharacter);
    }

    protected MatchMethod multipleMatch(MatchMethod method)
    {
        return (MatchData data, PersistentMatchData pdata, char currentCharacter, int pos) -> matchMultiple(data, pdata, currentCharacter, pos, method);
    }

    // Specific matching

    protected MatchData match(String s, char currentCharacter, int position)
    {
        return match(new MatchData(), s, currentCharacter, position);
    }

    protected MatchData match(char c, char currentCharacter)
    {
        return match(new MatchData(), c, currentCharacter);
    }

    protected MatchData match(MatchData data, String s, char currentCharacter, int position)
    {
        return match(data, new PersistentMatchData(0), s, currentCharacter, position);
    }

    protected MatchData match(MatchData data, char c, char currentCharacter)
    {
        return match(data, new PersistentMatchData(0), c, currentCharacter);
    }

    protected MatchData match(MatchData data, PersistentMatchData pdata, String s, char currentCharacter, int position)
    {
        if (!data.isHasMatch())
            return data;

        char c = s.charAt(position);

        return match(data, pdata, c, currentCharacter).sType(MatchType.STRING);
    }

    protected MatchData match(MatchData data, PersistentMatchData pdata, char c, char currentCharacter)
    {
        if(checkData(data, pdata)) return data;
        if (!data.isHasMatch()) return data;

        data.setHasMatch(c == currentCharacter);

//        endData(data, pdata);
        pdata.increment();

        return data.sType(MatchType.CHAR);
    }

    protected MatchData matchRegex(@Language("RegExp") @NonNls String regex, char currentCharacter)
    {
        return matchRegex(new MatchData(), regex, currentCharacter);
    }

    protected MatchData matchRegex(MatchData data, @Language("RegExp") @NonNls String regex, char currentCharacter)
    {
        return matchRegex(data, new PersistentMatchData(0), regex, currentCharacter);
    }

    protected MatchData matchRegex(MatchData data, PersistentMatchData pdata, @Language("RegExp") @NonNls String regex, char currentCharacter)
    {
        if(checkData(data, pdata)) return data;
        if (!data.isHasMatch()) return data;

        data.setHasMatch(String.valueOf(currentCharacter).matches(regex));

//        endData(data, pdata);
        pdata.increment();

        return data.sType(MatchType.REGEX);
    }

    // Helper matching

    protected MatchData matchIdentifier(char currentCharacter)
    {
        return matchIdentifier(new MatchData(), currentCharacter);
    }

    protected MatchData matchNumber(char currentCharacter)
    {
        return matchNumber(new MatchData(), currentCharacter);
    }

    protected MatchData matchFullNumber(char currentCharacter)
    {
        return matchFullNumber(new MatchData(), currentCharacter);
    }

    protected MatchData matchIdentifier(MatchData data, char currentCharacter)
    {
        return matchIdentifier(data, new PersistentMatchData(0), currentCharacter);
    }

    protected MatchData matchNumber(MatchData data, char currentCharacter)
    {
        return matchNumber(data, new PersistentMatchData(0), currentCharacter);
    }

    protected MatchData matchFullNumber(MatchData data, char currentCharacter)
    {
        return matchNumber(data, new PersistentMatchData(0), currentCharacter);
    }

    protected MatchData matchIdentifier(MatchData data, PersistentMatchData pdata, char currentCharacter)
    {
        return matchRegex(data, pdata, "[a-zA-Z0-9_]", currentCharacter).sType(MatchType.IDENTIFIER);
    }

    protected MatchData matchNumber(MatchData data, PersistentMatchData pdata, char currentCharacter)
    {
        return matchRegex(data, pdata, "[0-9]", currentCharacter).sType(MatchType.NUMBER);
    }

    protected MatchData matchFullNumber(MatchData data, PersistentMatchData pdata, char currentCharacter)
    {
        return matchRegex(data, pdata, "(-|[0-9]|e)", currentCharacter).sType(MatchType.FULL_NUMBER);
    }

    // Specific final matching

    private MatchData fixFinal(MatchData data)
    {
        data.setFullMatch(data.isHasMatch());
        return data;
    }

    protected MatchData matchFinal(String s, char currentCharacter, int position)
    {
        return fixFinal(match(s, currentCharacter, position));
    }

    protected MatchData matchFinal(char c, char currentCharacter)
    {
        return fixFinal(match(c, currentCharacter));
    }

    protected MatchData matchFinal(MatchData data, String s, char currentCharacter, int position)
    {
        return fixFinal(match(data, s, currentCharacter, position));
    }

    protected MatchData matchFinal(MatchData data, char c, char currentCharacter)
    {
        return fixFinal(match(data, c, currentCharacter));
    }

    protected MatchData matchFinal(MatchData data, PersistentMatchData pdata, String s, char currentCharacter, int position)
    {
        return fixFinal(match(data, pdata, s, currentCharacter, position));
    }

    protected MatchData matchFinal(MatchData data, PersistentMatchData pdata, char c, char currentCharacter)
    {
        return fixFinal(match(data, pdata, c, currentCharacter));
    }

    protected MatchData matchFinalRegex(@Language("RegExp") @NonNls String regex, char currentCharacter)
    {
        return fixFinal(matchRegex(regex, currentCharacter));
    }

    protected MatchData matchFinalRegex(MatchData data, @Language("RegExp") @NonNls String regex, char currentCharacter)
    {
        return fixFinal(matchRegex(data, regex, currentCharacter));
    }

    protected MatchData matchFinalRegex(MatchData data, PersistentMatchData pdata, @Language("RegExp") @NonNls String regex, char currentCharacter)
    {
        return fixFinal(matchRegex(data, pdata, regex, currentCharacter));
    }

    // Helper final matching

    protected MatchData matchFinalIdentifier(char currentCharacter)
    {
        return fixFinal(matchIdentifier(currentCharacter));
    }

    protected MatchData matchFinalNumber(char currentCharacter)
    {
        return fixFinal(matchNumber(currentCharacter));
    }

    protected MatchData matchFinalFullNumber(char currentCharacter)
    {
        return fixFinal(matchFullNumber(currentCharacter));
    }

    protected MatchData matchFinalIdentifier(MatchData data, char currentCharacter)
    {
        return fixFinal(matchIdentifier(data, currentCharacter));
    }

    protected MatchData matchFinalNumber(MatchData data, char currentCharacter)
    {
        return fixFinal(matchNumber(data, currentCharacter));
    }

    protected MatchData matchFinalFullNumber(MatchData data, char currentCharacter)
    {
        return fixFinal(matchFullNumber(data, currentCharacter));
    }

    protected MatchData matchFinalIdentifier(MatchData data, PersistentMatchData pdata, char currentCharacter)
    {
        return fixFinal(matchIdentifier(data, pdata, currentCharacter));
    }

    protected MatchData matchFinalNumber(MatchData data, PersistentMatchData pdata, char currentCharacter)
    {
        return fixFinal(matchNumber(data, pdata, currentCharacter));
    }

    protected MatchData matchFinalFullNumber(MatchData data, PersistentMatchData pdata, char currentCharacter)
    {
        return fixFinal(matchFullNumber(data, pdata, currentCharacter));
    }
}
