import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("requestId");
        String action = request.getParameter("action");

        String newStatus = action.equals("approve") ? "Approved" : "Rejected";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yourdatabase", "user", "password")) {
            String sql = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newStatus);
            stmt.setInt(2, Integer.parseInt(requestId));

            stmt.executeUpdate();
            response.sendRedirect("pendingRequests.jsp?success=Request+" + newStatus);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("pendingRequests.jsp?error=Approval+Failed");
        }
    }
}
