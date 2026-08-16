# Program to find mean, median, and mode

from statistics import mean, median, mode, StatisticsError

# Function to calculate mean, median, and mode
def calculate_statistics(numbers):
    try:
        mean_value = mean(numbers)
        median_value = median(numbers)
        mode_value = mode(numbers)
    except StatisticsError:
        mode_value = "No unique mode (multiple values have the same frequency)"
    
    print(f"Numbers: {numbers}")
    print(f"Mean: {mean_value}")
    print(f"Median: {median_value}")
    print(f"Mode: {mode_value}")

# Example usage
numbers = [4, 7, 2, 7, 3, 7, 5, 2, 3]
calculate_statistics(numbers)
#2
# Program to calculate range, variance, and standard deviation

from statistics import variance, stdev

def calculate_stats(data):
    # Range = max - min
    data_range = max(data) - min(data)
    
    # Variance and Standard Deviation
    data_variance = variance(data)
    data_stdev = stdev(data)
    
    print(f"Data: {data}")
    print(f"Range: {data_range}")
    print(f"Variance: {data_variance:.2f}")
    print(f"Standard Deviation: {data_stdev:.2f}")

# Example usage
data = [10, 12, 23, 23, 16, 23, 21, 16]
calculate_stats(data)
#3
# Program to display frequency distribution of marks in a class

from collections import Counter

def display_frequency_distribution(marks):
    # Count frequency of each mark
    frequency = Counter(marks)
    
    print("Marks\tFrequency")
    print("-" * 20)
    for mark in sorted(frequency):
        print(f"{mark}\t{frequency[mark]}")

# Example usage
marks = [85, 90, 78, 85, 92, 90, 75, 78, 85, 90, 92, 95, 78, 85]
display_frequency_distribution(marks)
#4
# Program to find correlation between two lists of numbers

from statistics import mean
import math

def calculate_correlation(x, y):
    if len(x) != len(y):
        raise ValueError("Both lists must have the same number of elements.")
    
    n = len(x)
    mean_x = mean(x)
    mean_y = mean(y)
    
    # Calculate numerator and denominator for Pearson correlation
    numerator = sum((x[i] - mean_x) * (y[i] - mean_y) for i in range(n))
    denominator = math.sqrt(sum((x[i] - mean_x) ** 2 for i in range(n)) * 
                            sum((y[i] - mean_y) ** 2 for i in range(n)))
    
    correlation = numerator / denominator
    return correlation

# Example usage
x = [43, 21, 25, 42, 57, 59]
y = [99, 65, 79, 75, 87, 81]

r = calculate_correlation(x, y)
print(f"Correlation coefficient (r): {r:.4f}")
#5
# Program to draw a bar chart of category frequencies
import matplotlib.pyplot as plt
from collections import Counter

# Sample data
categories = ['A', 'B', 'A', 'C', 'B', 'A', 'C', 'C', 'B', 'A']

# Count frequency of each category
freq = Counter(categories)

# Plot bar chart
plt.bar(freq.keys(), freq.values(), color='skyblue')
plt.title("Frequency of Categories")
plt.xlabel("Category")
plt.ylabel("Frequency")
plt.show()
#6
# Program to plot a simple line chart for sales growth
import matplotlib.pyplot as plt

# Sample data
months = ['Jan', 'Feb', 'Mar', 'Apr', 'May']
sales = [1500, 2000, 2500, 2800, 3200]

# Plot line chart
plt.plot(months, sales, marker='o', color='green')
plt.title("Sales Growth Over 5 Months")
plt.xlabel("Month")
plt.ylabel("Sales ($)")
plt.grid(True)
plt.show()
#7
# Program to create a scatter plot for height vs. weight
import matplotlib.pyplot as plt

# Sample data
height = [150, 155, 160, 165, 170, 175, 180, 185]
weight = [50, 55, 60, 65, 68, 72, 78, 85]

# Plot scatter chart
plt.scatter(height, weight, color='blue', marker='o')
plt.title("Relationship Between Height and Weight")
plt.xlabel("Height (cm)")
plt.ylabel("Weight (kg)")
plt.grid(True)
plt.show()
#8
# Program to replace missing values with the mean
import numpy as np
import pandas as pd

# Sample dataset with missing values (NaN)
data = {'Marks': [85, 90, np.nan, 88, 92, np.nan, 95]}
df = pd.DataFrame(data)

print("Before handling missing values:")
print(df)

# Replace NaN with mean
df['Marks'].fillna(df['Marks'].mean(), inplace=True)

print("\nAfter replacing missing values with mean:")
print(df)

#9
# Program to predict future values using simple linear regression
import numpy as np
from sklearn.linear_model import LinearRegression

# Sample data: X = months, y = sales
X = np.array([1, 2, 3, 4, 5]).reshape(-1, 1)  # Months
y = np.array([1500, 1800, 2100, 2400, 2700])  # Sales

# Create and train model
model = LinearRegression()
model.fit(X, y)

# Predict sales for month 6
future_month = np.array([[6]])
predicted_sales = model.predict(future_month)

print(f"Predicted sales for month 6: {predicted_sales[0]:.2f}")
#10
# Program to create a box plot
import matplotlib.pyplot as plt

# Sample data: marks of students
marks = [55, 78, 85, 90, 65, 72, 88, 95, 70, 80, 60, 75]

# Create box plot
plt.boxplot(marks)
plt.title("Box Plot of Student Marks")
plt.ylabel("Marks")
plt.grid(True)
plt.show()
