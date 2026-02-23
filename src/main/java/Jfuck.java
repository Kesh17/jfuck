import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public static class Jfuck {

    private static void run(String ip) {
        System.out.println(ip);
    }

    private static void repl() {
        //todo replace string with something better
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("bf> ");
                String ip;
                if ((ip = br.readLine()) != null) {
                    if(ip.isEmpty())
                        continue;
                    if(ip.equals("exit")){
                        System.out.println("exit");
                        break;
                    }
                    run(ip);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

static void main(String[] args) {
    System.out.println("Hello World!");

    if (args.length == 0) {
        Jfuck.repl();
    } else if (args.length == 1) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Jfuck.run(sb.toString());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}