import networkx as nx
import matplotlib.pyplot as plt
import math

# -------------------------------------
# GRAPH with your ACTUAL DISTANCES
# -------------------------------------

G = nx.Graph()

# Route 1 (Actual distances)
route1 = [
    ("H", "K", 215),
    ("K", "A", 150),
    ("A", "BL", 215)
]

# Route 2
route2 = [
    ("H", "M", 100),
    ("M", "R", 107),
    ("R", "B", 166),
    ("B", "BL", 288)
]

# Route 3
route3 = [
    ("H", "S", 231),
    ("S", "N", 150),
    ("N", "A", 186),
    ("A", "BL", 216)
]

# Add all edges
G.add_weighted_edges_from(route1)
G.add_weighted_edges_from(route2)
G.add_weighted_edges_from(route3)

# -------------------------------------
# NODE POSITIONS (Plane graph layout)
# -------------------------------------

pos = {
    "H": (0, 1),
    "K": (0, 0.6),
    "A": (0, 0.2),
    "BL": (0, -0.2),

    "M": (-0.8, 0.8),
    "R": (-0.6, 0.4),
    "B": (-0.3, 0.0),

    "S": (0.8, 1.1),
    "N": (0.6, 0.7),
}

# -------------------------------------
# DRAW BASE GRAPH
# -------------------------------------

plt.figure(figsize=(10, 8))

nx.draw_networkx_nodes(G, pos, node_color="lightblue",
                       node_size=1400, edgecolors="black")
nx.draw_networkx_labels(G, pos, font_size=12, font_weight="bold")

# Draw base edges
nx.draw_networkx_edges(G, pos, edgelist=list(G.edges()),
                       width=1.5, edge_color="lightgrey")

# -------------------------------------
# HELPER: Place labels beside edges
# -------------------------------------

def offset_label(u, v, pos, offset=0.07):
    ux, uy = pos[u]
    vx, vy = pos[v]
    mx, my = (ux + vx) / 2, (uy + vy) / 2
    dx, dy = vx - ux, vy - uy
    length = math.hypot(dx, dy)
    if length == 0:
        return (mx, my)
    px, py = -dy/length, dx/length
    return (mx + px * offset, my + py * offset)

# Add distance labels
added = set()
for u, v, w in G.edges(data="weight"):
    key = tuple(sorted((u, v)))
    if key in added:
        continue
    added.add(key)
    lx, ly = offset_label(u, v, pos)
    plt.text(lx, ly, f"{w} km", fontsize=10, color="black")

# -------------------------------------
# DRAW ROUTES IN DIFFERENT COLORS
# -------------------------------------

# Route 1 – shortest (580 km)
nx.draw_networkx_edges(G, pos, edgelist=[(u, v) for u, v, _ in route1],
                       width=4, edge_color="green", label="Route 1 (580 km)")

# Route 2
nx.draw_networkx_edges(G, pos, edgelist=[(u, v) for u, v, _ in route2],
                       width=3, edge_color="blue", label="Route 2 (661 km)")

# Route 3
nx.draw_networkx_edges(G, pos, edgelist=[(u, v) for u, v, _ in route3],
                       width=3, edge_color="red", label="Route 3 (783 km)")

# -------------------------------------
# FINAL TOUCHES
# -------------------------------------

plt.title("Graph of 3 Routes from Hyderabad to Bengaluru (with actual distances)",
          fontsize=14)
plt.axis("off")
plt.legend()

plt.show()
