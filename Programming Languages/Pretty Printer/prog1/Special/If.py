# If -- Parse tree node strategy for printing the special form if

from Special import Special
import sys

class If(Special):
    # TODO: Add fields and modify the constructor as needed.
    def __init__(self):
        pass

    def print(self, t, n, p):
        sys.stdout.write("if ")
