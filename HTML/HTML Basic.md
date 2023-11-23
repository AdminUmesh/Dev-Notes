# Basic HTML Document Structure:
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
```

1. `<!DOCTYPE html>`: This is the Document Type Declaration (DOCTYPE) for HTML5. It informs the browser that the document is written in HTML5, the latest version of HTML.

2. `<html lang="en">`: The `lang` attribute specifies the language of the document, and in this case, it is set to "en" for English.

3. `<head>`: The `<head>` element contains meta-information about the HTML document, such as the character set, viewport settings, and the title of the document.

   - `<meta charset="UTF-8">`: This meta tag sets the character encoding of the document to UTF-8, which is a widely used character encoding that supports a vast range of characters from various languages.
   
   - `<meta name="viewport" content="width=device-width, initial-scale=1.0">`: This meta tag is commonly used for responsive web design. It sets the viewport width to the device width and initial zoom level to 1.0, ensuring proper rendering on various devices.

   - `<title>Document</title>`: The `<title>` element sets the title of the HTML document, which is usually displayed in the browser's title bar or tab.

4. `<body>`: The `<body>` element contains the content of the HTML document, such as text, images, links, and other elements that make up the visible part of the webpage.


# Common HTML Tags:

1. **Headings:**
   - `<h1>` to `<h6>`: Represent different levels of headings.

2. **Paragraphs:**
   - `<p>`: Defines a paragraph.

3. **Links:**
   - `<a href="URL">Link Text</a>`: Creates a hyperlink.

4. **Images:**
   - `<img src="image.jpg" alt="Description">`: Embeds an image.

5. **Lists:**
   - `<ul>`: Unordered list.
   - `<ol>`: Ordered list.
   - `<li>`: List item.

6. **Tables:**
   - `<table>`: Defines a table.
   - `<tr>`: Defines a table row.
   - `<td>`: Defines a table cell.

7. **Forms:**
   - `<form>`: Defines a form.
   - `<input>`: Defines an input field.

8. **Semantic Tags:**
   - `<header>`, `<footer>`, `<nav>`, `<article>`, `<section>`, `<aside>`: Provide meaning to the structure.

### Attributes:

- Attributes provide additional information about HTML elements and are always included in the opening tag.
- Example: `<a href="https://www.example.com">Visit Example.com</a>`

### Comments:

- `<!-- This is a comment -->`: Comments are not displayed on the webpage but can be useful for leaving notes in the code.

### Important Concepts:

- **Elements:** The building blocks of HTML, defined by tags.
- **Attributes:** Provide additional information about elements.
- **Nested Elements:** Elements can be nested inside other elements.
- **Document Object Model (DOM):** A programming interface for web documents. It represents the structure of a document as a tree of objects.

This is a very basic overview, and there's a lot more to learn as you delve deeper into web development. HTML is often used in conjunction with CSS (Cascading Style Sheets) for styling and JavaScript for interactivity.

## Difference between semantic and non-semantic elements
**Semantic HTML elements:**

These elements simply mean, elements with meaning. The reason being, there definition in the code tells the browser and the developer what they are supposed to do. Framing in simpler words, these elements describe the type of content they are supposed to contain.

Following is the list of some semantic elements :

- article
- aside
- details
- figcaption
- figure
- footer
- form
- header
- main
- mark
- nav
- table
- section

**Non-Semantic elements:** 
Unlike, semantic elements they don’t have any meaning. They don’t tell anything about the content they contain. They can be used with different attributes to mark up semantics common to a group.

Following is the list of some non-semantic elements:

- div
- span

# Non-closed tags self-closing tags in HTML:

1. **`<img>`:**
   - Used to embed images.

   ```html
   <img src="image.jpg" alt="Description">
   ```

2. **`<br>`:**
   - Represents a line break.

   ```html
   <p>This is some text.<br>Next line of text.</p>
   ```

3. **`<hr>`:**
   - Represents a horizontal rule or line.

   ```html
   <hr>
   ```

4. **`<meta>`:**
   - Provides metadata about the HTML document.

   ```html
   <meta charset="UTF-8">
   ```

5. **`<input>`:**
   - Used to create various types of form controls.

   ```html
   <input type="text" name="username">
   ```

6. **`<link>`:**
   - Used to link external resources like stylesheets.

   ```html
   <link rel="stylesheet" href="styles.css">
   ```

7. **`<base>`:**
   - Specifies a base URL for all relative URLs in a document.

   ```html
   <base href="https://www.example.com/">
   ```

8. **`<col>`:**
   - Defines a column within a table and is often used within the `<colgroup>` element.

   ```html
   <col style="background-color:yellow">
   ```

9. **`<area>`:**
   - Defines an area inside an image map.

   ```html
   <map name="workmap">
      <area shape="rect" coords="34,44,270,350" alt="Computer" href="computer.htm">
   </map>
   ```

# Most Used
Sure, I'll address each of your points:

### Make a Field Required:

```html
<label for="username">Username:</label>
<input type="text" id="username" name="username" required>
```

### Show Hint in Input Field:

You can use the `placeholder` attribute to show a hint inside an input field. Here's an example:

```html
<label for="email">Email:</label>
<input type="email" id="email" name="email" placeholder="Enter your email address">
```

### Set Comment Field Max Value:

If by "comment field" you mean a textarea, you can use the `maxlength` attribute to set the maximum number of characters. Here's an example:

```html
<label for="comment">Comment:</label>
<textarea id="comment" name="comment" maxlength="200"></textarea>
```

### Create Table:

To create a simple table, you can use the `<table>`, `<tr>` (table row), `<td>` (table data), and `<th>` (table header) elements. Here's an example:

```html
<table>
  <tr>
    <th>Name</th>
    <th>Age</th>
  </tr>
  <tr>
    <td>John Doe</td>
    <td>25</td>
  </tr>
  <tr>
    <td>Jane Smith</td>
    <td>30</td>
  </tr>
</table>
```

### Create List:

You can create an ordered list (`<ol>`) or an unordered list (`<ul>`) with list items (`<li>`). Here's an example of an unordered list:

```html
<ul>
  <li>Item 1</li>
  <li>Item 2</li>
  <li>Item 3</li>
</ul>
```

### What is `<span>`:

`<span>` is an inline HTML element that is often used to apply styles to a specific portion of text or to group inline elements for styling purposes. It doesn't have any inherent semantic meaning and is typically used in conjunction with CSS.

<p>This is <span style="color: red;">red</span> text.</p>

```html
<p>This is <span style="color: red;">red</span> text.</p>
```

### H2O(water) and H2SO4:

```html
H<sub>2</sub>O
----------------
H<sub>2</sub>SO<sup>4</sup>
```