import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Collusion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder data = new StringBuilder();
        data.append(scanner.nextLine());
        String input;
        while (!(input=scanner.nextLine().trim()).equals("end"))
        {
            boolean isValid = false;
            String[] parts = input.split(" ");
            String command = parts[0];
            if (command.equals("mul")|| command.equals("add") || command.equals("sub")) {
                if(parts.length==1)
                {
                    isValid=true;
                    operate(data,command);
                }
            }
            if (command.equals("sum") || command.equals("gcd")){
                if(parts.length==3 && isNumeric(parts[1]) && (parts[2].equals("-b") || parts[2].equals("-f")))
                {
                    isValid=true;
                    sumOrGcd(data,command,Integer.valueOf(parts[1]),parts[2]);
                }
            }
            if (command.equals("replace")) {
                if(parts.length==4 && isNumeric(parts[3])){
                    isValid=true;
                    replace(data,parts[1],parts[2],Integer.parseInt(parts[3]));
                }
            }
            if(command.equals("count_entail"))
                if(parts.length==2){
                    isValid=true;
                    count(data,parts[1]);
                }
            if(command.equals("insert")){
                if(parts.length==2){
                    isValid=true;
                    data.append(parts[1]);
                    System.out.println(data);
                }
                if(parts.length==3 && isNumeric(parts[2])){
                    isValid=true;
                    int index=Integer.valueOf(parts[2]);
                    if(index > data.length())
                        System.out.println("CANNOT PERFORM THE COMMAND SUCCESSFULLY");
                    else{
                        data.insert(index,parts[1]);
                        System.out.println(data);
                    }
                }
            }
            if(command.equals("delete")){
                if(parts.length==2){
                    isValid=true;
                    delete(data,parts[1],false);
                }
                else if(parts.length==3 && parts[2].equals("-f")){
                    isValid=true;
                    delete(data,parts[1],true);
                }
            }
            if(command.equals("print") && parts.length==1){
                isValid=true;
                System.out.println(data);
            }
            if(!isValid)
                System.out.println("THE COMMAND IS INVALID");
        }
        System.out.println("END OF PROGRAM");
    }
    static boolean isNumeric(String s) {
        return s.matches("-?\\d+");
    }
    static int gcd(int a , int b)
    {
        a=a>0 ? a : -a;
        b=b>0 ? b : -b;
        while(a>0 && b>0){
            if(a>b)
                a%=b;
            else
                b%=a;
        }
        return a+b;
    }
    static void operate(StringBuilder data , String command)
    {
        String pattern="(-?\\d+)";
        Pattern p= Pattern.compile(pattern);
        Matcher matcher = p.matcher(data);
        int counter=0,a=0,b=0,indexA=0,indexB=0;
        while (matcher.find() && counter<2){
            counter++;
            String foundNum=matcher.group(1);
            if(counter==1){
                a=Integer.valueOf(foundNum);
                indexA=matcher.start();
            }
            else {
                b=Integer.valueOf(foundNum);
                indexB=matcher.end();
            }
        }
        if(counter<2){
            System.out.println("CANNOT PERFORM THE COMMAND SUCCESSFULLY");
            return;
        }
        if(command.equals("mul"))
             data.replace(indexA,indexB,String.valueOf(a*b));
        if(command.equals("add"))
            data.replace(indexA,indexB,String.valueOf(a+b));
        if(command.equals("sub"))
            data.replace(indexA,indexB,String.valueOf(a-b));
        System.out.println(data);
    }
    static void sumOrGcd(StringBuilder data,String command,int n,String direction){
        String pattern ="(-?\\d+)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(data);
        int counter=0;
        ArrayList<Integer> nums= new ArrayList<>();
        while (matcher.find()){
            String foundNum=matcher.group(1);
            nums.add(Integer.parseInt(foundNum));
            counter++;
        }
        if(counter<n){
            System.out.println("CANNOT PERFORM THE COMMAND SUCCESSFULLY");
            return;
        }
        int ans=0;
        if(command.equals("sum")){
            if(direction.equals("-b")){
                for(int i=0 ; i<n ; i++)
                    ans+=nums.get(i);
            }
            else
                for(int i=counter-1 ; i>=counter-n ; i--)
                    ans+=nums.get(i);
            data.append("S"+String.valueOf(ans)+"S");
        }
        else {
            if(direction.equals("-b")){
                for(int i=0 ; i<n ; i++)
                    ans= gcd(ans,nums.get(i));
            }
            else
                for(int i=counter-1 ; i>=counter-n  ; i--)
                    ans= gcd(ans,nums.get(i));

            data.append("G"+String.valueOf(ans)+"G");
        }
        System.out.println(data);
    }
    static void replace (StringBuilder data, String str1 , String str2 , int n){
        String pattern ="("+str1+")";
        Pattern p = Pattern.compile(pattern);
        int counter=0;
        while(counter<n){
            Matcher matcher=p.matcher(data);
            if(!matcher.find())
                break;
            int start=matcher.start(),end=matcher.end();
            data.replace(start,end,str2);
            counter++;
        }
        System.out.println(data);
    }
    static void count(StringBuilder data , String str){
        String pattern ="(?=("+str+")).";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(data);
        int counter=0;
        while (matcher.find())
            counter++;
        if(counter==0){
            System.out.println("CANNOT PERFORM THE COMMAND SUCCESSFULLY");
            return;
        }
        data.append("C"+String.valueOf(counter)+"C");
        System.out.println(data);
    }
    static void delete (StringBuilder data , String str , boolean direction){
        String pattern ="("+str+")";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(data);
        int counter=0,start=0,end=0;
        while (matcher.find()){
            counter++;
            if(!direction){
                if(counter==1){
                    start=matcher.start();
                    end=matcher.end();
                }
                continue;
            }
            start=matcher.start();
            end=matcher.end();
        }
        if(counter==0){
            System.out.println("CANNOT PERFORM THE COMMAND SUCCESSFULLY");
            return;
        }
        data.delete(start,end);
        System.out.println(data);
    }
}
