'''
    OMER CEVIK

    Finds the maximum depth of tree
    recursively.
'''

#   Node class.
class Node:

    #   Constructor of nodes.
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


#   Recursive function to evaluate max depth.
def findMaxDepthOfTree(root):
    if root is None:
        return 0

    left = findMaxDepthOfTree(root.left)
    right = findMaxDepthOfTree(root.right)

    return max(left, right) + 1


root = Node(0)
root.left = Node(1)
root.right = Node(2)
root.left.left = Node(3)
root.left.left.left = Node(9)
root.left.right = Node(4)

print('Maximum depth of tree is ' + str(findMaxDepthOfTree(root)))
