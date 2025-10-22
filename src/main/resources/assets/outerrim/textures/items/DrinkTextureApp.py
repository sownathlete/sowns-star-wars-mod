# Step 1: Creating the Cat class
class Cat:
    def __init__(self, name, gender, breed, age):
        """Initialize the attributes of the cat."""
        self.name = name
        self.gender = gender
        self.breed = breed
        self.age = age
        self.hunger = 50
        self.energy = 50
    
    def show(self):
        """Display information about the cat."""
        print(f'Cat: {self.name} is a {self.gender} {self.breed}. {self.name} is {self.age} year(s) old.')
        print(f'Energy level: {self.energy}. Hunger level: {self.hunger}.')
    
    def eat(self):
        """Simulate the cat eating to increase energy and decrease hunger."""
        self.energy += 10
        self.hunger -= 5
        print(f'{self.name} just ate.')
    
    def meow(self):
        """Simulate the cat meowing, which uses up some energy."""
        self.energy -= 2
        print(f'{self.name} says: Meow!')
    
    def play(self, minutes):
        """Simulate the cat playing, which decreases energy and increases hunger."""
        self.energy -= (minutes * 2)
        self.hunger += minutes
        print(f'{self.name} just played for {minutes} minutes.')
    
    def nap(self, minutes):
        """Simulate the cat napping, which increases energy and hunger."""
        self.energy += (minutes * 2)
        self.hunger += minutes
        print(f'{self.name} just took a {minutes}-minute nap.')

# Step 3: Adding code to the main program
def main():
    # Create a Cat object representing Garfield
    garfield = Cat(name="Garfield", gender="male", breed="Tabby", age=5)
    
    while True:
        action = input('\nPress I for info, E to eat, M to meow, N to nap, P to play, or Q to quit: ')
        
        if len(action) > 1:
            action = action[0]  # Just use the first letter
        action = action.upper()  # Force uppercase
        
        if action == 'I':
            garfield.show()
        elif action == 'E':
            garfield.eat()
        elif action == 'M':
            garfield.meow()
        elif action == 'N':
            minutes = int(input('How many minutes of nap time? '))
            garfield.nap(minutes)
        elif action == 'P':
            minutes = int(input('How many minutes of play time? '))
            garfield.play(minutes)
        elif action == 'Q':
            break
        else:
            print("Invalid option, please try again.")
    
    print('Bye')

# Run the main function
if __name__ == "__main__":
    main()
