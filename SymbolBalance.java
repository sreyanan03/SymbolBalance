import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 
import java.io.*;

public class SymbolBalance implements SymbolBalanceInterface{
    File myFile;
    String sect = "";
    Scanner myReader;
    MyStack<Character> stack = new MyStack<>();
    int errorLine = 0;

    public void setFile(String name) {
        myFile = new File(name);
        try {
            myReader = new Scanner(myFile);
        }
        catch (FileNotFoundException e) {
            System.out.println("Understood error.");
        }
    }

    public BalanceError checkFile() {
        String line;
        boolean inComment = false;
        boolean inQuotes = false;
        while (myReader.hasNextLine()) {
            
            line = myReader.nextLine(); // gives you each line in the file
            errorLine++;
            sect = line;

            int i = 0; // counter for while loop
            while(i< sect.length())  { //goes through each char of each line
                char c = sect.charAt(i);
                if (i < sect.length() - 1) {
                    if (!inQuotes && inComment){
                        if (sect.substring(i, i+2).equals("*/")) {
                            inComment = false;
                            if (stack.isEmpty()) {
                                EmptyStackError ES = new EmptyStackError(errorLine);
                                return ES;
                            }
                            char x = stack.pop();
                            if (x != '*') {
                                MismatchError ME = new MismatchError(errorLine, '*', x);
                                return ME;
                            }
                        }
                    }
                    if (!inQuotes && !inComment){
                        if (sect.substring(i, i+2).equals("/*")) {
                            inComment = true;
                            stack.push('*');
                        }
                    }
                }
                if (!inComment) {
                    if (sect.substring(i, i+1).equals("\"")) {
                        if (!stack.isEmpty() && stack.peek()=='"'){
                            stack.pop();
                            inQuotes=false;
                        }
                        else {
                            inQuotes = true;
                            stack.push('"');
                        }
                    }
                }

                if (!inComment && !inQuotes){
                    if((c == '{') || (c == '(') || (c =='[')) { // looking for these opening chars
                        stack.push(c);
                    }

                    else if ((c == '}' || c == ')' || c == ']') && stack.isEmpty()) {
                        EmptyStackError ES = new EmptyStackError(errorLine);
                        return ES;
                    }

                    else if((c == '}' || c == ')' || c == ']') && !stack.isEmpty()) {
                        char h = stack.pop();
                        //System.out.println(errorLine);

                        if (c == ')' && (h!= '('))
                        {
                            //System.out.println(errorLine);
                            MismatchError ME = new MismatchError(errorLine, c, h);
                            return ME;
                        }
                        else if (c == ']' && (h!= '['))
                        {
                            //System.out.println(errorLine);
                            MismatchError ME = new MismatchError(errorLine, c, h);
                            return ME;
                        }
                        else if (c == '}' && (h!= '{'))
                        {
                            System.out.println(line);
                            System.out.println(inComment);
                            System.out.println(i);
                            MismatchError ME = new MismatchError(errorLine, c, h);
                            return ME;
                        }
                    }
                }
                i++;
            }  
        }
            //End of the file (everything in lines string is read!)
            if(stack.isEmpty()) {
                return null;
                }
            else {
                NonEmptyStackError NE = new NonEmptyStackError(stack.peek(), stack.size()); // check this one
                return NE;
                } 
    } 
}

