# SymbolBalance
Reads through a Java file and checks for sinple syntatical errors.
Has two methods, as defined in the SymbolBalanceInterace: setFile + checkFile

setFile: takes in a String representing the path to the file that should be checked

checkFile: reads in the file character by character and check to make sure that all { }’s, ( )'s, [ ]'s, " "’s, and /* */’s are properly balanced. ignores characters within literal strings (" ") and comments blocks (/* */). Processes the file by iterating through it one character at a time.

three types of errors that it encounters:
1) file ends with one or more opening symbols missing their corresponding closing symbols.
2) a closing symbol without an opening symbol.
3) a mismatch between closing and opening symbols (for example: { [ } ] ).

Once an error is encountered, returns a BalanceError object with error info. Every error type has a class that descends from BalanceError with its own parameters.
I use the three methods: 
MismatchError(int lineNumber, char currentSymbol, char symbolPopped) - if symbol mismatch after popping stack
EmptyStackError(int lineNumber) - if empty stack popped
NonEmptyStackError(char topElement, int sizeOfStack) - Non-empty stack after parsing entire file
 
If no error is found, returns null
