class Word:
    def __init__(self, word):
        self._word = word
        self._vowels = "aeiou"

    def count_vowels(self):
        count = 0
        for letter in self._word.lower():
            if letter in self._vowels:
                count += 1
        return count

    def count_consonants(self):
        return len(self._word) - self.count_vowels()

    def __str__(self):
        return self._word
    
if __name__ == "__main__":
    word = Word("string")
    print(word)
    print(word.count_vowels())
    print(word.count_consonants())