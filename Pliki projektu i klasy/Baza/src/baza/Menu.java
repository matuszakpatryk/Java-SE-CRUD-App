package baza;

import java.io.IOException;
import java.util.Scanner;

class Menu
{

    void chooseShow() throws IOException, InterruptedException //Metoda, która wyświetla menu po wyświetleniu danych
    {
        String x;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[B] - Powróć do poprzedniego menu.");
            System.out.println("[M] - Powróć do menu głównego.");
            System.out.println("[Q] - Zakończ program.");
            x = s.next();
            x = x.toUpperCase();
            switch(x)
            {
                case "B":
                    Show.showMain();
                    break;
                case "M":
                    showMenu();
                    break;
                case "Q":
                    System.out.println("---Zamykanie programu---");
                    System.exit(0);
                    break;
                default:
                    System.out.println("nieprzewidziana sytuacja");
                    break;
            }
        } while (!x.equals("Q"));
        s.close();
    }

    void showMenu() throws IOException, InterruptedException //Metoda, która wyświetla Menu Wyświetlania
    {
        {
            Count write = new Count();
            ShowCount sd = new ShowCount();
            String x;
            Scanner s = new Scanner(System.in);
            System.out.println("");
            System.out.println("Witaj użytkowniku!");
            do
            {
                System.out.println("Wybierz odpowiednią komendę:");
                System.out.println("[1] - Wyświetl...");
                System.out.println("[2] - Zlicz...");
                System.out.println("[3] - Wyświetl obliczone...");
                System.out.println("[Q] - Zakończ program.");
                x = s.next();
                x = x.toUpperCase();
                switch(x)
                {
                    case "1":
                        Show.showMain();
                        break;
                    case "2":
                        write.showMain();
                        break;
                    case "3":
                        sd.showMain();
                        break;
                    case "Q":
                        System.out.println("---Zamykanie programu---");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("nieprzewidziana sytuacja");
                        break;
                }
            } while (!x.equals("Q"));
            s.close();
        }
    }
}
