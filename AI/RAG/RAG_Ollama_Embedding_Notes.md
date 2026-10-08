# RAG Practice Notes — Ollama & Embeddings

## 1. RAG Components

For basic RAG practice, we are using:

```text
.NET 8 Application
       |
       v
    Ollama
       |
       v
nomic-embed-text
       |
       v
Embedding / Vector
       |
       v
    Qdrant
       |
       v
Similarity Search
```

### What each term means

| Term | Simple meaning | Think of it as |
|---|---|---|
| Ollama | Runs AI models locally | AI model runner |
| nomic-embed-text | Model that converts text into embeddings | Embedding model |
| Embedding | Representing text meaning numerically | Meaning representation |
| Vector | The actual list/array of numbers | Array of numbers |
| Qdrant | Stores and searches vectors | Vector database |

> Note: The vector database is **Qdrant**, not "Quadrant".

---

# 2. Install Ollama

Ollama is a program that lets you run AI models locally on your computer.

Official website:

https://ollama.com/

For Windows, the normal installer is:

```text
OllamaSetup.exe
```

After installation, open a **new PowerShell** window and verify:

```powershell
ollama --version
```

Example:

```text
ollama version is 0.40.0
```

If you get a version, Ollama is installed correctly.

---

# 3. If `winget` Is Not Recognized

Normally Ollama can also be installed using:

```powershell
winget install --id Ollama.Ollama -e
```

If PowerShell says:

```text
winget : The term 'winget' is not recognized
```

check whether App Installer exists:

```powershell
Get-AppxPackage Microsoft.DesktopAppInstaller
```

If `winget.exe` exists but is not in PATH, test it directly:

```powershell
& "$env:LOCALAPPDATA\Microsoft\WindowsApps\winget.exe" --version
```

Temporarily add it to PATH:

```powershell
$env:Path += ";$env:LOCALAPPDATA\Microsoft\WindowsApps"
```

Then:

```powershell
winget --version
```

---

# 4. Install `nomic-embed-text`

After Ollama is installed, run this in **PowerShell/CMD**:

```powershell
ollama pull nomic-embed-text
```

This downloads the embedding model.

Verify:

```powershell
ollama list
```

You should see something similar to:

```text
NAME                ID              SIZE
nomic-embed-text    ...             ...
```

## Important

Do not install `nomic-embed-text` separately from Ollama.

This command:

```powershell
ollama pull nomic-embed-text
```

downloads the model through Ollama.

---

# 5. What Is `nomic-embed-text`?

`nomic-embed-text` is an **embedding model**.

Its job is to convert text into numbers that represent the semantic meaning of the text.

Example:

```text
"Customer Rahul lives in Delhi"
                |
                v
       nomic-embed-text
                |
                v
       [0.12, -0.45, 0.78, ...]
```

The exact numbers are not meant to be read by a human.

They are used by a vector database to compare semantic similarity.

---

# 6. What Is an Embedding?

An **embedding** is a numerical representation of the meaning of text.

Example:

```text
"Rahul lives in Delhi"
```

is converted into an embedding/vector.

Similar sentences should generally produce vectors that are close to each other in vector space.

For example:

```text
"Rahul lives in Delhi"
"Rahul is located in Delhi"
```

should have relatively similar embeddings because their meanings are similar.

---

# 7. What Is a Vector?

A vector is the actual list/array of numbers produced by the embedding model.

Example:

```json
[
  0.123,
  -0.456,
  0.789,
  0.234
]
```

Real embedding vectors contain many more numbers.

Think:

```text
Text
  |
  v
Embedding process
  |
  v
Vector
```

---

# 8. Ollama Port

Ollama normally exposes its local API at:

```text
http://localhost:11434
```

Check whether the port is running:

```powershell
netstat -ano | findstr :11434
```

You should see something similar to:

```text
TCP    127.0.0.1:11434    0.0.0.0:0    LISTENING
```

You can also test the Ollama API:

```powershell
curl.exe http://localhost:11434/api/tags
```

---

# 9. Test Embedding in Postman

You can test `nomic-embed-text` directly without writing .NET code.

## Method

```text
POST
```

## URL

```text
http://localhost:11434/api/embeddings
```

## Header

```text
Content-Type: application/json
```

## Body

In Postman:

```text
Body
  -> raw
  -> JSON
```

Use:

```json
{
  "model": "nomic-embed-text",
  "prompt": "Customer Rahul lives in Delhi"
}
```

Click **Send**.

You should receive a response containing an embedding:

```json
{
  "embedding": [
    0.012345,
    -0.034567,
    0.078912
  ]
}
```

The actual response will contain a much larger vector.

---

# 10. What Does the Embedding Result Mean?

Do not try to understand each number individually.

For example:

```text
[0.0123, -0.0345, 0.0789, ...]
```

does not mean:

```text
0.0123 = customer
-0.0345 = Delhi
0.0789 = Rahul
```

That is NOT how embeddings work.

Instead, the entire vector represents the semantic information of the input text.

The main purpose is **comparison**.

Example:

```text
Text A:
"Rahul lives in Delhi"
       |
       v
    Vector A
```

```text
Text B:
"Rahul is located in Delhi"
       |
       v
    Vector B
```

Vector A and Vector B should be relatively similar.

---

# 11. What Is Qdrant?

**Qdrant is a vector database.**

A normal SQL Server database stores structured data such as:

```text
CustomerId | Name  | City
--------------------------
1          | Rahul | Delhi
2          | Amit  | Mumbai
```

Qdrant is designed to store vectors and search for vectors that are similar to a query vector.

Conceptually:

```text
Qdrant
|
|-- Vector A -> "Rahul lives in Delhi"
|-- Vector B -> "Amit lives in Mumbai"
|-- Vector C -> "Raj lives in Delhi"
|-- Vector D -> "Priya lives in Bangalore"
```

---

# 12. Why Does RAG Need Qdrant?

Suppose we have a document:

```text
Rahul lives in Delhi.
Amit lives in Mumbai.
Raj lives in Delhi.
Priya lives in Bangalore.
```

We split the document into chunks.

Then:

```text
Chunk 1
"Rahul lives in Delhi."
        |
        v
nomic-embed-text
        |
        v
Vector 1
```

The same happens for the other chunks.

The vectors and their related text are stored in Qdrant.

Now the user asks:

```text
"Who lives in Delhi?"
```

The question is also converted into a vector:

```text
"Who lives in Delhi?"
        |
        v
nomic-embed-text
        |
        v
Question Vector
```

Qdrant searches for vectors similar to the question vector.

It may return:

```text
Rahul lives in Delhi.
Raj lives in Delhi.
```

These relevant chunks can then be provided to an LLM to generate the final answer.

---

# 13. Complete RAG Flow

```text
                    USER
                     |
                     v
             "Who lives in Delhi?"
                     |
                     v
              Ollama / Embedding
                     |
                     v
               Question Vector
                     |
                     v
                  Qdrant
                     |
              Similarity Search
                     |
                     v
              Relevant Chunks
                     |
                     v
                    LLM
                     |
                     v
          Human-readable answer
```

Remember:

```text
Ollama
  = runs the AI model

nomic-embed-text
  = embedding model

Embedding
  = process/result of representing text meaning numerically

Vector
  = actual array of numbers

Qdrant
  = vector database that stores/searches vectors
```

---

# 14. Qdrant + Docker

Qdrant does not have to run in Docker, but Docker is a convenient way to run it locally.

Typical command:

```bash
docker run -p 6333:6333 qdrant/qdrant
```

Then Qdrant is normally available at:

```text
http://localhost:6333
```

Conceptually:

```text
Windows PC
|
|-- .NET 8
|
|-- Ollama
|    |
|    `-- nomic-embed-text
|
`-- Docker
     |
     `-- Qdrant
```

---

# 15. Current Practice Progress

Completed:

```text
[✓] Install Ollama
[✓] Verify Ollama version
[✓] Install nomic-embed-text
[✓] Verify Ollama model
[✓] Check Ollama port 11434
[✓] Test embedding using Postman
[✓] Understand embedding/vector
```

Next RAG practice:

```text
[ ] Install/run Qdrant
[ ] Create a Qdrant collection
[ ] Store vectors in Qdrant
[ ] Store original text as payload
[ ] Generate a question embedding
[ ] Search Qdrant for similar vectors
[ ] Retrieve relevant chunks
[ ] Send chunks to an LLM
[ ] Generate the final RAG answer
```

---

# 16. Most Important Sentence

> **Ollama runs `nomic-embed-text`; `nomic-embed-text` converts text into embeddings/vectors; Qdrant stores those vectors and finds similar vectors.**

This is the basic relationship you should remember before continuing with RAG.
