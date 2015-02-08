<html>
<head>
<title>Welcome!</title>
</head>
<body>
	<p>We have these animals:</p>
	<table border=1>
		<#list animalList as animal>
			<tr>
				<td>${animal.name}</td>
				<td>${animal.price}</td>
			</tr>
		</#list>
	</table>
</body>
</html>