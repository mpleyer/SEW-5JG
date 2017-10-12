import random, threading, time

"""
console-based screen saver that simulates snowflakes
"""
class Schneeflocke(threading.Thread):
    height = 20
    width = 20
    counter = 0

    def __init__(self, x):
        threading.Thread.__init__(self)
        self.x = x
        self.y = 0

    #makes that the snowflakes move to the bottom and don't leave the range of the width and height
    def run(self):
        while self.y < Schneeflocke.height:
            self.x += random.randint(-1, 1)
            self.y += random.randint(0,1)
            if self.x >= Schneeflocke.width:
                self.x -= 1
            if self.x <= 0:
                self.x += 1
            time.sleep(0.2)

        #a counter gets higher every time a thread gets locked
        with threading.Lock():
            Schneeflocke.counter += 1

class Consumer(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self)
        #creates the 'frame' for the window
        self.window = "-" * Schneeflocke.height

    def run(self):
        while True:
            print(self.window)
            arr = [[" " for h in range(Schneeflocke.height)] for w in range(Schneeflocke.width + 1)]

            for coo in threads:
                arr[coo.y][coo.x] = "*"

            for i in arr:
                istr = ""
                for j in i:
                    istr += str(j)
                print(istr)

            #pints buttom of the frame and information how many snowflakes already reached the ground
            print(self.window + " Schneeflocken am Boden:" + str(Schneeflocke.counter))
            print("")
            #refrehs every 0.2 seconds the output
            time.sleep(0.2)

            #stops the program when every snowflake hit the ground
            if Schneeflocke.counter == numberOfSnowflakes:
                break

if __name__ == "__main__":
    #create empty array
    threads = []
    #Random number of snowflakes between 10 and 100
    numberOfSnowflakes = random.randint(10, 100)

    for i in range(numberOfSnowflakes):
        snowflake = Schneeflocke(random.randint(0, Schneeflocke.height -1))
        threads.append(snowflake)

    #starts the consumer thread
    consumer = Consumer()
    consumer.start()

    for t in threads:
        #without the time.sleep all threads would start at the same time. now every 0.2 seconds a new thread gets started
        time.sleep(0.2)
        t.start()

    for t in threads:
        t.join()
