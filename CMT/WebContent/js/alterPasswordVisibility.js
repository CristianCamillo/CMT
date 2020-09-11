function alterPasswordVisibility(password, eye)
{
	if(password.type === "password")
	{
		password.type = "text";
		eye.src = "svg/eye.svg";
	}
	else
	{
		password.type = "password";
		eye.src = "svg/eyeSlash.svg";
	}
}