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
    <meta name="google-signin-client_id"
          content="407580017200-0hu9t079pn3476scpk39n3gs9m29q59f.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <title>Title</title>
</head>
<body>
<%--    <div class="g-signin2" data-onsuccess="onSignIn"></div>--%>
<script>
    var xhr = new XMLHttpRequest();

    function onSignIn(googleUser) {
        // var id_token;
        // console.log('token first :'+id_token)
        var profile = googleUser.getBasicProfile();
        id_token = googleUser.getAuthResponse().id_token;
        console.log('token second :' + id_token)
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
        if (id_token !== undefined) {
            // var xhr = new XMLHttpRequest();
            if (xhr.readyState == 4 || xhr.readyState == 0) {
                // xhr.open("POST", "https://localhost:8443/maro/MainServlet", true);
             //   xhr.open("POST", "https://ec2-13-49-244-94.eu-north-1.compute.amazonaws.com:8443/maro/MainServlet", true);
               xhr.open("POST", "https://hadi.shghgh.ir:8443/maro/MainServlet", true);
                xhr.setRequestHeader('Content-Type', 'text/plain');
                xhr.onreadystatechange = callBackFunction;
                xhr.send(id_token);
            }
        } else console.log("id token is undefine");

        function callBackFunction() {
            if (xhr.readyState == 4) {
                var s = xhr.responseText;
                console.log('the responce is'+s)
                bank.balance.value=s;
               window.location.replace("https://hadi.shghgh.ir:8443/maro/Dispatcher");
         //        window.location.replace("https://localhost:8443/maro/Dispatcher");

            }
        }
    }


</script>
<%--<a href="#" onclick="signOut();">Sign out</a>--%>
<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }
</script>

<form name="bank">
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
    <a href="#" onclick="signOut();">Sign out</a>
<%--     <input type="text" name="balance">--%>
    <output name="balance"> </output>
</form>
</body>
</html>
