
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;

import java.util.HashMap;
import java.util.LinkedList;

public class ParamsNode extends Node
{
    public ParamsNode()
    {
        super("PARAMS");
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {

        return super.code(rho, n);
    }
}
