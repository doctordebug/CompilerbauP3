
package edu.uap.nodes;

import com.sun.deploy.util.ArrayUtil;
import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;
import edu.uap.Compiler.TramLabel;

import java.util.HashMap;
import java.util.LinkedList;

public class LetNode extends Node
{
    public LetNode()
    {
        super("LET");
    }

    @Override
    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        n++;
        TramLabel l = new TramLabel();
        LinkedList<Instruction> result = new LinkedList<>();
        result.add(new Instruction(Instruction.GOTO, l));

        elab_def(rho, n);

        for (Node def : getFunctions()) {
            result.addAll(def.code(rho, n));
        }

        /*Wie in if-node: Label generieren*/
        result.add(new Instruction(Instruction.NOP, l));

        for (Node body : getBody()) {
            result.addAll(body.code(rho, n));
        }

        return result;
    }

    /*In LET-Def können mehrere Funktionen deklariert werden*/
    public LinkedList<Node> getFunctions() {
        return getChildren().get(0).getChildren();
    }

    public LinkedList<Node> getBody() {
        return getChildren().get(1).getChildren();
    }

    /*jede funktion hat eigene Adressumgebung*/
    public void elab_def(HashMap<String, AddressPair> rho, int nl){
        for (Node def : getFunctions()) {
            ((FuncNode)def).elab_def(rho,nl);
        }
    }

}
