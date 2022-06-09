<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
  <head>
    <sj:head jqueryui="true" jquerytheme="eggplant" />
    <script type="text/javascript">
      $.subscribe("before", function (event, data) {
        alert("Before request ");
      });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Basic Struts 2 Application - Welcome</title>
  </head>
  <body>
    <sj:div
      href="%{ajax}"
      indicator="indicator"
      resizable="true"
      resizableAnimate="true"
      resizableGhost="true"
      resizableHelper="ui-state-highlight"
      resizableAspectRatio="true"
      cssStyle="width: 250px; height: 180px;"
      cssClass="ui-widget-content ui-corner-all"
    >
      <img
        id="indicator"
        src="images/indicator.gif"
        alt="Loading..."
        style="display: none"
      />
    </sj:div>

		<s:url var="ajax" value="/ajax1.action"/>
		<br/>
		<sj:a id="ajaxlink2" href="file-does-not-exist.html" indicator="indicator2" targets="result" onClickTopics="before" cssClass="buttonlink ui-state-default ui-corner-all"><span class="ui-icon ui-icon-refresh"></span>
		  Open Modal
		</sj:a>
		<img id="indicator2" src="images/indicator.gif" alt="Loading..." style="display:none"/>  

    <div>
      <h1>Struts 2 - Connecting to Database</h1>

      <s:form action="login">
        <s:textfield name="username" label="Username" />
        <s:textfield name="password" label="Password" />
        <s:submit value="Login" />
      </s:form>

      <p><a href="register.jsp">Register a user here.</a></p>
      <p><a href="<s:url action='list'/>">Display user list</a></p>
    </div>
  </body>
</html>
