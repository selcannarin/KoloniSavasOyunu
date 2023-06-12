package kolonisavasi;

/**
*
* @author Selcan Narin selcan.narin@ogr.sakarya.edu.tr
* @since 25.05.2023
* <p>
Koloninin özelliklerinin bulunduğu ve güncellendiği sınıf.
* </p>
*/

import java.util.Random;
public class Koloni {
    private char sembol;
    private long populasyon;
    private long yemekStogu;
    private int kazanma;
    private int kaybetme;
    private Taktik taktik;
    private Uretim uretim;


    public Koloni(char sembol, int populasyon, Taktik taktik, Uretim uretim) {
        this.sembol = sembol;
        this.populasyon = populasyon;
        this.yemekStogu = populasyon * populasyon;
        this.kazanma = 0;
        this.kaybetme = 0;
        this.taktik = taktik;
        this.uretim = uretim;
    }

    public int getKaybetme() {
        return kaybetme;
    }
    public void setKaybetme(int kaybetme) {
        this.kaybetme = kaybetme;
    }
    public int getKazanma() {
        return kazanma;
    }
    public void setKazanma(int kazanma) {
        this.kazanma = kazanma;
    }
    public long getPopulasyon() {
        return populasyon;
    }
    public void setPopulasyon(long populasyon) {
        this.populasyon = populasyon;
    }
    public char getSembol() {
        return sembol;
    }
    public void setSembol(char sembol) {
        this.sembol = sembol;
    }

    public long getYemekStogu() {
        return yemekStogu;
    }
    public void setYemekStogu(long yemekStogu) {
        this.yemekStogu = yemekStogu;
    }

    public void savasYap(Koloni koloni, Koloni rakipKoloni) {
        int savasGucu1 = koloni.taktik.Savas(); //Savas fonksiyonunun döndürüldüğü değer alınır.
        int savasGucu2 = rakipKoloni.taktik.Savas();
        Koloni kazananKoloni;
        Koloni kaybedenKoloni;

        if (savasGucu1 > savasGucu2) {
            // Mevcut koloni savaşı kazanır
            kazananKoloni = koloni;
            kaybedenKoloni = rakipKoloni;

        } else if (savasGucu1 < savasGucu2) {
            kazananKoloni = rakipKoloni;
            kaybedenKoloni = koloni;
            // Rakip koloni savaşı kazanır

        } else {
            // Berabere durumunda
            if (koloni.populasyon > rakipKoloni.populasyon) {
                // Mevcut koloni kazanır
                kazananKoloni = koloni;
                kaybedenKoloni = rakipKoloni;

            } else if (koloni.populasyon < rakipKoloni.populasyon) {
                kazananKoloni = rakipKoloni;
                kaybedenKoloni = koloni;
                // Rakip koloni kazanır

            } else {
                // Popülasyonlar eşit, rastgele biri kazanır
                Random random = new Random();
                if (random.nextBoolean()) {
                    kazananKoloni = koloni;
                    kaybedenKoloni = rakipKoloni;

                } else {
                    kazananKoloni = rakipKoloni;
                    kaybedenKoloni = koloni;

                }
            }
        }
            //Eğer kaybeden koloninin popülasyonu 5 veya 5 den küçükse direkt hepsini kaybeder.
            if(kaybedenKoloni.populasyon<=5&& kazananKoloni.populasyon>=1){
                kaybedenKoloni.setPopulasyon(0);

                // Yemek stoğunu transfer et
                double azalmaOrani = Math.abs(savasGucu1 - savasGucu2) / 1000.0;
                int transferEdilenYemek = (int) (kaybedenKoloni.yemekStogu * azalmaOrani);
                kaybedenKoloni.setYemekStogu(kaybedenKoloni.yemekStogu - transferEdilenYemek);
                kazananKoloni.setYemekStogu(kazananKoloni.yemekStogu + transferEdilenYemek);

                //kazanma ve kaybetme sayıları güncellenir.
                kazananKoloni.kazanma++;
                kaybedenKoloni.kaybetme++;
            }
            else{ 
                //Savas gücleri farkı hesaplanır ve 1000 değerine göre azalma oranı bulunur. 
                //Kaybeden koloninin popülasyonu azalmaoranı kadar azalır.
                //kaybeden kolonisinin yemek stoğunun %10’u kazanan koloniye geçer.
                double azalmaOrani = Math.abs(savasGucu1 - savasGucu2) / 1000.0;
    
                // Popülasyonu azalt
                int azalanPopulasyon = (int) (kaybedenKoloni.populasyon * azalmaOrani);
                kaybedenKoloni.setPopulasyon(kaybedenKoloni.populasyon - azalanPopulasyon);
        
                // Yemek stoğunu transfer et
                int transferEdilenYemek = (int) (kaybedenKoloni.yemekStogu * azalmaOrani);
                kaybedenKoloni.setYemekStogu(kaybedenKoloni.yemekStogu - transferEdilenYemek);
                kazananKoloni.setYemekStogu(kazananKoloni.yemekStogu + transferEdilenYemek);

                //kazanma ve kaybetme sayıları güncellenir.
                kazananKoloni.kazanma++;
                kaybedenKoloni.kaybetme++;


            }           
            
    }

    public void uret() {
        int uretilenYemek = uretim.Uret();
        yemekStogu += uretilenYemek;
    }


}