# Events in js
In JavaScript, events are things that happen in the browser, like a user clicking a button or typing on the keyboard. JavaScript can react to these events using event handlers, allowing web pages to be interactive.`For example, when you click a button, JavaScript can show a message or change something on the page.`

# Types of Events

## Mouse Events:

- **click:** Triggered when a mouse button is clicked on an element.
- **dblclick:** Triggered when a mouse button is double-clicked.
- **mouseover:** Triggered when the mouse pointer hovers over an element.
- **mouseout:** Triggered when the mouse pointer leaves an element.
- **mousemove:** Triggered when the mouse is moved within an element.
- **mousedown:** Triggered when the mouse button is pressed.
- **mouseup:** Triggered when the mouse button is released.

## Keyboard Events:

- **keydown:** Triggered when a key is pressed down.
- **keyup:** Triggered when a key is released.
- **keypress:** Triggered when a key is pressed and then released (deprecated).

## Focus Events:

- **focus:** Triggered when an element gains focus (e.g., an input field is clicked).
- **blur:** Triggered when an element loses focus.

## Form Events:

- **submit:** Triggered when a form is submitted.
- **change:** Triggered when the value of an input, select, or textarea element changes.
- **input:** Triggered when the user types or inputs text.
- **reset:** Triggered when a form is reset.

## Window Events:

- **load:** Triggered when the page and all its resources (images, scripts) have fully loaded.
- **resize:** Triggered when the window is resized.
- **scroll:** Triggered when the user scrolls in a page or element.

## Touch Events (for mobile devices):

- **touchstart:** Triggered when the user touches the screen.
- **touchmove:** Triggered when the user moves their finger across the screen.
- **touchend:** Triggered when the user stops touching the screen.
- **touchcancel:** Triggered when the touch event is interrupted.

## Other Events:

- **drag:** Triggered during a drag operation.
- **drop:** Triggered when an item is dropped.
- **contextmenu:** Triggered when the right mouse button is clicked (to open a context menu).

# Event Handling in JavaScript
There are three main ways to handle events in JavaScript:

### 1. Inline Event Handlers
You can add event handlers directly to HTML elements using attributes like onclick, onmouseover, etc.

```html
<button onclick="alert('Button clicked!')">Click me</button>
```

### 2. Using addEventListener()
A more flexible and modern way to handle events is by using addEventListener() in JavaScript.

```html
<button id="myButton">Click me</button>

<script>
  const button = document.getElementById('myButton');
  button.addEventListener('click', function() {
    alert('Button clicked!');
  });
</script>
```

### 3. Using `on<Event>` Properties
You can also set event handlers by directly assigning a function to an event property.

```html
<button id="myButton">Click me</button>

<script>
  const button = document.getElementById('myButton');
  button.onclick = function() {
    alert('Button clicked!');
  };
</script>
```

# Event Object
When an event occurs, an event object is automatically passed to the event handler. This object contains information about the event, such as which element triggered the event and which key was pressed.

```html
<button id="myButton">Click me</button>

<script>
  const button = document.getElementById('myButton');

  button.addEventListener('click', function(event) {
    console.log(event.type);  // Logs the event type (e.g., "click")
    console.log(event.target);  // Logs the target element (the button)
  });
</script>
```
**target:** The element that triggered the event (e.g., the button that was clicked).

**type:** The type of event that occurred (e.g., "click", "keydown").

**key:** For keyboard events, the key that was pressed (e.g., "Enter", "a").

**clientX / clientY:** The coordinates of the mouse pointer when a mouse event occurred.

**preventDefault():** A method to prevent the default behavior (e.g., prevent form submission or link navigation).

### preventDefault()
The  method is used in JavaScript to prevent the default behavior of an event from occurring. This is useful when you want to take control of what happens during the event, such as preventing a form from submitting or stopping a link from navigating to a new page.

**Common Use Cases for preventDefault():**

- **Prevent form submission:**  When a user submits a form, the browser usually reloads the page or navigates away. preventDefault() can stop this.

- **Prevent link navigation:** Clicking on a link usually takes you to a new page. You can stop this behavior using preventDefault().

- **Prevent text selection:** Preventing users from selecting text in certain situations.

**Example 1: Preventing Form Submission**

By default, submitting a form will cause the page to reload. If you want to handle the form submission with JavaScript (e.g., to validate the form before submitting), you can use preventDefault() to stop the form from submitting.

```html
<form id="myForm">
  <input type="text" id="name" placeholder="Enter your name">
  <button type="submit">Submit</button>
</form>

<script>
    // here you can validate page without submit the form
  const form = document.getElementById('myForm');

  form.addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the default form submission behavior

    const name = document.getElementById('name').value;
    console.log('Form submitted with name:', name);

    // Here, you can perform your own form handling (e.g., validation, AJAX request, etc.)
  });
</script>
```

## Event Propagation
Events can propagate through the DOM in two ways:

**Bubbling (default):** The event starts from the target element and bubbles up through the ancestors.

**Capturing:** The event starts from the root and propagates down to the target element.

`You can control event propagation using stopPropagation() and stopImmediatePropagation() methods.`

**Example of event propagation:**
```html
<div id="parent">
  <button id="child">Click me</button>
</div>

<script>
  const parent = document.getElementById('parent');
  const child = document.getElementById('child');

  parent.addEventListener('click', function() {
    alert('Parent clicked!');
  });

  child.addEventListener('click', function(event) {
    alert('Child clicked!');
    event.stopPropagation();  // Stops the event from bubbling up to the parent
  });
</script>
```

# Event Delegation
Instead of attaching an event listener to each child element, you can delegate the event to a parent element. This improves performance, especially for dynamically added elements.

```html
<ul id="myList">
  <li>Item 1</li>
  <li>Item 2</li>
  <li>Item 3</li>
</ul>

<script>
  const list = document.getElementById('myList');
  list.addEventListener('click', function(event) {
    if (event.target.tagName === 'LI') {
      alert('List item clicked: ' + event.target.textContent);
    }
  });
</script>
```
