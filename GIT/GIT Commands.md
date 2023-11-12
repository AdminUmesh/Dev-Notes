# <p style="text-align: center;">GIT Commands</p>

## Check git Version
```bash
git --version
```

## Check git Status
```bash
git status
```

## Check git branch
its show all available and selected branch
```bash
git branch
```

## Change git branch
```bash
git checkout 'another_branch_name'
```
```bash
git checkout origin 'another_branch_name'
```

## Create new branch
```bash
git branch 'branch_name'
```

## Add file Individual
```bash
git add filename
``` 

## Add All file
```bash
git add .
```

## GIT Commit
```bash
git commit -m "enter your message"
``` 

## GIT log
The git log command is used to display a log of commits in a Git repository. 
```bash
git log
``` 

## GIT Push
 Push changes from the local repository to a remote repository.
```bash
git push
``` 

```bash
git push origin branch_name
``` 

## GIT init
 it sets up all the necessary files and data structures needed for version control and `Creates Hidden Subdirectories and Files`
```bash
git init
``` 

## git remote add origin
The git remote add origin command is used to add a remote repository to your local Git repository.
```bash
git remote add origin <repository_url>
```

## git push -u origin 'branch_name'
The **-u** flag is often used the first time you push a branch to set up tracking. After the tracking relationship is established, you can simply use git pull and git push without specifying the remote and branch names:

**origin:** The name of the remote repository.

**master:** it's assumed that you are pushing the "master" branch.
```bash
git push -u origin master
```

## Merge in Master Branch
```bash
-> git checkout master
-> git merge 'another_branch_name'
-> git push -u origin master
```

## Remove git branch

**Only from local System**
```bash
git branch -d 'branch_name'
```
**From remote**
```bash
git push origin --delete 'branch_name'
```