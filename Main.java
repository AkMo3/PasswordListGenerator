package passwordGenerator;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	String Filename_Global = "";

	public static void main(String[] args) {
		try {
			ArrayList<String> Parameters = new ArrayList<String>();
			ArrayList<String> Parameter_Value = new ArrayList<String>();
			ArrayList<String> Integer_Value = new ArrayList<String>();

			AddParameter(Parameters, Parameter_Value, "Name");
			AddParameter(Parameters, Parameter_Value, "Birthdate(DDMMYYYY)");

			Integer_Value.add(Parameter_Value.get(1));

			AddParameter(Parameters, Parameter_Value, "Father's Name");
			AddParameter(Parameters, Parameter_Value, "Mother's Name");
			AddParameter(Parameters, Parameter_Value, "Important Person");

			char[] Special_Char = { '@', '$', '.', '/', ' ', ',', '&' };

			String file = "F:\\PasswordGenerator\\" + Parameter_Value.get(0) + ".txt";

			FormatBirthdate(Integer_Value);

			FileWriter fw = new FileWriter(file);

			for (int k = 0; k < Integer_Value.size(); k++) {
				String Birthdate = Integer_Value.get(k);
				for (int i = 0; i < Birthdate.length(); i++) {
					for (int j = i; j <= Birthdate.length(); j++) {
						if (j >= i) {
							String password = Parameter_Value.get(0) + Birthdate.substring(i, j);
							fw.write(password);
							fw.write("\n");
							for (int len = 0; len < Parameter_Value.size(); len++) {
								String name = Parameter_Value.get(len);
								for (int s = 0; s < Special_Char.length; s++) {
									password = name + Special_Char[s] + Birthdate.substring(i, j);
									fw.write(password);
									fw.write("\n");

									if (name != name.toLowerCase()) {
										password = name.toLowerCase() + Special_Char[s] + Birthdate.substring(i, j);
										fw.write(password);
										fw.write("\n");
									}

									if (name != name.toLowerCase()) {
										password = name.toUpperCase() + Special_Char[s] + Birthdate.substring(i, j);
										fw.write(password);
										fw.write("\n");
									}
								}

								for (int s1 = 0; s1 < Special_Char.length; s1++) {
									for (int s2 = 0; s2 < Special_Char.length; s2++)
										password = name + Special_Char[s1] + Birthdate.substring(i, j)
												+ Special_Char[s1];
									fw.write(password);
									fw.write("\n");

									if (name != name.toLowerCase()) {
										password = name.toLowerCase() + Special_Char[s1] + Birthdate.substring(i, j)
												+ Special_Char[s1];
										fw.write(password);
										fw.write("\n");
									}

									if (name != name.toUpperCase()) {
										password = name.toUpperCase() + Special_Char[s1] + Birthdate.substring(i, j)
												+ Special_Char[s1];
										fw.write(password);
										fw.write("\n");
									}
								}
							}
						}
					}
				}
			}

			fw.close();

			sc.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		System.out.println("Written");
	}

	private static void AddParameter(ArrayList<String> Parameter, ArrayList<String> Parameter_Value,
			String ParameterName) {
		Parameter.add(ParameterName);
		System.out.println(ParameterName);
		Parameter_Value.add(sc.nextLine());
	}

	private static void FormatBirthdate(ArrayList<String> Birthdate) {

		String Orignal_Birthdate = Birthdate.get(0);

		String Formated_birthdate = Orignal_Birthdate.substring(0, 4) + Orignal_Birthdate.substring(6, 8);
		Birthdate.add(Formated_birthdate);

		if (Orignal_Birthdate.charAt(0) == '0') {
			String Formated_DOB = Orignal_Birthdate.substring(1);
			Birthdate.add(Formated_DOB);
		}

		if (Orignal_Birthdate.charAt(2) == '0') {
			String Formated_DOB = Orignal_Birthdate.substring(1);
			Birthdate.add(Formated_DOB);
		}

		if (Orignal_Birthdate.charAt(2) == '0' && Orignal_Birthdate.charAt(0) == '0') {
			String Formated_DOB = Orignal_Birthdate.substring(1, 2) + Orignal_Birthdate.substring(3, 4)
					+ Orignal_Birthdate.substring(6, 8);
			Birthdate.add(Formated_DOB);
			Formated_DOB = Orignal_Birthdate.substring(1, 2) + Orignal_Birthdate.substring(3, 4)
					+ Orignal_Birthdate.substring(4, 8);
			Birthdate.add(Formated_DOB);
		}
	}

}
