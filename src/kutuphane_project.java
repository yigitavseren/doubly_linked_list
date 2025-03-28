import java.util.Scanner;

public class kutuphane_project {
    public static void main(String[] args) {
        ciftYonluBagliListe dll = new ciftYonluBagliListe();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1-) Kitabi listenin basina ekleme");
            System.out.println("2-) Kitabi listenin sonuna ekleme");
            System.out.println("3-) Kitabi listenin ortasina ekleme");
            System.out.println("4-) Kitap adina gore silme");
            System.out.println("5-) Ortadaki kitabi silme");
            System.out.println("6-) Listeyi bastan sona goruntuleme");
            System.out.println("7-) Listeyi sondan basa goruntuleme");
            System.out.println("8-) Exit");
            System.out.print("Seciminizi yapin: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    System.out.print("Kitap Adi: ");
                    String book1 = scanner.nextLine();
                    System.out.print("Yazar Adi: ");
                    String author1 = scanner.nextLine();
                    dll.basaEkle(book1, author1);
                    break;
                case 2:
                    System.out.print("Kitap Adi: ");
                    String book2 = scanner.nextLine();
                    System.out.print("Yazar Adi: ");
                    String author2 = scanner.nextLine();
                    dll.sonaEkle(book2, author2);
                    break;
                case 3:
                    System.out.print("Kitap Adi: ");
                    String book3 = scanner.nextLine();
                    System.out.print("Yazar Adi: ");
                    String author3 = scanner.nextLine();
                    dll.ortayaEkle(book3, author3);
                    break;
                case 4:
                    System.out.print("Silinecek Kitap Adi: ");
                    String book4 = scanner.nextLine();
                    if (!dll.ismeGoreSil(book4)) {
                        System.out.println("Kitap bulunamadi!");
                    }
                    break;
                case 5:
                    dll.ortayiSil();
                    break;
                case 6:
                    dll.dumduz();
                    break;
                case 7:
                    dll.tersten();
                    break;
                case 8:
                    scanner.close();
                    return;
                default:
                    System.out.println("Gecersiz secim!");
            }
        }
    }
}

class Node {
    String kitapAdi;
    String yazarAdi;
    Node next;
    Node prev;

    public Node(String bookName, String authorName) {
        this.kitapAdi = bookName;
        this.yazarAdi = authorName;
        this.next = null;
        this.prev = null;
    }
}

class ciftYonluBagliListe {
    private Node bas;
    private Node son;
    private int size;

    public ciftYonluBagliListe() {
        this.bas = null;
        this.son = null;
        this.size = 0;
    }

    public void basaEkle(String bookName, String authorName) {
        Node newNode = new Node(bookName, authorName);
        if (bas == null) {
            bas = son = newNode;
        } else {
            newNode.next = bas;
            bas.prev = newNode;
            bas = newNode;
        }
        size++;
    }

    public void sonaEkle(String bookName, String authorName) {
        Node newNode = new Node(bookName, authorName);
        if (son == null) {
            bas = son = newNode;
        } else {
            son.next = newNode;
            newNode.prev = son;
            son = newNode;
        }
        size++;
    }

    public void ortayaEkle(String bookName, String authorName) {
        if (size < 2) {
            sonaEkle(bookName, authorName);
            return;
        }
        Node newNode = new Node(bookName, authorName);
        int middleIndex = size / 2 + 1;
        Node temp = bas;
        for (int i = 0; i < middleIndex - 1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        newNode.prev = temp;
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        temp.next = newNode;
        if (newNode.next == null) {
            son = newNode;
        }
        size++;
    }

    public boolean ismeGoreSil(String bookName) {
        Node temp = bas;
        while (temp != null) {
            if (temp.kitapAdi.equals(bookName)) {
                if (temp.prev != null) {
                    temp.prev.next = temp.next;
                } else {
                    bas = temp.next;
                }
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                } else {
                    son = temp.prev;
                }
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void ortayiSil() {
        if (size < 2) {
            return;
        }
        int middleIndex = size / 2;
        Node temp = bas;
        for (int i = 0; i < middleIndex; i++) {
            temp = temp.next;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        if (temp == bas) {
            bas = temp.next;
        }
        if (temp == son) {
            son = temp.prev;
        }
        size--;
    }

    public void dumduz() {
        Node temp = bas;
        while (temp != null) {
            System.out.println(temp.kitapAdi + " - " + temp.yazarAdi);
            temp = temp.next;
        }
    }

    public void tersten() {
        Node temp = son;
        while (temp != null) {
            System.out.println(temp.kitapAdi + " - " + temp.yazarAdi);
            temp = temp.prev;
        }
    }
}