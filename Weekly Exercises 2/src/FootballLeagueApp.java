import java.util.*;

class Game {
    String homeTeam;
    String awayTeam;
    int homeScore;
    int awayScore;

    public Game(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}

class TeamStats {
    int wins = 0;
    int losses = 0;
    int draws = 0;

    public void addWin() { wins++; }
    public void addLoss() { losses++; }
    public void addDraw() { draws++; }

    public String toString() {
        return "Wins: " + wins + ", Losses: " + losses + ", Draws: " + draws;
    }
}

public class FootballLeagueApp {
    private static final List<Game> games = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Game");
            System.out.println("2. Team Performance");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addGame(scanner);
                case 2 -> showPerformance(scanner);
            }
        } while (choice != 0);
    }

    private static void addGame(Scanner scanner) {
        System.out.print("Home Team: ");
        String home = scanner.nextLine();
        System.out.print("Away Team: ");
        String away = scanner.nextLine();
        System.out.print("Home Score: ");
        int homeScore = Integer.parseInt(scanner.nextLine());
        System.out.print("Away Score: ");
        int awayScore = Integer.parseInt(scanner.nextLine());

        games.add(new Game(home, away, homeScore, awayScore));
        System.out.println("Game added.");
    }

    private static void showPerformance(Scanner scanner) {
        System.out.print("Enter team name: ");
        String team = scanner.nextLine();
        TeamStats stats = new TeamStats();

        for (Game g : games) {
            if (g.homeTeam.equalsIgnoreCase(team)) {
                if (g.homeScore > g.awayScore) stats.addWin();
                else if (g.homeScore < g.awayScore) stats.addLoss();
                else stats.addDraw();
            } else if (g.awayTeam.equalsIgnoreCase(team)) {
                if (g.awayScore > g.homeScore) stats.addWin();
                else if (g.awayScore < g.homeScore) stats.addLoss();
                else stats.addDraw();
            }
        }

        System.out.println("Performance of " + team + ": " + stats);
    }
}
