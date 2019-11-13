# Cond -- Parse tree node strategy for printing the special form cond

from Special import Special
import sys

class Cond(Special):
    # TODO: Add fields and modify the constructor as needed.
    def __init__(self, name):
        self.name = name

    def print(self, t, n, p):
        sys.stdout.write(self.name + " ")

