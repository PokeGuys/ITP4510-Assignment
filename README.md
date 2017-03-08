# ITP4510 Assignment
## Cross-Reference Map
Delimiter: ``` _.,;()[]{}+-*/!=<>\t```

### Reuirement
1. Skip reserved word
```
abstract, boolean, break, byte, case, catch, char, class, const, continue, default, do,
double, else, extends, false, final, finally, float, for, goto, if, implements, import,
instanceof, int, interface, long, native, new, null, operator, package, private,
protected, public, rest, return, short, static, strictfp, super, switch, synchronized,
this, throw, throws, transient, true, try, void, volatile, while 
```
2. Skip String
```
String a = "test";
char a = 'test';
String a = "test + test = testtest?";
char a = '\u0108';
```
3. Skip Number
```
Valid: 
byte b = 100;
short s = 10000;
int i = 100000;
int hexVal = 0x1a;
int binVal = 0b11010;
double d1 = 123.4;
double d2 = 1.234e2;
float f1  = 123.4f;
long creditCardNumber = 1234_5678_9012_3456L;
long socialSecurityNumber = 999_99_9999L;
float pi =  3.14_15F;
long hexBytes = 0xFF_EC_DE_5E;
long hexWords = 0xCAFE_BABE;
long maxLong = 0x7fff_ffff_ffff_ffffL;
byte nybbles = 0b0010_0101;
long bytes = 0b11010010_01101001_10010100_10010010;

Invalid:
float pi1 = 3_.1415F;
float pi2 = 3._1415F;
long socialSecurityNumber1 = 999_99_9999_L;
int x2 = 52_;
int x4 = 0_x52;
int x5 = 0x_52;
int x7 = 0x52_;
```
4. Skip Operator
```
~ % instanceof & ^ | && || ? :
```
5. Presented by alphabetical order
6. Line number with ascending order
7. Binary searching algo
8. NOT ALLOWED to use data structure provided by JAVA API.
9. Linkedlist to store line number for each identifier

### Problems
1. Split Whole String into pieces because of delimiter
```
String a = "test + test = testtest?";
It turns out.
String
a
"test 
test <- here is the problem
testtest?"
```
2. Number literal cannot be parsed by JAVA API
```
long maxLong = 0x7fff_ffff_ffff_ffffL;
byte nybbles = 0b0010_0101;
float pi =  3.14_15F;

Suffix: L/D/F
Underscore between number
```
3. String determinater false-positive for escape string
```
String a = "test\"\"test";

Should check the string is it contain escape string before determinate it is end of String
```

### Rules
1. Underscore in number
```
You can place underscores only between digits; you cannot place underscores in the following places:

1. At the beginning or end of a number
2. Adjacent to a decimal point in a floating point literal
3. Prior to an F or L suffix
4. In positions where a string of digits is expected
```

### Routine
1. Initial reserved word dictionary into tree from hardcoded array which i called it "dictionary".
2. Read the input Java source file line by line and log line number.
3. Split word.
4. Check is it in the "dictionary". If so, skip.
5. Check the "StringStart" flag. If true, skip. / Check is there anything in Stack and that "word" is not contain String literal. If so, skip.
6. Check is it a operator. If so, skip.
7. Check is it a number. If so, skip.
8. You got a identifier. Check is it inerted into "tree". If not, insert into "tree".
9. Insert "currentLine" into that identifier's linkedlist.
10. Goto step 2 until no more line.
11. Print out result with alphabetical order and the line number with ascending order
