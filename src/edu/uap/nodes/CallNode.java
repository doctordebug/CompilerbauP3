
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;
import edu.uap.Compiler.TramLabel;

import java.util.HashMap;
import java.util.LinkedList;

public class CallNode extends Node
{
    public CallNode()
    {
        super("CALL");
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        AddressPair address = rho.get(getName());
        LinkedList<Instruction> result = new LinkedList<>();
        System.out.println(getName());

        for(Node node:getParams())
            result.addAll(node.code(rho,n));

        TramLabel l = address.getLabel();
        int nestingLevel = address.getNestingLevel();

        result.add(new Instruction(Instruction.INVOKE,getParams().size(),2,n-nestingLevel));
        return result;
    }

    private String getName(){
        return (String)getChildren().get(0).getAttribute();
    }

    private LinkedList<Node> getParams(){
        return getChildren().get(1).getChildren();
    }
}
