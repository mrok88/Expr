grammar Expr;
start : expr ;
expr:	left=expr op=('*'|'/') right=expr #opExpr
    |	left=expr op=('+'|'-') right=expr #opExpr
    |	INT    #atomExpr
    |	'(' expr ')'   #parenExpr
    ;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;