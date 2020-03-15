import java.util.*;

public class p2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int numberOfPlayers=scanner.nextInt();
        int numberOfTeams=scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> players= new ArrayList<>();
        HashMap<String,String> teamOfPlayer= new HashMap<>();
        Set<String> criminals= new TreeSet<>();
        for (int i = 0; i < numberOfPlayers ; i++) {
            String player = scanner.nextLine();
            if (players.contains(player)) continue;
            players.add(player);
        }
        for (int i = 0; i <numberOfTeams ; i++) {
            String team = scanner.nextLine();
            int teamPlayers = scanner.nextInt();
            scanner.nextLine();
            boolean isCriminal = false;
            for (int j = 0; j < teamPlayers; j++) {
                String player = scanner.nextLine();
                if (!players.contains(player))
                    isCriminal = true;
                if (teamOfPlayer.containsKey(player)) {
                    isCriminal = true;
                    String teamP = teamOfPlayer.get(player);
                    if (!criminals.contains(teamP))
                        criminals.add(teamP);
                } else
                    teamOfPlayer.put(player, team);
            }
            if (isCriminal)
                criminals.add(team);
        }
    //    Set<String> ans = new TreeSet<String>(criminals);
        for (String team:criminals)
            System.out.println(team);



    }
}
