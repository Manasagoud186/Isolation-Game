import pandas as pd

data = {
    'City': ['New York', 'San Francisco', 'Los Angeles', 'New York'],
    'Salary': [100000, 120000, 90000, 110000]
}

df = pd.DataFrame(data)


df['City_encoded'] = df['City'].astype('category').cat.codes

print(df)
