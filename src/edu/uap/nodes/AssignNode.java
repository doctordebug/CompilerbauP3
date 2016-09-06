
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;
import edu.uap.Compiler.TramLabel;

import java.util.HashMap;
import java.util.LinkedList;

public class AssignNode extends Node
{
    public AssignNode()
    {
        super("ASSIGN");
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        LinkedList<Instruction> result = getValue().code(rho, n);
        /*find id in rho*/
        if(!rho.keySet().contains(getId().getAttribute())){
            /*declare*/
            TramLabel tl = new TramLabel();
            AddressPair ap = new AddressPair(tl,n);
            rho.put((String)getId().getAttribute(),new AddressPair(tl,n));
        }else{
            /*change*/
            /*TODO stuff*/
        }
        AddressPair location = rho.get((String) getId().getAttribute());
        result.add(new Instruction(Instruction.STORE,location.getLabel(), n - location.getNestingLevel()));
        result.add(new Instruction(Instruction.LOAD,location.getLabel(), n - location.getNestingLevel()));

        return result;
    }

    private Node getId(){
        return getChildren().get(0);
    }

    private Node getValue(){
        return getChildren().get(1);
    }
}
