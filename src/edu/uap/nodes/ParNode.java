
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;

import java.util.HashMap;
import java.util.LinkedList;

public class ParNode extends Node
{
    public ParNode()
    {
        super("PAR");
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        return super.code(rho, n);
    }
}
