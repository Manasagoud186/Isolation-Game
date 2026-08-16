import numpy as np
import pandas as pd
from sklearn.datasets import make_classification
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis as LDA
import matplotlib.pyplot as plt

# Generate a dataset with two classes
X, y = make_classification(
    n_samples=200,       # Total number of samples
    n_features=2,        # Number of features (2 for easy visualization)
    n_classes=2,         # Number of classes
    n_clusters_per_class=1,  # Number of clusters per class
    n_redundant=0,       # No redundant features
    n_informative=2,     # All features are informative
    class_sep=1.5,       # Separation between the classes
    random_state=42      # For reproducibility
)

# Convert to a pandas DataFrame for better visualization and analysis
data = pd.DataFrame(X, columns=['Feature 1', 'Feature 2'])
data['Class'] = y

# Display the first few rows of the dataset
print(data.head())

# Visualize the dataset
plt.figure(figsize=(8, 6))
for class_value in np.unique(y):
    plt.scatter(
        X[y == class_value, 0], 
        X[y == class_value, 1], 
        label=f'Class {class_value}',
        alpha=0.7
    )

plt.title('Dataset with Two Classes')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()
plt.grid(True)
plt.show()

# Apply Linear Discriminant Analysis (LDA) to project data into 1D space
lda = LDA(n_components=1) # reduces the dimensionality to one component
X_lda = lda.fit_transform(X, y) # fits the LDA model to the data and transforms it

# Visualize the data in LDA space
plt.figure(figsize=(8, 6))
for class_value in np.unique(y):
    plt.hist(
        X_lda[y == class_value], 
        bins=15, 
        alpha=0.7, 
        label=f'Class {class_value}'
    )

plt.title('Data Projected into LDA Space')
plt.xlabel('LDA Component')
plt.ylabel('Frequency')
plt.legend()
plt.grid(True)
plt.show()

# Perform Singular Value Decomposition (SVD)
U, Sigma, VT = np.linalg.svd(X)

# Display the results of SVD
print("U (Left singular vectors):\n", U)
print("\nSigma (Singular values):\n", Sigma)
print("\nVT (Right singular vectors):\n", VT)
