package kolonisavasi;
/**
*
* @author Selcan Narin selcan.narin@ogr.sakarya.edu.tr
* @since 25.05.2023
* <p>
Taktik 
* </p>
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Test {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Koloni popülasyonlarını girin: ");
            String input = scanner.nextLine();

            // Girdiyle gelen popülasyonları al ve diziye dönüştür
            String[] popStrArr = input.split(" ");
            long[] popArr = new long[popStrArr.length];

            for (int i = 0; i < popStrArr.length; i++) {
                popArr[i] = Long.parseLong(popStrArr[i]);
            }

            // Kolonileri oluştur ve rastgele sembol, taktik ve üretim ataması yap
            List<Koloni> koloniler = new ArrayList<>();
            Random random = new Random();

            for (long pop : popArr) {
                char sembol = (char) (random.nextInt(26) + 'A');
                Taktik taktik = random.nextBoolean() ? new ATaktik() : new BTaktik();
                Uretim uretim = random.nextBoolean() ? new AUretim() : new BUretim();

                Koloni koloni = new Koloni(sembol, (int) pop, taktik, uretim);
                koloniler.add(koloni);
            }

            Oyun oyun = new Oyun(koloniler);
            oyun.baslat();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}