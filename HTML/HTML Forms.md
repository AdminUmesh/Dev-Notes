-----------------------------------------------------------------------------------------------------
|                                              HTML FORM                                            |
-----------------------------------------------------------------------------------------------------
The HTML <form> element is used to create an HTML form for user input:

**<input> Element**
>>> An <input> element can be displayed in many ways, depending on the type attribute.

<input type="text">	
<input type="radio">	
<input type="checkbox">	
<input type="submit">	
<input type="button">

**<label> Element**
The <label> element is useful for screen-reader users, because the screen-reader will read out loud the label when the user focuses on the input element.

<form>
  <input type="radio" id="html" name="fav_language" value="HTML">
  <label for="html">HTML</label><br>
  <input type="radio" id="css" name="fav_language" value="CSS">
  <label for="css">CSS</label><br>
  <input type="radio" id="javascript" name="fav_language" value="JavaScript">
  <label for="javascript">JavaScript</label>
</form>


-----------------------------------------------------------------------------------------------------
|                                             FORM ATTRIBUTE                                        |
-----------------------------------------------------------------------------------------------------
**Action Attribute**
The action attribute defines the action to be performed when the form is submitted.
<form action="/action_page.php">

**Target Attribute**
The target attribute specifies where to display the response that is received after submitting the form. default value is _self which means that the response will open in the current window.

"_blank"	    The response is displayed in a new window or tab
"_self"	        The response is displayed in the current window
"_parent"	    The response is displayed in the parent frame
"_top"	        The response is displayed in the full body of the window
"framename"	    The response is displayed in a named iframe

<form action="/action_page.php" target="_blank">

**Method Attribute**
>>> The method attribute specifies the HTTP method to be used when submitting the form data. The form-data can be sent as URL variables (with method="get") or as HTTP post transaction (with method="post").

>>> The default HTTP method when submitting form data is GET. 
<form action="/action_page.php" method="get/post">

**Autocomplete Attribute**
When autocomplete is on, the browser automatically complete values based on values that the user has entered before.
<form action="/action_page.php" autocomplete="on">

**Novalidate Attribute**
>>> When present, it specifies that the form-data (input) should not be validated when submitted. The novalidate attribute is a boolean attribute.

-----------------------------------------------------------------------------------------------------
|                                           HTML FORM ELEMENT                                       |
-----------------------------------------------------------------------------------------------------
<input>   Used to take input from user. Depending on the type attribute.
<label>
<select>  The <select> element defines a drop-down list:

<option> The <option> elements defines an option that can be selected. By default, the first item in the drop-down list is selected.
<label for="cars">Choose a car:</label>
<select id="cars" name="cars">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="fiat">Fiat</option>
  <option value="audi">Audi</option>
</select>

<multiple> multiple attribute to allow the user to select more than one value in dropdown list:
<textarea> The <textarea> element defines a multi-line input field (a text area)
<button>  The <button> element defines a clickable button:
<fieldset> The <fieldset> element is used to group related data in a form
<legend> The <legend> element defines a caption for the <fieldset> element.
<datalist> https://www.w3schools.com/html/html_form_elements.asp
<output> https://www.w3schools.com/html/html_form_elements.asp
<optgroup> https://www.w3schools.com/html/html_form_elements.asp


-----------------------------------------------------------------------------------------------------
|                                            HTML INPUT TYPES                                       |
-----------------------------------------------------------------------------------------------------

Here are the different input types you can use in HTML:

<input type="button">
<input type="checkbox">
<input type="color">
<input type="date">
<input type="datetime-local">
<input type="email">
<input type="file">
<input type="hidden">
<input type="image">
<input type="month">
<input type="number">
<input type="password">
<input type="radio">
<input type="range">
<input type="reset">
<input type="search">
<input type="submit">
<input type="tel">
<input type="text">
<input type="time">
<input type="url">
<input type="week">


-----------------------------------------------------------------------------------------------------
|                                         HTML INPUT ATTRIBUTES                                     |
-----------------------------------------------------------------------------------------------------

value
readonly
desabled
size
maxlength
min
max
multiple
pattern
placeholder
required
step
autofocus
hight
width
list
autocomlete


-----------------------------------------------------------------------------------------------------
|                                      HTML INPUT FORM ATTRIBUTES                                   |
-----------------------------------------------------------------------------------------------------