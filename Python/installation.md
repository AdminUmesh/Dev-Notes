## 2. Install Python

1. Open the official [Python downloads for Windows](https://www.python.org/downloads/windows/).
2. Download a stable Python 3 release.
3. Run the installer.

## 3. Verify Python and pip

In VS Code, open **Terminal → New Terminal**. Run:

```powershell
python --version
```

Expected output is similar to:

```text
Python 3.13.x
```

Check pip, Python's package installer:

```powershell
python -m pip --version
```

Expected output includes a pip version and a Python installation path.

If `python` is not recognised, try:

```powershell
py --version
py -m pip --version
```

If `py` works but `python` does not, Python is installed but the `python` command may not be on PATH. Reopen the terminal first; if it still fails, repair the installation and enable the PATH option.

## 4. Install VS Code extensions

1. Open VS Code.
2. Press `Ctrl + Shift + X`.
3. Search for and install:
   - **Python** — publisher: Microsoft
   - **Pylance** — publisher: Microsoft
   - **Jupyter** — publisher: Microsoft (install this if you plan to use notebooks)

Official guide: [Python in VS Code](https://code.visualstudio.com/docs/python/python-tutorial).

## 5. Create a practice project

In the VS Code terminal, run:

```powershell
mkdir PythonPractice
cd PythonPractice
code .
```

If `code .` is unavailable, open the `PythonPractice` folder using **File → Open Folder** in VS Code.

## 6. Create a virtual environment

A virtual environment keeps each project's Python packages separate.

```powershell
python -m venv .venv
```

Activate it in PowerShell:

```powershell
.venv\Scripts\Activate.ps1
```

After activation, the terminal prompt should begin with `(.venv)`.

### If PowerShell blocks activation

You may see an execution-policy error. You can activate the environment for the current terminal session with:

```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
.venv\Scripts\Activate.ps1
```

This change applies only to the current PowerShell process.

Alternative in Command Prompt (CMD):

```cmd
.venv\Scripts\activate.bat
```

## 7. Select the Python interpreter in VS Code

1. Press `Ctrl + Shift + P`.
2. Search for **Python: Select Interpreter**.
3. Select the interpreter inside your project folder's `.venv` directory.
4. Open a new terminal if necessary.

The selected interpreter should point to a path similar to:

```text
PythonPractice\.venv\Scripts\python.exe
```

## 8. Verify by running a Python file

Create a file named `hello.py`:

```python
name = "Python"
numbers = [10, 20, 30]

print("Hello from", name)
print("Sum:", sum(numbers))
```

Run it in the terminal:

```powershell
python hello.py
```

Expected output:

```text
Hello from Python
Sum: 60
```

## 9. Upgrade pip and install starter packages

With the virtual environment activated, run:

```powershell
python -m pip install --upgrade pip
python -m pip install numpy pandas matplotlib requests
```

Verify the packages:

```powershell
python -m pip show numpy pandas matplotlib requests
```

These packages are useful for introductory numerical work, data handling, charts, and HTTP requests. They are not all required to run Python itself.

## 10. Optional: set up Jupyter Notebook in VS Code

A Jupyter Notebook (`.ipynb`) lets you run code in separate cells and keep code, output, charts, and Markdown notes together.

Install the notebook tools in the activated virtual environment:

```powershell
python -m pip install jupyter ipykernel
```

Then:

1. Install the **Jupyter** extension in VS Code.
2. Press `Ctrl + Shift + P`.
3. Choose **Create: New Jupyter Notebook**.
4. Use **Select Kernel** in the top-right corner.
5. Choose the Python interpreter from this project's `.venv`.
6. Add a code cell and run it.

Example cell:

```python
message = "Jupyter is working!"
print(message)
```

You do not need a separate Jupyter desktop application when using the VS Code extension.

## 11. Useful verification checklist

Run these commands from the activated project terminal:

```powershell
python --version
python -m pip --version
python -c "print('Python is working')"
python -m pip list
```

Confirm that:

- [ ] Python version is displayed.
- [ ] pip version is displayed.
- [ ] The test command prints `Python is working`.
- [ ] The terminal prompt shows `(.venv)` when the environment is activated.
- [ ] VS Code is using the `.venv` interpreter.
- [ ] `hello.py` runs successfully.
- [ ] (Optional) A Jupyter notebook cell runs successfully.

## 12. Common problems

### `python` is not recognised

- Close and reopen VS Code.
- Try `py --version`.
- If needed, repair/reinstall Python and enable **Add python.exe to PATH**.
- In VS Code, select the correct interpreter.

### `pip` is not recognised

Use:

```powershell
python -m pip --version
```

Using `python -m pip` is generally more reliable because it runs pip through the selected Python interpreter.

### `Activate.ps1` is blocked

Use the session-only PowerShell setting shown in section 6, or activate from CMD with `activate.bat`.

### VS Code cannot find the virtual environment

- Confirm that `.venv` exists in the project folder.
- Use **Python: Select Interpreter** and select `.venv\\Scripts\\python.exe`.
- Open a new terminal.

## 13. Important concepts

| Python term | Rough .NET comparison |
|---|---|
| `pip` | NuGet package manager |
| `requirements.txt` | A list of project package dependencies |
| `venv` | Isolated project environment for packages |
| `.py` | Python source file |
| `.ipynb` | Interactive notebook containing cells and outputs |
| `python file.py` | Run a Python script |

The comparisons are conceptual; Python environments and .NET project dependency management do not work exactly the same way.

## Official resources

- [Python for Windows downloads](https://www.python.org/downloads/windows/)
- [Python documentation](https://docs.python.org/3/)
- [Python tutorial in VS Code](https://code.visualstudio.com/docs/python/python-tutorial)
- [Jupyter project](https://jupyter.org/)
- [Jupyter documentation](https://docs.jupyter.org/)

---

**Recommended next step:** Once installation and verification are complete, practise Python basics—variables, data types, conditions, loops, functions, lists, dictionaries, and classes—before moving to AI libraries.
