The standard and safest way to test antivirus integration is with the EICAR test file.

- EICAR is not a real virus. It's a harmless test string recognized by antivirus products as malware for testing purposes.

### Create an EICAR Test File
1. Open Notepad.
2. Paste this exact single line:
```bash
X5O!P%@AP[4\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*
```
3. Save it as: `eicar.com` or `eicar.txt`

**Make sure:**

Save as type = All Files
Encoding = ANSI or UTF-8

### Before Save
**For testing only:**

#### Open Windows Security
**Go to:** Virus & threat protection
**Then** Manage settings
**Then** Exclusions
**Click** Add an exclusion

#### Create a folder like:
```bash
C:\AntivirusTest
```

`Add it as an exclusion.`

**Then create:** `C:\AntivirusTest\eicar.com`