# Authentication

## What is authentication?
Authentication is the process of verifying the identity of a user, system, or application.

`Authentication is the process that companies use to confirm that only the right people, services, and apps with the right permissions can use organizational resources.`

**The authentication process includes three primary steps:**

1. **Identification:** Users establish who they are typically through a username.

2. **Authentication:** Typically, users prove they are who they say they are by entering a password (something only the user is supposed to know), but to strengthen security, many organizations also require that they prove their identity with something they have (a phone or token device) or something they are (fingerprint or face scan).

3. **Authorization:** The system verifies that the users have permission to the system that they’re attempting to access.

**Why is authentication important?**

Authentication is important because it helps organizations protect their systems, data, networks, websites, and applications from attacks. It also helps individuals keep their personal data confidential, empowering them to conduct business, such as banking or investing, online with less risk.

**How authentication works?**

For people, authentication involves setting up a username, password, and other authentication methods, such as a facial scan, fingerprint, or PIN. To protect identities, none of these authentication methods are saved to the service’s database. Passwords are hashed (not encrypted) and the hashes are saved to the database. When a user enters a password, the entered password is also hashed, then the hashes are compared. If the two hashes match, then access is granted. For fingerprints and facial scans, the information is encoded, encrypted, and saved on the device.

## Types of authentication methods

**Password-based authentication**
Password-based authentication is the most common form of authentication. Many apps and services require people to create passwords that use a combination of numbers, letters, and symbols to reduce the risk that a bad actor will guess them.

**Certificate-based authentication**
Certificate-based authentication is an encrypted method that enables devices and people to identify themselves to other devices and systems. Two common examples are a smart card or when an employee’s device sends a digital certificate to a network or server.

**Biometric authentication**
In biometric authentication, people verify their identity using biological features. For example, many people use their finger or thumb to sign in to their phones, and some computers scan a person’s face or retina to verify their identity. The biometric data is also linked to a specific device, so attackers can’t use them without also gaining access to the device. 

**Token-based authentication**
In token-based authentication both a device and the system generate a new unique number called a time-based one-time PIN (TOTP) every 30 seconds. If the numbers match, the system verifies that the user has the device.

**One-time password**
One-time passwords (OTP) are codes generated for a specific sign-in event that expire shortly after they’re issued. They are delivered via SMS messages, email, or a hardware token.

**Push notification**
Some apps and services use push notifications to authenticate users. In these instances, people receive a message on their phone asking them to approve or deny the access request. Because sometimes people accidentally approve push notifications even though they are trying to sign in to the services who sent the notification, this method is sometimes combined with an OTP method. With OTP the system generates a unique number that the user has to enter. This makes the authentication more phishing resistant.

**Voice authentication**
In voice authentication, the person trying to access a service receives a phone call, in which they’re asked to enter a code or identify themselves verbally.

**Multifactor authentication**
One of the best ways to cut down on account compromise is to require two or more authentication methods, which may include any of the previously listed methods. An effective best practice is to require any two of the following:

`For example, many organizations ask for a password (something the user knows) and also send an OTP via SMS to a trusted device (something the user has) before allowing access.`

**Two-factor authentication**
Two-factor authentication is a type of multifactor authentication that requires two forms of authentication.