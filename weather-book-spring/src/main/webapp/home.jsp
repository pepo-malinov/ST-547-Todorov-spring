<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="uni.fmi.masters.beans.CommentBean" %>
<%@ page import="uni.fmi.masters.beans.UserBean" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Weather App</title>

    <meta name="apple-mobile-web-app-capable" content="yes">

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

	<link href="assets/css/main.css" rel="stylesheet">
		
	<script src="https://code.jquery.com/jquery-3.4.1.js" > </script>

    <link href="assets/css/font-awesome.min.css" rel="stylesheet">



</head>
<body>

<div class="home-page">

<% List<CommentBean> comments = 
	((List<CommentBean>) request.getAttribute("comments")); %>
	
	
	<% for(int i = 0; i < 10; i++) { %>
	
	<div>
		<h1> <% comments.get(i).getComment(); %></h1>
	</div>
	
	<% } %>

        <header>
            <div class="container">
                <nav class="navbar navbar-default">

                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <div class="current-weather">
                                <div class="img-container">
                                    <img src="assets/img/weather_icon.png" style="display:none;"/>
                                </div>
                                <div class="info">
                                    <div class="city">Пловдив</div>
                                    <div id="current-condition"></div>
                                    <div class="pull-right"><span id="current-temperature"></span> ℃</div>
                                </div>
                            </div>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li class="active">
                                    <a href="home.html">
                                        <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                                        <span class="nav-label">Начало</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="friends.html">
                                        <i class="fa fa-users" aria-hidden="true"></i>
                                        <span class="nav-label">Приятели</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="inbox.html">
                                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                        <span class="nav-label">Известия</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="profile.html">
                                        <i class="fa fa-cogs" aria-hidden="true"></i>
                                        <span class="nav-label">Профил</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="index.html">
                                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                        <span class="nav-label">Изход</span>
                                    </a>
                                </li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                </nav>
            </div>
        </header>


        <div class="container">


            <div class="row" style="margin-top:30px;">

                <div class="col-sm-4">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Отбележи място и напиши коментар</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <img class="thumbnail img-responsive" src="assets/img/icons/clear_sky.png">
                                    </div>
                                    <div class="col-sm-8">
                                        <div>
                                            <span class="label label-success"><span class="current-temp">27</span> ℃</span>
                                        </div>
                                        <label for="select-city">Избери град:</label>
                                        <select id="select-city" class="form-control">
                                            <option value="0" selected disabled>Изберете град</option>
                                            <option value="sofia">София</option>
                                            <option value="plovdiv">Пловдив</option>
                                            <option value="varna">Варна</option>
                                            <option value="burgas">Бургас</option>
                                            <option value="stara zagora">Стара Загора</option>
                                            <option value="ruse">Русе</option>
                                            <option value="pleven">Плевен</option>
                                            <option value="dobrich">Добрич</option>
                                            <option value="sliven">Сливен</option>
                                            <option value="shumen">Шумен</option>
                                            <option value="pernik">Перник</option>
                                            <option value="pazardvik">Пазарджик</option>
                                            <option value="qmbol">Ямбол</option>
                                            <option value="haskovo">Хасково</option>
                                            <option value="blagoevgrad">Благоевград</option>
                                            <option value="veliko tarnovo">Велико Търново</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                   <div class="col-sm-12">
                                       <label for="comment">Коментар:</label>
                                       <textarea class="form-control" rows="3" id="comment"></textarea>
                                   </div>
                                </div>

                            </div>
                        </div>
                        <div class="panel-footer" style="height:55px;">
                            <button type="button" id="postComment" class="btn btn-primary pull-right publish"><span class="glyphicon glyphicon-globe"></span> Публикувай</button>
                        </div>
                    </div>
                </div>
                <div class="col-sm-8">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Твоите отбелязвания</h3>
                        </div>

                        <ul class="list-group" style="min-height:241px;" id="contentList">

                            <li class="list-group-item" style="display:none" id="cloneMe">
                                <div class="row">
                                    <div class="col-sm-2 col-xs-3">
                                        <img src="assets/img/icons/clear_sky.png" class="thumbnail img-responsive">
                                    </div>
                                    <div class="col-sm-7 col-xs-6">
                                        <span class="label label-success"><span class="current-temp">27</span> ℃</span>
                                        <h4>Пловдив</h4>
                                        <p>Времето беше супер. Разхождах се по главната. Купих си сладолед и пих вода от чешмичката на джумаята. </p>
                                    </div>
                                    <div class="col-sm-3 col-xs-3">
                                        <button type="button" class="btn btn-danger pull-right remove-post"><span class="glyphicon glyphicon-remove"></span><span class="hidden-xs"> Изтрий</span></button>
                                    </div>
                                </div>
                            </li>
                            
                        </ul>
                    </div>

                </div>
            </div>


        </div>

</div>

<% UserBean user = ((UserBean)request.getAttribute("user")); %>

<script>

var userID = <%= user.getId() %>;

function saveToDB(city, temp, comment, user){
	
	$.ajax({
		method: "GET",
		url: "http://localhost:9080/WeatherBook/HelloWorldServlet" +
			"?router=insertComment&city=" + city 
			+ "&temp=" + temp
			+ "&comment=" + comment
			+ "&user=" + user,
		success: function(resp){			
		
			//TODO: Преместете логиката за визуализация на коментара тук!!!
	
				
				
			},
		error: function(){
			alert("Something went TEREBLY WRONG!!!! \nYOU BROKE IT!");
		}
			
		
	})
}

$(document).ready(function (){
	
	var apiid = "891be3d8ea6c5763c5d8e6ad1267c77b";
	
	$('#postComment').click(function (){
		
		cityName = $('#select-city').val();
		
		$.ajax({
			method: "GET",
			url: "http://api.openweathermap.org/data/2.5/weather?q=" 
				+ cityName + 
				"&appid=" + apiid + "&units=metric",
			success: function(resp){				
			
				var miniMe = $('#cloneMe').clone();
				
				miniMe.attr('id', '');
				miniMe.find('h4').text(cityName);
				miniMe.find('p').html($('#comment').val());
				miniMe.find('.current-temp').html(resp.main.temp);
				miniMe.find('button').click(function (){
					miniMe.remove();
					
				});
				
				miniMe.show();
				
				$('#contentList').append(miniMe);
				
				$('#comment').val("");				
					
				},
			error: function(){
				alert("Something went TEREBLY WRONG!!!! \nYOU BROKE IT!");
			}
				
			
		})
		
	});
	
});

</script>

</body>
</html>
