	package calculator;
	import javax.swing.*;
	import java.awt.event.*;
	import java.awt.*;

public class myCalculator {

		int infixOn=0;
		String str1="0";
		String str2="0";
		String operator="";
		String result_str="0";
		int numDigStr1=0;
		int numDigStr2=0;
		Float num1;
		Float num2;
		Float num;
		int state=1;
		JButton buts[];                                
		JButton b_mod=new JButton("MODE(INFIX)");
		JButton b_enter=new JButton("ENTER");
		JButton b_plu=new JButton("+");
		JButton b_sub=new JButton("-");
		JButton b_mul=new JButton("*");
		JButton b_div=new JButton("/");
		JButton b_equ=new JButton("=");
		JButton b_dig=new JButton(".");
		JTextField result_textField=new JTextField(result_str,12);
		Font font=new Font("Arial",0,35);
		//panel of North
		JPanel pN= new JPanel(); 
		//panel of Center
		JPanel pC= new JPanel();
		//panel of East
		JPanel pE=new JPanel();
		JFrame f=new JFrame("a simple calculator");
		
		public myCalculator() {
			buts=new JButton[10];
			for(int i=0;i<10;i++) {
				buts[i]=new JButton(""+i);
			}
			result_textField.setFont(font);
			result_textField.setHorizontalAlignment(JTextField.RIGHT);	
			//panel of North                          
			pN.add(result_textField);
			//panel of Center                          
			pC.setSize(240,240);
			pC.setLayout(new GridLayout(4,3));
			for(int i=1;i<10;i++) {
				pC.add(buts[i]);
			}
			pC.add(b_dig);
			pC.add(buts[0]);
			pC.add(b_equ);
			//panel of East
			pE.setSize(60,240);
			pE.add(b_enter);
			pE.add(b_plu);                                      
			pE.add(b_sub);
			pE.add(b_mul);
			pE.add(b_div);
			pE.add(b_mod);
			pE.setLayout(new GridLayout(6,1));
		    f.add(pN,BorderLayout.NORTH);
		    f.add(pC,BorderLayout.CENTER);
		    f.add(pE,BorderLayout.EAST);
		    f.setVisible(true); 
		    f.setLocation(500, 250);
		    f.setSize(380,350); 
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	

		    //different actionListener for buttons
		    //1.listener for number buttons
			class numListener implements ActionListener{
				public void actionPerformed(ActionEvent e)
				{
					String key = e.getActionCommand();
					switch(state) {
						case 1:{}
						case 2:{
							str1=getInputNumber(str1,key);
							num1=Float.parseFloat(str1);
							state=2;
							break;
							}
						case 3:{}
						case 4:{
							str2=getInputNumber(str2,key);
							num2=Float.parseFloat(str2);
							state=4;
							break;
							}
						case 5:{
							str2=getInputNumber(str2,key);
							num2=Float.parseFloat(str2);
							System.out.println(num1);
							System.out.println(num2);
							state=4;
							break;
						}
						case 6:{}
						case 7:{
							str1=getInputNumber(str1,key);
							num1=Float.parseFloat(str1);
							state=7;
							break;
						}
						case 8:{}
						case 9:{
							str2=getInputNumber(str2,key);
							num2=Float.parseFloat(str2);
							state=9;
							break;
							}
						case 10:{
							str2=getInputNumber(str2,key);
							num2=Float.parseFloat(str2);
							state=9;
							break;
							}
					
					}				
				}			
			}
			
			//listener for operator buttons
			class opeListener implements ActionListener{
				public void actionPerformed(ActionEvent e)
				{
					String key = e.getActionCommand();
					switch(state) {
					case 2:{
						operator=key;	
						System.out.println(operator);
						result_textField.setText(str1);
					    state=3;
					    break;	
					    }
					case 4:{
						num1=cal(num1,num2,operator);
						str1=getResult_Str(num1);	
						result_textField.setText(str1);
						str2="0";
						System.out.println(+num1);
						operator=key;
						System.out.println(operator);
						state=5;
						break;
						}
					case 9:{
						operator=key;	
						System.out.println(operator);
						num1=cal(num1,num2,operator);
						str1=getResult_Str(num1);
						result_textField.setText(str1);
						str2="0";
					    state=10;
					    break;	
					    }
					case 12:{
						operator=key;	
						System.out.println(operator);
						result_textField.setText(str1);
						str2="0";
						numDigStr1=0;
					    state=3;
					    break;	
					    }
					}								
				}
			}
			class equListener implements ActionListener{
				public void actionPerformed(ActionEvent e)
				{
					num1=cal(num1,num2,operator);
					str1=getResult_Str(num1);	
					result_textField.setText(str1);
					System.out.println(num1);
					//String key = e.getActionCommand();
				}
			}
			
			class modListener implements ActionListener{
				public void actionPerformed(ActionEvent e)
				{
					switch(infixOn) {
					case 0:
						state=6;
						b_mod.setText("MODE(P R N)");
						infixOn=1;
						break;
					case 1:
						state=1;
						b_mod.setText("MODE(INFIX)");
						infixOn=0;
						break;
					}				
				}
			}
			 
			class entListener implements ActionListener{
				public void actionPerformed(ActionEvent e)
				{
					state=8;
					result_textField.setText(str1);	
					
				}
			}
			
			 
			class digListener implements ActionListener{
				public void actionPerformed(ActionEvent e)
				{
					switch(state) {
					case 2:
						str1=str1+".";
						result_textField.setText(str1);	                  
						state=2;
						break;
					case 4:
						str2=str2+".";
						result_textField.setText(str2);	 
						state=4;
						break;
					case 7:
						str1=str1+".";
						result_textField.setText(str1);	                  
						state=7;
						break;
					case 9:
						str2=str2+".";
						result_textField.setText(str2);	 
						state=9;
						break;
					}					
				}
			}
		    //add the listener to each button
			numListener numListen =new numListener();
			for(int j=0;j<10;j++)
				buts[j].addActionListener(numListen);
			opeListener opeListen =new opeListener();
			equListener equListen=new equListener();
			modListener modListen=new modListener();
			entListener entListen=new entListener();
			digListener diglisten=new digListener();
			b_plu.addActionListener(opeListen);
			b_sub.addActionListener(opeListen);
			b_mul.addActionListener(opeListen);
			b_div.addActionListener(opeListen);
			b_equ.addActionListener(equListen);
			b_mod.addActionListener(modListen);
			b_enter.addActionListener(entListen);
			b_dig.addActionListener(diglisten);
		}
		
		public Float cal(Float num1,Float num2, String operator) {
			Float result=0f;
			if(operator=="+") {
				result=num1+num2;
			}
			if(operator=="-") {
				result=num1-num2;
			}
			if(operator=="*") {
				result=num1*num2;
			}
			if(operator=="/") {
				result=num1/num2;
			}	
			return result;			
		}
		
		public String getInputNumber(String str,String key) {
			if(str=="0")
				str=key;
			else
				str=str+key;
			num=Float.parseFloat(str);
			System.out.println(num);
			result_textField.setText(str);
			return str;	
		}
			
		public String getResult_Str(Float num1) {
			String str1;
			str1=""+num1;
			long result_num=0;
			int index=str1.indexOf(".");
			String s=str1.substring(index+1,str1.length());
			int in=Integer.parseInt(s);
			if(in==0) 
			{
				result_num=Math.round(num1);
				str1=""+result_num;			
			}										
			else
				str1=""+num1;	
			return str1;		
		}
		
		public static void main(String[] args) {
			myCalculator t1=new myCalculator();

			
		}	 
	}



