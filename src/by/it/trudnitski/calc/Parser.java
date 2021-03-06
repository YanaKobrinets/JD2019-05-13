package by.it.trudnitski.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Var calc(String expression) throws CalcExeption {
        String[] part = expression.split(Patterns.OPERATION);
        Var two=Var.createVar(part[1]);
        if(expression.contains("=")){
           return Var.saveVar(part[0],two);
        }
        Var one=Var.createVar(part[0]);
            if(one==null||two==null)
                return null; //TODO create error
            Pattern pattern= Pattern.compile(Patterns.OPERATION);
            Matcher matcher = pattern.matcher(expression);
            if(matcher.find()){
                String operation=matcher.group();
                 switch(operation){
                    case "+":
                        return one.add(two);
                    case "-":
                        return one.sub(two);
                    case "*":
                        return one.mul(two);
                    case "/":
                        return one.div(two);
                }
            }
            return null;
    }
}
