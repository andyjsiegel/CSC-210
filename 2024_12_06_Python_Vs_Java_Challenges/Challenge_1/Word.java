public class Word {
    
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public int countVowels() {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.toLowerCase().charAt(i);
            if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
                count++;
            }
        }
        return count;
    }

    public int countConsonants() {
        return word.length() - countVowels();
    }

    public String toString() {
        return word;
    }
    
    public static void main(String[] args) {
        Word word = new Word("string");
        System.out.println(word);
        System.out.println(word.countVowels());
        System.out.println(word.countConsonants());
    }
}
