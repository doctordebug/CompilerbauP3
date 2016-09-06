
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;

import java.util.HashMap;
import java.util.LinkedList;

public class SemiNode extends Node
{
    public SemiNode()
    {
        super("SEMI");
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        LinkedList<Instruction> result = new LinkedList<>();
        for(int i=0; i< (getChildren().size() - 1); i++){
            result.addAll(getChildren().get(i).code(rho,n));
            result.add(new Instruction(Instruction.POP));
        }
        result.addAll(getChildren().get(getChildren().size()-1).code(rho,n));
        return result;
    }


}
