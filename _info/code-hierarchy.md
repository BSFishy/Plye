---
layout: default
title: Code Hierarchy
---
# Code Hierarchy
The code hierarchy is the way in which the AST (Abstract Syntax Tree) is defined. Plye chooses to be a highly extensible and editable language. For that, the choice has been made to use many interfaces and abstract classes rather than specific classes, to provide the ability for other users to create their own custom content extending the interfaces and classes already implemented. This provides for a high level of extensibility while still maintaining functionality

## AST
The Plye AST (Abstract Syntax Tree) is an irregular heterogeneous AST. This means that each node has specific fields that relate to specific parts of the AST.

For example, a `Class` node would have a number of fields, including its name, fields, methods, etc. Due to this design choice, Plye uses AST visitors, which are similar to Lexer types in that they match specific parts of the input stream then cast to the data.

Plye also uses `Runners` which can run custom AST nodes.