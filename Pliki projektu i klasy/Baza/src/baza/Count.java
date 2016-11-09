package baza;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

class Count
{
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DBURL = "jdbc:sqlite:test1.db";
    private static final String DBURL1 = "jdbc:sqlite:test.db";
    private static Connection conn;
    private static Statement stmt;

    int isState(String state) //Metoda, która sprawdza czy argument jest prawidłową nazwą województwa
    {
        String tab[] = new String[16];
        int flag=0;
        tab[0]="POMORSKIE"; tab[1]="ZACHODNIOPOMORSKIE"; tab[2]="KUJAWSKO-POMORSKIE"; tab[3]="ŚLĄSKIE"; tab[4]="DOLNOŚLĄSKIE";
        tab[5]="OPOLSKIE"; tab[6]="WARMIŃSKO-MAZURSKIE"; tab[7]="PODLASKIE"; tab[8]="MAZOWIECKIE"; tab[9]="WIELKOPOLSKIE";
        tab[10]="LUBUSKIE"; tab[11]="ŁÓDZKIE"; tab[12]="LUBELSKIE"; tab[13]="PODKARPACKIE"; tab[14]="ŚWIĘTOKRZYSKIE"; tab[15]="MAŁOPOLSKIE";
        for (int i=0;i<=15;i++)
        {
            if(tab[i].equals(state))
            {
                flag++;
            }
        }
        if(flag==0)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

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

    private void createTable(String woj, int ile) //Metoda, tworzy tabele w pliku test.db z podanymi argumentami
{
    String name;
    String tmp;
    tmp = String.valueOf(ile);
    Scanner s = new Scanner(System.in);
    System.out.println("Podaj nazwę tabeli:");
    name = s.next();
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

    String createKomputer=("CREATE TABLE IF NOT EXISTS "+name);
    createKomputer = (createKomputer + "(id INTEGER PRIMARY KEY AUTOINCREMENT, Nazwa VARCHAR(20), Ilość VARCHAR(50))");
    try
    {
        stmt.execute(createKomputer);

    } catch (SQLException e)
    {
        System.err.println("Blad przy tworzeniu tabeli");
        e.printStackTrace();
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
    } catch (SQLException e)
    {
        System.err.println("Problem z otwarciem polaczenia");
        e.printStackTrace();
    }

    try
    {
        PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO "+name+" VALUES (NULL, ?, ?);)");
        prepStmt.setString(1, woj);
        prepStmt.setString(2, tmp);
        prepStmt.execute();
        System.out.println("Dodano rekord");
    }
    catch (SQLException e)
    {
        System.err.println("Blad przy dodawaniu rekordu");
        e.printStackTrace();
    }
}

    private void createTable2(String woj, String woj2, String year, int ile) //Metoda, tworzy tabele w pliku test.db z podanymi argumentami
    {
        String name;
        String tmp;
        tmp = String.valueOf(ile);
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj nazwę tabeli:");
        name = s.next();
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

        String createKomputer=("CREATE TABLE IF NOT EXISTS "+name);
        createKomputer = (createKomputer + "(id INTEGER PRIMARY KEY AUTOINCREMENT, Woj VARCHAR(20), Woj2 VARCHAR(20), Rok VARCHAR(20), Ilość VARCHAR(50))");
        try
        {
            stmt.execute(createKomputer);

        } catch (SQLException e)
        {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
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
        } catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        try
        {
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO "+name+" VALUES (NULL, ?, ?, ?, ?);)");
            prepStmt.setString(1, woj);
            prepStmt.setString(2, woj2);
            prepStmt.setString(3, year);
            prepStmt.setString(4, tmp);
            prepStmt.execute();
            System.out.println("Dodano rekord");
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy dodawaniu rekordu");
            e.printStackTrace();
        }
    }

    private void createTable3(String st, String en, int ile) //Metoda, tworzy tabele w pliku test.db z podanymi argumentami
    {
        String name;
        String tmp;
        tmp = String.valueOf(ile);
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj nazwę tabeli:");
        name = s.next();
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

        String createKomputer=("CREATE TABLE IF NOT EXISTS "+name);
        createKomputer = (createKomputer + "(id INTEGER PRIMARY KEY AUTOINCREMENT, Rok VARCHAR(20), Rokk VARCHAR(20), Ilość VARCHAR(50))");
        try
        {
            stmt.execute(createKomputer);

        } catch (SQLException e)
        {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
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
        } catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        try
        {
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO "+name+" VALUES (NULL, ?, ?, ?);)");
            prepStmt.setString(1, st);
            prepStmt.setString(2, en);
            prepStmt.setString(3, tmp);
            prepStmt.execute();
            System.out.println("Dodano rekord");
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy dodawaniu rekordu");
            e.printStackTrace();
        }
    }


    void showMain() throws IOException, InterruptedException //Metoda, która wyświetla głowne Menu Zliczania
    {
        Menu m = new Menu();
        String x;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[1] - Policz sumę komputerów w podanych latach.");
            System.out.println("[2] - Wyznacz województwo z największą ilością komputerów w danym roku.");
            System.out.println("[3] - Policz procent komputerów z dostępem do sieci w danym roku.");
            System.out.println("[4] - Policz rożnicę między dwoma województwami w ilości komputerów w podanym roku.");
            System.out.println("[5] - Wyznacz przyrost komputerów w podanych latach.");
            System.out.println("[B] - Powrót.");
            System.out.println("[Q] - Zakończ program.");
            x = s.next();
            x = x.toUpperCase();
            switch(x)
            {
                case "1":
                    writeAll();
                    break;
                case "2":
                    writeMax();
                    break;
                case "3":
                    writePercent();
                    break;
                case "4":
                    writeDifference();
                    break;
                case "5":
                    writeGain();
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

    private void writeAll() throws IOException, InterruptedException //Metoda, która sumuje liczbę komputerów w wybranych latach
    {
        String st, en, woj;
        int start,end,suma=0;
        Menu m = new Menu();
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
        if(isState(woj)==0)
        {
            woj="POLSKA";
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
                ResultSet result = stmt.executeQuery("SELECT Ilość FROM komputery WHERE Typ_PC='komputery w szkole' AND Rok BETWEEN "+start+" AND "+end);
                int ilosc;
                while(result.next())
                {
                    ilosc = result.getInt("Ilość");
                    suma+=ilosc;
                }
                System.out.println("");
                System.out.println("Ilość komputerów w latach "+start+"-"+end+" wynosi "+suma);
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
                ResultSet result = stmt.executeQuery("SELECT Ilość FROM komputery WHERE Typ_PC='komputery w szkole' AND Rok BETWEEN "+start+" AND "+end+" AND nazwa='"+woj+"'");
                int ilosc;
                while(result.next())
                {
                    ilosc = result.getInt("Ilość");
                    suma+=ilosc;
                }
            }
            catch (SQLException e)
            {
                System.err.println("Blad przy wykonywaniu SELECT");
                e.printStackTrace();
            }

            System.out.println("");
            System.out.println("Ilość komputerów w latach "+start+"-"+end+" w województwie "+woj+" wynosi "+suma);
        }

        String choose;
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[Y] - Aby zapisać te dane do tabeli.");
            System.out.println("[B] - Powróć do poprzedniego menu.");
            System.out.println("[M] - Powróć do menu głównego.");
            System.out.println("[Q] - Zakończ program.");
            choose = s.next();
            choose = choose.toUpperCase();
            switch(choose)
            {
                case "Y":
                    createTable(woj,suma);
                    break;
                case "B":
                    showMain();
                    break;
                case "M":
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
        } while (!choose.equals("Q"));
        s.close();
    }

    private void writeMax() throws IOException, InterruptedException //Metoda, która wyznacza województwo z najwiekszą ilością komputerów w danym roku
    {
        String year;
        int max=0;
        String stateMax="0";
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("Podaj rok:");
            year = s.next();
        } while(isInteger(year)==0);

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
        }
        catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        try
        {
            ResultSet result = stmt.executeQuery("SELECT Nazwa,Ilość FROM komputery WHERE Typ_PC='komputery w szkole' AND Rok="+year);
            int ilosc;
            String name;
            while(result.next())
            {
                name = result.getString("Nazwa");
                ilosc = result.getInt("Ilość");
                if (ilosc>max)
                {
                    max = ilosc;
                    stateMax = name;
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("Największa ilość komputerów wynosi "+max+" i jest w województwie "+stateMax);

        Menu m = new Menu();
        String choose;
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[Y] - Aby zapisać te dane do tabeli.");
            System.out.println("[B] - Powróć do poprzedniego menu.");
            System.out.println("[M] - Powróć do menu głównego.");
            System.out.println("[Q] - Zakończ program.");
            choose = s.next();
            choose = choose.toUpperCase();
            switch(choose)
            {
                case "Y":
                    createTable(stateMax,max);
                    break;
                case "B":
                    showMain();
                    break;
                case "M":
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
        } while (!choose.equals("Q"));
        s.close();
    }

    private void writePercent() throws IOException, InterruptedException //Metoda, która oblicza procent komputerów z Internetem w danym roku
    {
        float suma=0,sumaWeb=0;
        float percent;
        String year;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("Podaj rok:");
            year = s.next();
        } while(isInteger(year)==0);

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
        }
        catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        try
        {
            ResultSet result = stmt.executeQuery("SELECT Ilość,Typ_PC FROM komputery WHERE Rok="+year);
            int ilosc;
            String typ;
            while(result.next())
            {
                typ = result.getString("Typ_PC");
                ilosc = result.getInt("Ilość");
                if (typ.equals("komputery w szkole"))
                {
                    suma+=ilosc;
                }
                else
                {
                    sumaWeb+=ilosc;
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }

        percent = (sumaWeb/suma)*100;
        System.out.println("");
        System.out.println("Procent komputerów z dostępem do sieci w roku "+year+" wynosi "+percent+"%");

        int tmp = (int)(percent);
        Menu m = new Menu();
        String choose;
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[Y] - Aby zapisać te dane do tabeli.");
            System.out.println("[B] - Powróć do poprzedniego menu.");
            System.out.println("[M] - Powróć do menu głównego.");
            System.out.println("[Q] - Zakończ program.");
            choose = s.next();
            choose = choose.toUpperCase();
            switch(choose)
            {
                case "Y":
                    createTable(year,tmp);
                    break;
                case "B":
                    showMain();
                    break;
                case "M":
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
        } while (!choose.equals("Q"));
        s.close();
    }

    private void writeDifference() throws IOException, InterruptedException //Metoda, która pokazuje różnicę w ilości komputerów między dwoma województwami
    {
        String woj, woj2, year;
        int ilosc1=0,ilosc2=0,ilosc,roznica;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("Podaj województwo:");
            woj = s.nextLine();
            woj = woj.toUpperCase();
            System.out.println("Podaj drugie województwo:");
            woj2 = s.nextLine();
            woj2 = woj2.toUpperCase();
        } while ((isState(woj)==0) || (isState(woj2)==0));

        do
        {
            System.out.println("Podaj rok:");
            year = s.next();
        } while(isInteger(year)==0);

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
            ResultSet result = stmt.executeQuery("SELECT Ilość, Nazwa FROM komputery WHERE Typ_PC='komputery w szkole' AND (Nazwa='"+woj+"' OR Nazwa='"+woj2+"') AND Rok='"+year+"'");
            String nazwa;
            while(result.next())
            {
                nazwa = result.getString("Nazwa");
                ilosc = result.getInt("Ilość");
                if(nazwa.equals(woj))
                {
                    ilosc1+=ilosc;
                }
                else
                {
                    ilosc2+=ilosc;
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }

        System.out.println("");
        if(ilosc1>ilosc2)
        {
            roznica=ilosc1-ilosc2;
            System.out.println("W roku "+year+" w województwie "+woj+" było o "+roznica+" więcej komputerów niż w województwie "+woj2);
        }
        else
        {
            roznica=ilosc2-ilosc1;
            System.out.println("W roku "+year+" w województwie "+woj+" było o "+roznica+" mniej komputerów niż w województwie "+woj2);
            roznica*=(-1);
        }

        Menu m = new Menu();
        String choose;
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[Y] - Aby zapisać te dane do tabeli.");
            System.out.println("[B] - Powróć do poprzedniego menu.");
            System.out.println("[M] - Powróć do menu głównego.");
            System.out.println("[Q] - Zakończ program.");
            choose = s.next();
            choose = choose.toUpperCase();
            switch(choose)
            {
                case "Y":
                    createTable2(woj,woj2,year,roznica);
                    break;
                case "B":
                    showMain();
                    break;
                case "M":
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
        } while (!choose.equals("Q"));
        s.close();
    }

    private void writeGain() throws IOException, InterruptedException //Metoda, która oblicza przyrost komputerów między latami
    {
        String st,en;
        int suma1=0,suma2=0,ilosc;
        Menu m = new Menu();
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("Podaj rok rozpoczęcia:");
            st = s.next();
            System.out.println("Podaj rok zakończenia:");
            en = s.next();
        } while(((isInteger(st)==0) || (isInteger(en)==0)) || ((Integer.parseInt(en))-(Integer.parseInt(st))>1));

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
            ResultSet result = stmt.executeQuery("SELECT Ilość, Rok FROM komputery WHERE Typ_PC='komputery w szkole' AND Rok BETWEEN "+st+" AND "+en);
            String rok;
            while(result.next())
            {
                ilosc = result.getInt("Ilość");
                rok = result.getString("Rok");
                if(rok.equals(st))
                {
                    suma1+=ilosc;
                }
                else
                {
                    suma2+=ilosc;
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("Z roku "+st+" na rok "+en+" przybyło "+(suma2-suma1)+" komputerów.");

        String choose;
        do
        {
            System.out.println("");
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("[Y] - Aby zapisać te dane do tabeli.");
            System.out.println("[B] - Powróć do poprzedniego menu.");
            System.out.println("[M] - Powróć do menu głównego.");
            System.out.println("[Q] - Zakończ program.");
            choose = s.next();
            choose = choose.toUpperCase();
            switch(choose)
            {
                case "Y":
                    createTable3(st,en,(suma2-suma1));
                    break;
                case "B":
                    showMain();
                    break;
                case "M":
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
        } while (!choose.equals("Q"));
        s.close();
    }
}
