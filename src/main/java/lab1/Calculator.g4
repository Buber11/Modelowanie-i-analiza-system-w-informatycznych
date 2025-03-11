grammar Calculator;

prog: expression EOF;

expression
    : term ( ( PLUS | MINUS ) term )*
    ;

term
    : factor ( ( MULT | DIV ) factor )*
    ;



factor
    : ( PLUS | MINUS ) factor
    | LPAREN expression RPAREN
    | NUMBER
    ;

NUMBER : [0-9]+ ( '.' [0-9]+ )? ;
PLUS : '+' ;
MINUS : '-' ;
MULT : '*' ;
DIV : '/' ;
LPAREN : '(' ;
RPAREN : ')' ;
WS : [ \t\r\n]+ -> skip ;