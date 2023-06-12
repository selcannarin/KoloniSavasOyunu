package kolonisavasi;

/**
*
* @author Selcan Narin selcan.narin@ogr.sakarya.edu.tr
* @since 25.05.2023
* <p>
Taktik sınıfını genişleterek oluşturulan BTaktik sınıfı.
* </p>
*/

import java.util.Random;
class BTaktik extends Taktik {
    @Override
    public int Savas() {
        Random rand = new Random();
        return rand.nextInt(501) + 500; // 500 ile 1000 arasında rastgele bir sayı döndürülür.
    }
}