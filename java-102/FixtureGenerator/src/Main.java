import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Takım sayısını giriniz: ");
        int teamCount = scanner.nextInt();
        ArrayList teams = new ArrayList();
        if((teamCount % 2) != 0) {
            teams.add(new Team("BAY"));

        }

        for(int i = 0; i < teamCount; i++) {
            System.out.println("Takım ismini giriniz: ");
            String teamName = scanner.next();
            teams.add(new Team(teamName));
        }
        FixtureGenerator fixtureGenerator = new FixtureGenerator();
        List<List<Fixture<Team>>> fixtures = fixtureGenerator.getFixtures(teams, true);
        int i =0 ;
        for(List<Fixture<Team>> fixtureList : fixtures) {
            System.out.println("Round " + ++i);
            for(Fixture<Team> fixture : fixtureList) {
                System.out.println(fixture.getHomeTeam().getTeamName() + " - " + fixture.getAwayTeam().getTeamName());
            }
            System.out.println();
        }
    }
}
