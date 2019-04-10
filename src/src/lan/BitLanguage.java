/* Generated By:JavaCC: Do not edit this line. BitLanguage.java */
package lan;
import java.io.*;
import extract.*;

public class BitLanguage implements BitLanguageConstants {
        public ValueStack valueStack=new ValueStack();
        private IfExecute ifExecute=new IfExecute();
  public static void main(String args[])throws ParseException, IOException{
    evaluate(args[0]);
  }

  public static void evaluate(String path) throws ParseException, IOException{
        FileInputStream fileInputStream = new FileInputStream(new File(path));
    BitLanguage bitLanguage = new BitLanguage(fileInputStream);
    bitLanguage.analyse();
    bitLanguage.valueStack.showValue();
//    for (int i = 0;i<2;i++){
//      bitLanguage.analyse();
//      bitLanguage.valueStack.showValue();
//      
//      fileInputStream.close();
//      fileInputStream = new FileInputStream(new File("bit.txt"));
//      bitLanguage.ReInit(fileInputStream);
//      bitLanguage.valueStack.setEnemy(i);
//    }
    fileInputStream.close();
  }

  final public void analyse() throws ParseException {
    statement();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case letter:
      case 8:
      case 9:
      case 10:
      case 12:
      case 16:
      case 17:
      case 19:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      statement();
    }
  }

  final public void statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
    case 9:
      declare();
      break;
    case 10:
      block();
      break;
    case letter:
    case 19:
      assignment();
      jj_consume_token(7);
      break;
    case 12:
      ifStatement();
      break;
    case 16:
      whileStatement();
      break;
    case 17:
      returnStatement();
      jj_consume_token(7);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void declare() throws ParseException {
  String key;
    typeID();
    key = ID();
    jj_consume_token(7);
        if(ifExecute.peek())
        valueStack.putInt(key, 0);
  }

  final public void typeID() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
      jj_consume_token(8);
      break;
    case 9:
      jj_consume_token(9);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public String ID() throws ParseException {
  Token x;
  String id = "";
    x = jj_consume_token(letter);
    id+=String.valueOf(x.image);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case letter:
      case digit:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case digit:
        x = jj_consume_token(digit);
    id+=String.valueOf(x.image);
        break;
      case letter:
        x = jj_consume_token(letter);
    id+=String.valueOf(x.image);
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return id;}
    throw new Error("Missing return statement in function");
  }

  final public void block() throws ParseException {
    jj_consume_token(10);
    statementList();
    jj_consume_token(11);
  }

  final public void statementList() throws ParseException {
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case letter:
      case 8:
      case 9:
      case 10:
      case 12:
      case 16:
      case 17:
      case 19:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      statement();
    }
  }

  final public void ifStatement() throws ParseException {
        Token x;
        int value=0;
    jj_consume_token(12);
   ifExecute.put();
    jj_consume_token(13);
    value = expression();
    jj_consume_token(14);
        if(value<=0)
                ifExecute.set(false);
    statement();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 15:
      jj_consume_token(15);
        if(value>0)
                ifExecute.set(false);
        else
                ifExecute.set(true);
      statement();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
   ifExecute.pop();
  }

  final public void whileStatement() throws ParseException {
        int value;
        int temp_jj_gen=0;
        int brace_num=0;
        Token temp_token;
                temp_jj_gen=jj_gen;
                temp_token=token;
    jj_consume_token(16);
    jj_consume_token(13);
    value = expression();
    jj_consume_token(14);
        if(value<=0){
                token=getNextToken();
                if(token.image.equals("{"))
                        brace_num+=1;
                while(brace_num>0){
                        token=getNextToken();
                        if(token.image.equals("{"))
                                brace_num+=1;
                        else if(token.image.equals("}"))
                                brace_num-=1;
                }
        }
    statement();
        if(value>0){
                jj_gen=temp_jj_gen;
                token=temp_token;
        }
  }

  final public void returnStatement() throws ParseException {
  int value=0;
    jj_consume_token(17);
    value = expression();
        if(ifExecute.peek())
                valueStack.returnValue=value;
  }

  final public void assignment() throws ParseException {
  int value = 0;
  String key = "";
    key = primaryExpression();
    jj_consume_token(18);
    value = expression();
    if (valueStack.containInt(key)){
        if(ifExecute.peek())
         valueStack.putInt(key, value);
    }
    else {
      {if (true) throw new Error("No value named "+key+"!");}
    }
  }

  final public int expression() throws ParseException {
  int value = 0;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 20:
      value = globalRandom();
    {if (true) return value;}
      break;
    case 21:
      value = globalEnemy();
    {if (true) return value;}
      break;
    case letter:
    case digit:
    case 13:
    case 19:
      value = conditionalOrExpression();
    {if (true) return value;}
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String primaryExpression() throws ParseException {
  String id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 19:
      jj_consume_token(19);
    {if (true) return "CUR";}
      break;
    case letter:
      id = ID();
    {if (true) return id;}
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public int globalRandom() throws ParseException {
  int value;
    jj_consume_token(20);
    jj_consume_token(13);
    value = expression();
    jj_consume_token(14);
    double tempValue = value*Math.random();
    {if (true) return (int )Math.rint(tempValue);}
    throw new Error("Missing return statement in function");
  }

  final public int globalEnemy() throws ParseException {
  int value;
    jj_consume_token(21);
    jj_consume_token(22);
    value = expression();
    jj_consume_token(23);
    {if (true) return valueStack.enemy[value];}
    throw new Error("Missing return statement in function");
  }

  final public int conditionalOrExpression() throws ParseException {
  int temp = 0;
  int value = 0;
    temp = conditionalandExpression();
    value = temp;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 24:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_4;
      }
      jj_consume_token(24);
      temp = conditionalOrExpression();
    if (value <= 0 && temp <= 0)value = 0;
    else value = 1;
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public int conditionalandExpression() throws ParseException {
  int temp = 0;
  int value = 0;
    temp = equalityExpression();
    value = temp;
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 25:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_5;
      }
      jj_consume_token(25);
      temp = conditionalandExpression();
    if (value>0 && temp>0)value = 1;
    else value = 0;
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public int equalityExpression() throws ParseException {
  int temp = 0;
  int value = 0;
  int op = -1;
    temp = relationalExpression();
    value = temp;
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 26:
      case 27:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 26:
        jj_consume_token(26);
    op = 0;
        break;
      case 27:
        jj_consume_token(27);
    op = 1;
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      temp = equalityExpression();
    }
    switch (op){
      case 0:value = value == temp?1:0;
      break ;
      case 1:value = value != temp?1:0;
      break ;
      default :break ;
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public int relationalExpression() throws ParseException {
  int temp = 0;
  int value = 0;
  int op = -1;
    temp = additiveExpression();
    value = temp;
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 28:
      case 29:
      case 30:
      case 31:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 28:
        jj_consume_token(28);
    op = 0;
        break;
      case 29:
        jj_consume_token(29);
    op = 1;
        break;
      case 30:
        jj_consume_token(30);
    op = 2;
        break;
      case 31:
        jj_consume_token(31);
    op = 3;
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      temp = relationalExpression();
    switch (op){
      case 0:value = value>temp?1:0;
      break ;
      case 1:value = value >= temp?1:0;
      break ;
      case 2:value = value<temp?1:0;
      break ;
      case 3:value = value <= temp?1:0;
      break ;
      default :break ;
    }
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public int additiveExpression() throws ParseException {
  int temp = 0;
  int value = 0;
  int op = -1;
    temp = unaryExpression();
    value = temp;
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 32:
      case 33:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_8;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 32:
        jj_consume_token(32);
    op = 0;
        break;
      case 33:
        jj_consume_token(33);
    op = 1;
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      temp = additiveExpression();
    switch (op){
      case 0:value+=temp;
      break ;
      case 1:value-=temp;
      break ;
      default :break ;
    }
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public int unaryExpression() throws ParseException {
  int value = 0;
  String key = "";
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case letter:
    case 19:
      key = primaryExpression();
    if (key == "CUR")value = valueStack.cur;
    else value = valueStack.getInt(key);
    {if (true) return value;}
      break;
    case 13:
      jj_consume_token(13);
      value = expression();
      jj_consume_token(14);
    {if (true) return value;}
      break;
    case digit:
      value = NUM();
    {if (true) return value;}
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public int NUM() throws ParseException {
  Token x;
  int value = 0;
    x = jj_consume_token(digit);
    value+=Integer.parseInt(x.image);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case digit:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_9;
      }
      x = jj_consume_token(digit);
    value*=10;
    value+=Integer.parseInt(x.image);
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public BitLanguageTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xb1720,0xb1720,0x300,0x60,0x60,0xb1720,0x8000,0x382060,0x80020,0x1000000,0x2000000,0xc000000,0xc000000,0xf0000000,0xf0000000,0x0,0x0,0x82060,0x40,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3,0x3,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public BitLanguage(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public BitLanguage(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new BitLanguageTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public BitLanguage(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new BitLanguageTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public BitLanguage(BitLanguageTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(BitLanguageTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[34];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 34; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
