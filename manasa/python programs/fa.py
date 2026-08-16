import pandas as pd

# Read the dataset (CSV file)
data = pd.read_csv("students.csv")

# Display original data
print("Original Data:")
print(data)

# Filter records where marks are greater than 70
filtered_data = data[data["Marks"] > 70]

# Sort the filtered data by marks in descending order
sorted_data = filtered_data.sort_values(by="Marks", ascending=False)

# Display the result
print("\nFiltered and Sorted Data:")
print(sorted_data)
