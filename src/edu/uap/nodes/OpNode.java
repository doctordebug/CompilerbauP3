
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;

import java.util.HashMap;
import java.util.LinkedList;

public class OpNode extends Node
{
    public static final String ADD  = "+";
    public static final String SUB  = "-";
    public static final String MULT = "*";
    public static final String DIV  = "/";
    public static final String EQ   = "==";
    public static final String NEQ  = "!=";
    public static final String LT   = "&lt;";
    public static final String GT   = "&gt;";

    public OpNode(String value)
    {
        super("OP", value);
    }

    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        //LinkedList<Instruction> result = getLeftSide().code(rho, n);
        //result.addAll(getRightSide().code(rho, n));
        //result.addAll(super.code(rho, n));
        boolean firstValue = true;
        LinkedList<Instruction> result = new LinkedList<>();
        for(Node value: getChildren()) {
            int operator = -1;

            result.addAll(value.code(rho, n));
            String code = (String) getAttribute();
            if(!firstValue) {
                switch (code) {
                    case ADD:
                        result.add(new Instruction(Instruction.ADD));
                        break;
                    case SUB:
                        result.add(new Instruction(Instruction.SUB));
                        break;
                    case MULT:
                        result.add(new Instruction(Instruction.MUL));
                        break;
                    case DIV:
                        result.add(new Instruction(Instruction.DIV));
                        break;
                    case EQ:
                        result.add(new Instruction(Instruction.EQ));
                        break;
                    case NEQ:
                        result.add(new Instruction(Instruction.NEQ));
                        break;
                    case LT:
                        result.add(new Instruction(Instruction.LT));
                        break;
                    case GT:
                        result.add(new Instruction(Instruction.GT));
                        break;
                    default:
                        /*TODO: Insert Exception here*/
                        System.out.println("CODE NOT FOUND:" + code);
                }
            }
            firstValue = false;
        }
        return result;
    }
/*
    public Node getLeftSide() {
        return getChildren().get(0);
    }

    public Node getRightSide() {
        return getChildren().get(1);
    }
*/
}
