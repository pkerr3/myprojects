# Cons -- Parse tree node class for representing a Cons node

from Tree import Node
from Tree import Ident
from Special import *
from Tokens import TokenType

class Cons(Node):
    def __init__(self, a, d):
        self.car = a
        self.cdr = d
        self.parseList()

    # parseList() `parses' special forms, constructs an appropriate
    # object of a subclass of Special, and stores a pointer to that
    # object in variable form.   It would be possible to fully parse
    # special forms at this point. Since this causes complications
    # when using (incorrect) programs as data, it is easiest to let
    # parseList only look at the car for selecting the appropriate
    # object from the Special hierarchy and to leave the rest of
    # parsing up to the interpreter.
    def parseList(self):
        # TODO: implement this function and any helper functions
        # you might need

        # Does not include NULL/NIL yet
        
        # These don't have their own Special class. So they will get sent to Regular

        #is this necessary because true/false/str/int never hit these because 
        #the way the parser is set up
        if hasattr(self.car, "getType"):
            if self.car.getType() == TokenType.RPAREN or self.car.getType() == TokenType.LPAREN:
                #TODO: implement regular
                self.form = Regular(self.car)
                return
            
        #If/Else chain to construct the Special class node
        name = self.car.getName()
        if name == "begin":
            self.form = Begin()
        
        elif (">" in name or "<" in name or "==" in name or "!" in name):
            self.form = Cond(name)

        elif name == "define":
            self.form = Define()
        
        elif name == "if":
            self.form = If()
        
        elif name == "lambda":
            self.form = Lambda()
        
        elif name == "let":
            self.form = Let()
        
        elif name == "set":
            self.form = Set()
        
        elif name == "quote" or name == "'":
            self.form = Quote()
        
        else:
            self.form = Regular(self.car)

    def print(self, n, p=False):
        self.form.print(self, n, p)
        if self.cdr != None:
            self.cdr.print(0)

    def isPair(self):
        return True
    

if __name__ == "__main__":
    c = Cons(Ident("Hello"), Ident("World"))
    c.print(0)
