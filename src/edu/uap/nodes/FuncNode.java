
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;
import edu.uap.Compiler.TramLabel;

import java.util.HashMap;
import java.util.LinkedList;

public class FuncNode extends Node {
    public FuncNode() {
        super("FUNC");
    }

    public HashMap<String, AddressPair> elab_def(HashMap<String, AddressPair> rho, int nl) {
        rho.put(getName(), new AddressPair(new TramLabel(), nl));
        return rho;

    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        TramLabel l = rho.get(getName()).getLabel();

        //Sprungmarke zur Funktion
        LinkedList<Instruction> result = new LinkedList<>();
        result.add(new Instruction(Instruction.NOP, l));

        for (int pos = 0; pos < getParameters().size(); pos++) {
            rho.put((String) getParameters().get(pos).getAttribute(), new AddressPair(new TramLabel(pos), n));
        }
        getParameters().get(0).code(rho,n);
        for(Node node: getBody()){
            result.addAll(node.code(rho, n));
        }
        result.add(new Instruction(Instruction.RETURN));
        return result;
    }

    private String getName() {
        return (String) getChildren().get(0).getAttribute();
    }

    private LinkedList<Node> getParameters() {
        return getChildren().get(1).getChildren();
    }

    private LinkedList<Node> getBody() {
        return getChildren().get(2).getChildren();
    }


}
