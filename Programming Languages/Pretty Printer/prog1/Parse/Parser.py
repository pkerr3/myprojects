# Parser -- the parser for the Scheme printer and interpreter
#
# Defines
#
#   class Parser
#
# Parses the language
#
#   exp  ->  ( rest
#         |  #f
#         |  #t
#         |  ' exp
#         |  integer_constant
#         |  string_constant
#         |  identifier
#    rest -> )
#         |  exp+ [. exp] )
#
# and builds a parse tree.  Lists of the form (rest) are further
# `parsed' into regular lists and special forms in the constructor
# for the parse tree node class Cons.  See Cons.parseList() for
# more information.
#
# The parser is implemented as an LL(0) recursive descent parser.
# I.e., parseExp() expects that the first token of an exp has not
# been read yet.  If parseRest() reads the first token of an exp
# before calling parseExp(), that token must be put back so that
# it can be re-read by parseExp() or an alternative version of
# parseExp() must be called.
#
# If EOF is reached (i.e., if the scanner returns None instead of a token),
# the parser returns None instead of a tree.  In case of a parse error, the
# parser discards the offending token (which probably was a DOT
# or an RPAREN) and attempts to continue parsing with the next token.

import sys
from Tokens import TokenType
from Tree import *

class Parser:
    def __init__(self, s):
        self.scanner = s
        self.data = None

    def parseExp(self, tok = None):

        if tok == None:
            tok = self.scanner.getNextToken()
            if tok == None:
                exit(0)
        if tok.getType() == TokenType.LPAREN:
            node = Cons(tok, self.parseExp())
            return node


        if tok.getType() == TokenType.IDENT:
            return Cons(tok, self.parseExp())    
        elif tok.getType() == TokenType.INT:
            return IntLit(tok)
        elif tok.getType() == TokenType.STR:
            return StrLit(tok.getStrVal())
        elif tok.getType() == TokenType.QUOTE: #Swapped with BoolLit to stop ' from producing #t in the output
            return Cons(Ident("'"), self.parseExp())
        elif tok.getType() == TokenType.TRUE or tok.getType() == TokenType.FALSE:
            return BoolLit(tok)
        
        elif tok.getType() == TokenType.RPAREN or tok.getType() == TokenType.LPAREN:
            return self.parseRest(tok)
        else:
            return None

    def parseRest(self, tok):
        if (tok.getType() == TokenType.RPAREN):
            return Nil.getInstance()
        
        elif tok.getType() == TokenType.LPAREN:
            return Cons(tok, self.parseExp(self.scanner.getNextToken()))

        
        return None

    # TODO: Add any additional methods you might need

    def __error(self, msg):
        sys.stderr.write("Parse error: " + msg + "\n")
