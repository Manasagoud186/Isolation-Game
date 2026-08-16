# Import necessary libraries
import networkx as nx          # NetworkX is used to work with graphs and networks
import matplotlib.pyplot as plt  # Matplotlib is used to create visualizations

# --------------------------------------------------
# Create a directed graph
# --------------------------------------------------
G = nx.DiGraph()   # DiGraph supports directed edges (links)

# Add edges to the graph
# Example:
# A -> B, C
# B -> C
# D -> A, C
G.add_edges_from([
    ('A', 'B'),
    ('A', 'C'),
    ('B', 'C'),
    ('D', 'A'),
    ('D', 'C')
])

# --------------------------------------------------
# Run the PageRank algorithm
# --------------------------------------------------
# alpha is the damping factor (usually 0.85)
pagerank = nx.pagerank(G, alpha=0.85)

# Print PageRank values
print("PageRank values:")
for node, value in pagerank.items():
    print(f"{node}: {round(value, 4)}")

# --------------------------------------------------
# Visualize the graph
# --------------------------------------------------
# Position nodes using spring layout
pos = nx.spring_layout(G)

# Set node sizes proportional to PageRank values
node_sizes = [value * 10000 for value in pagerank.values()]

# Draw the graph
nx.draw(
    G,
    pos,
    with_labels=True,
    node_size=node_sizes,
    node_color='lightblue',
    font_size=10
)

# Add PageRank values as labels on nodes
labels = {node: f"{node}\n{pagerank[node]:.4f}" for node in G.nodes()}
nx.draw_networkx_labels(G, pos, labels=labels, font_size=12)

# Add title
plt.title("Graph Visualization with PageRank Scores")

# Show the graph
plt.show()
