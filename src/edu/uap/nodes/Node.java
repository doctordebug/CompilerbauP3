
package edu.uap.nodes;

import edu.uap.Compiler.AddressPair;
import edu.uap.Compiler.Instruction;

import java.util.HashMap;
import java.util.LinkedList;

public class Node
{
    private String type;
    private Object attribute;
    private LinkedList<Node> children;


    public Node(String type)
    {
        this.type = type;
        this.attribute = null;
        this.children = new LinkedList<Node>();
    }


    public Node(String type, Object attribute)
    {
        this.type = type;
        this.attribute = attribute;
        this.children = new LinkedList<Node>();
    }


    public String getType()
    {
        return this.type;
    }


    public Object getAttribute()
    {
        return this.attribute;
    }


    public LinkedList<Node> getChildren()
    {
        return this.children;
    }


    public void setType(String type)
    {
        this.type = type;
    }

    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }


    public void setChildren(LinkedList<Node> children)
    {
        this.children = children;
    }


    public void addChild(Node child)
    {
        this.children.add(child);
    }


    public void addChildren(LinkedList<Node> children)
    {
        this.children.addAll(children);
    }
    
    
    private String startTag()
    {
    	String tag = "<" + type;

        if (attribute != null)
        {
            tag += " attr=\"" + attribute + "\"";
        }

        tag += ">";
        
        return tag;
    }
    
    
    private String endTag()
    {
        return "</" + type + ">";
    }

    
    @Override
    public String toString()
    {
        String str = startTag();

        for (Node node: children)
        {
        	str += node.toString();
        }

        str += endTag();

        return str;
    }

    public LinkedList<Instruction> code(HashMap<String, AddressPair> rho, int n) {
        LinkedList<Instruction> result = new LinkedList<>();
        for (Node node : getChildren()) {
            result.addAll( node.code((HashMap<String, AddressPair>) rho.clone(), n + 1) );
        }
        return result;
    }
}
