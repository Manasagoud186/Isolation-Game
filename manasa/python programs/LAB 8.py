import matplotlib.pyplot as plt
import numpy as np

x = np.arange(1, 8)
y1 = [3, 5, 2, 8, 7, 6, 9]
y2 = [2, 4, 5, 3, 6, 7, 4]

# Line Chart
plt.plot(x, y1, marker='o', label='A')
plt.plot(x, y2, marker='s', linestyle='--', label='B')
plt.title('Line Chart'); plt.grid(); plt.legend()
plt.show()

# Bar Chart
plt.bar(x - 0.2, y1, width=0.4, label='A')
plt.bar(x + 0.2, y2, width=0.4, label='B')
plt.title('Bar Chart'); plt.legend()
plt.show()

# Scatter Plot
plt.scatter(x, y1, s=np.array(y1)*40, c=y2, cmap='viridis')
plt.title('Scatter Plot'); plt.colorbar()
plt.show()

# Pie Chart
plt.pie([40,25,20,15], labels=['A','B','C','D'], autopct='%1.1f%%', explode=[0.1,0,0,0])
plt.title('Pie Chart')
plt.show()
#2
import seaborn as sns
import matplotlib.pyplot as plt

# Load data
iris = sns.load_dataset("iris")
penguins = sns.load_dataset("penguins").dropna()

# Heatmap (numeric columns only)
sns.heatmap(iris.select_dtypes('number').corr(), annot=True)
plt.show()

# Jointplot
sns.jointplot(data=iris, x="sepal_length", y="petal_length")
plt.show()

# Pairplot
sns.pairplot(iris, hue="species")
plt.show()

# Catplot
sns.catplot(data=penguins, x="species", y="body_mass_g", kind="box")
plt.show()
#3
import matplotlib.pyplot as plt
x = [1, 2, 3, 4, 5]
y = [10, 15, 13, 18, 22]
labels = ["A", "B", "C", "D", "E"]
# 1️⃣ LINE CHART
# -------------------------------------------------------
plt.figure()
plt.plot(x, y, marker='o')
plt.title("Line Chart")
plt.xlabel("X Values")
plt.ylabel("Y Values")
plt.show()

# 2️⃣ BAR CHART
# -------------------------------------------------------
plt.figure()
plt.bar(x, y)
plt.title("Bar Chart")
plt.xlabel("X Values")
plt.ylabel("Y Values")
plt.show()

# 3️⃣ SCATTER PLOT
# -------------------------------------------------------
plt.figure()
plt.scatter(x, y)
plt.title("Scatter Plot")
plt.xlabel("X Values")
plt.ylabel("Y Values")
plt.show()

# 4️⃣ PIE CHART
# -------------------------------------------------------
plt.figure()
plt.pie(y, labels=labels, autopct="%0.1f%%")
plt.title("Pie Chart")
plt.show()
#4
import seaborn as sns
import matplotlib.pyplot as plt

# Load dataset
penguins = sns.load_dataset("penguins")

# Histogram
sns.histplot(penguins["body_mass_g"].dropna())
plt.show()

# Boxplot
sns.boxplot(data=penguins, x="species", y="body_mass_g")
plt.show()

# Scatter plot
sns.scatterplot(data=penguins, x="bill_length_mm", y="bill_depth_mm", hue="species")
plt.show()

# Pairplot
sns.pairplot(penguins.dropna(), hue="species")
plt.show()

# Heatmap
sns.heatmap(penguins.select_dtypes("number").corr(), annot=True)
plt.show()

#5
# geopandas_world_highlight.py
import geopandas as gpd
import matplotlib.pyplot as plt

# Load Natural Earth data directly from the official URL
url = "https://naturalearth.s3.amazonaws.com/110m_cultural/ne_110m_admin_0_countries.zip"
world = gpd.read_file(url)

# Select a few countries to highlight
highlight = world[world['NAME'].isin(['India', 'China', 'Australia', 'Brazil'])]

# Plot
ax = world.plot(figsize=(10, 6), color='lightgray', edgecolor='white')
highlight.plot(ax=ax, color='orange', edgecolor='black')

ax.set_title("World Map (Highlighted Countries)")
ax.set_axis_off()
plt.show()
