springsecuritydemo


These are the things to take into account, once you want to test the jwt out.


* localhost:8080/user  (This creates the user)
* localhost:8080/authenticate (With the credentials created above, you generate the jwt token)
* localhost:8080/hello (This one tests an endpoint using the jwt, in the authorization header)

Json to create and authenticate user:

```
{
	"username": "test",
	"password": "123"
}
```

Then call [localhost:8080/hello] using the Header 'Authorization': Bearer [Jwt Token]