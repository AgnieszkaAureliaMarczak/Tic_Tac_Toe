package two_players;

import java.util.Scanner;

public class TwoPlayers {
    static int wielkoscPlanszy;
    static char[][] planszaUzytkownika;
    static char aktualnySymbol = 'X';
    static int licznikRuchow = 0;

    public static void main(String[] args) {
        wielkoscPlanszy = ustalRozmiarPlanszy();
        planszaUzytkownika = new char[wielkoscPlanszy][wielkoscPlanszy];
        boolean wygrana = false;
        do {
            wydrukujPlansze();
            boolean poprawnyRuch = wykonajRuch();
            if (poprawnyRuch) {
                licznikRuchow++;
                wygrana = czyWygrana();
                if (wygrana) {
                    System.out.println("Brawo. " + aktualnySymbol + " wygrał.");
                }
                aktualnySymbol = aktualnySymbol == 'X' ? 'O' : 'X';
            } else {
                System.out.println("Niepoprawny ruch. Spróbuj jeszcze raz.");
            }

        } while (licznikRuchow < wielkoscPlanszy * wielkoscPlanszy && !wygrana);
        wydrukujPlansze();
        System.out.println("Koniec gry :)");
    }

    static int ustalRozmiarPlanszy() {
        System.out.println("Podaj rozmiar planszy:");
        Scanner plansza = new Scanner(System.in);
        return plansza.nextInt();
    }

    static void wydrukujPlansze() {
        System.out.print("\t");
        for (int i = 0; i < wielkoscPlanszy; i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
        for (int wiersz = 0; wiersz < wielkoscPlanszy; wiersz++) {
            System.out.print(wiersz + ":  ");
            for (int kolumna = 0; kolumna < wielkoscPlanszy; kolumna++) {
                char symbol = planszaUzytkownika[wiersz][kolumna];
                symbol = symbol != 0 ? symbol : ' ';
                System.out.print(symbol + " | ");
            }
            System.out.println();
        }
    }

    static boolean wykonajRuch() {
        int[] ruchGracza;
        ruchGracza = pobierzRuchCzlowieka();
        int wierszGracza = ruchGracza[0];
        int kolumnaGracza = ruchGracza[1];
        if (czyPoprawny(wierszGracza, kolumnaGracza) && czyPustePole(wierszGracza, kolumnaGracza)) {
            planszaUzytkownika[wierszGracza][kolumnaGracza] = aktualnySymbol;
            return true;
        }
        return false;
    }

    static boolean czyPoprawny(int wiersz, int kolumna) {
        return wiersz >= 0 && wiersz <= wielkoscPlanszy && kolumna >= 0 && kolumna <= wielkoscPlanszy;
    }

    static boolean czyPustePole(int wiersz, int kolumna) {
        return planszaUzytkownika[wiersz][kolumna] == 0;
    }

    static int[] pobierzRuchCzlowieka() {
        int[] pobranaPozycja = new int[2];
        System.out.println(aktualnySymbol + " twój ruch. \nPodaj numer wiersza.");
        Scanner wprowadzenie = new Scanner(System.in);
        int wierszGracza = wprowadzenie.nextInt();
        pobranaPozycja[0] = wierszGracza;
        System.out.println("Podaj numer kolumny.");
        int kolumnaGracza = wprowadzenie.nextInt();
        pobranaPozycja[1] = kolumnaGracza;
        return pobranaPozycja;
    }

    static boolean czyWygrana() {
        return czyWygranaWiersze() ||
                czyWygranaKolumny() ||
                czyWygranaPrzekatna1() ||
                czyWygranaPrzekatna2();
    }

    static boolean czyWygranaWiersze() {
        for (int wiersz = 0; wiersz < planszaUzytkownika.length; wiersz++) {
            boolean wygrana = true;
            int iloscTakichSamych = 0;
            for (int kolumna = 0; kolumna < planszaUzytkownika.length; kolumna++) {
                if (planszaUzytkownika.length <= 5) {
                    if (planszaUzytkownika[wiersz][kolumna] != aktualnySymbol) {
                        wygrana = false;
                        break;
                    }
                } else {
                    if (planszaUzytkownika[wiersz][kolumna] != aktualnySymbol) {
                        iloscTakichSamych = 0;
                        wygrana = false;
                    } else {
                        iloscTakichSamych++;
                        if (iloscTakichSamych == 5) {
                            wygrana = true;
                            break;
                        }
                    }
                }
            }
            if (wygrana) {
                return true;
            }
        }
        return false;
    }

    static boolean czyWygranaKolumny() {
        for (int kolumna = 0; kolumna < planszaUzytkownika.length; kolumna++) {
            boolean wygrana = true;
            int iloscTakichSamych = 0;
            for (int wiersz = 0; wiersz < planszaUzytkownika.length; wiersz++) {
                if (planszaUzytkownika.length <= 5) {
                    if (planszaUzytkownika[wiersz][kolumna] != aktualnySymbol) {
                        wygrana = false;
                        break;
                    }
                } else {
                    if (planszaUzytkownika[wiersz][kolumna] != aktualnySymbol) {
                        iloscTakichSamych = 0;
                        wygrana = false;
                    } else {
                        iloscTakichSamych++;
                        if (iloscTakichSamych == 5) {
                            wygrana = true;
                            break;
                        }
                    }
                }
            }
            if (wygrana) {
                return true;
            }
        }
        return false;
    }

    static boolean czyWygranaPrzekatna1() {
        boolean wygrana = true;
        int iloscTakichSamych = 0;
        for (int i = 0; i < planszaUzytkownika.length; i++) {
            if (planszaUzytkownika.length <= 5) {
                if (planszaUzytkownika[i][i] != aktualnySymbol) {
                    wygrana = false;
                    break;
                }
            } else {
                if (planszaUzytkownika[i][i] != aktualnySymbol) {
                    iloscTakichSamych = 0;
                    wygrana = false;
                } else {
                    iloscTakichSamych++;
                    if (iloscTakichSamych == 5) {
                        wygrana = true;
                        break;
                    }
                }
            }

        }
        return wygrana;
    }

    static boolean czyWygranaPrzekatna2() {
        boolean wygrana = true;
        int iloscTakichSamych = 0;
        for (int i = 0; i < planszaUzytkownika.length; i++) {
            if (planszaUzytkownika.length <= 5) {
                if (planszaUzytkownika[i][planszaUzytkownika.length - 1 - i] != aktualnySymbol) {
                    wygrana = false;
                    break;
                }
            } else {
                if (planszaUzytkownika[i][planszaUzytkownika.length - 1 - i] != aktualnySymbol) {
                    iloscTakichSamych = 0;
                    wygrana = false;
                } else {
                    iloscTakichSamych++;
                    if (iloscTakichSamych == 5) {
                        wygrana = true;
                        break;
                    }
                }
            }
        }
        return wygrana;
    }
}
