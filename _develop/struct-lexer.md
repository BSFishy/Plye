---
layout: default
title: Lexer
slug: struct-lexer
---
# Provalang Lexer
The main area of Provalang that needs to be extensible is the lexer and parser. These are the two main things that define how the language looks, and are what allows it to be extended to provide more features that are not included by default.

Using this concept, the lexer contains one *main* type to allow extension. The interface, `ILexerTokenType` allows anyone to create their own token type as well as token data.

## Procedure
> The procedure for Provalang is currently unoptimized, and is most definitely going to be changed in the future.

### Current
The current process for lexing, using the default lexer looks something like this:

1. Read out the entirety of the target file
2. Split the entirety of the target file into individual characters
3. Run through the lexing loop until a full token is found
   1. If it is the first character being tested, create a list of all potential tokens
   2. Eliminate potential token types by checking using the `ILexerTokenType.canCast` method
   3. Check if the list of potential tokens is empty, if so, throw an error as the token is unrecognized
   4. Sort the token types based on the `@OverridableLexerTokenType` annotation
   5. Check to see if any type in the sorted list should cast using the `ILexerTokenType.shouldCast` method
      1. If it should cast, cast using the `ILexerTokenType.cast` method and return the result and type in a `LexerToken`
      2. If not, continue the loop
   6. Once a token is casted, add that to the return list
4. Once the loop is finished, return the list of tokens

### Planned
As one could imagine, the current implementation is quite unoptimized. Here is the process that is planned to be implemented:

1. Get an input stream for the file and loop while there is more input
   1. If it is the first character in the token, add the default potential types to a list
   2. Feed the stream into those potential types, and eliminate types that cannot cast to the current data
   3. Sort the potential types based on overrides
   4. Check if any potential types should cast
      1. If there are no potential types, throw an error, as the input data is unrecognized
      2. If a type should cast, allow it to cast and return that
      3. If no types should cast, continue feeding data and eliminating
   5. Add cast data to return list
2. Return the list generated

Now, that is the planned implementation, and is by no means the definite plan of attack to implement the lexer. With that being said, a few things might eventually be moved around, but even still, one could imagine that that will be much more optimized memory- and CPU-wise.

## More Soon!