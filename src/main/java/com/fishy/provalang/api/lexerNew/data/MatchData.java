package com.fishy.provalang.api.lexerNew.data;

import lombok.Data;

@Data
public class MatchData
{
    public boolean hasMatch;
    public boolean isFullMatch;

    public MatchType type;

    private int matchNumber = 0;

    public MatchData()
    {
        hasMatch = true;
        isFullMatch = false;
    }

    public MatchData(boolean full)
    {
        hasMatch = true;
        isFullMatch = full;
    }

    public MatchData(boolean match, boolean full)
    {
        hasMatch = match;
        isFullMatch = full;
    }

    public MatchData(int matchNumber)
    {
        this.matchNumber = matchNumber;
    }

    public static MatchData of(PersistentMatchData data)
    {
        return new MatchData(data.getPreviousMatch());
    }

    public void setHasMatch(boolean value)
    {
        hasMatch = value;
    }

    public void setFullMatch(boolean value)
    {
        isFullMatch = value;
    }

    public MatchData sType(MatchType type)
    {
        this.type = type;
        return this;
    }

    public int getMatchNumber()
    {
        return matchNumber;
    }

    public void increment()
    {
        matchNumber++;
    }

    public enum MatchType
    {
        CHAR,
        STRING,
        REGEX,

        IDENTIFIER,
        NUMBER,
        FULL_NUMBER
    }
}
