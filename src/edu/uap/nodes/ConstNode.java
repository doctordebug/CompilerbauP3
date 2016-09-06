
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;

import java.util.HashMap;
import java.util.LinkedList;

public class ConstNode extends Node
{
    public ConstNode(Integer value)
    {
        super("CONST", value);
    }

    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        LinkedList<Instruction> result = new LinkedList<>();
        result.add(new Instruction(Instruction.CONST, (int)(getAttribute())));
        return result;
    }
}
