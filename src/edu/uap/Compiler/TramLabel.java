package edu.uap.Compiler;

/**
 * Created by olisa_000 on 05.09.16.
 */
public class TramLabel {
    private int address;

    public TramLabel(){}

    public TramLabel(int address){
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}
