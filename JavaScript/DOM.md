<h1 style="text-align: center;">HTML DOM</h1>

**What is DOM**
Document Object Model(DOM) It is a programming interface that allows us to create, change, or remove elements from the document. We can also add events to these elements to make our page more dynamic.

**HTML DOM tree**
The document structure of html is called DOM tree

## Target a html object

1. **console.log(document);**`
Return all document <html> to </html> includes <!doctype>

2. **console.log(document.all);**
Return all tag in a Array format

3. **console.log(document.title);**
Return title of the document

4. **console.log(document.body);**
Return all html which comes under body tag

5. **console.log(document.links);**
Return all anchor tag from document
**console.log(document.links[0]);** 
Return first position of anchor tag

6. **console.log(document.images);**
Return all used image from document

7. **console.log(document.form);**
Return Html form if found

8. **console.log(document.url);**
Return url of a website

9. **console.log(document.doctype);**
Return doctype name from document

10. **console.log(document.domain);**
Return domain name feom a website

11. **console.log(document.baseURI);**
Return a full url from a website

12. **console.log(document.getElementById("id"));**
Select specific element according given id
**console.log(document.getElementsByClassName("class"));**
Select specific element according given class name
**console.log(document.getElementByTagName("tag"));**
Select all matched tag

## DOM get Method
get attribute is used to get the name and value of Attributes.

## What we can get from HTML vie Get method
1. **HTML** 
innerHTML(its help to get HTML text and tag both)

2. **Text**
innerText(help to get only HTML text)

3. **Attribute**
- **getAttribute** (Return the value of attribute. ex-class name, id name ... etc)

```html
<div id="ids" class="myclass" style="1px:solid red">
Hello there
</div>
```

`console.log(document.getElementById("ids").getAttribute("class"));`
// Output  myclass

`console.log(document.getElementById("ids").getAttribute("style"););`
//Output  1px:solid red

- **attributes** 
Return a object of all atributes

`console.log(document.getElementById("ids").attributes;);`
// Outputid  "ids" class="myclass" style="1px:solid red"

`console.log(document.getElementById("ids").attributes[1];);`
// Output  class="myclass"

`console.log(document.getElementById("ids").attributes[1].value;);`
// Output  myclass

`console.log(document.getElementById("ids").attributes[1].name;);`
// Output  class


# DOM Set Method
Set attribute is used to set the name and value of Attributes.

1. **Set text value**
innerText- `.innerText="wow"`

2. **Set HTML Value**
innerHTML- `.innerHTML="<h1>wow</h1>"`

3. **SetAttribute**
SetAttribute can change the value of attribute
`console.log(document.getElementById("ids").Setattribut("class","xyz););`
//classname changed into xyz;

4. **Attributes**
Attribute can also set style of css/html (Its work like set attribute)
`console.log(document.getElementById("ids").attributes[1].value="hello";);`

5. **removeAttributes**
removeAttributes remove any attributes 
`console.log(document.getElementById("ids").removeAttributes("style"););`
//style will be removed


## QuerySelector
**DOM Targeting method**
Targetting Methods is used to get and set the value of a element/attributes. (This is the CSS Selector) 

1. **querySelector**
`document.querySelector(CSS Selector)`
// target only first element

```javascript
document.querySelector("#header").innerHTML="<h1>hello</h1>"
document.querySelector(".box").innerHTML="<h1>hello</h1>"
```

2. **querySelectorAll**
- `document.querySelectorAll(CSS Selector)`
// target all element

- `document.querySelectorAll(".list");`
// return all list class into array

- `document.querySelectorAll(".list").[1];`
// return 1st index list value ex:- <p>hii</p>

- `document.querySelectorAll(".list").[1].innerHTML;`
// return innerHTML of a ex hii

// we can also use advanced selector in querySelectorAll
`document.querySelectorAll("#header h1").[1].innerHTML;`

## CSS Styling Method

Using DOM CSS Styling Method we can get value of style and can change it

**get element**
`document.querySelector("#header").style.border;`
// output: 1px:solid:red

**Set element**
`document.querySelector("#header").style.backgroundColor="yellow;`
// background color changed to yellow

**Note**
background-color wrriten as backgroundColor ("-" removed, first capital)
https://www.youtube.com/watch?v=JeuPPUjY4kQ


## Add Event Listener
https://www.youtube.com/watch?v=NhRhrAziBRU


## CloneNode

## Contains

## hasAttribute & hasChildNode

## IsEqualNode