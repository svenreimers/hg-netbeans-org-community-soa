/* Generated By:JavaCC: Do not edit this line. SQLConditionParser.java */
package org.netbeans.modules.sql.framework.parser.conditionparser;


import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netbeans.modules.sql.framework.common.jdbc.SQLUtils;
import org.netbeans.modules.sql.framework.model.SQLConstants;
import org.netbeans.modules.sql.framework.model.SQLGenericOperator;
import org.netbeans.modules.sql.framework.model.SQLLiteral;
import org.netbeans.modules.sql.framework.model.SQLModelObjectFactory;
import org.netbeans.modules.sql.framework.model.SQLObject;
import org.netbeans.modules.sql.framework.model.SQLObjectFactory;
import org.netbeans.modules.sql.framework.model.SQLOperator;
import org.netbeans.modules.sql.framework.model.VisibleSQLPredicate;
import org.netbeans.modules.sql.framework.model.utils.SQLParserUtil;

import com.sun.sql.framework.exception.BaseException;

/** 
 * JavaCC generated SQL parser. 
 * Generated from SQLConditionParser.jj ($id$).
 * Do not edit this (.java) file directly, it is programmaticly generated.
 * 
 * @author Ahimanikya Satapathy
 * @author Ritesh Adval 
 * @author Jonathan Giron
 */
public class SQLConditionParser implements SQLConditionParserConstants {
    private SQLParserUtil sqlHelper;

    public void setSQLParserUtil(SQLParserUtil helper) {
        this.sqlHelper = helper;
    }

    public SQLConditionParser() {
        this(new ByteArrayInputStream(new byte[0]));
    }

    public SQLObject parse(String sql) throws BaseException {
        try {
            StringReader rReader = new StringReader(sql);
            this.ReInit(rReader);
            return this.SqlRootPredicate();
        } catch(TokenMgrError e) {
            throw new SQLConditionException(e);
        } catch(ParseException ex) {
            throw new SQLConditionException(ex);
        }
    }

    public SQLObject createPredicate(SQLObject left, String opName, SQLObject right) throws BaseException {
        ArrayList args = new ArrayList();

                if (left != null) {
                args.add(left);
                }

        if (right != null) {
                args.add(right);
        }
        SQLObject predicate = createOperator(opName, args);

        return predicate;
    }

    private SQLObject createOperator(String opName, List args) throws BaseException {
        SQLOperator fun = SQLObjectFactory.createOperatorFromParsedList(opName, args);
        //since by default some operatoras have bracket but for condition we want 
        //to put brackets only if user typed it
        fun.setShowParenthesis(false);
        return fun;
    }

    public static void main( String[] args )
            throws ParseException, TokenMgrError {
        SQLParserUtil helper = new  SQLParserUtil();
        SQLConditionParser parser = new SQLConditionParser( System.in );
        parser.setSQLParserUtil(helper);

        try {
            SQLObject obj = parser.SqlCompareExpr();
            System.out.println(" the sql is " + obj.toString());
        } catch(BaseException ex) {
            ex.printStackTrace();
        }
    }

   /**
   * Replaces all occurances of <i>old</i> in <i>src</i> with <i>nu</i>.
   *
   * @param src the source String
   * @param old the character to replace
   * @param nu  the String to replace <i>old</i> with
   * @return a copy of <i>src</i> with all instances of <i>old</i>
   *         replaced by <i>nu</i>
   * @throws java.lang.NullPointerException if any argument is null.
   */
  private final static String replace(String src, String old, String nu) {
    int srclen = src.length();
    int cur = 0;
    int loc = 0;
    int oldlen = old.length();
    StringBuffer buf = new StringBuffer(srclen+nu.length());
    do {
      loc = src.indexOf(old,cur);
      if(loc != -1) {
        buf.append(src.substring(cur,loc));
        buf.append(nu);
        cur = loc + oldlen;
      }
    } while(loc != -1);
    if(cur < srclen) {
      buf.append(src.substring(cur));
    }
    return buf.toString();
  }

//TOKEN : //end of line
//{
//   <EOL: "\n" | "\r" | "\r\n">
//}
  final public String SqlLValueTerm() throws ParseException {
  StringBuffer buffer = new StringBuffer();
  Token t = null;
    t = jj_consume_token(ID);
    buffer.append(t.image);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      t = jj_consume_token(DOT);
      buffer.append(t.image);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        t = jj_consume_token(ID);
      buffer.append(t.image);
        break;
      case ASTERISK:
        jj_consume_token(ASTERISK);
      buffer.append("*");
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return buffer.toString();}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlColumn() throws ParseException, BaseException {
    Token t = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COLUMN1:
      t = jj_consume_token(COLUMN1);
            {if (true) return sqlHelper.getColumnForFullyQualifiedName(t.image);}
      break;
    case COLUMN2:
      t = jj_consume_token(COLUMN2);
            {if (true) return sqlHelper.getColumnForFullyQualifiedName(t.image);}
      break;
    case COLUMN3:
      t = jj_consume_token(COLUMN3);
            {if (true) return sqlHelper.getColumn(t.image);}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlColumnRef() throws ParseException, BaseException {
   String column = null;
    column = SqlLValueTerm();
      {if (true) return sqlHelper.getColumn(column);}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SQLCurrentDate() throws ParseException, BaseException {
    Token t = null;
    SQLObject dateOp = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CURRENT_TIMESTAMP:
      t = jj_consume_token(CURRENT_TIMESTAMP);
        dateOp = createOperator(t.image, new ArrayList());
        {if (true) return dateOp;}
      break;
    case CURRENT_DATE:
      t = jj_consume_token(CURRENT_DATE);
        dateOp = createOperator("CURRENT_TIMESTAMP", new ArrayList());
        {if (true) return dateOp;}
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlSelectableElements() throws ParseException, BaseException {
   SQLObject sel = null;
   boolean openP = false;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPENPAREN:
      jj_consume_token(OPENPAREN);
        openP = true;
      sel = SqlWhereOr();
      jj_consume_token(CLOSEPAREN);
        if(sel instanceof VisibleSQLPredicate && openP) {
            ((VisibleSQLPredicate) sel).setShowParenthesis(true);
        } else if (sel instanceof SQLGenericOperator && openP) {
            ((SQLGenericOperator) sel).setShowParenthesis(true);
        }

        {if (true) return sel;}
      break;
    case FALSE:
    case NULL:
    case TRUE:
    case CAST:
    case CURRENT_DATE:
    case CURRENT_TIMESTAMP:
    case RUNTIME_INPUT:
    case COLUMN1:
    case COLUMN2:
    case COLUMN3:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case STRING_LITERAL:
    case ID:
    case ASTERISK:
    case MINUS:
      if (jj_2_1(2)) {
        sel = SqlFunction();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CURRENT_DATE:
        case CURRENT_TIMESTAMP:
          sel = SQLCurrentDate();
          break;
        case CAST:
          sel = SqlCastAs();
          break;
        case COLUMN1:
        case COLUMN2:
        case COLUMN3:
          sel = SqlColumn();
          break;
        case FALSE:
        case NULL:
        case TRUE:
        case INTEGER_LITERAL:
        case FLOATING_POINT_LITERAL:
        case STRING_LITERAL:
        case ID:
        case ASTERISK:
        case MINUS:
          sel = SqlLiteral();
          break;
        case RUNTIME_INPUT:
          sel = SqlRuntimeInput();
          break;
        default:
          jj_la1[4] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      {if (true) return sel;}
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlProductExprSelectable() throws ParseException, BaseException {
    SQLObject parent = null;
    SQLObject left = null;
    SQLObject right = null;
    String function = null;
    SQLObject result = null;
    left = SqlSelectableElements();
        result = left;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASTERISK:
      case SLASH:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASTERISK:
        jj_consume_token(ASTERISK);
              function = "*";
        break;
      case SLASH:
        jj_consume_token(SLASH);
              function = "/";
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = SqlSelectableElements();
        ArrayList args = new ArrayList();
        args.add(left);
        args.add(right);

        parent =  createOperator(function, args);
        left = parent;
        result = parent;
    }
        {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlSumExprSelectable() throws ParseException, BaseException {
    SQLObject parent = null;
    SQLObject left = null;
    SQLObject right = null;
    String function = null;
    SQLObject result = null;
    left = SqlProductExprSelectable();
        result = left;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONCAT:
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
                      function = "+";
        break;
      case MINUS:
        jj_consume_token(MINUS);
                      function = "-";
        break;
      case CONCAT:
        jj_consume_token(CONCAT);
                       function = "||";
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = SqlProductExprSelectable();
            ArrayList args = new ArrayList();
            args.add(left);
            args.add(right);

            parent = createOperator(function, args);
            left = parent;
            result = parent;
    }
        {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlCompareExpr() throws ParseException, BaseException {
    SQLObject left = null;
    SQLObject right = null;
    String symbol = null;
    SQLObject result = null;
    Token t = null;
    left = SqlSumExprSelectable();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IS:
    case LIKE:
    case LESS:
    case LESSEQUAL:
    case GREATER:
    case GREATEREQUAL:
    case EQUAL:
    case NOTEQUAL:
    case NOTEQUAL2:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IS:
        symbol = SqlIsNullClause();
            result = createPredicate(left, symbol, null);
        break;
      case LESS:
      case LESSEQUAL:
      case GREATER:
      case GREATEREQUAL:
      case EQUAL:
      case NOTEQUAL:
      case NOTEQUAL2:
        symbol = SqlCompareOp();
        right = SqlSumExprSelectable();
            result = createPredicate(left, symbol, right);
        break;
      case LIKE:
        t = jj_consume_token(LIKE);
          symbol = t.image;
        right = SqlSumExprSelectable();
            result = createPredicate(left, "like", right);
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
        if (result == null) {
            result = left;
        }
        {if (true) return (result);}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlRootPredicate() throws ParseException, BaseException {
   SQLObject result = null;
    //    [<OPENPAREN>]
    //       result = SqlFunction() 
    //       [<CLOSEPAREN>]
    //       | 
           result = SqlWhereOr();
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlWhereOr() throws ParseException, BaseException {
   SQLObject parent = null;
   SQLObject left = null;
   SQLObject right = null;
   SQLObject result = null;
    left = SqlWhereAnd();
      result = left;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_4;
      }
      jj_consume_token(OR);
      right = SqlWhereAnd();
         parent = createPredicate(left, "or", right);
         left = parent;
         result = parent;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlWhereAnd() throws ParseException, BaseException {
   SQLObject parent = null;
   SQLObject left = null;
   SQLObject right = null;
   SQLObject result = null;
    left = SqlWhereNot();
      result = left;
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_5;
      }
      jj_consume_token(AND);
      right = SqlWhereNot();
         parent = createPredicate(left, "and", right);
         left = parent;
         result = parent;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlWhereNot() throws ParseException, BaseException {
   SQLObject child = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
      child = SqlWhereElt();
        child = createPredicate(null, "not", child);
        {if (true) return child;}
      break;
    case FALSE:
    case NULL:
    case TRUE:
    case CAST:
    case CURRENT_DATE:
    case CURRENT_TIMESTAMP:
    case RUNTIME_INPUT:
    case COLUMN1:
    case COLUMN2:
    case COLUMN3:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case STRING_LITERAL:
    case ID:
    case OPENPAREN:
    case ASTERISK:
    case MINUS:
      child = SqlWhereElt();
     {if (true) return child;}
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlWhereElt() throws ParseException, BaseException {
   SQLObject result = null;
    result = SqlCompareExpr();
         {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlCastAs() throws ParseException, BaseException {
    SQLObject column = null;
    Object[] tuple;
    jj_consume_token(CAST);
    jj_consume_token(OPENPAREN);
    column = SqlSumExprSelectable();
    jj_consume_token(AS);
    tuple = SqlCastAsType();
    jj_consume_token(CLOSEPAREN);
        ArrayList argList = new ArrayList();
        argList.add(column);
        String castTo = (String) tuple[0];
        SQLLiteral literal = SQLModelObjectFactory.getInstance().createSQLLiteral(castTo, castTo, SQLLiteral.VARCHAR_UNQUOTED);
        argList.add(literal);

        String precision = (String) tuple[1];
        if (precision != null) {
                argList.add(precision);
                String scale = (String) tuple[2];
                if (scale != null) {
                        argList.add(scale);
                }
        }
        {if (true) return createOperator("castas", argList);}
    throw new Error("Missing return statement in function");
  }

  final public Object[] SqlCastAsType() throws ParseException, BaseException {
        Object[] tuple = new Object[3];
        Token type = null;
        Token scale = null;
        Token precision = null;

        int jdbcType = SQLConstants.JDBCSQL_TYPE_UNDEFINED;
        tuple[1] = null;
        tuple[2] = null;
    type = jj_consume_token(ID);
                tuple[0] = type.image;
                jdbcType = SQLUtils.getStdJdbcType(type.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPENPAREN:
      jj_consume_token(OPENPAREN);
      precision = jj_consume_token(INTEGER_LITERAL);
                        if (SQLUtils.isPrecisionRequired(jdbcType)) {
                            if (precision != null) {
                            tuple[1] = precision.image;
                            } else {
                                {if (true) throw new BaseException("Precision argument required for type " + type.image);}
                            }
                        }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CLOSEPAREN:
        jj_consume_token(CLOSEPAREN);
        break;
      case COMMA:
        jj_consume_token(COMMA);
        scale = jj_consume_token(INTEGER_LITERAL);
                        if (scale != null) {
                                if (SQLUtils.isScaleRequired(jdbcType)) {
                                    tuple[2] = scale.image;
                                } else {
                                        {if (true) throw new BaseException("Scale argument is invalid for type " + type.image);}
                                }
                        }
        jj_consume_token(CLOSEPAREN);
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
            {if (true) return tuple;}
    throw new Error("Missing return statement in function");
  }

  final public String SqlIsNullClause() throws ParseException {
    boolean foundnot = false;
    Token isToken = null;
    Token notToken = null;
    Token nullToken = null;
    isToken = jj_consume_token(IS);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      notToken = jj_consume_token(NOT);
            foundnot = true;
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
    nullToken = jj_consume_token(NULL);
        String result = (foundnot ? isToken.image + " " + notToken.image : isToken.image) + " " + nullToken;
        {if (true) return (result.trim().toUpperCase());}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlLiteral() throws ParseException, BaseException {
   SQLObject literal = null;
   Token t = null;
   boolean minus = false;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING_LITERAL:
      t = jj_consume_token(STRING_LITERAL);
      // trim off the open and close quotes
      String trimmed = t.image.substring(1, t.image.length() - 1);
      // replace all '' with '
      String result = replace(trimmed,"''","'");
      literal = SQLModelObjectFactory.getInstance().createVisibleSQLLiteral(result, result, Types.VARCHAR);
      break;
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MINUS:
        jj_consume_token(MINUS);
         minus = true;
        break;
      default:
        jj_la1[18] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
        t = jj_consume_token(INTEGER_LITERAL);
        literal = SQLModelObjectFactory.getInstance().createVisibleSQLLiteral(t.image, new Integer((minus ? "-" : "") + t.image).toString(), Types.INTEGER);
        break;
      case FLOATING_POINT_LITERAL:
        t = jj_consume_token(FLOATING_POINT_LITERAL);
        literal = SQLModelObjectFactory.getInstance().createVisibleSQLLiteral(t.image, new BigDecimal((minus ? "-" : "") + t.image).toString(), Types.NUMERIC);
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case NULL:
      jj_consume_token(NULL);
      literal = SQLModelObjectFactory.getInstance().createVisibleSQLLiteral("null", "null", Types.VARCHAR);
      break;
    case TRUE:
      jj_consume_token(TRUE);
      literal = SQLModelObjectFactory.getInstance().createVisibleSQLLiteral("true", Boolean.TRUE.toString(), Types.BOOLEAN);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      literal = SQLModelObjectFactory.getInstance().createVisibleSQLLiteral("false", Boolean.FALSE.toString(), Types.BOOLEAN);
      break;
    case ASTERISK:
      jj_consume_token(ASTERISK);

      break;
    case ID:
      t = jj_consume_token(ID);
       result = t.image;
      literal = SQLModelObjectFactory.getInstance().createSQLLiteral(result, result, SQLLiteral.VARCHAR_UNQUOTED);
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return literal;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlSelectable() throws ParseException, BaseException {
    SQLObject sel = null;
    sel = SqlWhereOr();
        {if (true) return sel;}
    throw new Error("Missing return statement in function");
  }

  final public List SqlSelectList() throws ParseException, BaseException {
  List list = new ArrayList();
  SQLObject curCol = null;
    curCol = SqlSelectable();
      list.add(curCol);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);

      curCol = SqlSelectable();
      list.add(curCol);
    }
    {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  final public List SqlSelectCols() throws ParseException, BaseException {
  List list = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FALSE:
    case NOT:
    case NULL:
    case TRUE:
    case CAST:
    case CURRENT_DATE:
    case CURRENT_TIMESTAMP:
    case RUNTIME_INPUT:
    case COLUMN1:
    case COLUMN2:
    case COLUMN3:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case STRING_LITERAL:
    case ID:
    case OPENPAREN:
    case ASTERISK:
    case MINUS:
      //    <ASTERISK>
      //    {
      //        list.add(new ColumnIdentifier("*"));
      //    }
      //    |
          list = SqlSelectList();
      break;
    default:
      jj_la1[22] = jj_gen;
      ;
    }
    {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  final public List SqlFunctionArgs() throws ParseException, BaseException {
    List args = new ArrayList();
    jj_consume_token(OPENPAREN);
    args = SqlSelectCols();
    jj_consume_token(CLOSEPAREN);
    {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlFunction() throws ParseException, BaseException {
  String name = null;
  List fnargs = null;
    jj_consume_token(ID);
        name = token.image;
    fnargs = SqlFunctionArgs();
    {if (true) return  createOperator(name,fnargs);}
    throw new Error("Missing return statement in function");
  }

  final public String SqlCompareOp() throws ParseException {
   String result = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUAL:
      jj_consume_token(EQUAL);
         result = "=";
      break;
    case NOTEQUAL:
      jj_consume_token(NOTEQUAL);
         result = "!=";
      break;
    case NOTEQUAL2:
      jj_consume_token(NOTEQUAL2);
         result = "!=";
      break;
    case GREATER:
      jj_consume_token(GREATER);
         result = ">";
      break;
    case GREATEREQUAL:
      jj_consume_token(GREATEREQUAL);
         result = ">=";
      break;
    case LESS:
      jj_consume_token(LESS);
         result = "<";
      break;
    case LESSEQUAL:
      jj_consume_token(LESSEQUAL);
         result = "<=";
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public SQLObject SqlRuntimeInput() throws ParseException, BaseException {
    Token t = null;
    t = jj_consume_token(RUNTIME_INPUT);
        {if (true) return this.sqlHelper.getRuntimeInput(t.image);}
    throw new Error("Missing return statement in function");
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    boolean retval = !jj_3_1();
    jj_save(0, xla);
    return retval;
  }

  final private boolean jj_3_1() {
    if (jj_3R_7()) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) return false;
    return false;
  }

  final private boolean jj_3R_8() {
    if (jj_scan_token(OPENPAREN)) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) return false;
    return false;
  }

  final private boolean jj_3R_7() {
    if (jj_scan_token(ID)) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) return false;
    if (jj_3R_8()) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) return false;
    return false;
  }

  public SQLConditionParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  //private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[24];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static private int[] jj_la1_3;
  static {
      jj_la1_0();
      jj_la1_1();
      jj_la1_2();
      jj_la1_3();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,0x0,0x20000000,0x20000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x400,0x20000000,0x0,0x0,0x0,0x0,0x0,0x20000000,0x0,0x20000000,0x0,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x10000,0x10000,0x0,0x0,0x0,0x0,0x2200,0x2200,0x80000,0x0,0x18000,0x0,0x0,0x8000,0x0,0x0,0x10000,0x0,0x18000,0x0,};
   }
   private static void jj_la1_2() {
      jj_la1_2 = new int[] {0x10000000,0x100000,0xe000,0xc00,0x1bfd01,0x1bfd01,0x0,0x0,0x4000000,0x4000000,0xe0000000,0xe0000000,0x0,0x0,0x1bfd01,0x2000000,0x0,0x0,0x0,0x30000,0x1b0001,0x2000000,0x1bfd01,0xe0000000,};
   }
   private static void jj_la1_3() {
      jj_la1_3 = new int[] {0x0,0x80,0x0,0x0,0x480,0x4a0,0x180,0x180,0x600,0x600,0xf,0xf,0x0,0x0,0x4a0,0x40,0x20,0x0,0x400,0x0,0x480,0x0,0x4a0,0xf,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[1];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public SQLConditionParser(java.io.InputStream stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SQLConditionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.InputStream stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public SQLConditionParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SQLConditionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public SQLConditionParser(SQLConditionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(SQLConditionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 24; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    return (jj_scanpos.kind != kind);
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[108];
    for (int i = 0; i < 108; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 24; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
          if ((jj_la1_3[i] & (1<<j)) != 0) {
            la1tokens[96+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 108; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}

