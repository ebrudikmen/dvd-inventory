
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class envanter extends JFrame
        implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private JTextField adSoyadText;
    private JTextField baslikText;
    private JTextField cikisyiliText;
    private JTextField sarkiText;

    private JTextArea icerik;
    private JTextArea pre2000;

    CiftBagliListe liste;
    CiftBagliListe.Iterator i;

    envanter(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        super();
        this.liste = liste;
        this.i = i;
        setTitle("ALBUM UYGULAMASI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);

        JButton ekleButon = new JButton("KİŞİ EKLE");
        ekleButon.addActionListener(this);
        ekleButon.setBounds(20, 255, 150, 50);
        panel.add(ekleButon);

        JButton bilgiButon = new JButton("BİLGİ GÖRÜNTÜLE");
        bilgiButon.addActionListener(this);
        bilgiButon.setBounds(200, 255, 150, 50);
        panel.add(bilgiButon);

        JButton silButon = new JButton("KİŞİ SİL");
        silButon.addActionListener(this);
        silButon.setBounds(20, 330, 150, 50);
        panel.add(silButon);

        JButton sıralaButon = new JButton("A-Z'YE SIRALA");
        sıralaButon.addActionListener(this);
        sıralaButon.setBounds(200, 330, 150, 50);
        panel.add(sıralaButon);

        JButton sıralaButon2 = new JButton("Z-A'YA SIRALA");
        sıralaButon2.addActionListener(this);
        sıralaButon2.setBounds(20, 405, 150, 50);
        panel.add(sıralaButon2);

        JButton pre2000button = new JButton("2000 ÖNCESİ");
        pre2000button.addActionListener(this);
        pre2000button.setBounds(200, 405, 150, 50);
        panel.add(pre2000button);

        JButton cıkısButon = new JButton("ÇIKIŞ YAP");
        cıkısButon.addActionListener(this);
        cıkısButon.setBounds(20, 475, 150, 50);
        panel.add(cıkısButon);

        JLabel label = new JLabel("AD SOYAD:");
        label.setBounds(20, 30, 200, 30);
        panel.add(label);
        adSoyadText = new JTextField();
        adSoyadText.setBounds(150, 30, 200, 30);
        panel.add(adSoyadText);

        JLabel label2 = new JLabel("DVD BAŞLIĞI:");
        label2.setBounds(20, 80, 200, 30);
        panel.add(label2);
        baslikText = new JTextField();
        baslikText.setBounds(150, 80, 200, 30);
        panel.add(baslikText);

        JLabel label3 = new JLabel("ÇIKIŞ YILI:");
        label3.setBounds(20, 130, 200, 30);
        panel.add(label3);
        cikisyiliText = new JTextField();
        cikisyiliText.setBounds(150, 130, 200, 30);
        panel.add(cikisyiliText);

        JLabel label4 = new JLabel("ŞARKI:");
        label4.setBounds(20, 180, 200, 30);
        panel.add(label4);
        sarkiText = new JTextField();
        sarkiText.setBounds(150, 180, 200, 30);
        panel.add(sarkiText);

        icerik = new JTextArea("\n !!!  sarkiların arasına (,) sarki" + "\n\n" + "  birden fazla sarki ekleyebilirsiniz.  !!!");

        icerik.setBounds(400, 30, 250, 320);
        panel.add(icerik);

        pre2000 = new JTextArea("2000 yılından önce çıkan albümler: \n");

        pre2000.setBounds(660, 30, 250, 320);

        panel.add(pre2000);

        add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonString = e.getActionCommand();

        if (buttonString.equals("KİŞİ EKLE")) {
            if (ekliMi() == 1) {
                icerik.setText("Envanterde bu isme ait bir kayıt var!");
            } else if (ekliMi() == 2) {
                icerik.setText("Ad ve soyad kısmı boş bırakılamaz.");
            } else {
                kisiEkle(liste, i);
                icerik.setText("Kişi Envantere Eklendi");
            }
        } else if (buttonString.equals("BİLGİ GÖRÜNTÜLE")) {
            if (ekliMi() == 0) {
                icerik.setText("Bu kişi envanterde  kayıtlı değildir!");
            } else if (ekliMi() == 2) {
                icerik.setText("Ad ve soyad kısmı boş bırakılamaz.");
            } else {
                icerik.setText(null);
                ArrayList<String> bilgi = goruntule(liste, i);
                for (int x = 0; x < bilgi.size(); x++) {
                    icerik.append(bilgi.get(x) + "\n");
                }
            }
        } else if (buttonString.equals("KİŞİ SİL")) {
            if (ekliMi() == 0) {
                icerik.setText("Bu kişi envanter de kayıtlı değildir!");
            } else if (ekliMi() == 2) {
                icerik.setText("Ad ve soyad kısmı boş bırakılamaz.");
            } else {
                kisiSil(liste, i);
                icerik.setText("Kişi Silindi");
            }
        } else if (buttonString.equals("A-Z'YE SIRALA")) {
            icerik.setText(null);
            ArrayList<String> artanSıra = liste.artanKayıtDondur(liste, i);
            for (int x = 0; x < artanSıra.size(); x++) {
                icerik.append(artanSıra.get(x) + "\n");
            }

        } else if (buttonString.equals("Z-A'YA SIRALA")) {
            icerik.setText(null);
            ArrayList<String> azalanSıra = liste.azalanKayıtDondur(liste, i);
            for (int x = 0; x < azalanSıra.size(); x++) {
                icerik.append(azalanSıra.get(x) + "\n");
            }
        } else if (buttonString.equals("ÇIKIŞ YAP")) {
            liste.dosyayaYaz(liste, i);
            System.exit(0);
        } else if (buttonString.equals("2000 ÖNCESİ")) {
            pre2000.setText(null);
            ArrayList<String> pre2000list = before2000(liste, i);
            for (int x = 0; x < pre2000list.size(); x++) {
                pre2000.append(pre2000list.get(x) + "\n");
            }
        }
    }

    public void kisiEkle(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        String adSoyad = adSoyadText.getText();

        String sarki1 = sarkiText.getText();

        ArrayList<String> list = new ArrayList<>();

        String[] dizi = sarki1.split(","); //Aralarında virgül konularak girilen 
        for (String a : dizi) //sarki1 stringini ayırabilmek için kullanıldı
        {
            list.add(a);
        }
        DVD yeniKisi = new DVD(adSoyad, null, 0, 0, list);
        i.add(yeniKisi);

    }

    public ArrayList goruntule(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        String adSoyad = adSoyadText.getText();
        String adSoyad2 = null;
        ArrayList<String> telNum = null;

        i.restart();
        do {
            adSoyad2 = i.adSoyadDondur();
            if (adSoyad2.equalsIgnoreCase(adSoyad)) {
                telNum = i.albumlerDondur();
                break;
            }
            i.next();

        } while (!adSoyad2.equalsIgnoreCase(adSoyad));

        return telNum;

    }

    public ArrayList before2000(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        int album = 0;
        ArrayList<String> list = new ArrayList<String>();

        i.restart();
        do {
            album = i.albumTarih();
            if (album <= 2000) {
                list.add(i.getAlbum());

                break;
            }
            i.next();

        } while (i.hasNext());

        return list;

    }

    public void kisiSil(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        String adSoyad = adSoyadText.getText();
        String adSoyad2 = null;
        i.restart();
        do {
            adSoyad2 = i.adSoyadDondur();
            if (adSoyad2.equalsIgnoreCase(adSoyad)) {
                i.delete();
                break;
            }
            i.next();

        } while (!adSoyad2.equalsIgnoreCase(adSoyad));

    }

    public int ekliMi() {
        String adSoyad = adSoyadText.getText();
        int deger = 0;
        ArrayList<String> kayıtlar = liste.artanKayıtDondur(liste, i);

        for (int x = 0; x < kayıtlar.size(); x++) {
            if ("".equals(adSoyad)) //ad soyad girişi yoksa 
            {
                deger = 2;
                break;
            } else if (kayıtlar.get(x).equalsIgnoreCase(adSoyad)) {
                deger = 1;
                break;
            } else {
                deger = 0;
            }

        }
        return deger;
    }

}
