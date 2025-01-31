/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baslangicbitis;

import java.util.Scanner;

/**
 * @file baslangicBitis
 * @description kullanıcının iki boyutlu bir labirentten çıkmasını amaçlar.
 * @assignment 1. Proje
 * @date 30/10/2023
 * @author Muhammed Berat Kerem Aydın / mberatkerem.aydin@stu.fsm.edu.tr
 */
public class BaslangicBitis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[][] labirent
                = {{'#', '!', '.', '.', 'R', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
                {'.', '.', '#', '.', '.', '.', '#', '.', 'H', '.', '.', '.', '.', '.', '!'},
                {'F', '.', '.', '.', '#', '.', '!', '.', '.', 'R', '.', '.', '#', '#', '.'},
                {'.', '.', '#', '.', '.', '#', '.', '.', '.', '.', 'F', '.', '.', '.', '.'},
                {'.', '!', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
                {'.', '.', 'H', '.', '.', '!', '.', '.', 'H', '.', '.', 'F', '.', '.', 'R'},
                {'#', '#', '#', '#', '.', '.', '#', '.', '.', '.', 'T', '.', '.', '.', 'E'},
                {'.', '.', '#', '.', 'F', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
                {'.', '#', '.', '.', '.', '.', '!', '.', '#', '.', '.', '.', '#', '.', '.'},
                {'.', 'T', 'T', '.', '#', '#', '.', '.', '.', '.', 'T', '.', '.', '.', 'R'},
                {'.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', 'T', '.'},
                {'B', '.', '#', '.', '.', '!', '.', '!', '.', '.', '.', '.', '.', '.', '#'},
                {'.', '.', '.', 'F', '!', '.', '.', '.', 'H', '.', '.', 'R', '.', '.', '.'},
                {'.', '.', 'H', '.', '.', '.', '!', '.', '.', '.', '#', '.', '.', '#', '.'},
                {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '#'}};

        int cikisKonumu[] = E_bulma(labirent);
        //konumYazdirma(cikisKonumu);
        int konum[] = B_bulma(labirent);
        int[] T = new int[1];
        int[] R = new int[1];
        int[] H = new int[1];
        int[] F = new int[1];
        char mevcutKarakterKontrol[] = {'B'};
        konumYazdirma(konum);
        int[] hamleSayisi = new int[1];
        int[] haritaSayaci = new int[1];
        haritaYazdirma(labirent, konum);
        Scanner input = new Scanner(System.in);
        System.out.println("hareket etmek için w,a,s,d tuşlarını ; envanteri açmak için + tuşunu ; çıkış yapmak için 'exit' yazınız.");
        String yon = input.nextLine();
        while (!yon.equals("exit")) {

            if (!yon.equals("+")) {
                hareketEtme(konum, hamleSayisi, labirent, yon, haritaSayaci);
                char mevcutKarakter[] = karakterAlgilama(labirent, konum);
                karakteriIsleme(mevcutKarakter, T, R, H, F, konum, yon, labirent, hamleSayisi, haritaSayaci);

                System.out.println(mevcutKarakter);
                if (haritaSayaci[0] == 5 && haritaSayaci[0] != 0) {
                    haritaDegistirme(labirent, konum);
                    mevcutKarakterKontrol = karakterAlgilama(labirent, konum);
                    karakteriIsleme(mevcutKarakterKontrol, T, R, H, F, konum, yon, labirent, hamleSayisi, haritaSayaci);
                    System.out.println(mevcutKarakterKontrol);
                    haritaSayaci[0] = 0;
                } else {
                    haritaYazdirma(labirent, konum);
                }
                if (konum[0] == cikisKonumu[0] && konum[1] == cikisKonumu[1]) {
                    System.out.println("tebrikler");
                    break;
                }
                System.out.println("hareket etmek için w,a,s,d tuşlarını ; envanteri açmak için + tuşunu ; çıkış yapmak için 'exit' yazınız.");
                yon = input.nextLine();
            } else {
                arti(T, R, H, F, labirent, konum, hamleSayisi, mevcutKarakterKontrol, yon,haritaSayaci);
                if (konum[0] == cikisKonumu[0] && konum[1] == cikisKonumu[1]) {
                    System.out.println("Çıkışa ulaştınız.Tebrikler");
                    break;
                }
                System.out.println("hareket etmek için w,a,s,d tuşlarını ; envanteri açmak için + tuşunu ; çıkış yapmak için 'exit' yazınız.");
                yon = input.nextLine();

            }

        }

    }

    public static void karakteriIsleme(char karakter[], int[] T, int[] R, int[] H, int[] F, int konum[], String yon, char labirent[][], int hamleSayisi[], int[] haritaSayaci) {
        Scanner inputt = new Scanner(System.in);
        char yonn;
        if (karakter[0] == '#') {
            System.out.println("duvar ile karşılaşıldı.");
            if (R[0] > 0) {
                System.out.println("envanteri açmak için + tuşuna basınız,kullanmak istemiyorsanız herhangi bir tuşa basınız.");
                yonn = inputt.nextLine().charAt(0);
                if (yonn == '+') {
                    arti(T, R, H, F, labirent, konum, hamleSayisi, karakter, yon,haritaSayaci);
                } else {
                    System.out.println("hiçbir bonus kullanılmadı.");
                    if (yon.charAt(0) == 'w') {
                        konum[0]++;
                    } else if (yon.charAt(0) == 's') {
                        konum[0]--;
                    } else if (yon.charAt(0) == 'd') {
                        konum[1]--;
                    } else if (yon.charAt(0) == 'a') {
                        konum[1]++;
                    }
                }
            } else {
                System.out.println("engel kaldırma bonusu bulunmamaktadır.");
                if (yon.charAt(0) == 'w') {
                    konum[0]++;
                } else if (yon.charAt(0) == 's') {
                    konum[0]--;
                } else if (yon.charAt(0) == 'd') {
                    konum[1]--;
                } else if (yon.charAt(0) == 'a') {
                    konum[1]++;
                }
            }
        } else if (karakter[0] == '!') {
            System.out.println("mayın ile karşılaşıldı.");
            if (F[0] > 0) {
                System.out.println("envanteri açmak için + tuşuna basınız,kullanmak istemiyorsanız herhangi bir tuşa basınız.");
                yonn = inputt.nextLine().charAt(0);
                if (yonn == '+') {
                    arti(T, R, H, F, labirent, konum, hamleSayisi, karakter, yon,haritaSayaci);
                } else {
                    System.out.println("bonus seçilmedi.");
                    hamleSayisi[0] += 5;
                    System.out.println("yeni hamle sayınız: " + hamleSayisi[0]);
                }
            } else {
                System.out.println("mayın çözme bonusu bulunmamaktadır.");
                hamleSayisi[0] += 5;
                System.out.println("yeni hamle sayınız: " + hamleSayisi[0]);
            }
            labirent[konum[0]][konum[1]] = '.';
        } else if (karakter[0] == 'T') {
            System.out.println("ışınlanma bonusu envantere eklendi.");
            labirent[konum[0]][konum[1]] = '.';
            T[0]++;
        } else if (karakter[0] == 'R') {
            System.out.println("engel kaldırma bonusu envantere eklendi.");
            labirent[konum[0]][konum[1]] = '.';
            R[0]++;
        } else if (karakter[0] == 'H') {
            System.out.println("hamle sayısı azaltma bonusu envantere eklendi.");
            labirent[konum[0]][konum[1]] = '.';
            H[0]++;
        } else if (karakter[0] == 'F') {
            System.out.println("mayın çözme bonusu envantere eklendi.");
            labirent[konum[0]][konum[1]] = '.';
            F[0]++;
        } else if (karakter[0] == '.' || karakter[0] == 'B') {

        }
        konumYazdirma(konum);
    }

    public static char[] karakterAlgilama(char[][] labirent, int konum[]) {
        char mevcutKarakter[] = new char[1];
        if (labirent[konum[0]][konum[1]] == '.') {
            mevcutKarakter[0] = '.';
        } else if (labirent[konum[0]][konum[1]] == '#') {
            mevcutKarakter[0] = '#';
        } else if (labirent[konum[0]][konum[1]] == '!') {
            mevcutKarakter[0] = '!';
        } else if (labirent[konum[0]][konum[1]] == 'T') {
            mevcutKarakter[0] = 'T';
        } else if (labirent[konum[0]][konum[1]] == 'R') {
            mevcutKarakter[0] = 'R';
        } else if (labirent[konum[0]][konum[1]] == 'H') {
            mevcutKarakter[0] = 'H';
        } else if (labirent[konum[0]][konum[1]] == 'F') {
            mevcutKarakter[0] = 'F';
        } else if (labirent[konum[0]][konum[1]] == 'B') {
            mevcutKarakter[0] = 'B';
        } else if (labirent[konum[0]][konum[1]] == 'E') {
            mevcutKarakter[0] = 'E';
        }
        return mevcutKarakter;
    }

    public static void haritaYazdirma(char labirent[][], int konum[]) {
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (i == konum[0] && j == konum[1]) {
                    System.out.print("X ");
                } else {
                    System.out.print(labirent[i][j] + " ");
                }

            }
            System.out.println("");
        }
    }

    public static void konumYazdirma(int konum[]) {
        System.out.println("(" + konum[0] + "," + konum[1] + ")");
    }

    public static int[] E_bulma(char[][] matrix) {
        int[] cikis = new int[2];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'E') {
                    cikis[index] = i;
                    cikis[index + 1] = j;
                }
            }
        }
        return cikis;
    }

    public static int[] B_bulma(char[][] matrix) {
        int[] giris = new int[2];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'B') {
                    giris[index] = i;
                    giris[index + 1] = j;
                }
            }
        }
        return giris;
    }

    public static void hareketEtme(int konum[], int[] hamleSayisi, char labirent[][], String yon, int[] haritaSayaci) {
        yon = yon.toLowerCase();
        if (yon.length() == 1) {
            if (yon.charAt(0) == 'w' && konum[0] != 0) {
                konum[0]--;
                hamleSayisi[0]++;
                haritaSayaci[0]++;
            } else if (yon.charAt(0) == 's' && konum[0] != labirent.length - 1) {
                konum[0]++;
                hamleSayisi[0]++;
                haritaSayaci[0]++;
            } else if (yon.charAt(0) == 'd' && konum[1] != labirent.length - 1) {
                konum[1]++;
                hamleSayisi[0]++;
                haritaSayaci[0]++;
            } else if (yon.charAt(0) == 'a' && konum[1] != 0) {
                konum[1]--;
                hamleSayisi[0]++;
                haritaSayaci[0]++;
            } else {
                System.out.println("geçerli yön tuşuna basınız.");
            }
        } else {
            System.out.println("talimata uygun girdi giriniz.");
        }
        System.out.println(hamleSayisi[0] + ". hamleyi yaptınız");
    }

    public static void R_bonus(int[] R, char labirent[][], int konum[]) {
        if (labirent[konum[0]][konum[1]] == '!') {
            labirent[konum[0]][konum[1]] = '.';
            R[0]--;
            System.out.println("duvar kaldırıldı.");
        } else {
            System.out.println("konumunuzda engel bulunmamaktadır.");
        }

    }

    public static void T_bonus(int[] T, char labirent[][], int konum[], char mevcutKarakterKontrol[], int hamleSayisi[], int[] R, int[] H, int[] F, String yon,int[] haritaSayaci) {
        Scanner input = new Scanner(System.in);
        System.out.println("gitmedik istediğiniz koordinat için sırasıyla x ve y değerlerini giriniz.");
        System.out.print("dikey=");
        int x = input.nextInt();
        System.out.print("yatay=");
        int y = input.nextInt();
        while (true) {
            if (x >= 0 && x < labirent.length && y >= 0 && y < labirent.length && labirent[x][y] != '#' && labirent[x][y] != '!') {
                konum[0] = x;
                konum[1] = y;
                T[0]--;
                mevcutKarakterKontrol = karakterAlgilama(labirent, konum);
                karakteriIsleme(mevcutKarakterKontrol, T, R, H, F, konum, yon, labirent, hamleSayisi,haritaSayaci);
                break;

            } else {
                System.out.println("yanlış x veya y değeri girdiniz, x ve y harita sınırları içerisinde olmalıdır (0-" + labirent.length + ")");
                System.out.println("gitmedik istediğiniz koordinat için sırasıyla x ve y değerlerini giriniz.");
                System.out.print("dikey=");
                x = input.nextInt();
                System.out.print("yatay=");
                y = input.nextInt();
            }
        }

        konumYazdirma(konum);
        haritaYazdirma(labirent, konum);
    }

    public static void H_bonus(int[] H, int hamleSayisi[]) {
        if (hamleSayisi[0] >= 2) {
            hamleSayisi[0] -= 2;
        } else {
            hamleSayisi[0] = 0;
            System.out.println("hamle sayınız sıfırlanmıştır.");
        }

        H[0]--;
        System.out.println("hamle sayınız 2 azaldı. \n yeni hamle sayınız : " + hamleSayisi[0]);
    }

    public static void F_bonus(int[] F, char labirent[][], int konum[]) {
        if (labirent[konum[0]][konum[1]] == '!') {
            labirent[konum[0]][konum[1]] = '.';
            F[0]--;
            System.out.println("mayın imha edildi.");
        } else {
            System.out.println("bulunduğunuz konumda mayın bulunmamaktadır");
        }

    }

    public static void arti(int T[], int R[], int H[], int F[], char labirent[][], int konum[], int hamleSayisi[], char karakter[], String yon,int []haritaSayaci) {
        Scanner input = new Scanner(System.in);
        System.out.println("Envanter : \n 1.Işınlanma bonusu:" + T[0] + "\n 2.Engel kaldırma bonusu:" + R[0] + "\n 3.hamle sayısını azaltma bonusu:" + H[0] + "\n 4.mayın çözme bonusu :" + F[0]);
        System.out.print("hangi bonusu kullanmak istiyorsunuz.");
        System.out.println("bonus kullanmaktan vaz geçtiyseniz esc yazınız.");
        String girdi = input.nextLine();
        while (true) {
            if (girdi.charAt(0) == '1' && T[0] > 0) {
                T_bonus(T, labirent, konum, karakter, hamleSayisi, R, H, F, yon,haritaSayaci);
                break;
            } else if (girdi.charAt(0) == '2' && karakter[0] == '#' && R[0] > 0) {
                R_bonus(R, labirent, konum);
                labirent[konum[0]][konum[1]] = '.';
                break;
            } else if (girdi.charAt(0) == '3' && H[0] > 0) {
                H_bonus(H, hamleSayisi);
                break;
            } else if (girdi.charAt(0) == '4' && karakter[0] == '!' && F[0] > 0) {
                F_bonus(F, labirent, konum);
                break;
            } else if (girdi.equals("esc")) {
                if (karakter[0] == '#') {
                    if (yon.charAt(0) == 'w') {
                        konum[0]++;
                    } else if (yon.charAt(0) == 's') {
                        konum[0]--;
                    } else if (yon.charAt(0) == 'd') {
                        konum[1]--;
                    } else if (yon.charAt(0) == 'a') {
                        konum[1]++;
                    }
                } else if (karakter[0] == '!') {
                    hamleSayisi[0] += 5;
                    System.out.println("hamle sayınız 5 arttı. \n yeni hamle sayınız : " + hamleSayisi[0]);
                } else {

                }

                System.out.println("hiç bir bonus kullanılmadı.");
                break;

            } else if (karakter[0] == '!') {
                System.out.println("kullanmak istediğiniz bonusu tekrar yazınız.");
                System.out.println("bonus kullanmaktan vaz geçtiyseniz esc yazınız.");
                girdi = input.nextLine();
            } else if (karakter[0] == '#') {
                System.out.println("kullanmak istediğiniz bonusu tekrar yazınız.");
                System.out.println("bonus kullanmaktan vaz geçtiyseniz esc yazınız.");
                girdi = input.nextLine();
            } else {
                System.out.println("1 ile 4 arasında bir rakam giriniz.");
                System.out.print("hangi bonusu kullanmak istiyorsunuz.-o bonusa sahip olmak zorundasınız-");
                girdi = input.nextLine();
            }

        }

        // haritaYazdirma(labirent, konum);
    }

    public static void haritaDegistirme(char labirent[][], int konum[]) {
        int t = 0;
        int r = 0;
        int h = 0;
        int f = 0;
        int unlem = 0;

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'T') {
                    t++;
                } else if (labirent[i][j] == 'R') {
                    r++;
                } else if (labirent[i][j] == 'H') {
                    h++;
                } else if (labirent[i][j] == 'F') {
                    f++;
                } else if (labirent[i][j] == '!') {
                    unlem++;
                }
                if (labirent[i][j] != '#' && labirent[i][j] != 'B' && labirent[i][j] != 'E') {
                    labirent[i][j] = '.';
                }
            }
        }

        while (unlem > 0) {
            int yeniKonumYatay = (int) (Math.random() * labirent.length);
            int yeniKonumDikey = (int) (Math.random() * labirent.length);
            if (labirent[yeniKonumYatay][yeniKonumDikey] == '.') {
                labirent[yeniKonumYatay][yeniKonumDikey] = '!';
                unlem--;
            }

        }
        while (t > 0) {
            int yeniKonumYatay = (int) (Math.random() * labirent.length);
            int yeniKonumDikey = (int) (Math.random() * labirent.length);
            if (labirent[yeniKonumYatay][yeniKonumDikey] == '.') {
                labirent[yeniKonumYatay][yeniKonumDikey] = 'T';
                t--;
            }

        }
        while (r > 0) {
            int yeniKonumYatay = (int) (Math.random() * labirent.length);
            int yeniKonumDikey = (int) (Math.random() * labirent.length);
            if (labirent[yeniKonumYatay][yeniKonumDikey] == '.') {
                labirent[yeniKonumYatay][yeniKonumDikey] = 'R';
                r--;
            }

        }
        while (h > 0) {
            int yeniKonumYatay = (int) (Math.random() * labirent.length);
            int yeniKonumDikey = (int) (Math.random() * labirent.length);
            if (labirent[yeniKonumYatay][yeniKonumDikey] == '.') {
                labirent[yeniKonumYatay][yeniKonumDikey] = 'H';
                h--;
            }

        }
        while (f > 0) {
            int yeniKonumYatay = (int) (Math.random() * labirent.length);
            int yeniKonumDikey = (int) (Math.random() * labirent.length);
            if (labirent[yeniKonumYatay][yeniKonumDikey] == '.') {
                labirent[yeniKonumYatay][yeniKonumDikey] = 'F';
                f--;
            }

        }
        System.out.println("harita güncellendi.");
        haritaYazdirma(labirent, konum);

    }

}
