import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# Sample movie dataset
movies = {
    'Title': ['Movie A', 'Movie B', 'Movie C', 'Movie D', 'Movie E'],
    'Genres': ['Action Adventure',
               'Action SciFi',
               'Romance Drama',
               'Action Adventure SciFi',
               'Drama']
}

df = pd.DataFrame(movies)

# Convert genres to vectors
vectorizer = CountVectorizer()
genre_matrix = vectorizer.fit_transform(df['Genres'])

# Calculate similarity
similarity = cosine_similarity(genre_matrix)

# Function to recommend movies
def recommend(movie_name):
    index = df[df['Title'] == movie_name].index[0]
    scores = list(enumerate(similarity[index]))
    sorted_scores = sorted(scores, key=lambda x: x[1], reverse=True)
    
    print(f"\nMovies similar to {movie_name}:")
    for i, score in sorted_scores[1:4]:   # top 3 recommendations
        print(df['Title'][i], " → Similarity:", round(score, 2))

# Example: find movies like Movie A
recommend('Movie A')
#2
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# Sample electronics
df = pd.DataFrame({
    'Product': ['Smartphone', 'Headphones', 'Speaker', 'Smartwatch', 'Laptop'],
    'Description': [
        '128GB storage OLED display 48MP camera',
        'Bluetooth noise cancellation wireless',
        'Portable Bluetooth speaker waterproof',
        'GPS heart-rate monitor fitness tracking',
        '16GB RAM RTX graphics high performance'
    ]
})

# TF-IDF
tfidf = TfidfVectorizer(stop_words='english')
tfidf_matrix = tfidf.fit_transform(df['Description'])

# Similarity
similarity = cosine_similarity(tfidf_matrix)

def recommend(product):
    idx = df[df['Product'] == product].index[0]
    scores = list(enumerate(similarity[idx]))
    scores = sorted(scores, key=lambda x: x[1], reverse=True)[1:4]
    return [df['Product'][i] for i, _ in scores]

print(recommend("Smartphone"))
#3
import pandas as pd
from sklearn.preprocessing import MultiLabelBinarizer
from sklearn.metrics.pairwise import cosine_similarity

# Sample movies
df = pd.DataFrame({
    'Movie': ['A','B','C','D','E'],
    'Genres': [
        ['Action','Adventure'],
        ['Action','Sci-Fi'],
        ['Romance','Drama'],
        ['Action','Adventure','Sci-Fi'],
        ['Drama']
    ]
})

# Convert genres to one-hot vectors
mlb = MultiLabelBinarizer()
genre_matrix = mlb.fit_transform(df['Genres'])

# Compute similarity
sim = cosine_similarity(genre_matrix)

def recommend(movie):
    idx = df[df['Movie']==movie].index[0]
    scores = list(enumerate(sim[idx]))
    scores = sorted(scores, key=lambda x: x[1], reverse=True)[1:4]
    return [df['Movie'][i] for i, _ in scores]

print(recommend('A'))   # Top 3 similar movies
#4
import numpy as np
import pandas as pd

# Ratings matrix (users × movies)
R = pd.DataFrame({
    'M1':[5,4,np.nan,1],
    'M2':[4,np.nan,2,1],
    'M3':[1,2,5,np.nan],
    'M4':[np.nan,3,4,2]
}, index=['U1','U2','U3','U4'])

def recommend(user):
    # 1. Mean-center
    R_filled = R.fillna(0)
    user_mean = R_filled.mean(axis=1)
    R_centered = R_filled.sub(user_mean, axis=0)

    # 2. SVD
    U, s, Vt = np.linalg.svd(R_centered, full_matrices=False)
    R_pred = (U @ np.diag(s) @ Vt) + user_mean.values.reshape(-1,1)

    # 3. Recommend top 3 unseen
    preds = pd.DataFrame(R_pred, index=R.index, columns=R.columns)
    unseen = R.loc[user].isna()
    return preds.loc[user][unseen].sort_values(ascending=False).head(3)

print(recommend('U1'))



 
#5
import numpy as np, pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# data
products = pd.DataFrame([('P1','Phone','6.5 inch OLED 128GB'),
                         ('P2','Headphones','wireless noise cancel'),
                         ('P3','Speaker','portable bluetooth'),
                         ('P4','Watch','gps heart rate')],
                        columns=['id','title','desc'])
R = pd.DataFrame({'P1':[5,4,np.nan],'P2':[np.nan,5,3],'P3':[4,np.nan,5],'P4':[np.nan,2,1]}, index=['U1','U2','U3'])

# content
tf = TfidfVectorizer(stop_words='english'); C = cosine_similarity(tf.fit_transform(products['desc']))

# simple SVD preds
def svd_preds(R,k=2):
    mu=R.mean(axis=1); X=R.sub(mu,axis=0).fillna(0).values
    U,s,Vt=np.linalg.svd(X,full_matrices=False)
    Xhat=(U[:,:k]@np.diag(s[:k])@Vt[:k,:])+mu.values.reshape(-1,1)
    return pd.DataFrame(Xhat,index=R.index,columns=R.columns)

preds = svd_preds(R, k=2)

# hybrid recommend
def recommend(user, top_n=3, cf_w=0.7):
    unr = R.loc[user].isna()
    cf = preds.loc[user, unr.index[unr]].astype(float)
    seen = R.loc[user].dropna().index.tolist()
    cont = {}
    for pid in cf.index:
        idx = products.index[products['id']==pid][0]
        cont[pid] = np.mean([C[idx, products.index[products['id']==s][0]] for s in seen]) if seen else 0.0
    cf = (cf - cf.min())/(cf.max()-cf.min()+1e-9); cont = pd.Series(cont)
    cont = (cont - cont.min())/(cont.max()-cont.min()+1e-9)
    score = cf_w*cf + (1-cf_w)*cont
    return products.set_index('id').loc[score.sort_values(ascending=False).head(top_n).index]['title'].tolist()

# example
print(recommend('U1', top_n=2))

