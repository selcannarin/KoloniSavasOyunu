package kolonisavasi;

/**
*
* @author Selcan Narin selcan.narin@ogr.sakarya.edu.tr
* @since 25.05.2023
* <p>
Uretim sınıfını genişleterek oluşturulan BUretim sınıfı
* </p>
*/

import java.util.Random;
class BUretim extends Uretim {
    @Override
    public int Uret() {
        Random random = new Random();
        return random.nextInt(6) + 5; //6 ile 10 arasında bir sayı döndürülür.
    }
}