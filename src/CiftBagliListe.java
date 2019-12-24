
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
public class CiftBagliListe {

    public class Node {

        //Çift bağlı liste için node classı oluşturulur.
        private DVD albumler;
        private Node previous;
        private Node next;

        public Node() {
            albumler = null;
            previous = null;
            next = null;
        }

        public Node(DVD yeniAlbumler, Node yeniPrevious, Node yeniNext) {
            albumler = yeniAlbumler;
            previous = yeniPrevious;
            next = yeniNext;
        }

        public DVD getAlbumler() {
            return albumler;
        }

        public void setAlbumler(DVD albumler) {
            this.albumler = albumler;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public class Iterator {
                                   //Oluşturulan listede gezinmek için yani kişi ekleyip silebilmek için oluşturulan class. 
        private Node position;

        public Iterator() {
            position = head;
        }

        public void restart() {
            position = head;
        }

        public void next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }

            position = position.next;

        }

        public boolean hasNext() {
            return (position != null);
        }

        public void previous() {
            if (!hasPrevious()) {
                throw new IllegalStateException();
            }

            position = position.previous;
        }
//

        public boolean hasPrevious() {
            return (position != null);
        }

        public String adSoyadDondur() {
            return position.albumler.getAdSoyad();

        }

        public ArrayList albumlerDondur() {
            return position.albumler.getList();
        }

        public int albumTarih() {
            return position.albumler.getAlbumCikis();
        }

        public String getAlbum() {
            return position.albumler.getAlbumBasligi();
        }

        public void add(DVD albumler) {
            int x = 0, y = 0, z = 0;

            if (head != null) {
                x = albumler.getAdSoyad().compareToIgnoreCase(head.albumler.getAdSoyad());
            }
            if (tail != null) {
                y = albumler.getAdSoyad().compareToIgnoreCase(tail.albumler.getAdSoyad());
            }
            if (y > 0) //listenin sonuna   eleman ekleniyor.
            {
                CiftBagliListe.this.addToEnd(albumler);

            } else if ((head == null && tail == null) || x < 0) {
                //liste boşsa
                CiftBagliListe.this.addToStart(albumler);

            } else {
                position = head;

                while (position != tail) {
                    position = position.next;
                    z = albumler.getAdSoyad().compareToIgnoreCase(position.albumler.getAdSoyad());

                    if (z < 0) {
                        break;
                    }
                }

                Node temp = new Node(albumler, position.previous, position);
                position.previous.next = temp;
                position.previous = temp;

            }

        }

        public void delete() {
            if (position == null) {
                throw new IllegalStateException();
            } else if (head == tail) // rehberde tek kalan düğümü silmek için
            {
                position = null;
                head = null;
                tail = null;
            } else if (position.previous == null) // listedeki head düğümünü silmek için
            {
                position.next.previous = null;
                position = null;
                head = head.next;
            } else if (position.next == null) //listedeki tail düğümünü silmek için
            {

                position.previous.next = null;
                position = null;
                tail = tail.previous;

            } else {
                position.previous.next = position.next;
                position.next.previous = position.previous;
                position = position.next;
            }
        }

    }

    public Iterator iterator() {
        return new Iterator();
    }
    private Node head;
    private Node tail;

    public CiftBagliListe() {
        head = null;
        tail = null;
    }

    public void addToStart(DVD albumler) {
        Node newHead = new Node(albumler, null, head);
        if (head != null) {
            head.previous = newHead;
        }
        if (head == null) {
            tail = newHead;
        }
        head = newHead;

    }

    public void addToEnd(DVD albumler) {
        Node newTail = new Node(albumler, tail, null);
        if (tail != null) {
            tail.next = newTail;
        }
        if (tail == null) {
            head = newTail;
        }
        tail = newTail;

    }

    public ArrayList artanKayıtDondur(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        Node position = head;
        ArrayList<String> list = new ArrayList<>();
        while (position != null) {
            list.add(position.albumler.getAdSoyad());
            position = position.getNext();
        }
        return list;
    }

    public ArrayList azalanKayıtDondur(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        Node position = tail;
        ArrayList<String> list2 = new ArrayList<>();
        while (position != null) {
            list2.add(position.albumler.getAdSoyad());
            position = position.getPrevious();
        }
        return list2;
    }

    public void dosyayaYaz(CiftBagliListe liste, CiftBagliListe.Iterator i) {
        try {
            String userDirectory = System.getProperty("user.dir");
            File dosya = new File(userDirectory, "sakla.txt");
            FileWriter yazici = new FileWriter(dosya);
            BufferedWriter yaz = new BufferedWriter(yazici);

            Node position = head;
            ArrayList<String> list = new ArrayList<>();
            while (position != null) {
                yaz.write(position.albumler.getAdSoyad());
                for (int x = 0; x < position.albumler.getList().size(); x++) {
                    yaz.write(", " + position.albumler.getList().get(x));
                }
                yaz.newLine();
                position = position.getNext();

            }

            yaz.close();
            System.out.println("Albumler dosyaya yazıldı");
        } catch (Exception hata) {
            hata.printStackTrace();
        }
    }

}
