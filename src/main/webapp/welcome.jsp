<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome page...</title>
  </head>
    <h2>Welcome!</h2>
    <p><s:property value="selectedAccount.firstName" /></p>
    <p><s:property value="selectedAccount.lastName" /></p>
    <p><s:property value="selectedAccount.age" /></p>
  </body>
</html>