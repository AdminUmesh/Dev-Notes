```c#
using Microsoft.AspNetCore.Mvc; //For Controller
using Microsoft.AspNetCore.Authorization; //For [AllowAnonymous]

public class HomeController : Controller
{
    [AllowAnonymous]
    public List<string> clientid_tokn()
    {
        List<string> client1 = new List<string>();
        if (Request.Cookies.ContainsKey("ClientId"))
        {
            var cookie = Request.Cookies["ClientId"];
            client1.Add(cookie);
        }
        return client1;
    }
}

// Without : Controller we can't access Request.Cookies
```