package kolonisavasi;
/**
*
* @author Selcan Narin selcan.narin@ogr.sakarya.edu.tr
* @since 25.05.2023
* <p>
Uretim sınıfını genişleterek oluşturulan AUretim sınıfı
* </p>
*/

import java.util.Random;
class AUretim extends Uretim {
    @Override
    public int Uret() {
        Random random = new Random();
        return random.nextInt(6); //0 ile 5 arası bir sayı döndürülür.
    }
}