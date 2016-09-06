
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;

import java.util.HashMap;
import java.util.LinkedList;

public class IDNode extends Node
{
    public IDNode(String value)
    {
        super("ID", value);
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        LinkedList<Instruction> result = new LinkedList<>();
        AddressPair location = rho.get(getName());
        result.add(new Instruction(Instruction.LOAD, location.getLabel(), n - location.getNestingLevel()));
        return result;
    }

    private String getName(){
        return (String)getAttribute();
    }
}
