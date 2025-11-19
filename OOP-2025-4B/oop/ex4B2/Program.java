package oop.ex4B2;

import java.util.ArrayList;
import java.util.List;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class Program {
    List<String[]> inputs = new ArrayList<String[]>();
    int finish_line = -1;

    public Program(List<String[]> inputs) {
        this.inputs = inputs;
        this.finish_line = inputs.size() - 1; //最後の行に "EOF" があるとする
    }

    private int stringToInt(String s){
        try{
            return Integer.parseInt(s);
        } catch(NumberFormatException e){
            throw new IllegalArgumentException();
        }
    }

    public int execute() {
        int counter = 0;

        for(int i = 0; i < finish_line+1;i++){
            String[] line = this.inputs.get(i);
            int arg;

            // デバッグ用
            // System.out.println(i + ": " + line[0] + " ");
            switch (line[0]) {
                case "acc":
                    arg = stringToInt(line[1]);
                    counter += arg;
                    break;
                case "jmp":
                    arg = stringToInt(line[1]);
                    i = arg-1;
                    break;
                case "jnq":
                    arg = stringToInt(line[1]);
                    if(arg != i) i = arg-1;
                    break;
                case "inc":
                    counter++;
                    break;
                case "dec":
                    counter--;
                    break;
                case "EOF":
                    i = finish_line;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        // 出力
        return counter;
    }
}
