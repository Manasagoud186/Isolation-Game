# Create data.txt with some text
with open("data.txt", "w") as f:
    f.write("Python is fun\n")
    f.write("File handling is important\n")
    f.write("We are counting words and lines\n")
# 6. Count words and lines
with open("data.txt", "r") as f:
    lines = f.readlines()

num_lines = len(lines)
num_words = sum(len(line.split()) for line in lines)

print("Number of lines:", num_lines)
print("Number of words:", num_words)
