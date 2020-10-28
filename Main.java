import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static FileWriter fw;

  /** Defination of parameters.

  * @param args Takes command line input. 
  *
  * @author Akash Mondal.
  * @version 1.0. 
  */ 
  public static void main(String[] args) {
    try {
      ArrayList<String> parameters = new ArrayList<String>();
      ArrayList<String> parametervalue = new ArrayList<String>();
      ArrayList<String> integervalue = new ArrayList<String>();
      HashSet<String> oldpasswords = new HashSet<String>();

      addparameter(parameters, parametervalue, "Name");
      addparameter(parameters, parametervalue, "birthdate(DDMMYYYY)");

      integervalue.add(parametervalue.get(1));

      addparameter(parameters, parametervalue, "Father's Name");
      addparameter(parameters, parametervalue, "Mother's Name");
      addparameter(parameters, parametervalue, "Important Person");

      char[] specialchar = { '@', '$', '.', '/', ' ', ',', '&' };
      String currentpath = System.getProperty("user.dir") + "\\Password List\\";

      String file = currentpath + parametervalue.get(0) + ".txt";

      fw = new FileWriter(file, true);

      formatbirthdate(integervalue);

      for (int k = 0; k < integervalue.size(); k++) {
        String birthdate = integervalue.get(k);
        for (int i = 0; i < birthdate.length(); i++) {
          for (int j = i; j <= birthdate.length(); j++) {
            if (j >= i) {
              String password = parametervalue.get(0) + birthdate.substring(i, j);
              writeIfUnique(oldpasswords, password);

              for (int len = 0; len < parametervalue.size(); len++) {              
                String name = parametervalue.get(len);
                for (int s = 0; s < specialchar.length; s++) {
                  password = name + specialchar[s] + birthdate.substring(i, j);
                  writeIfUnique(oldpasswords, password);

                  if (name != name.toLowerCase()) {
                    password = name.toLowerCase() + specialchar[s] + birthdate.substring(i, j);
                    writeIfUnique(oldpasswords, password);
                  }

                  if (name != name.toLowerCase()) {
                    password = name.toUpperCase() + specialchar[s] + birthdate.substring(i, j);
                    writeIfUnique(oldpasswords, password);
                  }
                }

                for (int s1 = 0; s1 < specialchar.length; s1++) {
                  for (int s2 = 0; s2 < specialchar.length; s2++) {
                    password = name + specialchar[s1] + birthdate.substring(i, j) 
                      + specialchar[s2];
                    writeIfUnique(oldpasswords, password);

                    if (name != name.toLowerCase()) {
                      password = name.toLowerCase() + specialchar[s1] + birthdate.substring(i, j)
                        + specialchar[s2];
                      writeIfUnique(oldpasswords, password);
                    }

                    if (name != name.toUpperCase()) {
                      password = name.toUpperCase() + specialchar[s1] + birthdate.substring(i, j)
                        + specialchar[s2];
                      writeIfUnique(oldpasswords, password);
                    }
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

  private static void addparameter(ArrayList<String> parameter, ArrayList<String> parametervalue,
      String parameterName) {
    parameter.add(parameterName);
    System.out.println(parameterName);
    parametervalue.add(sc.nextLine());
  }

  private static void formatbirthdate(ArrayList<String> birthdate) {

    String orignalbirthdate = birthdate.get(0);

    String formatedBirthdate = orignalbirthdate.substring(0, 4) + orignalbirthdate.substring(6, 8);
    birthdate.add(formatedBirthdate);

    if (orignalbirthdate.charAt(0) == '0') {
      String formattedDob = orignalbirthdate.substring(1);
      birthdate.add(formattedDob);
    }

    if (orignalbirthdate.charAt(2) == '0') {
      String formattedDob = orignalbirthdate.substring(1);
      birthdate.add(formattedDob);
    }

    if (orignalbirthdate.charAt(2) == '0' && orignalbirthdate.charAt(0) == '0') {
      String formattedDob = orignalbirthdate.substring(1, 2) + orignalbirthdate.substring(3, 4)
          + orignalbirthdate.substring(6, 8);
      birthdate.add(formattedDob);
      formattedDob = orignalbirthdate.substring(1, 2) + orignalbirthdate.substring(3, 4)
          + orignalbirthdate.substring(4, 8);
      birthdate.add(formattedDob);
    }
  }

  private static void writeIfUnique(HashSet<String> oldpasswords, String newPassword) {
    int size = oldpasswords.size();
    oldpasswords.add(newPassword);
    if (oldpasswords.size() != size) {
      try {
        fw.write(newPassword);
        fw.write("\n");
      } catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
      }
    }
  }

}
