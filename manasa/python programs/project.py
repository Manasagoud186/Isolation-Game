import networkx as nx
import matplotlib.pyplot as plt

# Create a weighted graph
G = nx.Graph()

# Add edges with distances (weights)
edges = [
    ("H", "K", 210),
    ("K", "A", 150),
    ("A", "BL", 210),          # Route 1 (Shortest)

    ("H", "M", 100),
    ("M", "R", 140),
    ("R", "B", 130),
    ("B", "BL", 250),          # Route 2

    ("H", "S", 190),
    ("S", "N", 150),
    ("N", "A", 230),           # Route 3
]

G.add_weighted_edges_from(edges)

# Node positions for clean layout
pos = {
    "H": (0, 0),
    "K": (1, 1),
    "A": (2, 1),
    "BL": (3, 0),

    "M": (0.5, -1),
    "R": (1.5, -1.5),
    "B": (2.5, -1),

    "S": (0, 1.5),
    "N": (1, 2),
}

# Draw the graph
plt.figure(figsize=(10, 7))
nx.draw(
    G, pos,
    with_labels=True,
    node_color="skyblue",
    node_size=2000,
    font_size=12,
    font_weight="bold",
)

# Draw edge labels (distances)
edge_labels = nx.get_edge_attributes(G, 'weight')
nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_size=10)

plt.title("Graph of 3 Routes: Hyderabad → Bengaluru")
plt.show()
