package baza;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

class ShowCount
{
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DBURL1 = "jdbc:sqlite:test.db";

    private static Connection conn;
    private static Statement stmt;

    private int start() //Metoda, która wyświetla nazwy tabel z bazy test.db
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
            conn = DriverManager.getConnection(DBURL1);
            stmt = conn.createStatement();
        } catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        int licznik=0;
        try
        {
            ResultSet result = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type = \"table\"");
            String nazwa;
            while(result.next())
            {
                nazwa = result.getString("name");
                if(!nazwa.equals("sqlite_sequence"))
                {
                    System.out.println("Tabela "+nazwa);
                    licznik++;
                }

            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }
        return licznik;
    }

    private int openTable(String tabela) //Metoda, która wyświetla zawartość tabeli o nazwie pobieranej z argumentu
    {
        int numberOfColumns=0;
        int licznik=0;
        String name[] = new String[50];

        try
        {
            ResultSet result = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type = \"table\"");
            String nazwa;
            while(result.next())
            {
                nazwa = result.getString("name");
                if(!nazwa.equals("sqlite_sequence"))
                {
                    name[licznik] = nazwa;
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
            return 0;
        }
        for(int i=0; i<licznik;i++)
        {
            if(!name[i].equals(tabela))
            {
                return 0;
            }
        }

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
            conn = DriverManager.getConnection(DBURL1);
            stmt = conn.createStatement();
        }
        catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        try
        {
            ResultSet result = stmt.executeQuery("SELECT *, COUNT(*) FROM "+tabela);
            ResultSetMetaData rsmd = result.getMetaData();
            numberOfColumns = rsmd.getColumnCount();
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }

        try
        {
            ResultSet result = stmt.executeQuery("SELECT * FROM "+tabela);
            String tab[] = new String[numberOfColumns];
            while(result.next())
            {
                for(int i=1;i<numberOfColumns;i++)
                {
                    tab[i]= result.getString(i);
                }
                for(int i=1;i<numberOfColumns;i++)
                {
                    System.out.print(tab[i]+"\t");
                }
                System.out.println("");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }
        System.out.println("");
        return 1;
    }

    void showMain() throws IOException, InterruptedException //Metoda, która wyświetla główne Menu Pokazywania Przerobionych Danych
    {
        Menu m = new Menu();
        Scanner s = new Scanner(System.in);
        String x;
        int tmp;
        System.out.println("");
        System.out.println("Istniejące tabele:");
        tmp = start();
        if(tmp==0)
        {
            System.out.println("Nie ma żadnych zapisany tabel!");
            System.out.println("Zapisz jakieś dane,a nastepnie wróc, aby wyświetlić!");
            do
            {
                System.out.println("");
                System.out.println("Wybierz odpowiednią komendę:");
                System.out.println("[B] - Powrót.");
                System.out.println("[Q] - Zakończ program.");
                x = s.next();
                x = x.toUpperCase();
                switch(x)
                {
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
        }
        else
        {
            do
            {
                System.out.println("");
                System.out.println("Wpisz nazwę tabeli, którą chcesz wyświetlić:");
                x = s.next();
            } while(openTable(x)==0);
        }

        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[B] - Powrót.");
            System.out.println("[Q] - Zakończ program.");
            x = s.next();
            x = x.toUpperCase();
            switch(x)
            {
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

}
