import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static FileWriter fw;

  /**
   * Defination of parameters.
   *
   * @param args Takes command line input.
   * @author Akash Mondal.
   * @version 1.0.
   */
  public static void main(String[] args) {
    try {
      ArrayList<String> parameters = new ArrayList<String>();
      ArrayList<String> parameterValue = new ArrayList<String>();
      ArrayList<String> integerValue = new ArrayList<String>();
      HashSet<String> oldPasswords = new HashSet<String>();

      addparameter(parameters, parameterValue, "Name");
      addparameter(parameters, parameterValue, "birthdate(DDMMYYYY)");

      birthDateCheck(parameters, parameterValue, integerValue);

      addparameter(parameters, parameterValue, "Father's Name");
      addparameter(parameters, parameterValue, "Mother's Name");
      addparameter(parameters, parameterValue, "Important Person");

      char[] specialchar = {'@', '$', '.', '/', ' ', ',', '&'};
      String currentpath = System.getProperty("user.dir") + "\\Password List\\";

      String file = currentpath + parameterValue.get(0) + ".txt";

      fw = new FileWriter(file, true);

      formatbirthdate(integerValue);

      for (int k = 0; k < integerValue.size(); k++) {
        String birthdate = integerValue.get(k);
        for (int i = 0; i < birthdate.length(); i++) {
          for (int j = i; j <= birthdate.length(); j++) {
            if (j >= i) {
              String password = parameterValue.get(0) + birthdate.substring(i, j);
              writeIfUnique(oldPasswords, password);

              for (int len = 0; len < parameterValue.size(); len++) {
                String name = parameterValue.get(len);
                for (int s = 0; s < specialchar.length; s++) {
                  password = name + specialchar[s] + birthdate.substring(i, j);
                  writeIfUnique(oldPasswords, password);

                  if (name != name.toLowerCase()) {
                    password = name.toLowerCase() + specialchar[s] + birthdate.substring(i, j);
                    writeIfUnique(oldPasswords, password);
                  }

                  if (name != name.toLowerCase()) {
                    password = name.toUpperCase() + specialchar[s] + birthdate.substring(i, j);
                    writeIfUnique(oldPasswords, password);
                  }
                }

                for (int s1 = 0; s1 < specialchar.length; s1++) {
                  for (int s2 = 0; s2 < specialchar.length; s2++) {
                    password = name + specialchar[s1] + birthdate.substring(i, j) + specialchar[s2];
                    writeIfUnique(oldPasswords, password);

                    if (name != name.toLowerCase()) {
                      password =
                          name.toLowerCase()
                              + specialchar[s1]
                              + birthdate.substring(i, j)
                              + specialchar[s2];
                      writeIfUnique(oldPasswords, password);
                    }

                    if (name != name.toUpperCase()) {
                      password =
                          name.toUpperCase()
                              + specialchar[s1]
                              + birthdate.substring(i, j)
                              + specialchar[s2];
                      writeIfUnique(oldPasswords, password);
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

  private static void addparameter(
      ArrayList<String> parameter, ArrayList<String> parameterValue, String parameterName) {
    parameter.add(parameterName);
    System.out.println(parameterName);
    parameterValue.add(sc.nextLine());
  }

  private static void formatbirthdate(ArrayList<String> birthdate) {

    String orignalBirthdate = birthdate.get(0);

    String formatedBirthdate = orignalBirthdate.substring(0, 4) + orignalBirthdate.substring(6, 8);
    birthdate.add(formatedBirthdate);

    if (orignalBirthdate.charAt(0) == '0') {
      String formattedDob = orignalBirthdate.substring(1);
      birthdate.add(formattedDob);
    }

    if (orignalBirthdate.charAt(2) == '0') {
      String formattedDob = orignalBirthdate.substring(1);
      birthdate.add(formattedDob);
    }

    if (orignalBirthdate.charAt(2) == '0' && orignalBirthdate.charAt(0) == '0') {
      String formattedDob =
          orignalBirthdate.substring(1, 2)
              + orignalBirthdate.substring(3, 4)
              + orignalBirthdate.substring(6, 8);
      birthdate.add(formattedDob);

      formattedDob =
          orignalBirthdate.substring(1, 2)
              + orignalBirthdate.substring(3, 4)
              + orignalBirthdate.substring(4, 8);
      birthdate.add(formattedDob);
    }
  }

  private static void writeIfUnique(HashSet<String> oldPasswords, String newPassword) {
    int size = oldPasswords.size();
    oldPasswords.add(newPassword);
    if (oldPasswords.size() != size) {
      try {
        fw.write(newPassword);
        fw.write("\n");
      } catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
      }
    }
  }

  private static void birthDateCheck(
      ArrayList<String> parameters,
      ArrayList<String> parameterValue,
      ArrayList<String> integerValue) {

    String birthDate = parameterValue.get(1);
    boolean isValid = true;

    if (birthDate.length() != 8) {
      System.out.println("Invalid birthdate");
      isValid = false;
    }

    try {
      String dd = birthDate.substring(0, 2);
      String mm = birthDate.substring(2, 4);
      String yyyy = birthDate.substring(4, 8);

      int date = Integer.parseInt(dd);
      int month = Integer.parseInt(mm);
      int year = Integer.parseInt(yyyy);

      if (date <= 0 || date >= 32) {
        System.out.println("Inviled date");
        isValid = false;
      }

      if (month <= 0 || month >= 13) {
        System.out.println("Inviled month");
        isValid = false;
      }

      if (year <= 1940 || year >= 2021) {
        System.out.println("Invalid year");
        isValid = false;
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    if (!isValid) {
      System.out.println("Invaled birthdate, Enter the value again");
      parameters.remove(1);
      parameterValue.remove(1);
      addparameter(parameters, parameterValue, "birthdate(DDMMYYYY)");
      integerValue.add(parameterValue.get(1));
      birthDateCheck(parameters, parameterValue, integerValue);
    } else {
      integerValue.add(parameterValue.get(1));
    }
  }
}
