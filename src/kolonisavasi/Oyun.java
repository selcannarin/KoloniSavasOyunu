package kolonisavasi;

/**
*
* @author Selcan Narin selcan.narin@ogr.sakarya.edu.tr
* @since 25.05.2023
* <p>
Oyun sınıfı, koloniler arasındaki savaşları yöneterek tur bazlı bir oyun kurar.
* </p>
*/
import java.util.List;
public class Oyun {
    private List<Koloni> koloniler;
    private int turSayisi;
    private int hayattaKalanKoloniSayisi;

    public Oyun(List<Koloni> koloniler) {
        this.koloniler = koloniler;
        this.turSayisi = 0;
        this.hayattaKalanKoloniSayisi = 0;
    }

    public void baslat() {
        System.out.println("Tur sayisi: " + turSayisi);
        System.out.printf("%-10s%-20s%-20s%-20s%-20s\n", "Koloni", "Populasyon", "Yemek Stogu", "Kazanma", "Kaybetme");
    
        for (Koloni koloni : koloniler) { //Başlangıç hali yazdırıldı.
            if (koloni.getPopulasyon() > 0) {
                System.out.printf("%-10s%-20s%-20s%-20s%-20s\n",
                        koloni.getSembol(),
                        koloni.getPopulasyon(),
                        koloni.getYemekStogu(),
                        koloni.getKazanma(),
                        koloni.getKaybetme());
            }
        }
    
        boolean koloniKaldimi = true; 
        while (koloniKaldimi) { //Koloni kaldıysa tur yapmaya devam edilir.
            turSayisi++;
            System.out.println("Tur sayisi: " + turSayisi);
            System.out.printf("%-10s%-20s%-20s%-20s%-20s\n", "Koloni", "Populasyon", "Yemek Stogu", "Kazanma", "Kaybetme");
    
            koloniKaldimi = false;
    
            for (int i = 0; i < koloniler.size(); i++) { //İç içe döngü ile hepsi birbiriyle savaşır.
                Koloni koloni = koloniler.get(i);
                if (koloni.getPopulasyon() <= 0 || koloni.getYemekStogu() <= 0) {
                    continue; // Koloni hayatta değil, atla
                }
    
                for (int j = i + 1; j < koloniler.size(); j++) {
                    Koloni rakipKoloni = koloniler.get(j);
                    if (rakipKoloni.getPopulasyon() <= 0 || rakipKoloni.getYemekStogu() <= 0) {
                        continue; // Rakip koloni hayatta değil, atla
                    }
    
                    koloni.savasYap(koloni, rakipKoloni);
                    koloni.uret(); //her turda üret fonksiyonu çağırılır. 

                }
                
                //Her turda her koloninin popülasyonu %20 oranında artar ve Yemek stoğu (GüncelPopülasyon x 2) oranında azalır.
                koloni.setPopulasyon((long) (koloni.getPopulasyon() + (koloni.getPopulasyon() * 0.2)));
                koloni.setYemekStogu((long) (koloni.getYemekStogu() - (koloni.getPopulasyon() * 2)));

    
                               
            }
            for (Koloni koloni : koloniler) {
                if (koloni.getPopulasyon()<= 1 || koloni.getYemekStogu() <= 0) { //Hayatta olmayan koloniler yazılır.
                    System.out.printf("%-10s%-20s%-20s%-20s%-20s\n",
                    koloni.getSembol(),
                    "--",
                    "--",
                    "--",
                    "--");
                    
                } else {
                    System.out.printf("%-10s%-20s%-20s%-20s%-20s\n", //Hayatta olan koloniler yazılır ve sayısı alınır.
                    koloni.getSembol(),
                    koloni.getPopulasyon(),
                    koloni.getYemekStogu(),
                    koloni.getKazanma(),
                    koloni.getKaybetme());

                    hayattaKalanKoloniSayisi++;
                    koloniKaldimi = true;
                }

        
            }
            if(hayattaKalanKoloniSayisi ==1){ //Hayatta olan bir koloni kaldıysa kazanan odur ve döngü biter.
                break;
            }
            hayattaKalanKoloniSayisi = 0;
        }
    }
    
    

}
