<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<% 
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("Manager")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Access Requests</title>
</head>
<body>
    <h2>Pending Access Requests</h2>
    <form action="ApprovalServlet" method="post">
        <table border="1">
            <tr>
                <th>Employee Name</th>
                <th>Software Name</th>
                <th>Access Type</th>
                <th>Reason</th>
                <th>Action</th>
            </tr>
            <!-- This should be populated dynamically with pending requests from the database -->
            <tr>
                <td>Employee 1</td>
                <td>Software 1</td>
                <td>Read</td>
                <td>Need for project X</td>
                <td>
                    <button type="submit" name="requestId" value="1" formaction="ApprovalServlet?action=approve">Approve</button>
                    <button type="submit" name="requestId" value="1" formaction="ApprovalServlet?action=reject">Reject</button>
                </td>
            </tr>
            <!-- Additional rows for other requests -->
        </table>
    </form>
</body>
</html>
