import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String softwareId = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yourdatabase", "user", "password")) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            String sqlUserId = "SELECT id FROM users WHERE username = ?";
            PreparedStatement stmtUser = conn.prepareStatement(sqlUserId);
            stmtUser.setString(1, username);
            ResultSet rsUser = stmtUser.executeQuery();
            if (rsUser.next()) {
                int userId = rsUser.getInt("id");

                String sqlRequest = "INSERT INTO requests (user_id, software_id, access_type, reason) VALUES (?, ?, ?, ?)";
                PreparedStatement stmtRequest = conn.prepareStatement(sqlRequest);
                stmtRequest.setInt(1, userId);
                stmtRequest.setInt(2, Integer.parseInt(softwareId));
                stmtRequest.setString(3, accessType);
                stmtRequest.setString(4, reason);

                stmtRequest.executeUpdate();
                response.sendRedirect("requestAccess.jsp?success=Request+Submitted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("requestAccess.jsp?error=Request+Failed");
        }
    }
}
