<%--
  Created by IntelliJ IDEA.
  User: hvr
  Date: 8/29/20
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="google-signin-client_id" content="407580017200-0hu9t079pn3476scpk39n3gs9m29q59f.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <title>Title</title>
</head>
<body>
<div class="g-signin2" data-onsuccess="onSignIn"></div>

hhhhh
<script>
    function onSignIn(googleUser) {
        // var id_token;
        // console.log('token first :'+id_token)
        var profile = googleUser.getBasicProfile();
        id_token = googleUser.getAuthResponse().id_token;
        console.log('token second :'+id_token)
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
        if (id_token !== undefined) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "http://localhost:8080/maro/MainServlet", true);
            xhr.setRequestHeader('Content-Type', 'text/plain');
            xhr.send(id_token);
        }else console.log("id token is undefine");
    }
</script>
<a href="#" onclick="signOut();">Sign out</a>
<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }
</script>
</body>
</html>
