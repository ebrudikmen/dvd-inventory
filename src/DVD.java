
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Acer
 */
public class DVD {

    private String adSoyad;
    private String albumBasligi;
    private int albumCikis;
    private int albumFiyat;
    ArrayList<String> sarkilar = new ArrayList<String>();

    DVD(String adSoyad, String albumBasligi, int albumCikis, int albumFiyat, ArrayList<String> list) {

        this.adSoyad = adSoyad;
        this.albumBasligi = albumBasligi;
        this.albumFiyat = albumFiyat;
        this.albumCikis = albumCikis;
        sarkilar = list;
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAlbumBasligi() {
        return albumBasligi;
    }

    public void setAlbumBasligi(String albumBasligi) {
        this.albumBasligi = albumBasligi;
    }

    public int getAlbumCikis() {
        return albumCikis;
    }

    public void setAlbumCikis(int albumCikis) {
        this.albumCikis = albumCikis;
    }

    public int getAlbumFiyat() {
        return albumFiyat;
    }

    public void setAlbumFiyat(int albumFiyat) {
        this.albumFiyat = albumFiyat;
    }

    public ArrayList<String> getSarkilar() {
        return sarkilar;
    }

    public void setSarkilar(ArrayList<String> sarkilar) {
        this.sarkilar = sarkilar;
    }

    @Override
    public String toString() {
        return "Album{" + "adSoyad=" + adSoyad + ", albumBasligi=" + albumBasligi + ", albumCikis=" + albumCikis + ", albumFiyat=" + albumFiyat + ", sarkilar=" + sarkilar + '}';
    }

    ArrayList getList() {
        return sarkilar;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
