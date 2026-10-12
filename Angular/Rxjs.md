# 1. RxJS

**RxJS (Reactive Extensions for JavaScript)** is a library for working
with asynchronous data using **Observables** and operators.

In Angular, RxJS is commonly used for: - HTTP requests with
`HttpClient` - Handling user input and events - Sharing data between
components through services - Managing streams of values over time

``` ts
import { Observable } from 'rxjs';
```

## 2. What is an Observable?

An **Observable** represents a stream of values that can arrive over
time. It may emit: - zero or more values - an error - a completion
notification

Example:

``` ts
import { Observable } from 'rxjs';

const numbers$ = new Observable<number>((subscriber) => {
  subscriber.next(1);
  subscriber.next(2);
  subscriber.next(3);
  subscriber.complete();
});
```

Here, `numbers$` is an Observable. It describes how values will be
delivered when someone subscribes.

> Naming convention: developers often end Observable variables with `$`,
> such as `numbers$` or `employees$`. This is a convention, not a
> requirement.

## 3. What does `subscribe()` do?

`subscribe()` registers handlers to receive an Observable's values,
errors, and completion notification. For a typical Angular `HttpClient`
request, subscribing starts the request.

``` ts
this.employeeService.getEmployees().subscribe({
  next: (employees) => {
    console.log(employees);
  },
  error: (error) => {
    console.error('Request failed', error);
  },
  complete: () => {
    console.log('Stream completed');
  }
});
```

The three handlers are: - `next`: called when a value is emitted -
`error`: called if the stream errors; normally no further values are
delivered - `complete`: called when the stream finishes normally

For a normal HTTP response, Angular's `HttpClient` Observable usually
emits the response and then completes.

## 4. What does "emit" mean?

To **emit** means to send or deliver a value through an Observable.

``` ts
import { Observable } from 'rxjs';

const message$ = new Observable<string>((subscriber) => {
  subscriber.next('Hello');
  subscriber.next('Welcome to Angular');
  subscriber.complete();
});

message$.subscribe({
  next: (message) => console.log(message),
  complete: () => console.log('Done')
});
```

Output:

``` text
Hello
Welcome to Angular
Done
```

In this example: - `subscriber.next(value)` emits a value. -
`subscriber.error(error)` ends the stream with an error. -
`subscriber.complete()` signals normal completion.

You will often hear "the Observable emits a value" and "the subscriber
receives the value."

## 5. Observable vs. Observer vs. Subscription

These names sound similar but mean different things.

  -----------------------------------------------------------------------
  Term                                Meaning
  ----------------------------------- -----------------------------------
  Observable                          The source that can deliver values
                                      over time

  Observer                            The handlers that react to `next`,
                                      `error`, and `complete`

  `subscribe()`                       Connects the observer to the
                                      Observable

  Subscription                        The object returned by
                                      `subscribe()`; can be used to
                                      unsubscribe
  -----------------------------------------------------------------------

Example:

``` ts
const subscription = numbers$.subscribe({
  next: (value) => console.log(value),
  error: (err) => console.error(err),
  complete: () => console.log('Finished')
});

// If needed, stop listening:
subscription.unsubscribe();
```

For a synchronous Observable that has already completed, unsubscribing
afterward has no practical effect. Unsubscribe when you need to stop an
ongoing stream.

## 6. How Angular HttpClient uses Observables

A typical service method:

``` ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AssistantRequest {
  question: string;
}

export interface AssistantResponse {
  answer: string;
}

@Injectable({ providedIn: 'root' })
export class AssistantService {
  private apiUrl = 'https://localhost:7028/api/analytics';

  constructor(private http: HttpClient) {}

  ask(request: AssistantRequest): Observable<AssistantResponse> {
    return this.http.post<AssistantResponse>(
      `${this.apiUrl}/ask`,
      request
    );
  }
}
```

What each part means:

-   `request: AssistantRequest`: the request object's expected type.
-   `Observable<AssistantResponse>`: the method returns an Observable
    that can emit an `AssistantResponse`.
-   `http.post<AssistantResponse>(url, request)`: sends a POST request
    with `request` as the body and declares the expected response type.
-   The generic type helps TypeScript with type checking; it does
    **not** validate the server's JSON at runtime.
-   The HTTP call generally begins when the returned Observable is
    subscribed to.

Component example:

``` ts
this.assistantService.ask({ question: 'How many leads do we have?' })
  .subscribe({
    next: (response) => {
      console.log(response.answer);
    },
    error: (error) => {
      console.error('Could not get an answer', error);
    }
  });
```

## 7. Why can't the return type simply be `AssistantResponse`?

This is incorrect:

``` ts
ask(request: AssistantRequest): AssistantResponse {
  return this.http.post<AssistantResponse>(
    `${this.apiUrl}/ask`,
    request
  );
}
```

`HttpClient.post()` returns an `Observable<AssistantResponse>`, not the
final response object immediately. The server response arrives
asynchronously.

Use:

``` ts
ask(request: AssistantRequest): Observable<AssistantResponse> {
  return this.http.post<AssistantResponse>(
    `${this.apiUrl}/ask`,
    request
  );
}
```

Or convert to a Promise if you prefer `async/await`:

``` ts
import { firstValueFrom } from 'rxjs';

async ask(request: AssistantRequest): Promise<AssistantResponse> {
  return firstValueFrom(
    this.http.post<AssistantResponse>(
      `${this.apiUrl}/ask`,
      request
    )
  );
}
```

Then:

``` ts
async sendQuestion(): Promise<void> {
  const response = await this.assistantService.ask({
    question: 'How many leads do we have?'
  });

  console.log(response.answer);
}
```

## 8. Common RxJS operators

Operators transform, filter, or combine values in an Observable stream.
They are usually used with `.pipe()`.

### `map()`

Transforms each emitted value.

``` ts
import { map } from 'rxjs/operators';

this.assistantService.ask(request).pipe(
  map(response => response.answer)
).subscribe(answer => {
  console.log(answer);
});
```

For modern RxJS, importing operators from `rxjs` is also supported:

``` ts
import { map } from 'rxjs';
```

### `tap()`

Performs a side effect, such as logging, without changing the emitted
value.

``` ts
import { tap } from 'rxjs';

this.assistantService.ask(request).pipe(
  tap(response => console.log('API response:', response))
).subscribe(response => {
  console.log(response.answer);
});
```

### `catchError()`

Handles an error and can return a fallback Observable.

``` ts
import { catchError, of } from 'rxjs';

this.assistantService.ask(request).pipe(
  catchError(error => {
    console.error(error);
    return of({ answer: 'Sorry, something went wrong.' });
  })
).subscribe(response => {
  console.log(response.answer);
});
```

Ensure the fallback object matches your response type.

### `debounceTime()`

Waits until values stop arriving for a specified duration. Useful for
search boxes.

``` ts
import { debounceTime, distinctUntilChanged } from 'rxjs';

searchControl.valueChanges.pipe(
  debounceTime(300),
  distinctUntilChanged()
).subscribe(value => {
  console.log('Search for:', value);
});
```

### `switchMap()`

Maps each source value to a new Observable and switches to the latest
one. Useful for search requests where newer input should replace older
requests.

``` ts
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';

searchControl.valueChanges.pipe(
  debounceTime(300),
  distinctUntilChanged(),
  switchMap(value => this.employeeService.searchEmployees(value ?? ''))
).subscribe(results => {
  this.employees = results;
});
```

Unsubscribing from the previous inner Observable can cancel an
in-progress Angular HTTP request when a newer search arrives.

## 9. What is `pipe()`?

`pipe()` lets you chain RxJS operators to process values before
subscribing.

``` ts
source$.pipe(
  map(value => value * 2),
  tap(value => console.log(value))
).subscribe();
```

The source emits a value, `map` transforms it, `tap` logs it, and the
subscriber receives the transformed value.

## 10. What is `of()`?

`of()` creates an Observable that emits the provided value or values and
then completes.

``` ts
import { of } from 'rxjs';

of('Angular', 'RxJS').subscribe(value => console.log(value));
```

Output:

``` text
Angular
RxJS
```

This is useful for examples, test data, and simple fallback values.

## 11. What is `from()`?

`from()` converts supported inputs, such as an array or Promise, into an
Observable.

``` ts
import { from } from 'rxjs';

from([10, 20, 30]).subscribe(value => console.log(value));
```

It emits `10`, then `20`, then `30`.

## 12. What does `unsubscribe()` do?

`unsubscribe()` stops a subscription from receiving further
notifications and may clean up resources.

``` ts
const subscription = someLongRunningStream$.subscribe(value => {
  console.log(value);
});

subscription.unsubscribe();
```

Angular `HttpClient` Observables usually complete after one response, so
manual unsubscription is generally unnecessary for a simple one-off
request. Long-running streams (for example, event streams) need more
careful cleanup. In components, Angular's `async` pipe or
lifecycle-aware RxJS patterns can help manage subscriptions.

## 13. What is the `async` pipe?

The Angular `async` pipe subscribes to an Observable or Promise in the
template, exposes its latest value, and automatically unsubscribes when
the view is destroyed or the expression changes.

Component:

``` ts
employees$ = this.employeeService.getEmployees();
```

Template:

``` html
<ul>
  <li *ngFor="let employee of employees$ | async">
    {{ employee.name }}
  </li>
</ul>
```

Make sure the component has the appropriate imports for `CommonModule`
or the relevant standalone directives.

## 14. Observable vs. Promise

  -----------------------------------------------------------------------
  Observable                          Promise
  ----------------------------------- -----------------------------------
  Can emit multiple values over time  Resolves once or rejects once

  Supports RxJS operators             Uses `.then()`, `.catch()`, and
                                      `async/await`

  Can be unsubscribed from            A standard Promise has no built-in
                                      cancellation method

  Common in Angular `HttpClient`      Useful when a single async result
                                      fits the task
  -----------------------------------------------------------------------

A typical HTTP request Observable emits one response and completes, but
the Observable type itself supports streams with multiple values.

## 15. Quick interview revision

-   **RxJS:** Library for reactive programming with asynchronous data
    streams.
-   **Observable:** Represents values that may arrive over time.
-   **subscribe():** Registers handlers and, for typical HTTP
    Observables, starts the request.
-   **emit:** Deliver a value through a stream.
-   **Observer:** Handlers for `next`, `error`, and `complete`.
-   **Subscription:** Returned by `subscribe()`; can be unsubscribed.
-   **`next()`:** Emits a value from an Observable producer.
-   **`complete()`:** Signals that no more values will be emitted.
-   **`pipe()`:** Chains RxJS operators.
-   **`map()`:** Transforms each value.
-   **`tap()`:** Performs a side effect without transforming the value.
-   **`catchError()`:** Handles errors in a stream.
-   **`switchMap()`:** Switches to the latest inner Observable.
-   **`async` pipe:** Subscribes in the template and manages cleanup.

## 16. A simple mental model

Think of an Observable as a delivery service:

1.  **Observable:** The delivery process that can provide data.
2.  **`subscribe()`:** You register to receive the delivery.
3.  **`next(value)`:** A package (value) arrives.
4.  **`error(error)`:** Delivery fails.
5.  **`complete()`:** The delivery process is finished.
6.  **Subscription:** Your active registration, which you can cancel.

For Angular API calls, remember this pattern:

``` ts
service.getData().subscribe({
  next: data => {
    // Use the response
  },
  error: error => {
    // Handle the error
  }
});
```
