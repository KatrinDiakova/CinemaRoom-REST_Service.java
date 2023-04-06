import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> newspaperWords = new HashMap<>();

        for (String word : scanner.nextLine().split(" ")) {
            newspaperWords.put(word, newspaperWords.getOrDefault(word, 0) + 1);
        }
        String[] noteWords = scanner.nextLine().split(" ");

        boolean canWriteNote = true;
        for (String word : noteWords) {
            if (!newspaperWords.containsKey(word) || newspaperWords.get(word) == 0) {
                canWriteNote = false;
                break;
            } else {
                newspaperWords.put(word, newspaperWords.get(word) - 1);
            }
        }
        System.out.println(canWriteNote ? "You get money" : "You are busted");
    }
}