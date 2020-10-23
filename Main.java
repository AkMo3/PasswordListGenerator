package passwordGenerator;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			ArrayList<String> Parameters = new ArrayList<String>();
			ArrayList<String> Parameter_Value = new ArrayList<String>();
			ArrayList<String> Integer_Value = new ArrayList<String>();
			Parameters.add("Name");
			System.out.println(Parameters.get(0));
			Parameter_Value.add(sc.nextLine());
			Parameters.add("Birthdate");
			System.out.println("Birthdate in DDMMYYYY");
			Parameter_Value.add(sc.nextLine());
			Integer_Value.add(Parameter_Value.get(1));
			Parameters.add("Father_Name");
			System.out.println("Father's Name");
			Parameter_Value.add(sc.nextLine());
			Parameters.add("Mother_Name");
			System.out.println("Mothers's Name");
			Parameter_Value.add(sc.nextLine());
			Parameters.add("Person1_Name");
			System.out.println("Other important name");
			Parameter_Value.add(sc.nextLine());
			char[] Special_Char = {'@', '$', '.', '/', ' ',',', '&'};
			String file = "F:\\PasswordGenerator\\"+Parameter_Value.get(0)+".txt";
			FileWriter fw = new FileWriter(file);
			boolean run = true;
			while(run) { 
				String Birthdate = Parameter_Value.get(1);
				for(int i =0;i<Birthdate.length();i++) {
					for(int j =i; j<=Birthdate.length();j++) {
						if(j>=i) { 	
							String password = Parameter_Value.get(0) + Birthdate.substring(i,j);
							fw.write(password);
							fw.write("\n");
							for(int len =0; len<Parameter_Value.size();len++) {
								String name = Parameter_Value.get(len);
								for(int s =0;s<Special_Char.length;s++) {
									password = name + Special_Char[s] + Birthdate.substring(i,j);
									fw.write(password);
									fw.write("\n");
									if(name!=name.toLowerCase()) 
										{password = name.toLowerCase() + Special_Char[s] + Birthdate.substring(i,j);
										fw.write(password);
										fw.write("\n");}
									if(name!=name.toLowerCase())
										{password = name.toUpperCase() + Special_Char[s] + Birthdate.substring(i,j);
										fw.write(password);
										fw.write("\n");}
								}
								for(int s1 =0;s1<Special_Char.length;s1++) {
									for(int s2 =0;s2<Special_Char.length;s2++)
										password = name + Special_Char[s1] + Birthdate.substring(i,j)+ Special_Char[s1];
										fw.write(password);
										fw.write("\n");
										if(name!=name.toLowerCase())
											{password = name.toLowerCase() + Special_Char[s1] + Birthdate.substring(i,j)+ Special_Char[s1];
											fw.write(password);
											fw.write("\n");}
										if(name!=name.toUpperCase())
											{password = name.toUpperCase() + Special_Char[s1] + Birthdate.substring(i,j)+ Special_Char[s1];
											fw.write(password);
											fw.write("\n");}
								}
							}
						}
						if(i==Birthdate.length()-1) run=false;
						}
					}
				}
				
				fw.close();
		}catch(Exception e) {System.out.println(e);}
		
		System.out.println("Written");
	}

}
