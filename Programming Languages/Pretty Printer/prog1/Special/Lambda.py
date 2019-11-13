# Lambda -- Parse tree node strategy for printing the special form lambda

from Special import Special
import sys

class Lambda(Special):
    # TODO: Add fields and modify the constructor as needed.
    def __init__(self):
        pass

    def print(self, t, n, p):
        sys.stdout.write("lambda ")