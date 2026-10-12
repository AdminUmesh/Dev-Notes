# Infosys .NET + Angular Interview — Questions and Answers

**Candidate:** Umesh Kumar Singh  
**Source:** Interview transcription provided by the candidate  
**Note:** Answers preserve the meaning of the original transcription. Wording has been lightly formatted for readability; unclear phrases are noted.

---

## Q1. Can you switch on your camera, Umesh?

**Your Answer:**

“Okay, sir. My name is Umesh Kumar Singh. I am from Mara, Bihar, but currently staying in Delhi. As for my educational qualification, I completed B.Tech from Kalgooraj University in 2023. After that, I am working as a software engineer on the .NET MVC and .NET Core stack. For databases, I have worked with MySQL, SQL Server, and Oracle. In frontend technologies, I have used React and Angular.”

## Q2. What kind of role are you currently having, Umesh?

**Your Answer:**

“Sir, Software Developer.”

## Q3. What are your roles and responsibilities?

**Your Answer:**

“Sir, my role is to create new APIs and design APIs, create and design the database, and after that I work on development sometimes.”

## Q4. You are saying you are completely in coding-related activities and will take care of them, right?

**Your Answer:**

“Right, sir.”

## Q5. Have you worked on Azure? Do you have any experience with Azure DevOps?

**Your Answer:**

“Yes, sir. I have experience with Azure. I hosted…”

## Q6. So, are you hosting your application on Azure?

**Your Answer:**

“Right, sir.”

## Q7. Have you created any CI/CD pipelines in your project?

**Your Answer:**

“Yes, sir.”

## Q8. Can you explain the concept of a service principal key?

**Your Answer:**

“Sir, we create services for reusable work. For example, if we integrate SMS services, we create a service…”

**Note:** The interviewer clarified that they were asking about a *service principal* in Azure DevOps. The answer appears to confuse application services with Azure service principals.

## Q9. What kind of service principal key have you configured? Have you created any Azure Functions in your project?

**Your Answer:**

“I created the containers for deploying to Azure. Mainly, I deploy directly by using VS Code.”

**Note:** The question and answer may overlap in the transcription. The interviewer appears to ask about service principals and Azure Functions; the answer discusses containers and deployment.

## Q10. Have you created a web app? What kind of application are you hosting in Azure—is it a web app or an Azure Function?

**Your Answer:**

“Sir, a web app. I created the web app and deployed it using Visual Studio.”

## Q11. If you want to verify your web configuration files after hosting, where can you verify them in Azure? Which property would you use to see your configuration?

**Your Answer:**

“Sir, I don’t remember this.”

## Q12. You have created storage containers, right?

**Your Answer:**

“Yes, sir.”

**Additional transcript fragment:** The transcription includes “after posting your application” followed by “Sir, don’t remember this,” before the interviewer asks about storage containers. The exact question associated with this fragment is unclear.

## Q13. How are you pushing/deploying those containers?

**Your Answer:**

“Sir, actually, we created containers for my APIs and named them, and after that I deployed on this. Like, sir, I am working on Azure…”

**Note:** The original transcription is unclear at this point and includes “Atlas” and “5 to 10 times.” The exact wording could not be reliably reconstructed.

## Q14. How are you handling exceptions in MVC?

**Your Answer:**

“Using try-catch, and for global exceptions we create custom middleware. For example, if we have to handle a SQL Server exception, we create SQL Server exception middleware and integrate it in our `Program.cs` file.”

## Q15. Can you explain routing?

**Your Answer:**

“Sir, basically, in an MVC project, routing by default uses the controller and action method.”

**Note:** “XR method” in the transcription is interpreted as “action method.”

## Q16. What kind of route is that?

**Your Answer:**

“Sir, this is default routing.”

## Q17. Where do you configure default routing?

**Your Answer:**

“Sir, in `Program.cs`.”

## Q18. Have you implemented any custom routes?

**Your Answer:**

“Not now. I authorize the route and also use roles in routing.”

**Note:** The answer is unclear. It may refer to authorization and role-based access rather than custom routing.

## Q19. Can you explain bundling and minification?

**Your Answer:**

“I don’t know this.”

## Q20. Can you explain the concept of a partial class?

**Your Answer:**

“Yes, sir. In C#, a partial class is a class in which we have created some reusable work that we can call in any component.”

## Q21. What is the use of a partial class?

**Your Answer:**

“Sir, mainly when we integrate headers and footers and reusable work that appears on every page.”

**Note:** This answer describes a reusable UI component or partial view more closely than a C# partial class.

## Q22. Explain partial view and `RenderPartial`.

**Your Answer:**

“Sir, a partial view is used when we need to reuse a view, and we integrate it into our layout for reusability.”

## Q23. Have you implemented dependency injection in your project?

**Your Answer:**

“Yes, sir.”

## Q24. Can you explain dependency injection and how you implemented it?

**Your Answer:**

“Okay, sir. First, we create interfaces. I use constructor injection for dependency injection. First, we register it in `Program.cs`. We create an interface and then resolve it. We register the implementation in `Program.cs`, so if someone calls this interface, it uses the registered class. When we use it in other classes, we inject it through the constructor.”

**Note:** This answer has been lightly organized from the longer, hesitant original response without changing its main meaning.

## Q25. Have you used SOLID principles in your project?

**Your Answer:**

“Yes, sir.”

## Q26. How many types of views are there in an MVC application?

**Your Answer:**

“Views? Okay. In an MVC application, we have a partial view and a view component, which is our main page and layout page.”

**Note:** The interviewer clarified that this was not the expected answer.

## Q27. How many types of views?

**Your Answer:**

“Return View, return Partial View?”

## Q28. How many types of view results can you return?

**Your Answer:**

“Sir, `return View`, `return PartialView`, and we can also redirect to another view.”

## Q29. How many types of action results are there in MVC?

**Your Answer:**

“Sir, action results include returning JSON, returning a view, and we can also return DTO types.”

## Q30. Have you worked on `JsonResult`?

**Your Answer:**

“Yes, sir.”

## Q31. How can you call your Web API from an AJAX call? Have you implemented AJAX in your application?

**Your Answer:**

“Yes, sir. I use AJAX to make an HTTP call, and also `HttpClient`.”

## Q32. How do you make an AJAX call?

**Your Answer:**

“In AJAX, we define the URL and specify the type of data we want to get, such as JSON or a view. We define the URL and callback functions such as success, error, or complete. We can also define the method, such as GET or POST, and the data type.”

## Q33. What is the difference between GET and POST in a Web API call?

**Your Answer:**

“GET is used to retrieve data, and POST is used to post data. But we can do both using POST or GET in some cases. When we use GET, data is passed through the URL and is visible.”

## Q34. How can you restrict Web API calls from Postman or Swagger?

**Your Answer:**

“Sir, using CORS policy.”

## Q35. Have you implemented JWT tokens in your application?

**Your Answer:**

“Yes, sir. I have implemented JWT token authentication and cookie authentication, both.”

## Q36. How does JWT work, and where do you store the token?

**Your Answer:**

“First, we install the JWT NuGet package. After login, we create a token and send it with all our requests. We store the token in browser cookies.”

## Q37. What are cookies? How many types of cookies are there?

**Your Answer:**

“There is one secure cookie that cannot be accessed by our browser, and one public cookie that can be accessed by both the browser and server.”

## Q38. Do you have any questions for us?

**Your Answer:**

“No, sir. Not now.”

---

## End of Interview

**Interviewer:** “Okay, that’s it, Umesh. Do you have any questions from your end?”  
**Candidate:** “No, sir, not now.”
