/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 4.3 */
/* JavaCCOptions:MULTI=false,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package tree;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public
class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children;
  protected int id;
  protected Object value;
  protected BitLanguageTree parser;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(BitLanguageTree p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) { this.value = value; }
  public Object jjtGetValue() { return value; }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() { return BitLanguageTreeTreeConstants.jjtNodeName[id]; }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix,String filepath2) {

    //将语法树保存到文件夹中
    File f = new File(filepath2);
    if (f.exists()) {
      try {
        FileWriter writer = new FileWriter(f,true);
        writer.write(toString(prefix) + "\r\n");
        writer.close();
      }catch(IOException e){
        System.out.println("语法树写入文件出错");
      }

      if (children != null) {
        for (int i = 0; i < children.length; ++i) {
          SimpleNode n = (SimpleNode)children[i];
          if (n != null) {
            n.dump(prefix + "   ",filepath2);
          }
        }
      }
    }
    else
      JOptionPane.showMessageDialog(null, "请先输入策略并点击保存按钮");

  }
}

/* JavaCC - OriginalChecksum=b0e921716ae2ad9192751e8e2528194c (do not edit this line) */
