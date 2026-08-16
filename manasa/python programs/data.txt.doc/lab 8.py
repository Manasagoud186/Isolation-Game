with open("sample.txt","w") as f:
    f.write("Hello, this is line one.\n")
    f.write("This is line two.\n")
    f.write("Python file handling is easy!\n")
print("sample.txt created and text written.")
#2
with open("sample.txt", "r") as f:
    content = f.read()

print("Contents of sample.txt:\n")
print(content)
#3
with open("sample.txt", "r") as f:
    print("Using readline():")
    print(f.readline())  
    print(f.readline())  
with open("sample.txt", "r") as f:
    print("\nUsing readlines():")
    lines = f.readlines()  
    print(lines)
#4
with open("sample.txt", "a") as f:
    f.write("This is an appended line.\n")

print("Line appended to sample.txt.")
#5
# 5. Using writelines()
lines = ["First line using writelines.\n",
         "Second line using writelines.\n",
         "Third line using writelines.\n"]

with open("multi.txt", "w") as f:
    f.writelines(lines)

# Read back
with open("multi.txt", "r") as f:
    print("Contents of multi.txt:")
    print(f.read())
#6
# Program to count the number of words and lines in data.txt

# Open the file in read mode
with open("data.txt", "r") as f:
    lines = f.readlines()   # Read all lines into a list

# Count number of lines
num_lines = len(lines)

# Count number of words (split each line into words and count)
num_words = sum(len(line.split()) for line in lines)

# Display results
print("Number of lines in data.txt:", num_lines)
print("Number of words in data.txt:", num_words)
#7
# Program to copy contents from source.txt to copy.txt

# Open source.txt in read mode and copy.txt in write mode
with open("source.txt", "r") as src, open("copy.txt", "w") as dst:
    # Read contents from source and write to destination
    dst.write(src.read())

print("Contents copied from source.txt to copy.txt successfully.")
#8
# 8. Using with open()
with open("sample.txt", "r") as f:
    print(f.read())

# File is automatically closed here
print("Is file closed?", f.closed)  # True
#9
# 9. Check file existence
import os
from pathlib import Path

filename = "sample.txt"

# Using os.path
if os.path.exists(filename):
    print(f"{filename} exists (checked with os).")

# Using pathlib
if Path(filename).exists():
    print(f"{filename} exists (checked with pathlib).")
#10
    import os

filename = "unwanted.txt"

# Check if file exists before deleting
if os.path.exists(filename):
    os.remove(filename)
    print(f"{filename} has been deleted successfully.")
else:
    print(f"{filename} does not exist, so it cannot be deleted.")


