package stormPath;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.function.Supplier;

public class StormRecorder {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        /* load data */
        BufferedReader fileInput = new BufferedReader(new FileReader("src/stormPath/StormList.txt"));
        String line = fileInput.readLine();
        ArrayList citylist = new ArrayList();
        ArrayList stormlist = new ArrayList();
        while (line != null) {
            int istr = line.length();
            String str1 = line.replaceAll("\\|", "");
            int istr2 = str1.length();
            int index = istr - istr2; // the number of |
            if (citylist.size() == 0) {
                for (int i = 1; i < index; i++) {
                    City b = new City(line.split("\\|")[i]); //把每个string变成city
                    citylist.add(b);
                    String name = line.split("\\s")[0];
                    int year = Integer.valueOf(line.split("\\s")[1]);
                    Storm c = new Storm(name, year); //变成storm
                    b.addStorm(c); // citylist add cities in first line, cities add strom
                    if(!stormlist.contains(name)){
                        stormlist.add(name);
                    }
                }
            }
            // finish adding first line, read next line
            line = fileInput.readLine();
            String checkname = line.split("\\s")[0];
            for (int o =0;o<stormlist.size();o++){
                if (stormlist.get(o).equals(checkname)){
                    line = fileInput.readLine();
                    break;
                }
            }
            while (line != null) {
                String checkname1 = line.split("\\s")[0];
                for (int o =0;o<stormlist.size();o++){
                    if (stormlist.get(o).equals(checkname1)){
                        line = fileInput.readLine();
                        break;
                    }
                }
                int istr1 = line.length();
                String str2 = line.replaceAll("\\|", "");
                int istr3 = str2.length();
                int index1 = istr1 - istr3;
                int size = citylist.size();
                // get index and size for the next line
                //check whether in
                for (int j = 1; j < index1; j++) {
                    boolean whetherin = false;
                    for (int k = 0; k < size; k++) {
                        boolean checkin = false;
                        if (line.split("\\|")[j].equals(((City) citylist.get(k)).getName())) {
                            checkin = true;
                        }
                        // if in, citylist add city ,city add strom
                        if (checkin) {
                            String name1 = line.split("\\s")[0];
                            int year1 = Integer.valueOf(line.split("\\s")[1]);
                            Storm c1 = new Storm(name1, year1);
                            ((City) citylist.get(k)).addStorm(c1);
                            if(!stormlist.contains(name1)){
                                stormlist.add(name1);
                            };
                            break;
                        }
                    }
                    // if not in, only city add storm
                    City d = new City(line.split("\\|")[j]);  //trim
                    String name2 = line.split("\\s")[0];
                    int year2 = Integer.valueOf(line.split("\\s")[1]);
                    Storm c2 = new Storm(name2, year2);
                    if(!stormlist.contains(name2)){
                        stormlist.add(name2);
                    }
                    if (!d.wasHit(c2)) {
                        d.addStorm(c2);
                    }
                    for (int x = 0; x < citylist.size(); x++) {
                        if (((City) citylist.get(x)).getName().equals(d.getName())) {
                            whetherin = true;
                        }
                    }
                    if (!whetherin) {
                        citylist.add(d);
                    }
                }
                line = fileInput.readLine();
            }
        }
        System.out.println(citylist);
        BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the name of a city ");
        String input = kbd.readLine();
        if (input.equals("exit")) {
            System.exit(0);
        }
        while (!input.equals("exit")) {
            boolean whetherin3 = false;
            //check whether citylist contain the input
            for (int q = 0; q < citylist.size(); q++) {
                if (((City) citylist.get(q)).getName().trim().equals(input)) {
                    //get out the first blank and print
                    int length =  (citylist.get(q)).toString().length();
                    System.out.println((citylist.get(q)).toString().trim());
                    whetherin3 = true;
                    input = kbd.readLine();
                }
            }
            // exit when input is exit
            if (input.equals("exit")){
                System.exit(0);
            }
            // if input neither exit or in citylist
            if (!whetherin3) {
                System.out.println("This is not a valid city.");
                input = kbd.readLine();
            }
        }
    }
}
