import Helper as h

def loadForPrim(file_name):
    # c:/Users/marco/Desktop/UniOvi Marco/2º/2º - Second semester/Alg/Lab/lab06/Practica4 2024.eng/algstudent/s4/graph4.txt
    # Loading the lines of the files into an array
    lines = []
    try:
        with open(file_name, 'r') as file:
            for line in file:
                lines.append(line)
    except FileNotFoundError:
        print("File not found!")

    # Getting the number of nodes
    n = int(lines[0])

    edges = h.fullMatrixFromFile(file_name)
    h.printMatrix(edges)
    
    return n, edges

def min(node, mst, n):
        minValue = float('inf')
 
        for i in range(n):
            if (mst[i] == False) and (node[i] < minValue):
                minValue = node[i]
                minIndex = i
 
        return minIndex


def printMST(n, path, edges, totalCost): # TO MODIFY
        print("COSTE TOTAL ÓPTIMO=" + str(totalCost))
        print("*********************")
        print("ARISTAS SELECCIONADAS=")
        for i in range(1, n):
            print("ARISTA NUMERO " + str(i) + " :  DESDE NODO= " + str(path[i]) + "  HASTA NODO " + str(i) + " *** COSTE= " + str(edges[i][path[i]]))

def prim(n, edges):
    nodes = [float('inf')] * n    #Cost for going to each node
    path = [None] * n    #Storing the path for reaching each node
    visited = [False] * n    #Storing the visited nodes in the minimum spanning tree

    # The first node is reached since the start
    path[0] = -1
    nodes[0] = 0

    totalCost = 0
    
    for i in range(n):
        from_node = min(nodes, visited, n)
        visited[from_node] = True

        for to_node in range(n):
            if (visited[to_node] == False) and (edges[from_node][to_node] > 0) and (nodes[to_node] > edges[from_node][to_node]):
                    nodes[to_node] = edges[from_node][to_node]
                    path[to_node] = from_node

    for cost in nodes:
        totalCost += cost

    return path, totalCost


def main():
    file_name = input("Enter the name of the file: ")
    n, edges = loadForPrim(file_name)
    path, totalCost = prim(n,edges)
    printMST(n, path, edges, totalCost)

if __name__ == "__main__":
    main()



























































































































































































