//package com.no320.json; 
//
//import java.io.IOException;
//import java.io.Writer;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
////import org.apache.commons.lang.ArrayUtils;
//
//public class JSONArray
//{
//  private ArrayList myArrayList;
//
// 
//
//  String toString(int indentFactor, int indent)
//  {
//	  
//	   
//    int len = length();
//    if (len == 0) {
//      return "[]";
//    }
//
//    StringBuffer sb = new StringBuffer("[");
//    if (len == 1) {
//      sb.append(JSONUtils.valueToString(this.myArrayList.get(0), indentFactor, indent));
//    } else {
//      int newindent = indent + indentFactor;
//      sb.append('\n');
//      for (int i = 0; i < len; i++) {
//        if (i > 0) {
//          sb.append(",\n");
//        }
//        for (int j = 0; j < newindent; j++) {
//          sb.append(' ');
//        }
//				sb.append(JSONUtils.valueToString(this.myArrayList.get(i), indentFactor, newindent));
//      }
//      sb.append('\n');
//      for (i = 0; i < indent; i++) {
//        sb.append(' ');
//      }
//    }
//    sb.append(']');
//    return sb.toString();
//  }
//  
// 
//  
//
//  public int length()
//  {
//    return this.myArrayList.size();
//  }
//}