# Git Initialization Guide (Windows)

## 🧩 Step 1: Install Git
Download Git for Windows:

👉 [Download Git](https://git-scm.com/download/win)

Run the installer and follow the default setup instructions.

## ✅ Step 2: Verify Installation
After installation, open Git Bash or your terminal and run:

```bash
git --version
```
This should output the installed Git version.

## ⚙️ Step 3: Basic Git Configuration
Configure Git with your personal details and preferences:

1. Set your name:
   ```bash
   git config --global user.name "Your Name"
   ```

2. Set your email:
   ```bash
   git config --global user.email "your_email@example.com"
   ```

3. Set the default branch name (recommended: main):
   ```bash
   git config --global init.defaultBranch main
   ```

## 🔍 Step 4: Check Your Configuration
View global Git settings:
```bash
git config --list
```

View setting sources:
```bash
git config --list --show-origin
```

## 🛠️ Step 5: Start Using Git
Here are some common Git commands to get started:

```bash
git init                   # Initialize a new Git repository
git clone <repo_url>       # Clone an existing repository
git add <file>             # Stage a file for commit
git commit -m "message"    # Commit changes with a message
git push                   # Push changes to remote
git pull                   # Pull changes from remote
```

## 🔐 Authentication
When you push code for the first time, Git may prompt for your login credentials. For convenience and security, consider setting up:

- GitHub CLI or SSH keys for authentication
- Git Credential Manager (included in Git for Windows)

## 🎉 You're All Set!
You’re now ready to use Git for version control and collaboration. Happy coding! 💻
