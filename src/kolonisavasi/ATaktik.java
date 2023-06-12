package kolonisavasi;
/**
*
* @author Selcan Narin selcan.narin@ogr.sakarya.edu.tr
* @since 25.05.2023
* <p>
Taktik sınıfını genişleterek oluşturulan ATaktik sınıfı.
* </p>
*/
import java.util.Random;
class ATaktik extends Taktik {
    @Override 
    public int Savas() {
        Random rand = new Random();
        return rand.nextInt(501); //0 ile 500 arasında random sayı döndürür.
    }
}