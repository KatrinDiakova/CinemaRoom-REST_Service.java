import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] str1 = scanner.nextLine().toLowerCase().split("");
        String[] str2 =  scanner.nextLine().toLowerCase().split("");
        Map<String, Integer> anagramWords = new HashMap<>();
        for (String word : str1) {
            anagramWords.put(word, anagramWords.getOrDefault(word, 0) + 1);
        }
        if (str1.length == str2.length) {
            isAnagram(anagramWords, str2);
        } else {
            System.out.println("no");
        }
    }

    private static void isAnagram(Map<String, Integer> anagramWords, String[] listWords) {
        boolean isAnagram = true;
        for (String word : listWords) {
            if (!anagramWords.containsKey(word) || anagramWords.get(word) == 0) {
                isAnagram = false;
                break;
            } else {
                anagramWords.put(word, anagramWords.get(word) - 1);
            }
        }
        System.out.println(isAnagram ? "yes" : "no");
    }
}