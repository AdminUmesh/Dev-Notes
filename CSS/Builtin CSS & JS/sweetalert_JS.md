**Site Url:**
- https://sweetalert.js.org/

**CDN**
```js
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
```

**Ex:-1**
hello world alert
```js
swal("Here's the title!", "...and here's the text!");
```

**Ex:-2**
updated successfully
```js
swal("Good job!", "You clicked the button!", "success");
```

**Ex:-3**
are you sure want to delete
```js
swal("Click on either the button or outside the modal.")
.then((value) => {
  swal(`The returned value is: ${value}`);
});
```