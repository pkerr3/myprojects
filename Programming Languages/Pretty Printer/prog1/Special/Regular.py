# Regular -- Parse tree node strategy for printing regular lists

from Special import Special
from Tokens import TokenType
import sys

class Regular(Special):
    # TODO: Add fields and modify the constructor as needed.
    
    def __init__(self, token):
        self.tok = token

    def print(self, t, n = 0, p = False):
        for _ in range(n):
            sys.stdout.write(' ')
        if self.tok.getType() == TokenType.RPAREN:
            if p:
                sys.stdout.write(")\n")
        elif self.tok.getType() == TokenType.LPAREN:
            sys.stdout.write("(")
        else:
            sys.stdout.write(self.tok.getName() + ' ')