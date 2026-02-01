# Unallocated Drive Recovery – FREE (TestDisk Guide)

This markdown note is for **Windows users** whose hard drive is **NOT showing in File Explorer** and appears as **Unallocated** in Disk Management.

---

## ✅ WHEN TO USE THIS GUIDE

Proceed **ONLY IF**:
- Disk is visible in **Disk Management** by correct size
- Status shows **Unallocated**

❌ Do NOT use this if disk does not appear at all

---

## 🚫 VERY IMPORTANT – DO NOT DO THESE

- ❌ Do NOT format the drive
- ❌ Do NOT initialize disk
- ❌ Do NOT create new volume
- ❌ Do NOT run CHKDSK
- ❌ Do NOT copy files unless partition restore fails

---

## 🧰 TOOL REQUIRED (100% FREE)

**TestDisk** (by CGSecurity)
- Open‑source
- No file‑size limit
- Restores lost partitions with original data

Download (official):
https://www.cgsecurity.org/wiki/TestDisk_Download

---

## 🪜 STEP‑BY‑STEP RECOVERY PROCESS

### STEP 1: Open TestDisk
1. Extract the downloaded ZIP file
2. Right‑click **testdisk_win.exe** → Run as Administrator
3. Select **Create** (log)

---

### STEP 2: Select Disk
1. Select the disk by **correct size**
2. Press **Enter**
3. Partition type → **Intel / PC** (most Windows systems)

---

### STEP 3: Analyze Disk
1. Select **Analyse**
2. Press **Enter** on **Quick Search**

---

### STEP 4: Preview Files (MOST IMPORTANT STEP)

When a partition appears:
- Select it
- Press **P** to preview files

✅ If folders/files look correct → continue
❌ If files look incorrect → STOP

---

### STEP 5: Exit Preview (DO NOT COPY FILES)

- Press **Q** to exit preview screen

❌ Do NOT press:
- **C** (copy)
- **A** (select all)

---

### STEP 6: Set Partition Type

Back on partition list:
- Use **Left / Right Arrow keys**
- Set partition type to:
  - **P = Primary** (recommended)

---

### STEP 7: WRITE PARTITION (FINAL STEP)

1. Press **Enter**
2. Select **Write**
3. Press **Y** to confirm
4. Exit TestDisk
5. **Restart your PC**

---

## 🎉 EXPECTED RESULT AFTER RESTART

- Drive letter restored
- Files and folders intact
- No paid software used

---

## 🟡 IF QUICK SEARCH FINDS NOTHING

1. Choose **Deeper Search**
2. Wait (15–60 minutes)
3. Same rules:
   - **P** to preview
   - Files OK → **Write**

---

## 🔁 LAST OPTION (ONLY IF PARTITION CANNOT BE RESTORED)

Use **PhotoRec** (also free):
- Recovers files even without partition
- File names may be changed

---

## 🧠 QUICK TROUBLESHOOTING

- Partition written but drive not visible?
  - Assign drive letter using Disk Management

- Disk becomes RAW?
  - Stop and re‑run TestDisk

---

## ✅ SUMMARY

- Unallocated drive = recoverable
- Preview works = partition restorable
- Writing partition is better than copying files

---

📌 Keep this `.md` file for offline recovery reference

