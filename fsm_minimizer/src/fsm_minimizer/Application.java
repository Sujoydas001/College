package fsm_minimizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/*
 * 
 * @Author SUJOY DAS.
 * 
 * */

/*
 * 
 *  input file format
 *  states are considered as index starting from 0 , thus they are taken from input file
fsm minimiser
A,0
B,1
C,2
D,3
E,4
F,5
STATES
0,4,0,2,0    
1,5,0,2,1
2,4,0,0,0
3,5,0,0,1
4,0,0,3,0
5,3,0,4,1  ==>


  PS     NS0     OP    NS1    OP
   A      E     0      C     0   
   B      C     0      A     0   
   C      B     0      G     0   
   D      G     0      A     0   
   E      F     1      B     0   
   F      E     0      D     0   
   G      D     0      G     0  
 * 
 * 
 * 
 * */

public class Application {
	public static void main(String[] strings ) {
		ArrayList<String> states = new ArrayList<>();  //states with index A at 0 , B at 1.....
		HashMap<String,ArrayList<String>> map = new HashMap<>() ; 
		ArrayList<String[]> inputList=new ArrayList<>();
		try {
			System.out.println("/*INPUT SECTION*/");
			BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
			System.out.println(bReader.readLine());
			
			String line = bReader.readLine();
			while( !line.equals("STATES") ) {
				states.add(line.split(",")[0]);	   /* read the alphabets for the states as per index */	
				line = bReader.readLine();
			}
			
			/*printing the states with index from states*/
			System.out.print("[");
			for (int i = 0 ; i < states.size();i++ ) {
				System.out.print(" "+states.get(i)+"("+i+"),");
			}
			System.out.println("]");
			
			while(bReader.ready()) {
				String currString=bReader.readLine();
				inputList.add(currString.split(","));
			}
			bReader.close();
			System.out.println("________________________________");
			System.out.println("PS(idx) NS(idx)0 OP NS(idx)1  OP");
			for ( String[] each:inputList ) {
				for ( String each2: each ) {
					System.out.print( "   "+each2 + "   ");
				}
				System.out.println();
			}
			  /* write the input data to the output.txt .......*/
			try {   
			    PrintWriter pWriter = new PrintWriter(new FileWriter(new File("output.txt"),true)) ; 
			    pWriter.write("\n___________________________________________________________________________\n");
			    pWriter.write("\nNEW TASK\n");
				pWriter.write("\n________________________________\nINPUT STATE TRANSITION TABLE\n");
				System.out.println("________________________________\n  PS     NS0     OP    NS1    OP");
				pWriter.write("\n________________________________\n  PS     NS0     OP    NS1    OP\n");
				for ( String[] each:inputList ) {
					for ( int i = 0 ; i < 5 ;i++  ) {
						if ( i == 0 || i == 1 || i == 3 ) {
							System.out.print( "   "+states.get(Integer.parseInt(each[i])) + "   ");
							pWriter.write( "   "+states.get(Integer.parseInt(each[i])) + "   ");
						}else {
							System.out.print( "   "+each[i] + "   ");
							pWriter.write( "  "+each[i] + "   ");
						}
						
					}
					System.out.println();
					pWriter.write("\n");
				}
				pWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
			
			
			
			
			System.out.println("________________________________");
			System.out.println("/*OUTPUT SECTION*/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int count = 0;
		System.out.println("________________________________");
		System.out.println("at stage :p"+count++);
		System.out.println(states);
		/*entry into map for p0 to p1 stage */
		for ( String[] each: inputList) {
//			System.out.println(each[0]+":"+each[2]+":"+each[4]);
			if ( map.containsKey(each[2]+""+each[4])) {
				map.get(each[2]+""+each[4]).add(each[0]);
			}else {
				map.put(each[2]+""+each[4], new ArrayList<>());
				map.get(each[2]+""+each[4]).add(each[0]);
			}
			
		}
		
	
		/*
		 * p0 to p1 complete
		 * */
		System.out.println("________________________________");
		System.out.println("at stage :p"+count++);
		callPrint(map,states);
		HashMap<String,ArrayList<String>> temp = null;
		boolean again=true;
		while(again) {
			temp = new HashMap<>() ; 
			for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			    ArrayList<String> currstates= entry.getValue();
			    if ( currstates.size()>1) {
			    	for ( String each: currstates) {
				    	String k1 = inputList.get(Integer.parseInt(each))[1];
				    	String k2 = inputList.get(Integer.parseInt(each))[3];
				    	String key= getKey(map,k1,k2);
				    	if ( temp.containsKey(key)) {
				    		temp.get(key).add(each);
						}else {
							temp.put(key, new ArrayList<>());
							temp.get(key).add(each);
						}
						
				    }
			    }else {
			    	temp.put(entry.getKey(), new ArrayList<>());
					temp.get(entry.getKey()).addAll(entry.getValue());
			    }
			    
			}
			System.out.println("________________________________");
			System.out.println("at stage :p"+count);
			callPrint(temp,states);
			again = false;
			for (Map.Entry<String, ArrayList<String>> entry : temp.entrySet()) {
//			    System.out.println(entry.getKey()+" : "+entry.getValue());
			    if ( !map.containsValue(entry.getValue())) {
			    	again = true;
			    }
			   
			}
			map = temp;
			count++;
		}
		
		/*calculation done states are separated*/
		
		ArrayList<ArrayList<String>> outputIndex= new ArrayList<>(); 
		for (Map.Entry<String, ArrayList<String>> entry : temp.entrySet()) {
//		    System.out.println(entry.getKey()+" : "+entry.getValue());
		    ArrayList<String> p=entry.getValue();
		    String pt="";
		    for(String each : p ) {
		    	pt+=each+",";
		    }
		    String q1=inputList.get(Integer.parseInt(p.get(0)))[1]; 
		    String q2=inputList.get(Integer.parseInt(p.get(0)))[3]; 
		    String o0=inputList.get(Integer.parseInt(p.get(0)))[2]; 
		    String o1=inputList.get(Integer.parseInt(p.get(0)))[4]; 
		    ArrayList<String> tArrayList=new ArrayList<>(); 
		    tArrayList.add(pt);  
		    for (Map.Entry<String, ArrayList<String>> eachentry : temp.entrySet()) {
//			    
			   if ( eachentry.getValue().contains(q1)) {
				   String pt1="";
				    for(String each : eachentry.getValue() ) {
				    	pt1+=each+",";
				    }
				    tArrayList.add(pt1);
			   }
			}
		    tArrayList.add(o0);
		    for (Map.Entry<String, ArrayList<String>> eachentry : temp.entrySet()) {
//			    
			   if ( eachentry.getValue().contains(q2)) {
				   String pt2="";
				    for(String each : eachentry.getValue() ) {
				    	pt2+=each+",";
				    }
				    tArrayList.add(pt2);

			   }
			}
		    tArrayList.add(o1);
		    
		    
		    
		   outputIndex.add(tArrayList);
		}
		
		
		ArrayList<ArrayList<String>> output= new ArrayList<>(); 
		for ( ArrayList<String> each:outputIndex ) {
			ArrayList<String> tempList = new ArrayList<>();
			for (int i = 0;i< each.size();i++ ) {
				if ( i == 0 || i == 1 || i== 3) {
					String[] indexes=each.get(i).split(",");
					String state="";
					for (String e:indexes ) {
						state += states.get(Integer.parseInt(e));
					}
					tempList.add(state);
				}else {
					tempList.add(each.get(i));
				}
				
			}
			output.add(tempList);
		}
		String format = "%-15s %-15s %-15s %-15s %-15s";
		System.out.println("________________________________");
		System.out.printf(format + "%n", "PS(idx)", "NS(idx)0","OP", "NS(idx)1","OP");
		for ( ArrayList<String> each:outputIndex ) {
			System.out.printf(format + "%n", each.get(0),each.get(1),each.get(2),each.get(3),each.get(4));
			
		}
	
		try {
			    PrintWriter pWriter = new PrintWriter(new FileWriter(new File("output.txt"),true)) ; 
				pWriter.write("________________________________\nMINIMISED FSM STATE TRANSITION TABLE\n");
				System.out.println("________________________________");
			      pWriter.write("\n________________________________\n");
				pWriter.printf(format+ "%n", "PS", "NS0","OP", "NS1","OP");
		      
				System.out.println("MINIMISED FSM STATE TRANSITION TABLE");
				System.out.printf(format + "%n", "PS", "NS0","OP", "NS1","OP");
				for (ArrayList<String> each:output ) {
					System.out.printf(format + "%n", each.get(0),each.get(1),each.get(2),each.get(3),each.get(4));
					pWriter.printf(format + "%n", each.get(0),each.get(1),each.get(2),each.get(3),each.get(4));
				}
				System.out.println("END....");
				pWriter.write("\n___________________________________________________________________________\n");
				pWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	
	
		
	}
	private static void callPrint(HashMap<String, ArrayList<String>> map , ArrayList<String> states) {
		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			ArrayList<String> setOfStates = entry.getValue();
			System.out.print("[");
			for ( String each:setOfStates) {
				System.out.print(" "+(each) + ",");
			}
			System.out.println("]");
		}
	}
	public static String getKey(HashMap<String,ArrayList<String>> map,String k1, String k2) {
		String key1="",key2="";
		boolean forkey1=false,forkey2=false;
		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
		    ArrayList<String> availableStates = entry.getValue();
		    for ( String each:availableStates) {
		    	if ( each.equals(k1)) {
		    		key1+=entry.getKey();
		    		forkey1 = true;
		    	}
		    	if ( each.equals(k2)) {
		    		key2+=entry.getKey();
		    		forkey2=true;
		    	}
		    	if ( forkey1 && forkey2 ) {
		    		break;
		    	}
		    }
		}
		return key1+""+key2;
	}
	

}
