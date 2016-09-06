/* CompilerTest class for TRIPLA 2015 */

package edu.uap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;


import edu.uap.Compiler.AbstractMachine;
import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;
import edu.uap.nodes.Node;
import edu.uap.parser;

public class CompilerTest
{
	public static void main(String[] args)
	{
		PrintWriter pw = null;
		Node ast;

		try
		{
			parser triplaParser = new parser(new Lexer(new FileReader("beispiel.tripla")));
			ast = ((Node) (triplaParser.parse().value));

			pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("ast.xml"))));

			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			pw.print(ast.toString());
			System.out.println("\"ast.xml\" file created");

			compile(ast);

		}
		catch (FileNotFoundException e)
		{
			System.err.println("1" + e.getMessage());
		}
		catch (IOException e)
		{
			System.err.println("2"+e.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.err.println("3"+e+" "+e.getMessage());
		}
		catch (Exception e){
			System.err.println("4"+e);
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
		}
	}

	private static void compile(Node ast) {
		LinkedList<Instruction> instr = ast.code(new HashMap<>(), 0);
		instr.add(new Instruction(Instruction.HALT));
		for(int i = 0; i < instr.size(); i++){
			Instruction in = instr.get(i);
			if(in.hasLabel()) {
				System.out.println(in.getLabel());
				in.getLabel().setAddress(i+1);
			}
		}
		System.out.println("\n###Erzeugtes Programm###");
		for(int i = 1; i < instr.size()+1; i++){
			System.out.println(i+". "+instr.get(i-1));
		}
		System.out.println("###Programmende###");
		Instruction[] program= instr.toArray(new Instruction[instr.size()]);

		new AbstractMachine(program , true);
	}
}
