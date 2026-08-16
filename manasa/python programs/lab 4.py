import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis as LDA

# -------------------------------------------------------------
# 1️⃣ Generate dataset – Apples (Class 0) vs Oranges (Class 1)
# -------------------------------------------------------------
np.random.seed(42)

# Apples
apple_weight = np.random.normal(150, 10, 50)
apple_diameter = np.random.normal(7.0, 0.5, 50)

# Oranges
orange_weight = np.random.normal(200, 15, 50)
orange_diameter = np.random.normal(8.5, 0.6, 50)

# Combine into a single dataset
X = np.column_stack((
    np.concatenate([apple_weight, orange_weight]),
    np.concatenate([apple_diameter, orange_diameter])
))
y = np.array([0]*50 + [1]*50)  # 0=Apple, 1=Orange

# Convert to DataFrame
data = pd.DataFrame(X, columns=['Weight', 'Diameter'])
data['Class'] = y

# -------------------------------------------------------------
# 2️⃣ Display dataset
# -------------------------------------------------------------
print(data.head())

# -------------------------------------------------------------
# 3️⃣ Apply Linear Discriminant Analysis (LDA)
# -------------------------------------------------------------
lda = LDA(n_components=1)
X_lda = lda.fit_transform(X, y)

# -------------------------------------------------------------
# 4️⃣ Visualize Linear Discriminants (1D projection)
# -------------------------------------------------------------
plt.figure(figsize=(8, 6))
for class_value in np.unique(y):
    plt.scatter(
        X_lda[y == class_value],
        np.zeros_like(X_lda[y == class_value]),
        label=f'Class {class_value} ({ "Apples" if class_value==0 else "Oranges" })',
        alpha=0.7
    )

plt.title('Linear Discriminants Visualization')
plt.xlabel('LDA Component')
plt.yticks([])
plt.legend()
plt.grid(True)
plt.show()

# -------------------------------------------------------------
# 5️⃣ Perform Singular Value Decomposition (SVD)
# -------------------------------------------------------------
U, Sigma, VT = np.linalg.svd(X)

# -------------------------------------------------------------
# 6️⃣ Display results of SVD
# -------------------------------------------------------------
print("\nU (Left singular vectors):\n", U)
print("\nSigma (Singular values):\n", Sigma)
print("\nVT (Right singular vectors):\n", VT)
  

