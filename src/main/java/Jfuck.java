import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jfuck {

    private void run(String ip) {
        //todo handle program_counter and size comparison
        final int size = 30_000;
        Program program = new Program(size);
       for(char programCounter:  ip.toCharArray()) {
           switch (programCounter) {
               case '+':
                   program.container[program.dataCounter]++;
                   break;
               case '-':
                   program.container[program.dataCounter]--;
                   break;
               case '.':
                    System.out.println((char) program.container[program.dataCounter]);
                    break;
               case ',':
//                   todo handle input later
                   Scanner sc =new Scanner(System.in);
                   char in =  sc.next().charAt(0);
                   program.container[program.dataCounter] = (int)in;
                   break;
               case '>':
                   if(program.dataCounter <  size) {
                       program.dataCounter++;
                   }
                   break;
               case '<':
                   if(program.dataCounter > 0) {
                       program.dataCounter--;
                   }
                   break;
               case '[':
                   break;
               case ']':
                   break;
               default:
                   /*
                    treated as comments
                    */
                   break;

           }

       }
    }

    private void repl() {
        //todo replace string with something better
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("bf> ");
                String ip;
                if ((ip = br.readLine()) != null) {
                    if (ip.isEmpty())
                        continue;
                    if (ip.equals("exit")) {
                        System.out.println("exit");
                        break;
                    }
                    Jfuck jfuck = new Jfuck();
                    jfuck.run(ip);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

void main(String[] args) {
    System.out.println("Hello World!");

    if (args.length == 0) {
        Jfuck jfuck = new Jfuck();
        jfuck.repl();
    } else if (args.length == 1) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Jfuck jfuck = new Jfuck();
            jfuck.run(sb.toString());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

class Program{
    int[] container;
    int dataCounter;

    Program(int size) {
        container = new int[size];
        this.dataCounter = 0;
    }
}