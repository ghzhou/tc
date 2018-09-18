package com.zhoujie.tc;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TC extends HttpServlet
{
    String proofOfLife = null;
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
    	super.init(config);
        Object o = config.getServletContext().getAttribute("org.mortbay.ijetty.contentResolver");
        android.content.ContentResolver resolver = (android.content.ContentResolver)o;
        android.content.Context androidContext = (android.content.Context)config.getServletContext().getAttribute("org.mortbay.ijetty.context");
        proofOfLife = androidContext.getApplicationInfo().packageName;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestPath = request.getPathInfo();
        if (requestPath.contains("unlockDoor")) {
            unlockDoor();
            return;
        } else if (requestPath.contains("openElevator")) {
            openElevator();
            return;
        } else if(requestPath.contains("wakeUp")) {
            WakeupOverLan.sendWol("192.168.1.2", "68:B5:99:F2:18:35");
            return;
        }
        WakeupOverLan.sendWol("192.168.1.2", "68:B5:99:F2:18:35");
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<html>");
        out.println("<h1>Hello From Servlet Land!</h1>");
        out.println("Brought to you by: " + proofOfLife);
        out.println("</html>");
        out.flush();
    }

    private void openElevator() {
    }

    private void unlockDoor() {
    }
}
