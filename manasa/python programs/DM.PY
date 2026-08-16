import heapq

# ---------------------------------------
# GRAPH for 3 Routes (Hyderabad → Bengaluru)
# ---------------------------------------

graph = {
    "H": [("K", 210), ("M", 100), ("S", 190)],     # Hyderabad connections
    "K": [("H", 210), ("A", 150)],                # Kurnool
    "A": [("K", 150), ("BL", 210), ("N", 230)],   # Anantapur
    "BL": [("A", 210), ("B", 250)],               # Bengaluru
    "M": [("H", 100), ("R", 140)],                # Mahbubnagar
    "R": [("M", 140), ("B", 130)],                # Raichur
    "B": [("R", 130), ("BL", 250)],               # Ballari
    "S": [("H", 190), ("N", 150)],                # Srisailam
    "N": [("S", 150), ("A", 230)],                # Nandyal
}

# ---------------------------------------
# Dijkstra's Algorithm Implementation
# ---------------------------------------

def dijkstra(graph, start, goal):
    dist = {node: float("inf") for node in graph}
    prev = {node: None for node in graph}

    dist[start] = 0
    pq = [(0, start)]  # priority queue

    while pq:
        current_dist, u = heapq.heappop(pq)

        if current_dist > dist[u]:
            continue

        for v, weight in graph[u]:
            new_distance = dist[u] + weight

            if new_distance < dist[v]:
                dist[v] = new_distance
                prev[v] = u
                heapq.heappush(pq, (new_distance, v))

    # Recover shortest path
    path = []
    node = goal
    while node is not None:
        path.insert(0, node)
        node = prev[node]

    return dist[goal], path


# ---------------------------------------
# RUN the algorithm
# ---------------------------------------

distance, path = dijkstra(graph, "H", "BL")

print("Shortest Path from Hyderabad to Bengaluru:")
print(" → ".join(path))
print("Total Distance:", distance, "km")
