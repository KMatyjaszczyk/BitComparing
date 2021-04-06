/*
*   Program obliczający liczbę zgodnych bitów w dwóch haszach
*   po wykonaniu fukcji haszującej
*   Autor: Krzysztof Matyjaszczyk
*   Data: 2021-03-31
*/

public class PorownanieHaszy {

    //przetwarzanie ciagu znakow (haszu) na tablice intow
    public static int[] hexStringToIntegerArray(String s) {
        int length = s.length();
        int[] array = new int[length/2];
        for (int i = 0; i < length; i += 2) {
            //pobranie dwoch bajtow z hexStringa, przekonwertowanie na inta
            //pierwsza cyfra z dwuznakowego ciagu musi byc pomnozona przez 16
            array[i/2] = ((Character.digit(s.charAt(i), 16) * 16)
                                + Character.digit(s.charAt(i+1), 16));
        }
        return array;
    }

    //przetwarzanie liczb typu int w ciagi binarne
    //(w przypadku ciagu krotszych niz 8 znakow dopelniamy je zerami z przodu)
    public static String integerArrayToBinaryString(int[] tab) {
        StringBuilder strB = new StringBuilder();
        for (int j : tab) {
            strB.append(String.format("%8s", Integer.toBinaryString(j))
                    .replace(' ', '0'));
        }
        return strB.toString();
    }

    //porownanie dwoch haszy w postaci binarnej, funkcja zlicza powtorzenia
    public static int compareTwoBinHashes(String h1, String h2) {
        int counter = 0;
        for (int i = 0; i < h1.length(); i++) {
            if (h1.charAt(i) == h2.charAt(i))
                counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        if (args.length != 2)                           //nieodpowiednia liczba argumentow
            System.out.println("Poprawna składnia: PorownanieHaszy H1 H2");
        else if (args[0].length() != args[1].length())  //hasze nie sa tej samej dlugosci
            System.out.println("Hasze muszą być tej samej długości");
        else {
            String hash1 = args[0];
            String hash2 = args[1];
            int[] intHash1 = hexStringToIntegerArray(hash1);
            int[] intHash2 = hexStringToIntegerArray(hash2);
            hash1 = integerArrayToBinaryString(intHash1);
            hash2 = integerArrayToBinaryString(intHash2);
            System.out.println("Liczba takich samych bitów w haszu: " + compareTwoBinHashes(hash1, hash2));
        }
    } //end of main function
} //end of class PorownanieHaszy
