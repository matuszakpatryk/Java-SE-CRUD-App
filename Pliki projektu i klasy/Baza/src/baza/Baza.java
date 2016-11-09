//Patryk Matuszak - 19.10.2016, 30.10.2016, 04.11.2016
//Program wpisuje do bazy danych informacje o ilości komputerów w szkołach ponadgimnazjalnych w latach 2002-2012
//w podziale na województwa. Program zlicza i wyznacza rzeczy z bazy danych.
//Plik pobrałem z: https://bdl.stat.gov.pl/BDL/dane/podgrup/tablica
//Aby uruchomić program, trzeba dodać bilbiotekę sqlite-jdbc-3.8.11.2
//oraz umieścić w folderze plik dane1.txt

package baza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Baza //Główna klasa całego projektu.
{
   private static void writeData (String fileName) throws IOException //Metoda, która zapisuje rekordy z pliku do bazy
   {

       jdbcShow data = new jdbcShow();
       BufferedReader plik = null;
       try
       {
           plik = new BufferedReader(new FileReader(fileName));
           int i = 1;
           String[] wiersz = new String[2500];
           wiersz[i] = plik.readLine();
           while (wiersz[i] != null)
           {
               data.insertData(wiersz[i],i);
               i++;
               wiersz[i] = plik.readLine();
           }
       }
       finally
       {
           if (plik != null)
           {
               plik.close();
           }
       }
   }


    public static void main(String[] args) throws IOException, InterruptedException
    {
        Menu m = new Menu();
        String fileName = "dane1.txt";
        jdbcShow data = new jdbcShow();
        data.createTable();
        if(jdbcShow.CheckIfBaseEmpty()==1)
        {
            System.out.println("Pusta baza");
            System.out.println("---Dodawanie rekordów!");
            writeData(fileName);
        }
        m.showMenu();
    }
}