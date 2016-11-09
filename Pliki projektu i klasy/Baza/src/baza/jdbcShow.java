package baza;

import java.sql.*;

class jdbcShow {

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DBURL = "jdbc:sqlite:test1.db";

    private static Connection conn;
    private static Statement stmt;

    void createTable() //Metoda tworzy tabelę "komputery" w pliku test1.db
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

        String createKomputer="CREATE TABLE IF NOT EXISTS komputery";
        createKomputer = (createKomputer + "(id INTEGER PRIMARY KEY AUTOINCREMENT, Nazwa VARCHAR(20), Typ_PC VARCHAR (50), Typ_Szkoły VARCHAR (50), Rok VARCHAR(4), Ilość VARCHAR(10))");
        try
        {
            stmt.execute(createKomputer);

        } catch (SQLException e)
        {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        }

    }

    void insertData(String wiersz, int j) //Metoda, która zapisuje podane argumenty do bazy
    {
        String[] tab = new String[400];
        int n = 0;
        for(int i=0; i<wiersz.length();i++)
        {
            if(wiersz.charAt(i)!=';')
            {
                tab[n] = (tab[n]+wiersz.charAt(i));
            }
            else
            {
                n++;
            }
        }
        for(int i=1;i<6;i++)
        {
            tab[i] = tab[i].substring(4);
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
            conn = DriverManager.getConnection(DBURL);
            stmt = conn.createStatement();
        } catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        try
        {
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO komputery VALUES (NULL, ?, ?, ?, ?, ?);)");
            prepStmt.setString(1, tab[1]);
            prepStmt.setString(2, tab[2]);
            prepStmt.setString(3, tab[3]);
            prepStmt.setString(4, tab[4]);
            prepStmt.setString(5, tab[5]);
            prepStmt.execute();
            System.out.println("Dodano "+j+" rekord");
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy dodawaniu rekordu");
            e.printStackTrace();
        }
    }

    static int CheckIfBaseEmpty() //Metoda, która sprawdza czy tabela "komputery" jest pusta
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
            if(!result.next())
            {
                return 1;
            }
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy wykonywaniu SELECT");
            e.printStackTrace();
        }
        return 0;
    }
}
