<h1 style="text-align: center;">CSS SELECTORS</h1>
CSS Selector tell the browser to which HTML element should be selected for the CSS property.

# Simple Selectors

## Type Selector
CSS type selector matches element by node name
```css
p{
    color:red;
}
```

## ID Selector
CSS type selector matches element by specific id attribute

```css
#ids{
    color:green;
}
```

## Class Selector
CSS type selector matches element by specific class attribute

```css
.box{
    color:green;
}
```

## Universal Selector
The artristic(*) is the known as CSS universal selector. it can be used to select any and all types of element in HTML page.

```css
*{
    color:red;
}

// all tags colour will be red
```
```css
#myid *{
    color:green;
}

// all property of myid's color will be green
```

# Combinators SELECTORS
Combinator Selector can select multiple child and multiple parent seletor

## Descendant Selector## 
We can select multiple child and multiple parent seletor

```css
.box p{
    color:red;
}

// select all p which is under box class
```

```css
p span{
    color:green;
}

// Selected all span which under p tag
```

```css
div p span{
    color:blue;
}

// Selected all span which under (p and div) tag
```

## Direct Child Selector or Child Selector## 
Select Direct Sibling of a selector

```css
#list > li{
    color:blue;
}

// select all li tag which first sibling of list tag or (which is direct sibling of list tag)
```

## Adjacent Sibling Selector

```css
#list + li{
    color:blue;
}

// select only first li sibling which comes after list
```

## General Sibling Selector

```css
#list ~ li{
    color:blue;
}

// select all li sibling which comes after list
```

# Attributes SELECTORS
Select HTML element with specific aattributes and with its value.

## A[attr]
Element with specific attribute

```css
img [title]{
    border:3px solid red;
}

// applied on those img tag which used attribute as title
```

## A[attr=val]
attribute with value
```css
img [title='flower']{
    border:3px solid red;
}

// applied on those img tag which used attribute as title="flower"
```

## A[attr^=val]
attribute begins with value

```css
img [title^='flower']{
    border:3px solid red;
}

// applied on those all img tag which title attribute start with flower (ex=> title="folower of", title="folower is red")
```

## A[attr$=val]
attribute end with value

```css
img [title$='red']{
    border:3px solid red;
}

// applied on those all img tag which title attribute with red (ex=> title="folower is red", title="this is red")
```

## A[attr*=val]
value occures anywhere in attribute

```css
img [alt*='red']{
    border:3px solid red;
}

// applied on those all img tag which alt attribute occures anywhere red (ex=> alt="folower is red", alt="red is best", alt="dark red not allowed")
```

## A[attr~=val] 
value matches attribute with space seperated list (means any value which start and end with space)


# Pseudo-Classes Selectors
- [Click here](https://www.youtube.com/watch?v=RqTXyLThQsw)
