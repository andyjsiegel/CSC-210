class Numbers:
    def __init__(self, numbers: list[int] = None):
        if numbers is None:
            self._numbers = []
        else:
            self._numbers = numbers
        self._numbers.sort()

    def add_number(self, number: int):
        self._numbers.append(number)
        self._numbers.sort()

    def remove(self, number: int):
        self._numbers.remove(number)

    def get_max(self):
        return self._numbers[-1]

    def get_min(self):
        return self._numbers[0]

    def remove_duplicates(self):
        self._numbers = list(set(self._numbers))
        self._numbers.sort()

    def __str__(self):
        return " ".join(str(number) for number in self._numbers).strip()

if __name__ == "__main__":
    my_numbers = Numbers()
    my_numbers.add_number(10)
    my_numbers.add_number(1)
    my_numbers.add_number(5)
    my_numbers.add_number(10)
    my_numbers.add_number(25)
    my_numbers.add_number(10)
    my_numbers.add_number(-25)
    print(my_numbers) # -25 1 5 10 10 10 25
    print(my_numbers.get_max()) # 25
    print(my_numbers.get_min()) # -25
    my_numbers.remove_duplicates()
    print(my_numbers) # -25 1 5 10 25