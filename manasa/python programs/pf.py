import seaborn as sns
import matplotlib.pyplot as plt
import numpy as np

months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
sales = [50, 80, 65, 90, 120, 150, 170, 160, 140, 130, 110, 95]
sns.set_style("whitegrid")
plt.figure(figsize=(9, 5))
sns.lineplot(x=months, y=sales, marker="o", linestyle="-", color="b", label="Sales")

plt.title("Monthly Sales Trend")
plt.xlabel("Month")
plt.ylabel("Sales (in $1000s)")
plt.xticks(rotation=45)
plt.legend()
plt.show()
#2
import numpy as np
x = np.random.rand(50) * 10
y = x + np.random.randn(50)
sns.scatterplot(x=x, y=y)
plt.title("Scatter Plot Example")
plt.xlabel("X-axis")
plt.ylabel("Y-axis")
plt.show()
#3
import numpy as np
data = [np.random.randn(100) * i for i in range(1, 5)]
sns.boxplot(data=data)
plt.title("Box Plot Example")
plt.xlabel("Categories")
plt.ylabel("Values")
plt.show()
#4
categories = ["A", "B", "C", "D"]
values = np.random.randint(10, 100, size=4)
sns.barplot(x=categories, y=values)
plt.title("Bar Plot Example")
plt.xlabel("Categories")
plt.ylabel("Values")
plt.show()
#5
import seaborn as sns
import matplotlib.pyplot as plt
import numpy as np
data = np.random.randn(1000)  
sns.histplot(data, bins=30, kde=False)
plt.show()
#6
import seaborn as sns
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
np.random.seed(42)
data = np.random.randn(10, 10)
df = pd.DataFrame(data,columns=
[f'Var{i}' for i in range(1, 11)])
df += np.random.normal(0, 0.01, df.shape)
sns.clustermap(df, cmap="coolwarm", standard_scale=1, method="ward")
plt.show()
#7
from matplotlib import pyplot as plt
import numpy as np

data = np.arange(0, 10) ** 2
plt.plot(data)

plt.show()
#8
from matplotlib import pyplot as plt
import numpy as np
x = np.linspace(-10, 10, 100)
y = 3.5 - 2.3 * x + 0.5 * x ** 2  

# plot the data
plt.plot(x, y)
plt.show()
#9
from matplotlib import pyplot as plt
import numpy as np
linestyles = ["-", "--", "-.", ":"] 
x = np.linspace(-10, 10, 100) 
for i, ls in enumerate(linestyles): 
    y=3.5-2.3*(x+i)+0.5*(x+i)**2 
    plt.plot(x,y,linestyle=ls,label=ls) 
plt.legend() 
plt.show()
#10
from matplotlib import pyplot as plt
import numpy as np
colors = ["r", "g", "b", "k"]
x = np.linspace(-10, 10, 100)
for i, c in enumerate(colors):
    y =3.5-2.3*(x+i)+0.5*(x+i)** 2
    plt.plot(x, y, color=c)
plt.show()

