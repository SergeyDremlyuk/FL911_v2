<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28.10.2017
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script
            src="https://code.jquery.com/jquery-1.10.2.min.js"
            integrity="sha256-C6CB9UYIS9UJeqinPHWTHVqh/E1uhG5Twh+Y5qFQmYg="
            crossorigin="anonymous"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,.form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],.form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }

        #chatroom {
            font-size: 16px;
            height: 40px;
            line-height: 40px;
            width: 300px;
        }

        .received {
            width: 160px;
            font-size: 10px;
        }
    </style>

    <script>
        var wsocket;
        var serviceLocation = "ws://localhost:8080/web/chat/";
        var $nickName;
        var $message;
        var $chatWindow;
        var room = '';

        function onMessageReceived(evt) {
           var msg = JSON.parse(evt.data); // native API
            var $messageLine = $('<tr><td class="received">' + msg.received
                + '</td><td class="user label label-info">' + msg.sender
                + '</td><td class="message badge">' + msg.message
                + '</td></tr>');
            $chatWindow.append($messageLine);
        }
        function sendMessage() {

            var msg = '{"message":"' + $message.val() + '", "sender":"'
                + $nickName.val() + '", "received":""}';

           wsocket.send(msg);
            $message.val('').focus();
        }

        function connectToChatserver() {
            room = $('#chatroom option:selected').val();
            wsocket = new WebSocket(serviceLocation + room);

           wsocket.onmessage = onMessageReceived;
//            wsocket.onmessage = function (event) {
//                debugger;
//            }
        }

        function leaveRoom() {
            wsocket.close();
            $chatWindow.empty();
            $('.chat-wrapper').hide();
            $('.chat-signin').show();
            $nickName.focus();
        }

        $(document).ready(function() {
            $nickName = $('#nickname');
            $message = $('#message');
            $chatWindow = $('#response');
            $('.chat-wrapper').hide();
            $nickName.focus();

            $('#enterRoom').click(function(evt) {
                evt.preventDefault();
                connectToChatserver();
                $('.chat-wrapper h2').text('Chat # '+$nickName.val() + "@" + room);
                $('.chat-signin').hide();
                $('.chat-wrapper').show();
                $message.focus();
            });
            $('#do-chat').submit(function(evt) {
                evt.preventDefault();
                sendMessage()
            });

            $('#leave-room').click(function(){
                leaveRoom();
            });
        });
    </script>

</head>
<body>
<div class="container chat-signin">
    <form class="form-signin">
        <h2 class="form-signin-heading">Chat sign in</h2>
        <label for="nickname">Nickname</label> <input type="text"
                                                      class="input-block-level" placeholder="Nickname" id="nickname">
        <div class="btn-group">
            <label for="chatroom">Chatroom</label> <select size="1" id="chatroom">
            <option>arduino</option>
            <option>java</option>
            <option>groovy</option>
            <option>scala</option>
        </select>
        </div>
        <button class="btn btn-large btn-primary" type="submit"
                id="enterRoom">Sign in</button>
    </form>
</div>
<!-- /container -->

<div class="container chat-wrapper">
    <form id="do-chat">
        <h2 class="alert alert-success"></h2>
        <table id="response" class="table table-bordered"></table>
        <fieldset>
            <legend>Enter your message..</legend>
            <div class="controls">
                <input type="text" class="input-block-level" placeholder="Your message..." id="message" style="height:60px"/>
                <input type="submit" class="btn btn-large btn-block btn-primary"
                       value="Send message" />
                <button class="btn btn-large btn-block" type="button" id="leave-room">Leave
                    room</button>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
