import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder htmlCode= new StringBuilder();
        while(true){
            String inputLine=scanner.nextLine().trim();
            htmlCode.append(inputLine);
            if(inputLine.equals("</html>"))
                break;
        }
        Stack <String> stack = new Stack<>();
        Pattern pattern = Pattern.compile("</?(\\w+)\\s*(\\s[^/>]*)*>");
        Matcher matcher = pattern.matcher(htmlCode);
        while (matcher.find()){
            String tagName=matcher.group(1);
            String htmlTag=matcher.group(0);
            if(htmlTag.startsWith("</")){
                String stackTop = stack.pop();
                if(!stackTop.equals(tagName)){
         //           System.out.println(stackTop + " " + tagName);
                    System.out.println("NO");
                    return;
                }
            }else
                stack.push(tagName);
        }
        if(stack.size()==0)
            System.out.println("YES");
        else
            System.out.println("NO");


    }
}
