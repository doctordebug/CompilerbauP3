
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;
import edu.uap.Compiler.TramLabel;

import java.util.HashMap;
import java.util.LinkedList;

public class IfNode extends Node
{
    public IfNode()
    {
        super("IF");
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        TramLabel tl1 = new TramLabel();
        TramLabel tl2 = new TramLabel();
        LinkedList<Instruction> result = getCondition().code(rho, n);

        result.add(new Instruction(Instruction.IFZERO, tl1));
        result.addAll(getThen().code(rho, n));
        result.add(new Instruction(Instruction.GOTO, tl2));
        /*Nop als Sprungaddresse eingefügt. für ifzero*/
        result.add(new Instruction(Instruction.NOP, tl1));
        result.addAll(getElse().code(rho, n));
        /*Nop als Sprungaddresse eingefügt. für goto*/
        Instruction nop = new Instruction(Instruction.NOP,tl2);
        result.add(nop);

        return result;
    }

    private Node getCondition(){
        return getChildren().get(0);
    }

    private Node getThen(){
        return getChildren().get(1);
    }

    private Node getElse(){
        return getChildren().get(2);
    }
}
