'''
    OMER CEVIK

    Finds and prints the same characters
    in two lists with time complexity O(m + n).
'''
def findSameCharacters(A, B):

    stack = [0] * 256
    for elementOfA in A:                # Complexity of that loop is O(m).
        stack[ord(elementOfA)] = 1

    print('Matching characters are\t',end=' ')

    for elementOfB in B:                # Complexity of that loop is O(n).
        if stack[ord(elementOfB)] == 1:
            print('\'' + elementOfB + '\'',end=' ')
    print()

A = ['a', 'b', 'c', 'd', 'k', 'l', 'h']
B = ['c', 'j', 't', 'b', 'h']
print('First list is\t' + str(A))
print('Second list is\t' + str(B))
findSameCharacters(A, B)
