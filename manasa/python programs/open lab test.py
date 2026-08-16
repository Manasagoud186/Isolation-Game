#2
from scipy.optimize import linprog

c = [-40, -30]                  # maximize profit
A_ub = [[2,1],[1,1]]
b_ub = [100,80]

res = linprog(c, A_ub=A_ub, b_ub=b_ub, bounds=[(0,None),(0,None)])

print("Optimal values:", res.x)
print("Max Profit:", -res.fun)
#3
import numpy as np
from scipy import stats

data = [1, 2, 2, 3]
print("Mode:", stats.mode(data).mode)

print("Mean:", np.mean(data))
print("Median:", np.median(data))
print("Mode:", stats.mode(data).mode[0])
print("Variance:", np.var(data, ddof=1))
print("Std Dev:", np.std(data, ddof=1))
print("IQR:", np.percentile(data,75) - np.percentile(data,25))
