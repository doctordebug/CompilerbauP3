package edu.uap.Compiler;

/**
 * Created by olisa_000 on 05.09.16.
 */
public class AddressPair {
    private Object loc;
    private int nl;

    public AddressPair(Object loc, int nl){
        this.loc = loc;
        this.nl = nl;
    }

    public int getLoc(){
        if(loc instanceof TramLabel)
            return ((TramLabel)loc).getAddress();
        if(loc instanceof TramLabel)
            return ((TramLabel)loc).getAddress();
        return (Integer)loc;
    }

    public TramLabel getLabel() {
        return (TramLabel)loc;
    }

    public int getNestingLevel() {
        return nl;
    }
}
