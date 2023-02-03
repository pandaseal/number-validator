import sys
import re

def main(pn):
    print("Inmatat personnummer:", pn)

def testExampleNumbers():
    checkFile('validPNs.txt')
    checkFile('invalidPNs.txt')

def checkFile(filename):
    with open(filename) as f:
        print("Checking numers in file with name:", filename)
        for line in f:
            input = line.strip()
            print("input:", input)
            checkPersonalNumber(input)

def checkPersonalNumber(input):
    errors = []
    errors.append(checkLength(input))
    errors.append(luhnsAlgorithm(input))
    errors.append(checkNumDigits(input))

    print(errors)

def checkNumDigits(n):
    onlyDigits = re.sub('\D', '', n)
    numDigits = len(onlyDigits)

    if not((numDigits == 10) or (numDigits == 12)):
        return "Not 10 or 12 digits"

    return

def checkLength(n):
    length = len(n)
    errors = []

    if length > 13: # 12 digits and one '-'
        errors.append("too long")
    
    if length < 10:
        errors.append("too short")

    if len(errors) > 0:
        return errors
    else:
        return

def checkExistingDate(n):
    # assume that PN has correct length and shape
    
    # extract the 10 digits
    if len(n) >= 12:
        n = n[2:]
    if len(n) == 11:
        n = n.replace('-', '').replace('+','')


def luhnsAlgorithm(n):
    # assume that PN has correct length and shape

    # extract the 10 digits
    if len(n) >= 12:
        n = n[2:]
    if len(n) == 11:
        n = n.replace('-', '').replace('+','')

    # apply the algo
    individualDigits = [int(x) for x in list(n)]
    controlDigit = individualDigits[-1]
    digitsToCheck = individualDigits[:-1]
    multiplyBy = [2, 1, 2, 1, 2, 1, 2, 1, 2]
    product = [a*b for a, b in zip(digitsToCheck, multiplyBy)]
    sumOfDigits = digitSum(product)
    calculatedControlDigit = (10-(sumOfDigits % 10)) % 10

    if controlDigit == calculatedControlDigit:
        return
    else:
        return 'Control digit incorrect'

def digitSum(listOfNumbers):
    sum = 0
    for i in listOfNumbers:
        if i > 9:
            a = int(i/10)
            b = i % 10
            sum += a + b
        else:
            sum += i
    return sum


if __name__ == "__main__":
    #pn = sys.argv[1] # TODO handling of not pasting just one number at a time, or instructions for usage
    #main(pn)

    testExampleNumbers()
