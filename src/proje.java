
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class proje {

    /*Projede açılan pencereden çıkış yap butonuna basıldığı zaman çıkış yapıldığında girilen bilgiler sakla.txt 
    dosyasına kaydedilir.Öteki türlü çıkıldığında herhangi bir kayıt işlemi gerçekleşmez.*/
 /*Ad Soyad=Ebru Dikmen,Cem ÇORBACIOĞLU
      Numara=05140010122,05130000242
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String adSoyad;
        String albumBasligi;
        int albumCikis;
        int albumFiyat;

        try {
            String userDirectory = System.getProperty("user.dir");//Dosyadan bilgiler okunur belli işlemler uygulanarak 
            File file = new File(userDirectory, "bilgiler.txt");//parçalanır ve listeye eklenir.
            BufferedReader reader = new BufferedReader(new FileReader(file));
            CiftBagliListe liste = new CiftBagliListe();
            CiftBagliListe.Iterator i = liste.iterator();
            String satir = reader.readLine();

            while (satir != null) {

                StringTokenizer st1 = new StringTokenizer(satir, ",");
                adSoyad = st1.nextToken();
                albumBasligi = st1.nextToken();
                albumCikis = Integer.parseInt(st1.nextToken().substring(1));
                albumFiyat = Integer.parseInt(st1.nextToken().substring(1));
                ArrayList<String> list = new ArrayList<>();
                while (st1.hasMoreTokens()) {
                    list.add(st1.nextToken());
                }
                DVD yeniKisi = new DVD(adSoyad, albumBasligi, albumCikis, albumFiyat, list);
                i.add(yeniKisi); // dosyadan okunan kisiler listeye eklenir
                satir = reader.readLine();
            }

            envanter p = new envanter(liste, i);
            p.setVisible(true);
        } catch (FileNotFoundException error) {
            System.out.println("Dosya bulunamadı...");

        }
    }
}
