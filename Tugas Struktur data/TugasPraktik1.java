import java.util.LinkedList; 
import java.util.Arrays;     

public class TugasPraktik1 {

    public static void main(String[] args) {
        
        
        
        int StrukturBaris; 
        System.out.println("Soal 1: Variabel 'StrukturBaris' telah berhasil dideklarasikan.");
        

        System.out.println("-------------------------------------------------");

        
        String KataBaru = "Deklarasi tipe data String";
        System.out.println("Soal 2: Isi variabel KataBaru adalah -> " + KataBaru);

        System.out.println("-------------------------------------------------");

        
        
        int[] empatAngka = {7, 10, 20, 23};
        System.out.println("Soal 3: Isi array empatAngka adalah -> " + Arrays.toString(empatAngka));

        System.out.println("-------------------------------------------------");

        
        
        String[][] Angka = {
            {"1", "3", "5"},    
            {"14", "19", "20"}, 
            {"22", "27", "29"}  
        };
        System.out.println("Soal 4: Isi array 2D Angka adalah -> " + Arrays.deepToString(Angka));

        System.out.println("-------------------------------------------------");

        
        
        LinkedList<Integer> listAngka = new LinkedList<>(Arrays.asList(22, 19, 44, 60, 72));
        System.out.println("Soal 5: Isi linked list listAngka adalah -> " + listAngka);
    }
}