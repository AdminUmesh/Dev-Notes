# This Keyword in global

The global object is window. in javaScript for browser

```js
console.log(this); //window.global
//it return global object that is window
```

# Inside a function (in strict mode)
```js
*use strict*;

functtion x(){
    console.log(this);
     //undefine
}
```

# Inside a function (in non-strict mode)
```js
functtion x(){
    console.log(this);
}
x(); //window object
window.x() //window object
```

**Note**
if the value of this keyword is undefine or null this keyword is replaced wih globalobject only in non strict mode

**this keywords value depends on how a funcion is called.**

```js
const obj={
    a: 10,
    x: function(){
        console.log(this);
    },
};
obj.x();
//{a: 10, x: ƒ}  because this will reference to object 

const obj={
    a: 10,
    x: function(){
        console.log(this.a);
    },
};
obj.x();
//10  because this will reference to object of a
```

# Override the value of this keyword
```js
const student={
    name: "umeesh",
    printname: function(){
        console.log(this.name);
    },
};

const student2={
    name: "pooja",
};

student.printname.call(student2);
// pooja
```
**Note**
the value of this can be modified using call() apply() and bind() method

# this inside arrow function
Example:-1
```js
//the value of this under arrow functtion is not the current object but it is enclosing lexical context
const obj={
    a: 10,
    x:() => {
        console.log(this);
        //it is behave like it is present in global space it in a function
    },
};
obj.x();
// window
```

Example:-2
```js
const obj2={
    a: 10,
    x: function(){
        const y = () =>{
            console.log(this);
        }
        y();
    },
};
obj2.x();
// {a: 10, x: ƒ}
//because the enclosing lexical context is x function here
```

# call() apply() and bind() method
???
