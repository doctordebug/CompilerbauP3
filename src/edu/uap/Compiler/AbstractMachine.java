package edu.uap.Compiler;
import java.util.Arrays;

/**
 * Created by olisa_000 on 05.09.16.
 */
public class AbstractMachine {

    private Instruction[] program;
    private static Integer[] stack;

    private static int TOP;
    private static int PP;
    private static int FP;
    private static int PC;
    private int opcode;
    private static boolean debugModus;

    public AbstractMachine(Instruction[] program, boolean debugModus) {
        this.program = program;
        AbstractMachine.debugModus = debugModus;
        stack = new Integer[1];


        TOP = -1;
        PP = 0;
        FP = 0;
        PC = 0;

        while (PC >= 0) {
            execute(program[PC]);
            //if(TOP > 20 && debugModus) PC = -1;
        }
        if (debugModus) {
            showResult();
        }
    }

    private void showResult() {
        System.out.println("Das Ergebnis der Berechnung ist :" + stack[TOP]);
    }

    public void execute(Instruction i) {
        checkArraySize();
        opcode = i.getOpcode();
        switch (opcode) {
            case Instruction.CONST:
                stack[TOP + 1] = i.getArg1();
                TOP++;
                PC++;
                break;
            case Instruction.LOAD:
                stack[TOP + 1] = stack[spp(i.getArg2(), PP, FP) + i.getArg1()];
                TOP++;
                PC++;
                break;
            case Instruction.STORE:
                stack[spp(i.getArg2(), PP, FP) + i.getArg1()] = stack[TOP];
                //TOP--;
                PC++;
                break;
            case Instruction.ADD:
                stack[TOP - 1] = stack[TOP - 1] + stack[TOP];
                TOP--;
                PC++;
                break;
            case Instruction.SUB:
                stack[TOP - 1] = stack[TOP - 1] - stack[TOP];
                TOP--;
                PC++;
                break;
            case Instruction.MUL:
                stack[TOP - 1] = stack[TOP - 1] * stack[TOP];
                TOP--;
                PC++;
                break;
            case Instruction.DIV:
                stack[TOP - 1] = stack[TOP - 1] / stack[TOP];
                TOP--;
                PC++;
                break;
            case Instruction.LT:
                if (stack[TOP - 1] < stack[TOP]) {
                    stack[TOP - 1] = 1;
                } else {
                    stack[TOP - 1] = 0;

                }
                TOP--;
                PC++;
                break;
            case Instruction.GT:
                if (stack[TOP - 1] > stack[TOP]) {
                    stack[TOP - 1] = 1;
                } else {
                    stack[TOP - 1] = 0;
                }
                TOP--;
                PC++;
                break;
            case Instruction.EQ:
                if (stack[TOP - 1] == stack[TOP]) {
                    stack[TOP - 1] = 1;
                } else {
                    stack[TOP - 1] = 0;
                }
                PC++;
                TOP--;
                break;
            case Instruction.NEQ:
                if (stack[TOP - 1] != stack[TOP]) {
                    stack[TOP - 1] = 1;
                } else {
                    stack[TOP - 1] = 0;
                }
                TOP--;
                PC++;
                break;
            case Instruction.IFZERO:
                if (stack[TOP] == 0) {
                    PC = i.getArg1();
                } else {
                    PC++;
                }
                //TOP --;
                break;
            case Instruction.GOTO:
                PC = i.getArg1();
                break;
            case Instruction.HALT:
                PC = -1;
                break;
            case Instruction.POP:
                stack[TOP] = null;
                TOP--;
                PC++;
                break;
            case Instruction.NOP:
                PC++;
                break;
            case Instruction.INVOKE:
                stack[TOP + 1] = PP;
                stack[TOP + 2] = FP;
                stack[TOP + 3] = spp(i.getArg3(), PP, FP);
                stack[TOP + 4] = sfp(i.getArg3(), PP, FP);
                stack[TOP + 5] = PC + 1;
                PP = TOP - i.getArg1() + 1;
                FP = TOP + 1;
                TOP = TOP + 5;
                PC = i.getArg2();
                break;
            case Instruction.RETURN:
                int res = stack[TOP];
                TOP = PP;
                PP = stack[FP];
                PC = stack[FP + 4];
                FP = stack[FP + 1];
                stack[TOP] = res;
                break;
        }

        if (debugModus) {
            debug();
            //System.out.println("DEBUG"+TOP+" instruction:"+i);

        }

        if (PC == program.length) {
            PC = -1;
        }
    }

    private void checkArraySize() {
        Integer[] helper;
        /*Maximal wird  fp + 4 belegt*/
        if ((FP + 4) > stack.length) {
            helper = new Integer[stack.length + 4];
            System.arraycopy(stack, 0, helper, 0, stack.length);
            stack = helper;
        }
        /*Maximal wird top + 5 belegt*/
        if ((TOP + 6) > stack.length) {
            helper = new Integer[stack.length + 6];
            System.arraycopy(stack, 0, helper, 0, stack.length);
            stack = helper;
        }
    }

    public void debug() {

        System.out.println();
        System.out.println("TOP                -> STELLE: " + TOP + " WERT: " + stack[TOP]);
        try {
            System.out.println("FRAMEPOINTER       -> STELLE: " + FP + " WERT: " + stack[FP]);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (FP < 0) {
                System.out.println("FRAMEPOINTER       -> STELLE: " + FP + " (OHNE WERT)");
            }
        }
        try {
            System.out.println("PARAMETERPOINTER   -> STELLE: " + PP + " WERT: " + stack[PP]);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (PP < 0) {
                System.out.println("PARAMETERPOINTER   -> STELLE: " + PP + " (OHNE WERT)");
            }
        }
        try {
            System.out.println("PROGRAMCOUNTER   -> STELLE: " + PC + " ANWEISUNG:" + program[PC]);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (PC < 0) {
                System.out.println("PROGRAMCOUNTER     -> " + PC + " (KEINE ANWEISUNG)");
            }
        }
        System.out.println("________________________________________Aktueller Stack________________________________________");
        System.out.println(Arrays.toString(stack));
        System.out.println("_______________________________________________________________________________________________");
    }

    public int spp(int d, int pp, int fp) {
        if (d == 0) {
            return pp;
        } else {
            return spp(d - 1, stack[fp + 2], stack[fp + 3]);
        }
    }

    public int sfp(int d, int pp, int fp) {
        if (d == 0) {
            return fp;
        } else {
            return sfp(d - 1, stack[fp + 2], stack[fp + 3]);
        }
    }
}
