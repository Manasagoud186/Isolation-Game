import pandas as pd

# Reading from a dictionary (as a dataset)
data = pd.DataFrame({
    "ID": [1, 2, 3, 4, 5],
    "Car": ["Tesla Model 3", "Toyota Corolla", "BMW X5", "Honda Civic", "Ford Mustang"],
    "Price": [45000, 22000, 60000, 25000, 55000],
    "Fuel_Type": ["Electric", "Petrol", "Diesel", "Petrol", "Petrol"]
})

print("Data:\n", data)
#2
# Select only Car and Price columns
selected = data[["Car", "Price"]]
print("=== Selected Columns ===\n", selected)
#4
# Filter cars with Price > 30000
filtered = data[data["Price"] > 30000]
print("=== Cars with Price > 30000 ===\n", filtered)
#5
# Add a new column 'Tax' as 10% of Price
data["Tax"] = data["Price"] * 0.10
print("=== After Adding Tax Column ===\n", data)
#6
# Sort by Price (Descending order)
sorted_data = data.sort_values(by="Price", ascending=False)
print("=== Sorted by Price (Descending) ===\n", sorted_data)
#7
# Group by Fuel_Type and find average Price
grouped = data.groupby("Fuel_Type")["Price"].mean().reset_index()
print("=== Average Price by Fuel Type ===\n", grouped)
#8
# Rank cars by Price (highest = rank 1)
data["Rank"] = data["Price"].rank(ascending=False)
print("=== Ranked by Price ===\n", data)
#9
import matplotlib.pyplot as plt

# Bar plot of Car vs Price
plt.bar(data["Car"], data["Price"], color="skyblue")
plt.title("Car Prices")
plt.xlabel("Car")
plt.ylabel("Price ($)")
plt.xticks(rotation=45)
plt.tight_layout()
plt.show()
#10
from sklearn.preprocessing import MinMaxScaler

scaler = MinMaxScaler()
data["Price_Normalized"] = scaler.fit_transform(data[["Price"]])
print("=== Normalized Price (0–1 Range) ===\n", data)

