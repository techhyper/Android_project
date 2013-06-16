package com.example.calculate;

import java.util.Stack;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
	}
	public void ButtonClickHandler(View v){
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		TextView textView1 = (TextView)findViewById(R.id.textView1);
		
	switch (v.getId()) {
		case R.id.button1:
			editText1.append("1");			
			break;
		case R.id.button2:
			editText1.append("2");			
			break;
		case R.id.button3:
			editText1.append("3");			
			break;
		case R.id.button4:
			editText1.append("4");			
			break;
		case R.id.button5:
			editText1.append("5");			
			break;
		case R.id.button6:
			editText1.append("6");			
			break;
		case R.id.button7:
			editText1.append("7");			
			break;
		case R.id.button8:
			editText1.append("8");			
			break;
		case R.id.button9:
			editText1.append("9");			
			break;
		case R.id.button10:
			editText1.append("0");			
			break;
		case R.id.button11:
			editText1.append(".");			
			break;
		case R.id.button13:
			editText1.append("+");			
			break;
		case R.id.button14:
			editText1.append("-");			
			break;
		case R.id.button15:
			editText1.append("*");			
			break;
		case R.id.button16:
			editText1.append("/");			
			break;
		case R.id.button19:
			editText1.append(")");			
			break;
		case R.id.button20:
			editText1.append("(");			
			break;
		case R.id.button21:
			editText1.append("√");			
			break;
		case R.id.button22:
			editText1.append("^");			
			break;	
		case R.id.button18:
			 if(editText1.getText().toString().length() > 0){
    			 String strText1Tmp = editText1.getText().toString().substring(0, editText1.getText().toString().length()-1);
    			 editText1.setText("");
    			 editText1.append(strText1Tmp);
			 }		
			break;
		case R.id.button17:
			editText1.setText("");
			textView1.setText("");
  			 break;
		case R.id.button12:
			try{
			 String strStack = toPostfix(editText1.getText().toString());
		     float intValue = Calculator(strStack);
  			 textView1.setText(Float.toString(intValue));
			}catch (Exception e) {
				textView1.setText("Error data wrong.");
			}
  			 break;
		}
	}
	public static int getPriority(char chaOperator){
		if(chaOperator=='+'||chaOperator=='-'){
			return 1;
		}else if (chaOperator=='*'||chaOperator=='/'){
			return 2;
		}else if (chaOperator=='^'||chaOperator=='√'){
			return 3;
		}
		return 0;
	}
	public static boolean isFloat(String strInput){
	       try{
	    	  Float.parseFloat(strInput);
	          return true;
	       }catch(Exception e){
	          return false;
	       }
	    }	
	@SuppressLint("UseValueOf")
	public static String toPostfix(String strInfix){
		String strExpression;
		String strPostfix = " ";
 
		strInfix  = strInfix.replaceAll("\\+|\\(|\\)|-|\\*|/|\\^|√", " $0 ");
		StringTokenizer strToken = new StringTokenizer(strInfix);
 
		Stack<Character> operatorStack = new Stack<Character>();
 
		while(strToken.hasMoreTokens()){
			strExpression = strToken.nextToken();
 
			if(Character.isDigit(strExpression.charAt(0))){
				strPostfix = strPostfix + " " + Float.parseFloat(strExpression);
			}else if(strExpression.equals("(")){
				Character operator = new Character('(');
				operatorStack.push(operator);
			}else if (strExpression.equals(")")){
				while(((Character) operatorStack.peek()).charValue() != '('){
					strPostfix = strPostfix + " " + operatorStack.pop();
				} 
				operatorStack.pop();
			}else{ 
				while(!operatorStack.isEmpty() && !(operatorStack.peek()).equals("(") && getPriority(strExpression.charAt(0)) <= getPriority(((Character) operatorStack.peek()).charValue())){
					strPostfix = strPostfix + " " + operatorStack.pop();
				} 
				Character operator = new Character(strExpression.charAt(0));
				operatorStack.push(operator);
			}
		} 
		while(!operatorStack.isEmpty()){
			strPostfix = strPostfix + " " + operatorStack.pop();
		} 
		return strPostfix;
	}
    public static float Calculator(String strPostfix) {
    	float a;
    	float b;
    	float result = 0;
 
    	String[] arrPostfix = strPostfix.split(" ");
	    Stack<Float> CalStack = new Stack<Float>();
 
		for(int i = 0; i < arrPostfix.length; i++){
			String ch = arrPostfix[i];
 
			if(isFloat(ch)){
				CalStack.push(Float.parseFloat(ch));
			}else{
 
				if(ch.equals("+")){
					a = CalStack.pop();
					b = CalStack.pop();
					result = a + b;
					CalStack.push(result);
				}else if(ch.equals("-")){
					a = CalStack.pop();
					b = CalStack.pop();
					result = b - a;
					CalStack.push(result);
				}else if(ch.equals("*")){
					a = CalStack.pop();
					b = CalStack.pop();
					result = a * b;
					CalStack.push(result);
				}else if(ch.equals("/")){
					a = CalStack.pop();
					b = CalStack.pop();
					result = b / a;
					CalStack.push(result);
				}else if(ch.equals("^")){
					a = CalStack.pop();
					b = CalStack.pop();
					result = (float) Math.pow(b,a);
					CalStack.push(result);
				}else if(ch.equals("√")){
					a = CalStack.pop();
					result = (float) Math.sqrt(a);
					CalStack.push(result);
				}
			}
		}
 
		return result;
	}
	@Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
