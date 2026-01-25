
# Check Battery Health in Windows (Using powercfg)

This guide explains **how to generate a battery report** in Windows and **calculate battery health percentage**.

---

## 🔋 What is Battery Health?
Battery health shows how much capacity your battery can hold **now** compared to when it was **new**.

- **Design Capacity** → Original battery capacity
- **Full Charge Capacity** → Current maximum charge capacity

---

## 🛠️ Step 1: Open Command Prompt (Admin)

1. Press **Win + X**
2. Click **Terminal (Admin)** or **Command Prompt (Admin)**

---

## 🛠️ Step 2: Generate Battery Report

Run this command:

```
powercfg /batteryreport
```

You will see a message like:
```
Battery life report saved to:
C:\Users\YourName\battery-report.html
```

---

## 📂 Step 3: Open Battery Report

1. Open **File Explorer**
2. Go to:
   ```
   C:\Users\YourName\battery-report.html
   ```
3. Double-click the file
4. It will open in your web browser

---

## 🔍 Step 4: Find Battery Capacity Values

In the report, scroll to **Installed batteries** section.

You will see:

- **DESIGN CAPACITY**
- **FULL CHARGE CAPACITY**

Example:
```
Design Capacity:      50,000 mWh
Full Charge Capacity: 42,000 mWh
```

---

## 🧮 Step 5: Calculate Battery Health %

### Formula:
```
Battery Health % = (Full Charge Capacity ÷ Design Capacity) × 100
```

### Example calculation:
```
Battery Health % = (42,000 ÷ 50,000) × 100
Battery Health % = 84%
```

---

## 📊 Battery Health Interpretation

| Battery Health | Condition |
|--------------|----------|
90–100% | Excellent (Like new) |
80–89% | Good |
65–79% | Fair |
Below 65% | Poor (Consider replacement) |

---

## ⚠️ Important Notes
- Battery health drops naturally over time
- Heat and overcharging reduce battery life
- This report works on **laptops only**
- Desktop PCs will not show battery data

---

## 🔁 How Often to Check
- Every **3–6 months**
- After noticing fast battery drain

---

## ✅ Summary
- `powercfg /batteryreport` generates battery report
- Report is saved as an **HTML file**
- Use capacity values to calculate health
- Battery Health % helps decide replacement timing

---

📌 Tip: Keep battery between **20% – 80%** for longer lifespan
