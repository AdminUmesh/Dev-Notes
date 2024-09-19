# URL
A URL (Uniform Resource Locator), is a string of characters that provides the address used to access resources on the internet. It's essentially a web address that specifies the location of a resource.

**Here's a breakdown of the main components of a URL:**

1. **Scheme**: This indicates the protocol used to access the resource. Common schemes include "http://" for Hypertext Transfer Protocol, "https://" for Hypertext Transfer Protocol Secure, "ftp://" for File Transfer Protocol, etc.

2. **Domain Name**: This is the human-readable name that identifies the server hosting the resource. For example, in the URL "https://www.example.com/page", "www.example.com" is the domain name.

3. **Path**: This specifies the location of a specific resource on the server. In the URL "https://www.example.com/page", "/page" is the path.

4. **Query Parameters**: These are additional parameters provided to the resource, usually in the form of key-value pairs. They come after the path and are preceded by a question mark ("?"). For example, in the URL "https://www.example.com/search?q=example", the query parameter is "q" with a value of "example".

5. **Fragment Identifier**: This refers to a specific section within the resource. It comes after a hash symbol ("#"). For instance, in the URL "https://www.example.com/page#section1", "section1" is the fragment identifier.

Here's a more detailed example:

```
https://www.example.com:8080/path/to/resource?name=value#section
  |          |           |         |            |          |
  |          |           |         |            |    Fragment Identifier
  |          |           |         |     Query Parameters
  |          |           |         Path
  |          |           Port
  |          Domain Name
  Scheme
```

In this example:
- **Scheme:** "https"
- **Domain Name:** "www.example.com"
- **Port:** "8080" (optional, default is typically 80 for HTTP and 443 for HTTPS)
- **Path:** "/path/to/resource"
- **Query Parameters:** "name=value"
- **Fragment Identifier:** "section"

## HTTP, HTTPS, and FTP
These are three different protocols used for communication over computer networks, particularly on the internet. Each protocol serves specific purposes:

1. **HTTP (Hypertext Transfer Protocol):**
   - **Purpose:** HTTP is the foundation of data communication on the World Wide Web. It is used for transferring hypertext, which can include text, images, videos, links, and other multimedia content.
   - **Port:** The default port for HTTP is 80.

2. **HTTPS (Hypertext Transfer Protocol Secure):**
   - **Purpose:** HTTPS is a secure version of HTTP. It uses encryption (typically SSL/TLS) to secure the data transmitted between the client and the server. It is commonly used for secure communication over the internet, especially for online transactions and sensitive information.
   - **Port:** The default port for HTTPS is 443.

3. **FTP (File Transfer Protocol):**
   - **Purpose:** FTP is a protocol used for transferring files between computers on a network. It allows users to upload or download files from or to their computers or servers.
   - **Port:** FTP typically uses two channels: a command channel (usually port 21) for sending commands and receiving responses, and a data channel for transferring files. The data channel can use either active or passive mode, and the ports for the data channel depend on the mode used.

**In summary**, `HTTP is used for general web communication, HTTPS adds a layer of security through encryption, and FTP is specifically designed for file transfer.` The "s" in HTTPS stands for "secure," and it ensures that the data exchanged between the user and the website is encrypted, providing a higher level of security compared to HTTP.