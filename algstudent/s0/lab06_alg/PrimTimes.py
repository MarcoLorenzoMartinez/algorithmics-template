import time
import Helper as h
from Prim import prim

def PrimTimes():
    i = 256
    print("n\t\ttime")
    while (i < 256*2**200):
        #n, edges = prim.loadForPrim("graph" + str(i) + ".txt")
        t3 = time.time()
        edges = h.triangularMatrixRandomIntegers(i,100,1000)
        edges = h.fullMatrix(i, edges)
        t4 = time.time()
        print("------------" + str(i) + "------------")
        t1 = time.time()
        prim(i, edges)
        t2 = time.time()
        
        print(str(i) + "\t\t" + str(t2-t1) + "\tCreating=" + str(t4-t3))
        i *= 2

def main():
    PrimTimes()

if __name__ == "__main__":
    main()