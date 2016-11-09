package baza;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


class Show
{
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DBURL = "jdbc:sqlite:test1.db";
    private static Connection conn;
    private static Statement stmt;

    private static int isInteger(String s) //Metoda sprawdza czy argument (String) można rzutować do typu int 2002>=x<=2012
    {
        int tmp;
        try
        {
            tmp = Integer.parseInt(s);
            if((tmp<2002) || (tmp>2012))
            {
                return 0;
            }
        }
        catch(NumberFormatException e)
        {
            return 0;
        }
        catch(NullPointerException e)
        {
            return 0;
        }
        return 1;
    }

    static void showMain() throws IOException, InterruptedException //Metoda, która wyświetla głowne Menu Wyświetlania
    {
        Menu m = new Menu();
        String x;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[1] - Wyświetl całą bazę danych.");
            System.out.println("[2] - Wyświetl dane dla podanych lat.");
            System.out.println("[3] - Wyświetl dane dla podanych województw.");
            System.out.println("[B] - Powrót.");
            System.out.println("[Q] - Zakończ program.");
            x = s.next();
            x = x.toUpperCase();
            switch(x)
            {
                case "1":
                    showAll();
                    break;
                case "2":
                    showYears();
                    break;
                case "3":
                    showState();
                    break;
                case "B":
                    m.showMenu();
                    break;
                case "Q":
                    System.out.println("---Zamykanie programu---");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieprzewidziana sytuacja!");
                    break;
            }
        } while (!x.equals("Q"));
        s.close();
    }

    private static void showAll() throws IOException, InterruptedException //Metoda, która wyświetla wszystkie rekordy bazy
    {
        Menu m = new Menu();
        try
        {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try
        {
            conn = DriverManager.getConnection(DBURL);
            stmt = conn.createStatement();
        } catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        try
        {
            ResultSet result = stmt.executeQuery("SELECT * FROM komputery");
            int id,ilosc,rok;
            String nazwa,Typ_PC,Typ_Szkoły;
            while(result.next())
            {
                id = result.getInt("id");
                nazwa = result.getString("nazwa");
                Typ_PC = result.getString("Typ_PC");
                Typ_Szkoły = result.getString("Typ_Szkoły");
                rok = result.getInt("Rok");
                ilosc = result.getInt("Ilość");
                System.out.println("id="+id+" nazwa="+nazwa+" Typ_PC="+Typ_PC+" Typ_Szkoły="+Typ_Szkoły+" Rok="+rok+" Ilość="+ilosc);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }
        m.chooseShow();
    }

    private static void showYears() throws IOException, InterruptedException //Metoda, która wyświetla dane dla podanych lat
    {
        Menu m = new Menu();
        String st, en, woj;
        int start,end;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("Podaj rok rozpoczęcia:");
            st = s.next();
            System.out.println("Podaj rok zakończenia:");
            en = s.next();
        } while((isInteger(st)==0) || (isInteger(en)==0));
        System.out.println("Podaj województwo (opcjonalnie):");
        woj = s.next();
        start = Integer.parseInt(st);
        end = Integer.parseInt(en);
        woj = woj.toUpperCase();
        Count c = new Count();
        if(c.isState(woj)==0)
        {
            try
            {
                Class.forName(DRIVER);
            }
            catch (ClassNotFoundException e)
            {
                System.err.println("Brak sterownika JDBC");
                e.printStackTrace();
            }
            try
            {
                conn = DriverManager.getConnection(DBURL);
                stmt = conn.createStatement();
            } catch (SQLException e)
            {
                System.err.println("Problem z otwarciem polaczenia");
                e.printStackTrace();
            }
            try
            {
                ResultSet result = stmt.executeQuery("SELECT * FROM komputery");
                int id,ilosc,rok;
                String nazwa,Typ_PC,Typ_Szkoły;
                while(result.next())
                {
                    id = result.getInt("id");
                    nazwa = result.getString("nazwa");
                    Typ_PC = result.getString("Typ_PC");
                    Typ_Szkoły = result.getString("Typ_Szkoły");
                    rok = result.getInt("Rok");
                    ilosc = result.getInt("Ilość");
                    if((rok>=start) && (rok<=end))
                    {
                        System.out.print("Id="+id+"\tWojewództwo="+nazwa+"\tTyp_PC="+Typ_PC+"\tTyp_Szkoły="+Typ_Szkoły);
                        System.out.println("\tRok="+rok+"\tIlość="+ilosc);
                    }
                }
            }
            catch (SQLException e)
            {
                System.err.println("Blad przy wykonywaniu SELECT");
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                Class.forName(DRIVER);
            }
            catch (ClassNotFoundException e)
            {
                System.err.println("Brak sterownika JDBC");
                e.printStackTrace();
            }
            try
            {
                conn = DriverManager.getConnection(DBURL);
                stmt = conn.createStatement();
            } catch (SQLException e)
            {
                System.err.println("Problem z otwarciem polaczenia");
                e.printStackTrace();
            }
            try
            {
                ResultSet result = stmt.executeQuery("SELECT * FROM komputery");
                int id,ilosc,rok;
                String nazwa,Typ_PC,Typ_Szkoły;
                while(result.next())
                {
                    id = result.getInt("id");
                    nazwa = result.getString("nazwa");
                    Typ_PC = result.getString("Typ_PC");
                    Typ_Szkoły = result.getString("Typ_Szkoły");
                    rok = result.getInt("Rok");
                    ilosc = result.getInt("Ilość");
                    if(((rok>=start) && (rok<=end)) && (nazwa.equals(woj)))
                    {
                        System.out.print("Id="+id+"\tWojewództwo="+nazwa+"\tTyp_PC="+Typ_PC+"\tTyp_Szkoły="+Typ_Szkoły);
                        System.out.println("\tRok="+rok+"\tIlość="+ilosc);
                    }
                }
            }
            catch (SQLException e)
            {
                System.err.println("Blad przy wykonywaniu SELECT");
                e.printStackTrace();
            }
        }
        m.chooseShow();
    }

    private static void showState() throws IOException, InterruptedException //Metoda, która wyświetla dane dla podanego województwa
    {
        Menu m = new Menu();
        int licznik=0;
        String woj;
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj województwo:");
        do
        {
            woj = s.nextLine();
            woj = woj.toUpperCase();
            try
            {
                Class.forName(DRIVER);
            }
            catch (ClassNotFoundException e)
            {
                System.err.println("Brak sterownika JDBC");
                e.printStackTrace();
            }
            try
            {
                conn = DriverManager.getConnection(DBURL);
                stmt = conn.createStatement();
            } catch (SQLException e)
            {
                System.err.println("Problem z otwarciem polaczenia");
                e.printStackTrace();
            }
            try
            {
                ResultSet result = stmt.executeQuery("SELECT * FROM komputery");
                int id,ilosc,rok;
                String nazwa,Typ_PC,Typ_Szkoły;
                while(result.next())
                {
                    id = result.getInt("id");
                    nazwa = result.getString("nazwa");
                    Typ_PC = result.getString("Typ_PC");
                    Typ_Szkoły = result.getString("Typ_Szkoły");
                    rok = result.getInt("Rok");
                    ilosc = result.getInt("Ilość");
                    if(nazwa.equals(woj))
                    {
                        System.out.print("Id="+id+"\tWojewództwo="+nazwa+"\tTyp_PC="+Typ_PC+"\tTyp_Szkoły="+Typ_Szkoły);
                        System.out.println("\tRok="+rok+"\tIlość="+ilosc);
                        licznik++;
                    }
                }
            }
            catch (SQLException e)
            {
                System.err.println("Blad przy wykonywaniu SELECT");
                e.printStackTrace();
            }
            if(licznik==0)
            {
                System.out.println("Nie ma takiego województwa!");
                System.out.println("Podaj województwo ponownie:");
            }
        } while (licznik==0);
        m.chooseShow();
    }
}
