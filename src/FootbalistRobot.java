import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.StrictMath.sqrt;

public class FootbalistRobot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data=scanner.nextLine();
        String pattern = "(@#(forward|keeper),x=\\d+,y=\\d+,distance=\\d+#@)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(data);
        int goals=0,previousDistance=0,previousEnd=0;
        boolean shoot = false ;
        while(matcher.find()){
            if(matcher.start() - previousEnd >=200)
                break;
            previousEnd=matcher.end();
            String foundData = matcher.group(1);
            if(foundData.contains("keeper"))
                continue;
            int x= findX(foundData),y=findY(foundData),distance=findDistance(foundData);
            if(distance<previousDistance)
                shoot=false;
            if(shoot==true && sqrt(x*x + y*y)-distance<=10)
            {
                goals++;
                shoot=false;
            }
            if(distance<10)
                shoot=true;
            previousDistance=distance;
         //   System.out.println("found: " + foundData +" " + shoot );
        }
        System.out.println(goals);
    }
    static int findX (String data)
    {
        int positionX = data.indexOf("x");
        int positionY = data.indexOf("y");
        String ans = data.substring(positionX+2,positionY-1);
        return Integer.valueOf(ans);
    }
    static int findY (String data)
    {
        int positionY = data.indexOf("y");
        int positionDi = data.indexOf("di");
        String ans = data.substring(positionY+2,positionDi-1);
        return Integer.valueOf(ans);
    }
    static int findDistance (String data)
    {
        int positionE = data.indexOf("e");
        int position1 = data.indexOf("#@");
        String ans = data.substring(positionE+2,position1);
        return Integer.valueOf(ans);
    }
}
