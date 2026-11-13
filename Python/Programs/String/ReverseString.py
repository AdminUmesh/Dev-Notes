# Original string
text = "Hello World"

# Store the reversed string
reversed_text = ""

# Traverse the string from last character to first
for i in range(len(text) - 1, -1, -1):

    # Append each character to the reversed string
    reversed_text += text[i]

# Display original and reversed strings
print("Original String:", text)
print("Reversed String:", reversed_text)