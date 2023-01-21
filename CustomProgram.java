import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class CustomProgram {
    public static void main(String[] args) throws Exception {
        // reads the provided customPage.html into list
        Scanner in = new Scanner(new File("customPage.html"));
        ArrayList<String> list = new ArrayList<>();
        while (in.hasNextLine()) list.add(in.nextLine());


        String savedName = "";
        if (args.length > 0) for (String arg : args[0].split("&")) {
            String[] keyValuePair = arg.split("=");
            switch (keyValuePair[0]) {
                case "name":
                    if (keyValuePair.length > 1) {
                        savedName = keyValuePair[1];
                    }
                    break;

                case "background":
                    if (keyValuePair[1] != (null) && keyValuePair[1].equals("Dark")) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).contains("background-color: white")) {
                                list.set(i, "background-color: black;");
                                list.set(i + 1, "color: white;");

                            }
                        }
                    }
                    break;

                case "Greeting":
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).contains("<body>")) {
                            if (savedName.equals("")) {
                                String s = "<h1>Welcome Stranger</h1>";
                                list.add(i + 1, s);
                                ;
                            } else {
                                String s = "<h1>Welcome " + savedName + "</h1>";
                                list.add(i + 1, s);
                            }

                        }
                    }
                    break;

                case "SuppressOptions":
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).contains("<h1>Page Customization Options:</h1>")) {
                            for (int j = i; j < list.size(); j++) {
                                if (list.get(j).contains("WARNING: Errors found in form!</p>")) {
                                    break;
                                }
                                list.set(j, "");
                            }

                        }
                    }
                    break;
            }
        }

        // print the resulting html out to system.out (standard out)
        for (String line : list)
            System.out.println(line);
    }
}