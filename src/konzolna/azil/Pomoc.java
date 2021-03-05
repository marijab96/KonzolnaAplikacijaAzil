package konzolna.azil;

import java.math.BigDecimal;
import java.util.Scanner;

public class Pomoc {

    private static final Scanner ulaz = new Scanner(System.in);

    public static boolean ucitajBoolena(String poruka, boolean bo, boolean novi){

        String s;

        while(true){
            System.out.print(poruka + ": ");
            try {
                s= ulaz.nextLine().toLowerCase();

                if(s.isEmpty() && !novi) {
                    return bo;
                }

                if(s.equals("da")){
                    return true;
                }

                if(s.equals("ne")) {
                    return false;
                }

            } catch (Exception e) {
                System.out.println("Krivi unos");
            }
        }

    }

    public static Long ucitajLong(String poruka, boolean novi){

        long i;
        while(true){
            System.out.print(poruka + ": ");
            try {

                String unos = ulaz.nextLine();
                if(unos.isEmpty() && !novi) {
                    return null;
                }

                i=Integer.parseInt(unos);

                return i;

            } catch (Exception e) {
                System.out.println("Krivi unos");
            }
        }


    }

    public static Integer ucitajCijeliBroj(String poruka, boolean novi){

        int i;
        while(true){
            System.out.print(poruka + ": ");
            try {

                String unos = ulaz.nextLine();
                if(unos.isEmpty() && !novi) {
                    return null;
                }

                i=Integer.parseInt(unos);

                return i;

            } catch (Exception e) {
                System.out.println("Krivi unos");
            }
        }
    }
    public static int ucitajCijeliBroj(String poruka, int min, int max){

        int i;
        while(true){
            System.out.print(poruka + ": ");
            try {
                i=Integer.parseInt(ulaz.nextLine());

                if(i==0){
                    return i;
                }

                if(i<min || i>max){
                    System.out.println("Broj nije u danom rasponu (" + min + "-" + max + ")");
                    continue;
                }

                return i;

            } catch (Exception e) {
                System.out.println("Krivi unos");
            }
        }
    }

    public static String ucitajString(String poruka, boolean novi) {

        String s;
        while (true){
            System.out.print(poruka + ": ");
            s= ulaz.nextLine();

            if(s.trim().isEmpty() && novi){
                System.out.println("Obavezan unos");
                continue;
            }
            return s;

        }
    }

    public static BigDecimal ucitajDecimalniBroj (String poruka,boolean novi){

        BigDecimal bg;
        while(true){
            System.out.print(poruka + ": ");
            try {

                String unos = ulaz.nextLine();
                if(unos.isEmpty() && !novi) {
                    return null;
                }

                bg=BigDecimal.valueOf(Double.parseDouble(unos));

                return bg;

            } catch (Exception e) {
                System.out.println("Krivi unos");
            }
        }



    }


}
