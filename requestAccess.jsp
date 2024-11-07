<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<% 
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("Employee")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Request Access</title>
</head>
<body>
    <h2>Request Software Access</h2>
    <form action="RequestServlet" method="post">
        <label for="softwareId">Software:</label>
        <select id="softwareId" name="softwareId" required>
            <!-- Populate this list dynamically from the database -->
            <option value="1">Software 1</option>
            <option value="2">Software 2</option>
        </select><br><br>
        
        <label for="accessType">Access Type:</label>
        <select id="accessType" name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br><br>
        
        <label for="reason">Reason for Request:</label>
        <textarea id="reason" name="reason" required></textarea><br><br>
        
        <button type="submit">Submit Request</button>
    </form>
</body>
</html>
