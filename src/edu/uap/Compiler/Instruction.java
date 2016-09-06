package edu.uap.Compiler;

/**
 * Created by olisa_000 on 05.09.16.
 */
public class Instruction {
    private int opcode;
    private Object arg1;
    private Object arg2;
    private Object arg3;
    private TramLabel label;

    public final static int CONST = 1;
    public final static int LOAD = 2;
    public final static int STORE = 3;
    public final static int ADD = 4;
    public final static int SUB = 5;
    public final static int MUL = 6;
    public final static int DIV = 7;
    public final static int LT = 8;
    public final static int GT = 9;
    public final static int EQ = 10;
    public final static int NEQ = 11;
    public final static int IFZERO = 12;
    public final static int GOTO = 13;
    public final static int HALT = 14;
    public final static int NOP = 15;
    public final static int INVOKE = 16;
    public final static int RETURN = 17;
    public final static int POP = 18;



    public Instruction(int opcode, Integer arg1, Integer arg2, Integer arg3)
    {
        this(opcode, arg1, arg2);
        this.arg3 = arg3;
    }


    public Instruction(int opcode, Integer arg1, Integer arg2)
    {
        this(opcode, arg1);
        this.arg2 = arg2;
    }


    public Instruction(int opcode, Integer arg1)
    {
        this(opcode);
        this.arg1 = arg1;
    }


    public Instruction(int opcode, TramLabel tl)
    {
        this(opcode);
        this.arg1 = tl;
        this.label = tl;
    }
    public Instruction(int opcode, TramLabel tl, Integer arg2){
        this(opcode);
        this.arg1 = tl;
        this.arg2 = arg2;
        this.label = tl;
    }

    public Instruction(int opcode, int arg1, TramLabel tl, int arg3) {
        this(opcode);
        this.arg1 = arg1;
        this.arg2 = tl;
        this.arg3 = arg3;
        this.label = tl;
    }


    public Instruction(int opcode)
    {
        this.opcode = opcode;
    }

    private Integer getIntValue(Object o){
        if(o instanceof Integer) return (Integer) o;
        if(o instanceof TramLabel){

            return ((TramLabel) o).getAddress();
        }
        return null;
    }

    public void setLabel(TramLabel label) {
        this.label = label;
    }

    public TramLabel getLabel() {
        return label;
    }

    public boolean hasLabel(){
        return (this.label != null);
    }

    public int getArg1()
    {
        return getIntValue(arg1);
    }


    public void setArg1(Integer arg1)
    {
        this.arg1 = arg1;
    }


    public int getArg2()
    {
        return getIntValue(arg2);
    }


    public void setArg2(Integer arg2)
    {
        this.arg2 = arg2;
    }


    public int getArg3()
    {
        return getIntValue(arg3);
    }


    public void setArg3(Integer arg3)
    {
        this.arg3 = arg3;
    }


    public int getOpcode()
    {
        return opcode;
    }


    public void setOpcode(int opcode)
    {
        this.opcode = opcode;
    }


    @Override
    public String toString()
    {
        String retStr = "";

        switch (opcode)
        {
            case Instruction.CONST:
                retStr += "CONST";
                break;
            case Instruction.LOAD:
                retStr += "LOAD";
                break;
            case Instruction.STORE:
                retStr += "STORE";
                break;
            case Instruction.ADD:
                retStr += "ADD";
                break;
            case Instruction.SUB:
                retStr += "SUB";
                break;
            case Instruction.MUL:
                retStr += "MUL";
                break;
            case Instruction.DIV:
                retStr += "DIV";
                break;
            case Instruction.LT:
                retStr += "LT";
                break;
            case Instruction.GT:
                retStr += "GT";
                break;
            case Instruction.EQ:
                retStr += "EQ";
                break;
            case Instruction.NEQ:
                retStr += "NEQ";
                break;
            case Instruction.IFZERO:
                retStr += "IFZERO";
                break;
            case Instruction.GOTO:
                retStr += "GOTO";
                break;
            case Instruction.HALT:
                retStr += "HALT";
                break;
            case Instruction.NOP:
                retStr += "NOP";
                break;
            case Instruction.INVOKE:
                retStr += "INVOKE";
                break;
            case Instruction.RETURN:
                retStr += "RETURN";
                break;
            case Instruction.POP:
                retStr += "POP";
                break;
            default:
                retStr += "ERROR";
        }

        if (arg1 != null)
        {
            retStr += " " + getIntValue(arg1);

            if (arg2 != null)
            {
                retStr += " " + getIntValue(arg2);

                if (arg3 != null)
                {
                    retStr += " " + getIntValue(arg3);
                }
            }
        }

        return retStr;
    }

}
