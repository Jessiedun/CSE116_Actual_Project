import random


def getName(playerNames):
    return playerNames

def askQuestion():
    x = "How many circles are there in the picture?"
    y = "How many squares are there in the picture?"
    z = "What was the first shape in the picture?"
    qDict = {x: 1, y: 2, z: 1}
    return random.choice(list(qDict.items()))


def checkPlayersAmountLeft(playerNames):
    return len(playerNames)


playerNames = ["Justin", "Johnson", "Melinda"]
print(getName(playerNames))
print(checkPlayersAmountLeft(playerNames))
print(askQuestion())